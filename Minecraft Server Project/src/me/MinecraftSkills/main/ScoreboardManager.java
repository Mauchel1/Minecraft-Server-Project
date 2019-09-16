package me.MinecraftSkills.main;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


public class ScoreboardManager {

	protected static final main plugin = main.p;
	private static Timer timer = new Timer();
	
	public static void displayScoreboardLvl(Player player)
	{
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective o = sb.registerNewObjective("Skillboard", "Kriterium1", "displayname"); //Name, Criteria, Displayname
		
		//Team creator = sb.registerNewTeam("Team");
		
		o.setDisplayName("Skillboard Übersicht Level");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.getScore(ChatColor.DARK_PURPLE + "Armbrust").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Crossbow"));
		o.getScore(ChatColor.RED + "Axt").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Axe"));
		o.getScore(ChatColor.DARK_GRAY + "Bergbau").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Mining"));
		o.getScore(ChatColor.LIGHT_PURPLE + "Bogen").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Bow"));
		o.getScore(ChatColor.GREEN + "Farmen").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Farming"));
		o.getScore(ChatColor.AQUA + "Fischen").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Fishing"));
		o.getScore(ChatColor.BLUE + "Fäuste").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Fist"));
		o.getScore(ChatColor.YELLOW + "Graben").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Digging"));
		o.getScore(ChatColor.DARK_GREEN + "Holzfällen").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Woodcutting"));
		o.getScore(ChatColor.DARK_RED + "Schwert").setScore(PlayerManager.getSkillLvl(player.getUniqueId().toString(), "Sword"));

		player.setScoreboard(sb);
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "ScoreboardManager: " + "Scoreboard opened");

		
		timer.schedule(new TaskSwitchScoreboardOff(o), 10000);
	}
	
	
	public static void displayScoreboardXp(Player player)
	{
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		
		Objective o = sb.registerNewObjective("Skillboard", "Kriterium1", "displayname"); //Name, Criteria, Displayname
		
		//Team creator = sb.registerNewTeam("Team");
		
		o.setDisplayName("Skillboard Übersicht XP");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.getScore(ChatColor.DARK_PURPLE + "Armbrust").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Crossbow"));
		o.getScore(ChatColor.RED + "Axt").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Axe"));
		o.getScore(ChatColor.DARK_GRAY + "Bergbau").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Mining"));
		o.getScore(ChatColor.LIGHT_PURPLE + "Bogen").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Bow"));
		o.getScore(ChatColor.GREEN + "Farmen").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Farming"));
		o.getScore(ChatColor.AQUA + "Fischen").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Fishing"));
		o.getScore(ChatColor.BLUE + "Fäuste").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Fist"));
		o.getScore(ChatColor.YELLOW + "Graben").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Digging"));
		o.getScore(ChatColor.DARK_GREEN + "Holzfällen").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Woodcutting"));
		o.getScore(ChatColor.DARK_RED + "Schwert").setScore((int) PlayerManager.getSkillXP(player.getUniqueId().toString(), "Sword"));

		player.setScoreboard(sb);
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "ScoreboardManager: " + "Scoreboard opened");

		timer.schedule(new TaskSwitchScoreboardOff(o), 10000);
	}


	
}


class TaskSwitchScoreboardOff extends TimerTask
{
	private Objective o;
	
	public TaskSwitchScoreboardOff(Objective o) {
		this.o = o;
	}
	
	@Override
	public void run() {
		
		//sb.clearSlot(DisplaySlot.SIDEBAR);
		o.unregister();
		Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "ScoreboardManager: " + "Scoreboard closed");
	}
	
}