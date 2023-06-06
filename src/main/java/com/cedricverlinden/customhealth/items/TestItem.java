package com.cedricverlinden.customhealth.items;

import com.cedricverlinden.customhealth.constants.Rarity;
import com.cedricverlinden.customhealth.managers.ItemManager;
import org.bukkit.Material;

public class TestItem extends ItemManager {

	public TestItem() {
		super(Material.WOODEN_SWORD, "Testing", Rarity.LEGENDARY, 70, "Test the item", 30);
	}
}
