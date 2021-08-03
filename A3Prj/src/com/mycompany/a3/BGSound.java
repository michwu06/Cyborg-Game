package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable {
	private Media media;
	
	public BGSound(String file) {
		if (Display.getInstance().getCurrent() == null) {
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
		}
		
		try {
			InputStream input = Display.getInstance().getResourceAsStream(getClass(), "/"+file);
			media = MediaManager.createMedia(input, "audio/mp3", this);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		media.setTime(0);
		media.play();
	}
	
	public void pause() {
		media.pause();
	}
	
	public void play() {
		media.play();
	}
	
	public void setVol(int v) {
		media.setVolume(v);
	}
}
