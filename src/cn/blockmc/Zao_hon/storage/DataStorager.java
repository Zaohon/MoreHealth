package cn.blockmc.Zao_hon.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import cn.blockmc.Zao_hon.PlayerHealth;

public abstract class DataStorager {
	public abstract void insertHealth(Player player,PlayerHealth phealth);
//	public abstract HashMap<UUID,Double> selectAllHealth();
//	public abstract HashMap<UUID,Double> selectAllMaxHealth();
	public abstract PlayerHealth getHealth(UUID uuid);
	public abstract void updateHealth(UUID uuid,PlayerHealth phealth);
	public abstract PreparedStatement setupPreparedStatement(Connection conn, PreparedStatementType type);
}
