package cn.blockmc.Zao_hon.Listener;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import cn.blockmc.Zao_hon.MoreHealth;
import cn.blockmc.Zao_hon.PlayerHealth;

public class HealthManager {
	private EventListener listener;
	private MoreHealth plugin;
	private final PlayerHealth defaultHealth;
	private HashMap<UUID, PlayerHealth> playerHealth = new HashMap<UUID, PlayerHealth>();

	public HealthManager(MoreHealth plugin) {
		this.plugin = plugin;
		double health = plugin.getConfig().getDouble("DefaultMaxHealth");
		defaultHealth = new PlayerHealth(health, health);

		this.listener = new EventListener(plugin);
		this.plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}

	public void setPlayerHealth(UUID uuid, PlayerHealth phealth) {
		playerHealth.put(uuid, phealth);
	}

	public PlayerHealth getPlayerHealth(UUID uuid) {
		return playerHealth.get(uuid);
	}

	public void savePlayerHealth(UUID uuid) {
		plugin.getDataStorager().updateHealth(uuid, playerHealth.get(uuid));
	}

	public void removePlayerHealth(UUID uuid) {
		playerHealth.remove(uuid);
	}

	public PlayerHealth loadPlayerHealth(Player player) {
		PlayerHealth phealth = plugin.getDataStorager().getHealth(player.getUniqueId());
		if (phealth == null) {
			phealth = defaultHealth.clone();
			plugin.getDataStorager().insertHealth(player, phealth);
			playerHealth.put(player.getUniqueId(), phealth);
		}
		return phealth;
	}

}
