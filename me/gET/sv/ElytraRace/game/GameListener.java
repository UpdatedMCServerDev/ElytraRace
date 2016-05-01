package me.gET.sv.ElytraRace.game;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

import me.gET.sv.ElytraRace.main.Arena;

public class GameListener implements Listener {

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e){
		if(e.getEntityType() != EntityType.PLAYER) return;
		if(Arena.getArena((Player) e.getEntity()) == null) return;
		Player p = (Player) e.getEntity();
		
		if(e.getCause() == DamageCause.ENTITY_ATTACK){
			p.teleport(Arena.getArena(p).getSpawn());
			e.setCancelled(true);
		}
		
		if(e.getCause() == DamageCause.FALL){
			p.setVelocity(new Vector(0, 5, 0));
			e.setCancelled(true);
		}
		else{
			return;
		}
		
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		if(Arena.getArena(e.getPlayer()) != null){
			Arena.getArena(e.getPlayer()).removePlayer(e.getPlayer());
		}
	}
	
}
