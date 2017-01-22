package com.tinkergration.botania.traits;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import vazkii.botania.api.mana.ManaItemHandler;
import net.minecraft.entity.player.EntityPlayer;

public class TraitManalicious extends AbstractTrait{
	
	private static final int MANA_PER_DAMAGE = 60;
	
	public TraitManalicious() {
		super("manalicious", TextFormatting.BLUE);
	}

	@Override
	public void onUpdate(ItemStack tool, World world, Entity player, int itemSlot, boolean isSelected) {
		super.onUpdate(tool, world, player, itemSlot, isSelected);
		
		if(!world.isRemote && player instanceof EntityPlayer && tool.getItemDamage() > 0 && ManaItemHandler.requestManaExactForTool(tool, (EntityPlayer) player, MANA_PER_DAMAGE * 2, true)) {
			tool.setItemDamage(tool.getItemDamage() - 1);
		}
	}
	
	
}
