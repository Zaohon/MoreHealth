package cn.blockmc.Zao_hon;

import org.bukkit.plugin.java.JavaPlugin;

import cn.blockmc.Zao_hon.Listener.HealthManager;
import cn.blockmc.Zao_hon.storage.DataStorager;
import cn.blockmc.Zao_hon.storage.SqliteStorager;

public class MoreHealth extends JavaPlugin {
	private double ratio = 0;
	private DataStorager dataStorager;
	private HealthManager healthManager;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		ratio = this.getConfig().getDouble("OneHeart");

		dataStorager = new SqliteStorager(this);
		healthManager= new HealthManager(this);

		PR("========================");
		PR("      MoreHealth         ");
		PR("     Version: " + this.getDescription().getVersion());
		PR("     Author:Zao_hon           ");
		PR("========================");
	}

	public void PR(String str) {
		this.getLogger().info(str);
	}

	public DataStorager getDataStorager() {
		return dataStorager;
	}
	public HealthManager getHealthManager(){
		return healthManager;
	}

}
