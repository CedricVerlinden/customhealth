package com.cedricverlinden.customhealth.managers;

import com.cedricverlinden.customhealth.CustomHealth;
import com.cedricverlinden.customhealth.constants.Rarity;
import com.cedricverlinden.customhealth.utils.Chat;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemManager {

	private static Map<UUID, ItemManager> itemData = new HashMap<>();

	final Material material;
	final String displayName;

	final Rarity rarity;
	final int damage;
	final String ability;
	final int mana;

	private final UUID uuid;
	private final ItemStack itemStack;
	private final File file;
	private final YamlConfiguration configuration;

	protected ItemManager(Material material, String displayName, Rarity rarity, int damage, String ability, int mana) {
		this.material = material;
		this.displayName = displayName;

		this.rarity = rarity;
		this.damage = damage;
		this.ability = ability;
		this.mana = mana;
		this.configuration = new YamlConfiguration();

		uuid = UUID.randomUUID();
		NamespacedKey key = new NamespacedKey(CustomHealth.getInstance(), "CustomItem");

		itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, uuid.toString());

		itemMeta.setUnbreakable(true);
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

		itemMeta.displayName(Component.text(rarity.getColor() + displayName));

		ArrayList<String> lore = new ArrayList<>();
		lore.add(Chat.color("&7Damage: &c+" + damage));
		lore.add(Chat.color("&7Strength: &c+112"));
		lore.add(Chat.color("&7Crit Chance: &c+9%"));
		lore.add(Chat.color("&7Crit Damage: &c+65%"));
		lore.add(Chat.color("&r"));
		lore.add(Chat.color("&6Ability: " + ability + " &e&lRIGHT CLICK"));
		lore.add(Chat.color("&8Mana Cost: &3" + mana));

		itemMeta.setLore(lore);

		itemStack.setItemMeta(itemMeta);

		// Save everything
		// TODO: split everything up in 2 to 3 functions
		// TODO: a way to retrieve the data from the files (when retrieved add to Map)
		itemData.put(uuid, this);

		String path = "items/" + uuid + ".yml";
		file = new File(CustomHealth.getInstance().getDataFolder(), path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}

		configuration.set("Material", material.toString());
		configuration.set("displayName", displayName);
		configuration.set("Rarity", rarity.toString());
		configuration.set("Damage", damage);
		configuration.set("Ability", ability);
		configuration.set("Mana", mana);

		try {
			configuration.save(file);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	protected ItemManager(UUID uuid, Material material, String displayName, Rarity rarity, int damage, String ability, int mana) {
		this.material = material;
		this.displayName = displayName;

		this.rarity = rarity;
		this.damage = damage;
		this.ability = ability;
		this.mana = mana;
		this.configuration = new YamlConfiguration();
		this.uuid = uuid;

		NamespacedKey key = new NamespacedKey(CustomHealth.getInstance(), "CustomItem");

		itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, uuid.toString());

		itemMeta.setUnbreakable(true);
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

		itemMeta.displayName(Component.text(rarity.getColor() + displayName));

		ArrayList<String> lore = new ArrayList<>();
		lore.add(Chat.color("&7Damage: &c+" + damage));
		lore.add(Chat.color("&7Strength: &c+112"));
		lore.add(Chat.color("&7Crit Chance: &c+9%"));
		lore.add(Chat.color("&7Crit Damage: &c+65%"));
		lore.add(Chat.color("&r"));
		lore.add(Chat.color("&6Ability: " + ability + " &e&lRIGHT CLICK"));
		lore.add(Chat.color("&8Mana Cost: &3" + mana));

		itemMeta.setLore(lore);

		itemStack.setItemMeta(itemMeta);

		// Save everything
		// TODO: split everything up in 2 to 3 functions
		// TODO: a way to retrieve the data from the files (when retrieved add to Map)
		itemData.put(uuid, this);

		String path = "items/" + uuid + ".yml";
		file = new File(CustomHealth.getInstance().getDataFolder(), path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}

		configuration.set("Material", material.toString());
		configuration.set("displayName", displayName);
		configuration.set("Rarity", rarity.toString());
		configuration.set("Damage", damage);
		configuration.set("Ability", ability);
		configuration.set("Mana", mana);

		try {
			configuration.save(file);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	public static ItemManager getItemInstance(UUID uuid) {
		return itemData.get(uuid);
	}

	public static void registerItems() {
		File folder = new File(CustomHealth.getInstance().getDataFolder() + "/items");
		File[] files = folder.listFiles();

		if (files == null) {
			return;
		}

		for (File file : files) {
			YamlConfiguration configuration = new YamlConfiguration();
			try {
				configuration.load(file);
				new ItemManager(UUID.fromString(file.getName().replace(".yml", "")), Material.getMaterial(configuration.getString("Material")), configuration.getString("displayName"), Rarity.valueOf(configuration.getString("Rarity")), configuration.getInt("damage"), configuration.getString("Ability"), configuration.getInt("Mana"));
			} catch (IOException | InvalidConfigurationException exception) {
				exception.printStackTrace();
			}

		}
	}

	public String getDisplayName() {
		return displayName;
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

	public UUID getUUID() {
		return uuid;
	}
}
