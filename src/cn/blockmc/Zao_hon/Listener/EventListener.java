package cn.blockmc.Zao_hon.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cn.blockmc.Zao_hon.MoreHealth;
import cn.blockmc.Zao_hon.PlayerHealth;

public class EventListener implements Listener{
	private final MoreHealth plugin;
	private final HealthManager manager;
	public EventListener(MoreHealth plugin) {
		this.plugin = plugin;
		this.manager = plugin.getHealthManager();
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		PlayerHealth phealth = manager.loadPlayerHealth(player);
		
	}

}
