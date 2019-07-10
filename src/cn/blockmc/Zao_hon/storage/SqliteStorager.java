package cn.blockmc.Zao_hon.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.entity.Player;

import cn.blockmc.Zao_hon.MoreHealth;
import cn.blockmc.Zao_hon.PlayerHealth;

public class SqliteStorager extends DataStorager {
	private MoreHealth plugin;
	private final String path;

	public SqliteStorager(MoreHealth plugin) {
		this.plugin = plugin;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			plugin.PR("加载JDBC数据库失败");
			plugin.onDisable();
		}
		path = "jdbc:sqlite:" + plugin.getDataFolder() + "/" + "portals.db";
		this.setupTable();
	}

	public Connection setupConnection() {
		try {
			return DriverManager.getConnection(path);
		} catch (SQLException e) {
			plugin.PR("连接数据库失败");
			plugin.onDisable();
			return null;
		}
	}

	public void setupTable() {
		Connection connection = setupConnection();
		try {
			Statement stat = connection.createStatement();
			stat.execute(
					"CREATE TABLE IF NOT EXISTS playerhealth(NAME TEXT , UUID TEXT , HEALTH DOUBLE,MAXHEALTH DOUBLE)");
			stat.close();
			connection.close();
		} catch (SQLException e) {
			plugin.PR("创建初始表失败");
			e.printStackTrace();
		}

	}

	@Override
	public void insertHealth(Player player, PlayerHealth phealth) {
		Connection connection = setupConnection();
		PreparedStatement ps = setupPreparedStatement(connection, PreparedStatementType.INSECT_HEALTH);
		try {
			ps.setString(1, player.getName());
			ps.setString(2, player.getUniqueId().toString());
			ps.setDouble(3,phealth.getHealth());
			ps.setDouble(4,phealth.getMaxHealth());
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//	@Override
//	public HashMap<UUID, Double> selectAllHealth() {
//		Connection connection = setupConnection();
//		HashMap<UUID, Double> map = new HashMap<UUID, Double>();
//		Statement stat;
//		try {
//			stat = connection.createStatement();
//			ResultSet rs = stat.executeQuery("SELECT UUID AND HEALTH FROM playerhealth");
//			while (rs.next()) {
//				map.put(UUID.fromString(rs.getString(1)), rs.getDouble(2));
//			}
//			stat.close();
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return map;
//
//	}

//	@Override
//	public HashMap<UUID, Double> selectAllMaxHealth() {
//		Connection connection = setupConnection();
//		HashMap<UUID, Double> map = new HashMap<UUID, Double>();
//		Statement stat;
//		try {
//			stat = connection.createStatement();
//			ResultSet rs = stat.executeQuery("SELECT UUID AND MAXHEALTH FROM playerhealth");
//			while (rs.next()) {
//				map.put(UUID.fromString(rs.getString(1)), rs.getDouble(2));
//			}
//			stat.close();
//			connection.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return map;
//
//	}
	@Override
	public PlayerHealth getHealth(UUID uuid) {
		PlayerHealth phealth = null;
		Connection connection = setupConnection();
		PreparedStatement ps = setupPreparedStatement(connection, PreparedStatementType.SELECT_HEALTH_AND_MAX_HEALTH);
		try {
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				phealth = new PlayerHealth(rs.getDouble(1),rs.getDouble(2));
			}
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phealth;
	}

	@Override
	public void updateHealth(UUID uuid,PlayerHealth phealth) {
		Connection connection = setupConnection();
		PreparedStatement ps = setupPreparedStatement(connection, PreparedStatementType.UPDATE_HEALTH);
		try {
			ps.setString(1, uuid.toString());
			ps.setDouble(2, phealth.getHealth());
			ps.setDouble(3, phealth.getMaxHealth());
			ps.execute();
			ps.close(); 
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PreparedStatement setupPreparedStatement(Connection conn, @Nonnull PreparedStatementType type) {
		String str = "";
		switch (type) {
		case SELECT_HEALTH_AND_MAX_HEALTH:
			str = "SELECT FROM playerhealth WHERE UUID = ?";
			break;
		case INSECT_HEALTH:
			str = "INSECT INTO playerhealth VALUE(?,?,?,?)";
			break;
		case UPDATE_HEALTH:
			str = "UPDATE playerhealth WHERE UUID = ? SET HEALTH = ? AND MAXHEALTH = ?";
			break;
		default:
			break;
		}
		try {
			return conn.prepareStatement(str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
