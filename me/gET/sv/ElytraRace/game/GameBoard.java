package me.gET.sv.ElytraRace.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class GameBoard {

	private Game game;
	
	private ScoreboardManager manager;
	private Scoreboard board;
	
	private Objective title;
	private Score time;
	private Score players;
	
	public GameBoard(){
		this.manager = Bukkit.getScoreboardManager();
		this.board = manager.getNewScoreboard();
	
	}
	
	/////////////////////////////
	//////////GETTERS////////////
	/////////////////////////////
	public Scoreboard getScoreboard(){
		
		title = board.registerNewObjective("test", "dummy");
		title.setDisplaySlot(DisplaySlot.SIDEBAR);
		title.setDisplayName(ChatColor.BOLD + game.getArena().getName());
		
		time = title.getScore("Time: ");
		time.setScore(0);
		
		players = title.getScore("Players: ");
		players.setScore(0);
		
		return board;
	}
	
	public int getPlayers(){
		return players.getScore();
	}
	
	/////////////////////////////
	//////////SETTERS////////////
	/////////////////////////////
	public GameBoard setGame(Game g){
		this.game = g;
		return this; 
	}
	
	public void setPlayers(int i){
		players.setScore(i);
	}
	
	public void setTime(int i){
		time.setScore(i);
	}
	
}
