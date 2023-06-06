package com.cedricverlinden.customhealth.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		//Player player = (Player) event.getDamager();

		/*ItemStack sword = player.getInventory().getItemInMainHand();
		PersistentDataContainer pdc = sword.getItemMeta().getPersistentDataContainer();

		if (!(pdc.has(CustomHealth.getKey()))) {
			return;
		}

		boolean isPlayer = event.getEntity() instanceof Player;
		String entityName = event.getEntity().getName();

		if (isPlayer) {
			Player target = (Player) event.getEntity();
			entityName = target.getName();
			target.sendMessage(Component.text(Color.color("&7You have been damaged by &e" + player.getName() + " &7for &e" + CustomHealth.sword.getDamage())));
		}

		player.sendMessage(Component.text(Color.color("&7You have damaged &e" + entityName + " &7for &e" + CustomHealth.sword.getDamage())));*/
	}
}
