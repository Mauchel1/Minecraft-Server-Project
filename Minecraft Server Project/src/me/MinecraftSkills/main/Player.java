package me.MinecraftSkills.main;

public class Player {

	public String uuid;
	
	public int lvlWoodcutting;
	int lvlAgility;
	int lvlMining;
	int lvlFarming;
	int lvlAlchemy;
	int lvlFishing;
	
	int xpWoodcutting;
	int xpAgility;
	int xplMining;
	int xpFarming;
	int xpAlchemy;
	int xpFishing;
	
	
	public Player(String UUID) {
		uuid = UUID;
	}
	
	
//	XP-Berechnung
//	x = 10(n^2-m^2) + 1010(n-m)
//	or
//	10(n-m)(n+m+101)
//	where n is the target level and m is the current level.
//
//	To find how much exp is needed to get to level "L" from 0 exp, this equation can be used:
//	exp = 1010L + 10L^2
//	or
//	10L(101+L)


}
