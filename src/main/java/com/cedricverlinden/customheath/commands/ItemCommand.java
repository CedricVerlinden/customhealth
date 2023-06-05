package com.cedricverlinden.customheath.commands;

import com.cedricverlinden.customheath.CustomHeath;
import com.cedricverlinden.customheath.utils.Color;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player player)) {
			return true;
		}

		player.getInventory().addItem(CustomHeath.sword.getItem());
		player.sendMessage(Component.text(Color.color("&7This item hits with a damage of &e" + CustomHeath.sword.getDamage())));
		player.sendMessage(Component.text(Color.color("&7It costs &e" + CustomHeath.sword.getMana() + " &7to use it's ability")));

		return true;
	}
}
