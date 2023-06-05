package com.cedricverlinden.customheath.items;

import com.cedricverlinden.customheath.CustomHeath;
import com.cedricverlinden.customheath.utils.Color;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.UUID;

public class Item {

	// Item itself
	final Material material;
	final String displayName;

	// Custom properties
	final Rarity rarity;
	final int damage;
	final String ability;
	final int mana;

	private final ItemStack itemStack;

	public Item(Material material, String displayName, Rarity rarity, int damage, String ability, int mana) {
		this.material = material;
		this.displayName = displayName;

		this.rarity = rarity;
		this.damage = damage;
		this.ability = ability;
		this.mana = mana;

		UUID uuid = UUID.randomUUID();

		itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.getPersistentDataContainer().set(CustomHeath.getKey(), PersistentDataType.STRING, uuid.toString());

		itemMeta.setUnbreakable(true);
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

		itemMeta.displayName(Component.text(rarity.getColor() + displayName));

		// Lore
		ArrayList<String> lore = new ArrayList<>();
		lore.add(Color.color("&7Damage: &c+" + damage));
		lore.add(Color.color("&7Strength: &c+112"));
		lore.add(Color.color("&7Crit Chance: &c+9%"));
		lore.add(Color.color("&7Crit Damage: &c+65%"));
		lore.add(Color.color("&r"));
		lore.add(Color.color("&6Ability: " + ability + " &e&lRIGHT CLICK"));
		lore.add(Color.color("&8Mana Cost: &3" + mana));

		itemMeta.setLore(lore);


		itemStack.setItemMeta(itemMeta);
	}

	public ItemStack getItem() {
		return itemStack;
	}

	public int getDamage() {
		return damage;
	}

	public int getMana() {
		return mana;
	}
}
