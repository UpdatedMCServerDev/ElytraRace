package me.gET.sv.ElytraRace.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.gET.sv.ElytraRace.main.Arena;
import me.gET.sv.ElytraRace.main.Main;
import me.gET.sv.SpawnPlugin.main.Spawn;

public class Game {

	private GameTime time;
	private GameBoard board;
	private Arena arena;
	
	public String doneMessage = "A partida acabou!";
	
	private int timeTask;
	
	public Game(){
		this.time = new GameTime().setGame(this);
		this.board = new GameBoard().setGame(this); 
	}
	
	
	/////////////////////////////
	//////////GETTERS////////////
	/////////////////////////////
	public GameTime getGameTime(){
		return time;
	}
	public GameBoard getGameBoard(){
		return board;
	}
	public Arena getArena() {
		return arena;
	}
	
	/////////////////////////////
	//////////SETTERS////////////
	/////////////////////////////
	public Game setArena(Arena a){
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
				p.sendMessage("A partida iniciou!");
				p.setLevel(0);
				p.teleport(arena.getSpawn());
				p.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
				addScoreboard(p);
			}
		}
	}
	
	public void stop(){
		
		stopTimer();
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(arena.getPlayers().contains(p.getUniqueId())){
				p.teleport(Spawn.getSpawn());
				removeScoreboard(p);
			}
		}
	}
	
	public void sendDoneMessage(Player p){
		p.sendMessage(doneMessage);
	}	
	
	public void startTimer(){
		timeTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), time, 0, 20);
	}
	
	public void stopTimer(){
		Bukkit.getServer().getScheduler().cancelTask(timeTask);
	}
	
	public void addScoreboard(Player p){
		p.setScoreboard(board.getScoreboard());
	}
	public void removeScoreboard(Player p){
		p.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
	}
	
}
