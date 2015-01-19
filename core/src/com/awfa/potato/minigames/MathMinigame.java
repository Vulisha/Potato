package com.awfa.potato.minigames;

import java.util.Random;

import com.awfa.potato.messages.MessageExtra;
import com.awfa.potato.messages.MessageSystem;
import com.awfa.potato.messages.MessageSystem.Message;

public class MathMinigame extends Minigame {
	private int addendA;
	private int addendB;
	private int sum;
	private MinigameManager minigameManager;
	
	public static final int UPPER_BOUND = 100;
	public static final int LOWER_BOUND = 10;
	public static final float WINDUP_TIME = 2.f;
	public static final float LENGTH = 6.f;

	public MathMinigame(MinigameManager minigameManager) {
		super(minigameManager);
	}
	
	@Override
	public void recieveMessage(Message message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void recieveMessage(Message message, MessageExtra extra) {
		if (message == MessageSystem.Message.PLAYER_CHAT) {
			if (Integer.valueOf(extra.message) == sum) {
				minigameManager.awardWin(extra.entity);
			}
		}
	}

	@Override
	public void initialize() {
		Random randomGenerator = new Random();
		addendA = randomGenerator.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
		addendB = randomGenerator.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
		sum = addendA + addendB;
		
		minigameManager.instruct("Get ready to type");
	}

	@Override
	public void startAction() {
		minigameManager.instruct("Solve: " + addendA + " + " + addendB);
	}

	@Override
	public void endAction() {
		minigameManager.instruct("The solution was: " + sum);
		minigameManager.awardLoseToUnprocessed();
	}

	@Override
	public float getWindup() {
		return WINDUP_TIME;
	}

	@Override
	public float getLength() {
		return LENGTH;
	}
}
