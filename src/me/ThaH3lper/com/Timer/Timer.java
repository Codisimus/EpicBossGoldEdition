package me.ThaH3lper.com.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Mobs.MobHandler;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;

public class Timer {

	public EpicTimer et;
	public List<UUID> mobs = new ArrayList<UUID>();
	public EpicLocation el;

	int clock;

	public Timer(EpicTimer et, EpicLocation el)
	{
		this.et = et;
		this.el = el;
		this.clock = et.interval;
	}
	public boolean tick(int sec)
	{
		if(mobs.size() >= et.amount)
			return false;

		clock -= sec;

		if(clock <= 0)
		{
			clock = et.interval;
			if(mobs.size() < et.amount)
			{
				el.LoadChunk();
				mobs.add(MobHandler.SpawnMob(et.bosses.get(EpicBoss.r.nextInt(et.bosses.size())).cmdName, el.location).getUniqueId());
				return true;
			}
		}
		return false;
	}
	public void WalkCheck()
	{
		if(et.walk == 0)
			return;
		for(LivingEntity l : el.location.getWorld().getLivingEntities())
		{
			if(mobs.contains(l.getUniqueId()))
			{
				if(el.location.distanceSquared(l.getLocation()) >= Math.pow(et.walk, 2))
					l.teleport(el.location);
			}
		}

	}
}
