package com.example.demo.client;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import com.example.demo.Message;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {
	
	private String username;
	private MessageListener messageListener;
	
	public MyStompSessionHandler(MessageListener messageListener, String username) {
		this.username = username;
		this.messageListener = messageListener;
	}
	
	@Override 
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		System.out.println("Client Connected!!");
		
		
		session.subscribe("/topic/messages", new StompFrameHandler(){

			@Override
			public Type getPayloadType(StompHeaders headers) {
				// TODO Auto-generated method stub
				return Message.class;
			}

			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				// TODO Auto-generated method stub
				try {
					if(payload instanceof Message) {
						Message message = (Message) payload;
						messageListener.onMessageRecieve(message);
						System.out.println("Received message: " + message.getUser() + ": " + message.getMessage());
						
					} else {
						System.out.println("Recieved unexpected payload type: " + payload.getClass());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		
		System.out.println("Client Subscribed /topics!!");
		
		session.subscribe("/topic/users", new StompFrameHandler() {

			@Override
			public Type getPayloadType(StompHeaders headers) {
				// TODO Auto-generated method stub
				return new ArrayList<String>().getClass();
			}

			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				// TODO Auto-generated method stub
				try {
					if(payload instanceof ArrayList) {
						ArrayList<String> activeUsers = (ArrayList<String>) payload;
						messageListener.onActiveUsersUpdated(activeUsers);
						System.out.println("Received active users: " + activeUsers);
						
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		System.out.println("Subscribed to /topic/users");
		
		session.send("/app/connect", username);
		session.send("/app/request-users", "");
	}
	
	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		exception.printStackTrace();
	}
	
	

}
