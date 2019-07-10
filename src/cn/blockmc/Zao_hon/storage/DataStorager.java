package cn.blockmc.Zao_hon.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public abstract class DataStorager {
	public abstract void insertHealth(Player player,double health,double maxHealth);
	public abstract HashMap<UUID,Double> selectHealth();
	public abstract HashMap<UUID,Double> selectMaxHealth();
	public abstract void updateHealth(UUID uuid,double health,double maxHealth);
	public abstract PreparedStatement setupPreparedStatement(Connection conn, PreparedStatementType type);
}
