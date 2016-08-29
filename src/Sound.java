import sun.audio.*;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {
	InputStream in,inShotgun;
	
	Clip clip;
	//ContinuousAudioStream loop = null;
	//AudioData musicData;
	public Sound(String file){
		try{
			//AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(file));
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			//in = new FileInputStream("credits.wav");
			//as = new AudioStream(in);
			//inShotgun = new FileInputStream("shotgun.wav");
			//as5 = new AudioStream(inShotgun);
			//audioData = as.getData();
			//loop = new ContinuousAudioDataStream(audioData);
			
		
			//musicData = as.getData();
			//loop = new ContinuousAudioStream(as);
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	public void play(){
		if (!clip.isRunning()){
			clip.setFramePosition(0);
			clip.start();
		}
	}
	public void stop(){
		if (clip.isRunning()){
			clip.stop();
		}
	}
	public void loop(){
		if (!clip.isRunning()){
			clip.setFramePosition(0);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	
}
