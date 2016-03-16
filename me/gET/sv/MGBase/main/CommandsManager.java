package me.gET.sv.MGBase.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("minigame")){
			if(args.length <= 1) return false;
			
			if(args[0].equalsIgnoreCase("enter")){
				if(Arena.getArena(args[1]) == null){ p.sendMessage("Arena não encontrada!"); return true; }
				if(Arena.getArena(p) != null){ sender.sendMessage("Você só pode entrar em uma arena por vez!"); return true; }
				
				Arena.getArena(args[1]).addPlayer(p);
				sender.sendMessage("Você Entrou na arena!");
				
				return true;
				
			}
			
			if(args[0].equalsIgnoreCase("start")){
				if(!p.isOp()) { p.sendMessage("Você não tem permissão para isso."); return true; }
				if(Arena.getArena(args[1]) == null){ p.sendMessage("Arena não encontrada!"); return true; }
				
				Arena.getArena(args[1]).start();
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("stop")){
				if(!p.isOp()) { p.sendMessage("Você não tem permissão para isso."); return true; }
				if(Arena.getArena(args[1]) == null){ p.sendMessage("Arena não encontrada!"); return true; }
				
				Arena.getArena(args[1]).stop();
				
				return true;
			}
			
			if(args[0].equalsIgnoreCase("create")){
				if(!p.isOp()) { p.sendMessage("Você não tem permissão para isso."); return true; }
				if(args.length <= 3) return false;
				
				if(args[1].equalsIgnoreCase("setSpawn")){
					if(Arena.getArena(args[2]) == null){ p.sendMessage("Arena não encontrada!"); return true; }
					
					Arena.getArena(args[2]).setSpawn(p.getLocation());
					
					return true;
				}
				
				if(args[1].equalsIgnoreCase("setLobby")){
					if(Arena.getArena(args[2]) == null){ p.sendMessage("Arena não encontrada!"); return true; }
					
					Arena.getArena(args[2]).setLobby(p.getLocation());
					
					return true;
				}
				
				if(args[1].equalsIgnoreCase("verify")){
					if(Arena.getArena(args[2]) == null){ p.sendMessage("Arena não encontrada!"); return true; }
					
					p.sendMessage("Nome: " + String.valueOf(Arena.getArena(args[2]).getName() != null) + " (" + Arena.getArena(args[2]).getName() + ")");
					p.sendMessage("Spawn: " + String.valueOf(Arena.getArena(args[2]).getSpawn() != null));
					p.sendMessage("Lobby: " + String.valueOf(Arena.getArena(args[2]).getLobbyLocation() != null));
					
					return true;
				}
				
				new Arena(args[1], null, null);
				return true;
			}
			
		}
		return false;
	}

}
