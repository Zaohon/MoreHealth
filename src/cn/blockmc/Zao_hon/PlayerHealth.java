package cn.blockmc.Zao_hon;

public class PlayerHealth {
	private double health = 100;
	private double maxHealth = 100;

	public PlayerHealth(double health, double maxHealth) {
		this.health = health;
		this.maxHealth = maxHealth;
	}

	public double getHealth() {
		return health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}
	@Override
	public PlayerHealth clone() {
		return new PlayerHealth(health,maxHealth);
	}
}
