package me.gET.sv.ElytraRace.lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Countdown implements Runnable{

	private Lobby lobby;
	private int i;
	
	public Countdown(Lobby l) {
		this.lobby = l;
		this.i = 30;
	}
	
	@Override
	public void run() {
		if (i == 0) {
			lobby.getArena().start();
			lobby.getLobbyTime().stopCountDown();
		}
		
			
			switch (i) {
			case 30:
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
						p.sendMessage(ChatColor.BOLD + (30 +" segundos para começar a partida"));
					}
				}
				break;
			
			case 15:
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
						p.sendMessage(ChatColor.BOLD + (15 +" segundos para começar a partida"));
					}
				}
				break;
				
			case 5:
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
						p.sendMessage(ChatColor.BOLD + "" + 5);
					}
				}
				break;
				
			case 4:
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
						p.sendMessage(ChatColor.BOLD + "" + 4);
					}
				}
				break;
				
			case 3:
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
						p.sendMessage(ChatColor.BOLD + "" + 3);
					}
				}
				break;
				
			case 2:
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
						p.sendMessage(ChatColor.BOLD + "" + 2);
					}
				}
				break;
				
			case 1:
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(lobby.getArena().getPlayers().contains(p.getUniqueId())){
						p.sendMessage(ChatColor.BOLD + "" + 1);
					}
				}
				break;
				
			default:
				break;
			}
			
			i--;
		
	}

}
