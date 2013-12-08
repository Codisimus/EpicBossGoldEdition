package me.ThaH3lper.com.SkillsCollection.EffectsCollection;

import org.bukkit.Location;
import org.bukkit.Sound;

public class EffectSound {

	//- effect boss sound sound.sound:volume:pitch

	public static void PlaySound(Location location, String ed)	{

		String[] data = ed.split(":");

		Sound sound = Sound.valueOf(data[0].toUpperCase());
		float volume = 	(data.length > 1) 	? Float.parseFloat(data[1]) : 1;
		float pitch  =	(data.length > 2) 	? Float.parseFloat(data[2]) : 1;


		location.getWorld().playSound(location, sound, volume, pitch);
	}
}
