package com.cedricverlinden.customheath.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Logger {

	public static ConsoleCommandSender console = Bukkit.getConsoleSender();

	public static void log(String message) {
		console.sendMessage(message);
	}
}
