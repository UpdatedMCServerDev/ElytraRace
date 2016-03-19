package me.gET.sv.ElytraRace.lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.gET.sv.ElytraRace.main.Arena.GameState;
import me.gET.sv.ElytraRace.main.Main;

public class LobbyTime implements Runnable{

	private Lobby lobby;
	private int time = 120;
	
	private int countDown = 0;
	
	@Override
	public void run() {
		
		if(time <= 0){
			for(Player p : Bukkit.getServer().getOnlinePlayers()){
				if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
					p.sendMessage("Não há players suficientes para começar a partida!");
					time = 120;
					p.setLevel(time);
					return;
				}
			}
		}
		
		if(lobby.getArena().getPlayersLenth() == 1){
			if(!(lobby.getArena().getGameState() == GameState.INITING)){
				lobby.getArena().setGameState(GameState.INITING);
				time = 30;
				countDown = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Countdown(lobby), 0, 20);
			}
		}
		
		for(Player p : Bukkit.getServer().getOnlinePlayers()){
			if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
				lobby.getLobbyBoard().setTime(time);
				lobby.getLobbyBoard().setNickels(p);
				p.setLevel(time);
			}
		}
		
		time--;
	}
	
	/////////////////////////////
	//////////SETTERS////////////
	/////////////////////////////
	public LobbyTime setLobby(Lobby l){
		lobby = l;
		return this;
	}
	
	/////////////////////////////
	//////////GETTERS////////////
	/////////////////////////////
	public int getTime(){
		return time;
	}
	
	/////////////////////////////
	//////////VOIDS//////////////
	/////////////////////////////
	public void stopCountDown(){
		Bukkit.getServer().getScheduler().cancelTask(countDown);
	}

}
