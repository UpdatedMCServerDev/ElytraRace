package me.gET.sv.ElytraRace.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.gET.sv.Economy.main.Economy;


public class LobbyBoard {

	private Lobby lobby;
	
	private ScoreboardManager manager;
	private Scoreboard board;
	
	private int lenth = 0;
	
	private Objective title;
	private Score time;
	private Score players;
	private Score money;
	
	public LobbyBoard(Lobby l){
		
		this.manager = Bukkit.getScoreboardManager();
		this.board = manager.getNewScoreboard();
		this.lobby = l;
	}
	
	/////////////////////////////
	//////////GETTERS////////////
	/////////////////////////////
	public Scoreboard getScoreboard(Player p){
		lenth++;
		
		title = board.registerNewObjective(String.valueOf(lenth), "dummy");
		title.setDisplaySlot(DisplaySlot.SIDEBAR);
		title.setDisplayName(ChatColor.BOLD +lobby.getArena().getName());	
		
		time = title.getScore("Time: ");
		time.setScore(120);
		
		players = title.getScore("Players: ");
		players.setScore(0);
		
		money = title.getScore("Nickels: ");
		money.setScore(Economy.getMoney(p));
		
		return board;
	}
	
	public int getPlayers(){
		return players.getScore();
	}
	
	/////////////////////////////
	//////////SETTERS////////////
	/////////////////////////////
	public void setNickels(Player p){
		money.setScore(Economy.getMoney(p));
	}
	
	public void setPlayers(int i){
		players.setScore(i);
	}
	
	public void setTime(int i){
		time.setScore(i);
	}
	
}
