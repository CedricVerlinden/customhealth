package com.cedricverlinden.customhealth;

import com.cedricverlinden.customhealth.commands.ItemCommand;
import com.cedricverlinden.customhealth.listeners.EntityDamageByEntityListener;
import com.cedricverlinden.customhealth.managers.ItemManager;
import com.cedricverlinden.customhealth.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomHealth extends JavaPlugin {

	private static CustomHealth instance;

	@Override
	public void onEnable() {
		instance = this;

		Logger.log("Loading commands...");
		loadCommands();
		Logger.log("Loading listeners...");
		loadListeners();
		Logger.log("Loading items...");
		ItemManager.registerItems();

		Logger.log("Started!");
	}

	@Override
	public void reloadConfig() {
		ItemManager.registerItems();
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

	public static CustomHealth getInstance() {
		return instance;
	}
}
