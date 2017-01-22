package com.tinkergration.botania.modifiers;

import net.minecraft.init.Enchantments;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.utils.ToolBuilder;
import slimeknights.tconstruct.tools.modifiers.ToolModifier;

public class ModifierAmphibious extends ToolModifier {

	public ModifierAmphibious() {
		super("amphibious", 0xfbe28b);

	    addAspects(new ModifierAspect.SingleAspect(this), new ModifierAspect.DataAspect(this), ModifierAspect.freeModifier);
	}
	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		ToolBuilder.addEnchantment(rootCompound, Enchantments.AQUA_AFFINITY);
	}

}
