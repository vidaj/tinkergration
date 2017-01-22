package com.tinkergration.botania.modifiers;

import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.Modifier;
import vazkii.botania.common.item.ModItems;

public class BotaniaModifiers {

	public final Modifier amphibious = new ModifierAmphibious();
	
	public final Modifier wuthering = new ModifierWuthering();
	
	public void registerModifiers() {
		amphibious.addItem(runeOfWater(), 1, 1);
		wuthering.addItem(runeOfWrath(), 1, 1);
	}
	
	private static ItemStack runeOfWrath() {
		return new ItemStack(ModItems.rune, 1, 0);
	}

	private static ItemStack runeOfWater() {
		return new ItemStack(ModItems.rune, 1, 13);
	}
}
