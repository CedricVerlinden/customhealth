package com.cedricverlinden.customheath;

import com.cedricverlinden.customheath.commands.ItemCommand;
import com.cedricverlinden.customheath.items.Item;
import com.cedricverlinden.customheath.items.Rarity;
import com.cedricverlinden.customheath.listeners.EntityDamageByEntityListener;
import com.cedricverlinden.customheath.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomHeath extends JavaPlugin {

	public static CustomHeath instance;
	public static Item sword;
	public static NamespacedKey key;

	@Override
	public void onEnable() {
		instance = this;
		key = new NamespacedKey(this, "Item");
		sword = new Item(Material.GOLDEN_SWORD, "Aspect of the End", Rarity.RARE, 100, "Instant Transmission", 50);

		Logger.log("Loading commands...");
		loadCommands();
		Logger.log("Loading listeners...");
		loadListeners();

		Logger.log("Started!");
	}


	@Override
	public void onDisable() {
		Logger.log("Shutting down...");
	}

	void loadCommands() {
		Bukkit.getPluginCommand("item").setExecutor(new ItemCommand());
	}

	void loadListeners() {
		Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
	}

	public static Item getSword() {
		return sword;
	}

	public static NamespacedKey getKey() {
		return key;
	}

	public static CustomHeath getInstance() {
		return instance;
	}
}
