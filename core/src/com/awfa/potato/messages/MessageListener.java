package com.awfa.potato.messages;

public interface MessageListener {
	public void recieveMessage(MessageSystem.Message message);
	public void recieveMessage(MessageSystem.Message message, MessageExtra extra);
}
