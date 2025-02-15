package com.example.demo.client;

import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String username = JOptionPane.showInputDialog(null,
						"Enter Username (Max: 26 Characters): ",
						"Chat Application",
						JOptionPane.QUESTION_MESSAGE);
				
				if(username == null || username.isEmpty() || username.length() > 16) {
					JOptionPane.showMessageDialog(null,
							"Invalid Username",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				CleintGUI clientGUI;
				try {
					clientGUI = new CleintGUI(username);
					clientGUI.setVisible(true);
					System.out.println("Hey");
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});

	}

}
