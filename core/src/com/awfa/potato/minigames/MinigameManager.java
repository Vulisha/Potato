package com.awfa.potato.minigames;

import com.awfa.potato.components.PlayerComponent;
import com.awfa.potato.exceptions.MissingComponentException;
import com.awfa.potato.messages.MessageExtra;
import com.awfa.potato.messages.MessageListener;
import com.awfa.potato.messages.MessageSystem;
import com.awfa.potato.messages.MessageSystem.Message;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class MinigameManager implements MessageListener {
	public enum State {
		OUT_MINIGAME, IN_MINIGAME_WINDUP, IN_MINIGAME;
	}
	
	public static final float REST_TIME = 3.f;
	
	private State state; 
	private Minigame minigame;
	private MessageSystem messageSystem;
	private float minigameTimer;
	
	private ComponentMapper<PlayerComponent> playerComponentMapper =
			ComponentMapper.getFor(PlayerComponent.class);
	
	public MinigameManager(MessageSystem messageSystem) {
		this.messageSystem = messageSystem;
		minigameTimer = 0.f;
		state = State.OUT_MINIGAME;
	}
	
	public void update(float delta) {
		if (state == State.OUT_MINIGAME) {
			if (minigameTimer > REST_TIME) {
				// TODO: set minigame
				state = State.IN_MINIGAME_WINDUP;
				minigame.initialize();
				minigameTimer = 0.f;
				messageSystem.sendMessage(Message.MINIGAME_START);
			}
		} else if (state == State.IN_MINIGAME_WINDUP) {
			if (minigameTimer > minigame.getWindup()) {
				minigame.startAction();
				state = State.IN_MINIGAME;
				minigameTimer = 0.f;
			}
		} else if (state == State.IN_MINIGAME) {
			if (minigameTimer > minigame.getLength()) {
				minigame.endAction();
				state = State.OUT_MINIGAME;
				minigameTimer = 0.f;
				messageSystem.sendMessage(Message.MINIGAME_OVER);
			} else {
				minigame.update();
			}
		}
		
		minigameTimer += delta;
	}
	
	// Methods for minigames to use
	
	public void instruct(String message) {
		System.out.println(message);
	}
	
	public void awardWin(Entity playerEntity) {
		checkIsPlayer(playerEntity);
		PlayerComponent playerComponent = playerComponentMapper.get(playerEntity);
		playerComponent.score++;
		
		MessageExtra extra = new MessageExtra();
		extra.entity = playerEntity;
		messageSystem.sendMessage(Message.PLAYER_WIN, extra);
	}
	
	public void awardLose(Entity playerEntity) {
		checkIsPlayer(playerEntity);
		
		MessageExtra extra = new MessageExtra();
		extra.entity = playerEntity;
		messageSystem.sendMessage(Message.PLAYER_LOSE, extra);
	}
	
	public void awardWinToUnprocessed() {
		// TODO award win to unprocessed players
	}
	
	public void awardLoseToUnprocessed() {
		// TODO award lose to unprocessed players
	}
	
	@Override
	public void recieveMessage(Message message) {
		minigame.recieveMessage(message);
	}

	@Override
	public void recieveMessage(Message message, MessageExtra extra) {
		minigame.recieveMessage(message, extra);
	}
	
	private void checkIsPlayer(Entity playerEntity) {
		if(!playerComponentMapper.has(playerEntity)) {
			throw new MissingComponentException(playerEntity.getId() + " is missing the player component");
		}
	}
}
