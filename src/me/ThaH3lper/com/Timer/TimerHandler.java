package me.ThaH3lper.com.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;

import me.ThaH3lper.com.EpicBoss;
import me.ThaH3lper.com.Location.EpicLocation;
import me.ThaH3lper.com.Location.LocationHandler;
import me.ThaH3lper.com.Mobs.EpicMobs;

public class TimerHandler {

	public static List<EpicMobs> getMobs(String s)
	{
		List<EpicMobs> list = new ArrayList<EpicMobs>();
		for(EpicMobs em : EpicBoss.plugin.listMobs)
		{
			if(em.cmdName.contains(s))
				list.add(em);
		}
		return list;
	}

	public static EpicTimer getEpicTimer(String s)
	{
		for(EpicTimer et : EpicBoss.plugin.listTimers)
		{
			if(s.equals(et.cmdName))
				return et;
		}
		return null;
	}

	public static void SaveAllTimers()
	{
		List<String> save = new ArrayList<String>();
		for(Timer t : EpicBoss.plugin.allTimers)
		{
                        //EpicTimer
                        String epicTimer = t.et.cmdName;
                        //EpicLocation
                        String epicLocation = t.el.name;
			//Time
			String time = t.clock + "";
			//Mobs
			String mobs = "";
			for(UUID c : t.mobs)
				mobs += c + ",";
			if(mobs.equals(""))
				mobs = "null";

			save.add(epicTimer + "," + epicLocation + ":" + time + ":" + mobs);
		}
		EpicBoss.plugin.savelist.getCustomConfig().set("Timers", save);
		EpicBoss.plugin.savelist.saveCustomConfig();
	}

	public static void LoadAllTimers()
	{
		List<String> list = EpicBoss.plugin.savelist.getCustomConfig().getStringList("Timers");
		if(list == null)
			return;
		for(String s : list)
		{
			String[] parts = s.split(":");
                        String[] split = parts[0].split(",");

			//EpicTimer
                        EpicTimer et = TimerHandler.getEpicTimer(split[0]);

                        //EpicLocation
                        EpicLocation el = LocationHandler.getEpicLocation(split[1]);

			//Time
			int time = Integer.parseInt(parts[1]);

			//Mob
			List<UUID> mobslist = new ArrayList<UUID>();
			String[] mobs = parts[2].split(",");
			for(String m : mobs)
			{
				if(!m.equals("") && !m.equals("null"))
				{
					for(LivingEntity l : EpicBoss.plugin.getMobsAll())
					{
						UUID i = UUID.fromString(m);
						if(l.getUniqueId().compareTo(i) == 0)
						{
							mobslist.add(l.getUniqueId());
						}
					}
				}
			}

                        Timer t = new Timer(et, el);
                        t.clock = time;
                        t.mobs = mobslist;
                        EpicBoss.plugin.allTimers.add(t);
		}
	}
}
