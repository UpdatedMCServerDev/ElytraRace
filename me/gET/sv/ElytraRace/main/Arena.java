package me.gET.sv.ElytraRace.main;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.gET.sv.ElytraRace.game.Game;
import me.gET.sv.ElytraRace.lobby.Lobby;

public class Arena {

	private String name;
	private Location spawn;
	private Location lobbyLocation;
	private ArrayList<UUID> players;
	private GameState state;
	
	private Lobby lobby;
	private Game game;
	
	private static ArrayList<Arena> arenas;
	
	public Arena(String name, Location s, Location l){
		this.name = name;
		this.spawn = s;
		this.lobbyLocation = l;
		players = new ArrayList<UUID>();
		this.state = GameState.PRE_GAME;
		
		lobby = new Lobby().setArena(this);
		game = new Game().setArena(this);
		
		arenas = new ArrayList<Arena>();
		arenas.add(this);
	}
	
	public enum GameState{
		PRE_GAME,
		INITING,
		IN_GAME;
	}
	
	/////////////////////////////
	//////////GETTERS////////////
	/////////////////////////////
	public String getName(){
		return name;
	}
	public Location getSpawn(){
		return spawn;
	}
	public Location getLobbyLocation(){
		return lobbyLocation;
	}
	public ArrayList<UUID> getPlayers(){
		return players;
	}
	public int getPlayersLenth(){
		return players.size();
	}
	
	public ArrayList<Arena> getArenas(){
		return arenas;
	}
	
	public static Arena getArena(String s){
		for(Arena a : arenas){
			if(a.getName().equals(s)){
				return a;
			}
		}
		return null;
	}
	public static Arena getArena(Player p){
		for(Arena a : arenas){
			if(a.getPlayers().contains(p.getUniqueId())){
				return a;
			 }
		 }
		 return null;
	 }
	public GameState getGameState(){
		return state;
	}
	
	/////////////////////////////
	//////////SETTERS////////////
	/////////////////////////////
	public void setName(String n){
		this.name = n;
	}
	public void setSpawn(Location s){
		this.spawn = s;
	}
	public void setLobby(Location l){
		this.lobbyLocation = l;
	}
	public void setPlayers(ArrayList<UUID> p){
		this.players = p;
	}
	public void setGameState(GameState state){
		this.state = state;
	}
	
	/////////////////////////////
	//////////BOOLEANS///////////
	/////////////////////////////
	public boolean isInGame(Player p){
		return getArena(p) != null;
	}
	
	/////////////////////////////
	//////////VOIDS//////////////
	/////////////////////////////
	public void addPlayer(Player p){
		if(state == GameState.IN_GAME){ p.sendMessage("Essa partida já iniciou!"); return; }
		if(state == GameState.INITING) { p.sendMessage("Você não pode entrar, a partida está iniciando!"); }
		
		players.add(p.getUniqueId());
		lobby.addScoreboard(p);
		if(players.size() == 1){
			lobby.startTimer();
		}
	}
	
	public void removePlayer(Player p){
		players.remove(p.getUniqueId());
	}
	
	public void start(){
		lobby.stop();
		game.start();
	}
	public void stop(){
		game.stop();
	}
	
	
}
