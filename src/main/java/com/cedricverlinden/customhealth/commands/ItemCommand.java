package com.cedricverlinden.customhealth.commands;

import com.cedricverlinden.customhealth.CustomHealth;
import com.cedricverlinden.customhealth.items.TestItem;
import com.cedricverlinden.customhealth.managers.ItemManager;
import com.cedricverlinden.customhealth.utils.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class ItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player player)) {
			return true;
		}

		if (args.length == 0) {
			ItemManager test = new TestItem();

			player.getInventory().addItem(test.getItem());
			player.sendMessage(Component.text(Chat.color("&7This item hits with a damage of &e" + test.getDamage())));
			player.sendMessage(Component.text(Chat.color("&7It costs &e" + test.getMana() + " &7to use it's ability")));

			Chat.debug(player, "&7UUID: &a" + test.getUUID());
			return true;
		}

		String param = args[0].toLowerCase();

		if ("check".equals(param)) {
			ItemMeta currentItemMeta = player.getInventory().getItemInMainHand().getItemMeta();

			if (currentItemMeta == null) {
				Chat.debug(player, "&cThis item doesn't have meta data");
				return true;
			}

			PersistentDataContainer pdc = currentItemMeta.getPersistentDataContainer();
			NamespacedKey key = new NamespacedKey(CustomHealth.getInstance(), "CustomItem");
			if (!(pdc.getKeys().contains(key))) {
				Chat.debug(player, "&cThis item is not a custom item hence it does not have a NamespacedKey");
				return true;
			}

			UUID uuid = UUID.fromString(Objects.requireNonNull(pdc.get(key, PersistentDataType.STRING)));
			ItemManager item = ItemManager.getItemInstance(uuid);

			Chat.debug(player, "&7Current Item UUID: &a" + uuid);
			Chat.debug(player, "&7Current Item Name: &a" + item.getDisplayName());
			return true;
		}

		player.sendMessage(Component.text(Chat.color("&cUse: /item [check]")));
		return true;
	}
}
