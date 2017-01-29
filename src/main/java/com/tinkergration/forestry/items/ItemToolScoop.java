package com.tinkergration.forestry.items;

import java.util.List;

import com.google.common.collect.ImmutableSet;
import com.tinkergration.forestry.ForestryModule;
import com.tinkergration.forestry.materials.ScoopHeadMaterialStats;

import forestry.api.core.IToolScoop;
import forestry.apiculture.MaterialBeehive;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

public class ItemToolScoop extends ToolCore implements IToolScoop {

	public static final ImmutableSet<net.minecraft.block.material.Material> effective_materials = ImmutableSet.of(
			net.minecraft.block.material.Material.LEAVES);
	
	public ItemToolScoop() {
		super(new PartMaterialType(TinkerTools.toolRod, MaterialTypes.HANDLE), new PartMaterialType(ForestryModule.scoopHead, ForestryModule.MaterialTypes.ScoopHead.GetName()), new PartMaterialType(TinkerTools.binding, MaterialTypes.EXTRA));
		addCategory(Category.HARVEST);
		this.setHarvestLevel("scoop", 1);
	}
	

	@Override
	public float damagePotential() {
		return 0;
	}

	@Override
	public double attackSpeed() {
		return 0;
	}
	

	@Override
	public NBTTagCompound buildTag(List<Material> materials) {
		ToolNBT toolData = new ToolNBT();
		HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
		ScoopHeadMaterialStats head = materials.get(1).getStatsOrUnknown(ForestryModule.MaterialTypes.ScoopHead.GetName());
		ExtraMaterialStats extra = materials.get(2).getStatsOrUnknown(MaterialTypes.EXTRA);
		
		toolData.durability += head.durability + extra.extraDurability + handle.durability;
		toolData.speed += head.miningSpeed;
		toolData.durability = Math.max(1, Math.round((float) toolData.durability * handle.modifier));
		
		return toolData.get();
	}
	
	@Override
	public boolean isEffective(IBlockState state) {
		return state.getMaterial() instanceof MaterialBeehive;
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return !ToolHelper.isBroken(stack) && state.getMaterial() instanceof MaterialBeehive;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		if (ToolHelper.isBroken(stack)) {
			return true;
		}

		return super.onBlockStartBreak(stack, pos, player);
	}


}