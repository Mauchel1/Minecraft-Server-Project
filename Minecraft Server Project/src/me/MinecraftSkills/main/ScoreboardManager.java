package me.MinecraftSkills.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class ScoreboardManager {

	protected static final main plugin = main.p;
	
	public static void displayScoreboard(Player player)
	{
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective o = sb.registerNewObjective("name", "criterium", "displayname");
		
		Team creator = sb.registerNewTeam("Team");
		
		o.setDisplayName("Hurray Welcome! ");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.getScore("WasFuerEinMist").setScore(123);
		
		player.setScoreboard(sb);
	}
	
}
