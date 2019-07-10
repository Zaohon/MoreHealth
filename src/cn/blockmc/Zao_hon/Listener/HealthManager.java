package cn.blockmc.Zao_hon.Listener;

import java.util.HashMap;
import java.util.UUID;

import cn.blockmc.Zao_hon.MoreHealth;

public class HealthManager {
	private MoreHealth plugin;
	private HashMap<UUID, Double> playerHealth = new HashMap<UUID, Double>();
	private HashMap<UUID, Double> playerMaxHealth = new HashMap<UUID, Double>();

	public HealthManager(MoreHealth plugin) {
		this.plugin = plugin;
	}
	
	public void setPlayerHealth(UUID uuid , double health){
		
	}
	public double getPlayerHealth(UUID uuid){
		return playerHealth.get(uuid);
	}
	public void setPlayerMaxHealth(UUID uuid,double health){
		
	}
	public double getPlayerMaxHealth(UUID uuid){
		return 0;
	}
	
}
