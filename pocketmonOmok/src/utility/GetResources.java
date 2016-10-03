package utility;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class GetResources {
	public static ImageIcon getImageIcon(String imageDir, int sizeX, int sizeY) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(
				ImageIO.read(new File(imageDir)).getScaledInstance(
					sizeX, sizeY, Image.SCALE_AREA_AVERAGING)
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return icon;
	}
	
	public static void soundPlay(String soundDir) {
		try {
			AudioInputStream gameStartSound = AudioSystem.getAudioInputStream(new File(soundDir));
			Clip clip = AudioSystem.getClip();
			
			clip.stop();
			clip.open(gameStartSound);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}