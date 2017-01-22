package com.tinkergration.forestry.items;


import java.util.List;

import com.google.common.collect.ImmutableSet;
import com.tinkergration.forestry.ForestryModule;
import com.tinkergration.forestry.materials.GrafterHeadMaterialStats;

import forestry.api.arboriculture.IToolGrafter;
import forestry.arboriculture.PluginArboriculture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

public class ItemToolGrafter extends ToolCore implements IToolGrafter {

	public static final ImmutableSet<net.minecraft.block.material.Material> effective_materials = ImmutableSet.of(
			net.minecraft.block.material.Material.LEAVES);
	
	public ItemToolGrafter() {
		super(new PartMaterialType(TinkerTools.toolRod, MaterialTypes.HANDLE), new PartMaterialType(ForestryModule.grafterHead, ForestryModule.MaterialTypes.GrafterHead.GetName()));
		addCategory(Category.HARVEST);
	}
	
	@Override
	public float getSaplingModifier(ItemStack stack, World world, EntityPlayer player, BlockPos pos) {
		return 100;
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
	  public float getStrVsBlock(ItemStack stack, IBlockState state) {
		
	    if(state.getMaterial() == net.minecraft.block.material.Material.LEAVES) {
	      return ToolHelper.calcDigSpeed(stack, state);
	    }
	    
	    return super.getStrVsBlock(stack, state);
	  }

	@Override
	public NBTTagCompound buildTag(List<Material> materials) {
		HandleMaterialStats handle = materials.get(0).getStats(MaterialTypes.HANDLE);
	    GrafterHeadMaterialStats head = materials.get(1).getStats(ForestryModule.MaterialTypes.GrafterHead.GetName());

	    
	    ToolNBT data = new ToolNBT();

	    if (handle == null || head == null) {
	    	return data.get();
	    }
	    
	    data.durability = Math.max(10, (int)((handle.modifier * (float)head.durability) + handle.durability));
	    data.speed = head.speed * handle.modifier;

	    return data.get();
	}
	
	@Override
	public boolean isEffective(IBlockState state) {
		return effective_materials.contains(state.getMaterial());
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		Block block = state.getBlock();
		return block instanceof BlockLeaves ||
				state.getMaterial() == net.minecraft.block.material.Material.LEAVES ||
				super.canHarvestBlock(state, stack);
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		if (ToolHelper.isBroken(stack)) {
			return true;
		}

		IBlockState blockToBreak = player.getEntityWorld().getBlockState(pos);
		
		boolean shouldDropSapling = breakingNonForestryLeaves(blockToBreak);
			
		boolean result = super.onBlockStartBreak(stack, pos, player);
		
		if (shouldDropSapling) {
			Block block = blockToBreak.getBlock();
			ItemStack sapling = new ItemStack(block.getItemDropped(blockToBreak, player.worldObj.rand, 0), 1, block.damageDropped(blockToBreak));
			Block.spawnAsEntity(player.worldObj, pos, sapling);
		}
		
		return result;
	}

	private boolean breakingNonForestryLeaves(IBlockState blockToBreak) {
		return blockToBreak.getBlock() != PluginArboriculture.blocks.leaves &&
				blockToBreak.getMaterial() == net.minecraft.block.material.Material.LEAVES;
	}
}
