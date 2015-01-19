package com.awfa.potato.minigames;

import com.awfa.potato.messages.MessageListener;

public abstract class Minigame implements MessageListener {
	protected MinigameManager minigameManager;
	
	public Minigame(MinigameManager minigameManager) {
		this.minigameManager = minigameManager;
	}
	
	public abstract void initialize();
	public abstract void startAction();
	public void update() { }
	public abstract void endAction();
	
	public abstract float getWindup();
	public abstract float getLength();
}
