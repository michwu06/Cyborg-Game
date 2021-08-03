package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form{
	private GameWorld gw;
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play() {
		//code to accept user commands
		
		Label myLabel = new Label("Enter a Command: ");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if(sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
						case 'x':
							System.out.println("Do you want to exit? (y/n)");
							break;
						case 'a':
							gw.accelerate();
							break;
						case 'b':
							gw.brake();
							break;
						case 'l':
							gw.steerLeft(5);
							break;
						case 'r':
							gw.steerRight(5);
							break;
						case 'c':
							gw.cyborgColli();
							break;
						case '1':
							gw.baseColli(1);
							break;
						case '2':
							gw.baseColli(2);
							break;
						case '3':
							gw.baseColli(3);
							break;
						case '4':
							gw.baseColli(4);
							break;
						case 'e':
							gw.energyColli();
							break;
						case 'g':
							gw.droneColli();
							break;
						case 't':
							gw.tick();
							break;
						case 'd':
							gw.display();
							break;
						case 'm':
							gw.map();
							break;
						case 'y':
							gw.exit();
							break;
						case 'n':
							break;
					}//switch
			}//actionPerformed
		});//actionListener
	
	}//play
}

