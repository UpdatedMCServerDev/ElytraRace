package me.gET.sv.MGBase.lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.gET.sv.MGBase.main.Arena;
import me.gET.sv.MGBase.main.Main;

public class Lobby {

	private LobbyTime time;
	private LobbyBoard board;
	private Arena arena;
	
	private int timeTask;
	
	public Lobby(){
		time = new LobbyTime().setLobby(this);
		board = new LobbyBoard(this);
	}
	
	/////////////////////////////
	//////////GETTERS////////////
	/////////////////////////////
	public LobbyTime getLobbyTime(){
		return time;
	}
	public LobbyBoard getLobbyBoard(){
		return board;
	}
	public Arena getArena() {
		return arena;
	}
	
	/////////////////////////////
	//////////SETTERS////////////
	/////////////////////////////
	public Lobby setArena(Arena a){
		this.arena = a;
		return this;
	}
	
	/////////////////////////////
	//////////VOIDS//////////////
	/////////////////////////////
	
	public void start(){
		
		startTimer();
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(arena.getPlayers().contains(p.getUniqueId())){
				p.teleport(arena.getLobbyLocation());
				addScoreboard(p);
			}
		}
	}
	
	public void stop(){
		
		stopTimer();
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(arena.getPlayers().contains(p.getUniqueId())){
				removeScoreboard(p);
			}
		}
	}
	
	public void startTimer(){
		timeTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), time, 0, 20);
	}
	
	public void stopTimer(){
		Bukkit.getServer().getScheduler().cancelTask(timeTask);
	}
	
	public void addScoreboard(Player p){
		p.setScoreboard(board.getScoreboard(p));
	}
	public void removeScoreboard(Player p){
		p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
	}

}
