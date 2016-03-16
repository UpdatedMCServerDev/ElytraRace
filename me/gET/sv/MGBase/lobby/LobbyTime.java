package me.gET.sv.MGBase.lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyTime implements Runnable{

	private Lobby lobby;
	private int time = 120;
	
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

}
