package com.tinkergration.botania.modifiers;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class ModifierWuthering extends ModifierTrait{

	public ModifierWuthering() {
		super("wuthering", 0x5e0000);
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage,
			boolean isCritical) {
		super.onHit(tool, player, target, damage, isCritical);
		target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 0));
	}
}
