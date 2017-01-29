package com.tinkergration.forestry.materials;

import com.tinkergration.TinkergrationColors;
import com.tinkergration.botania.traits.BotaniaTraits;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.utils.HarvestLevels;

public class MaterialString extends Material {

	public MaterialString() {
		super("string", TinkergrationColors.string);
		addItem("ingotElvenElementium", 1, Material.VALUE_Ingot);
		
		Item elementiumIngot = vazkii.botania.common.item.ModItems.manaResource;
		setRepresentativeItem(new ItemStack(elementiumIngot, 1, 7));
		addTrait(BotaniaTraits.manalicious);
	}
	
	public void registerStats() {
		TinkerRegistry.addMaterialStats(this,
                new HeadMaterialStats(334, 5.25f, 3.50f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(0.70f, -50),
                new ExtraMaterialStats(100));
	}
	
	@SideOnly(Side.CLIENT)
	public void setRenderInfo() {
		setRenderInfo(new MaterialRenderInfo.Metal(TinkergrationColors.elementium, 0f, 0.3f, 0f));
	}
}