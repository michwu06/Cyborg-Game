package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	private Media media;
	
	public Sound(String file) {
		if (Display.getInstance().getCurrent() == null) {
			System.out.println("Error: Create sound objects after calling show()!");
			System.exit(0);
		}
		
		try {
			InputStream input = Display.getInstance().getResourceAsStream(getClass(), "/" + file);
			media = MediaManager.createMedia(input, "audio/wav");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		media.setTime(0);
		media.play();
	}
	
	public void setVol(int v) {
		media.setVolume(v);
	}
}
