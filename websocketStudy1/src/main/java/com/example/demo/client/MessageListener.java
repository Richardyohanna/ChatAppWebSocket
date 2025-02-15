package com.example.demo.client;

import java.util.ArrayList;

import com.example.demo.Message;

public interface MessageListener {
	public void onMessageRecieve(Message message);
	public void onActiveUsersUpdated(ArrayList<String> users);
}
