package me.ThaH3lper.com.Listener;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Location.LocationHandler;
import me.ThaH3lper.com.Timer.EpicTimer;
import me.ThaH3lper.com.Timer.Timer;
import me.ThaH3lper.com.Timer.TimerHandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;

public class SignPlace implements Listener{

	@EventHandler(priority = EventPriority.HIGH)
	public void SignChange(SignChangeEvent e)
	{
		if(e.getLine(0).equalsIgnoreCase("epictimer"))
		{
			EpicTimer et = TimerHandler.getEpicTimer(e.getLine(1));
			EpicLocation el = LocationHandler.getEpicLocation(e.getLine(2));
			if(et != null && el != null)
			{
				if(e.getPlayer().hasPermission("epicboss.admin") || e.getPlayer().hasPermission("epicboss.timer"))
				{
					EpicBoss.plugin.allTimers.add(new Timer(et, el));
					e.getPlayer().sendMessage(ChatColor.GREEN + "Timer Created!");
                                        e.getBlock().setType(Material.AIR);
				}
			}
		}
	}
}
