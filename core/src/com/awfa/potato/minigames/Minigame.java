package com.awfa.potato.minigames;

import com.awfa.potato.messages.MessageListener;

public interface Minigame extends MessageListener {
	public void initialize();
	public void startAction();
	public void endAction();
	
	public float getWindup();
	public float getLength();
}
