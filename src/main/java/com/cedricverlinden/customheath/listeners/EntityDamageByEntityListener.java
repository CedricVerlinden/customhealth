package com.cedricverlinden.customheath.listeners;

import com.cedricverlinden.customheath.CustomHeath;
import com.cedricverlinden.customheath.utils.Color;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		Player player = (Player) event.getDamager();

		ItemStack sword = player.getInventory().getItemInMainHand();
		PersistentDataContainer pdc = sword.getItemMeta().getPersistentDataContainer();

		if (!(pdc.has(CustomHeath.getKey()))) {
			return;
		}

		boolean isPlayer = event.getEntity() instanceof Player;
		String entityName = event.getEntity().getName();

		if (isPlayer) {
			Player target = (Player) event.getEntity();
			entityName = target.getName();
			target.sendMessage(Component.text(Color.color("&7You have been damaged by &e" + player.getName() + " &7for &e" + CustomHeath.sword.getDamage())));
		}

		player.sendMessage(Component.text(Color.color("&7You have damaged &e" + entityName + " &7for &e" + CustomHeath.sword.getDamage())));
	}
}
