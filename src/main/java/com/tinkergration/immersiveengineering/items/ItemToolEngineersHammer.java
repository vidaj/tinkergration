package com.tinkergration.immersiveengineering.items;

import java.util.List;

import com.tinkergration.immersiveengineering.ImmersiveEngineeringModule;

import blusunrize.immersiveengineering.common.IEContent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.tools.TinkerTools;

public class ItemToolEngineersHammer extends ToolCore {

	private final Item ieHammer;

	public ItemToolEngineersHammer() {
		super(new PartMaterialType(TinkerTools.toolRod, MaterialTypes.HANDLE), new PartMaterialType(ImmersiveEngineeringModule.toolHammerHead, MaterialTypes.HEAD), new PartMaterialType(TinkerTools.binding, MaterialTypes.EXTRA));
		ieHammer = IEContent.itemTool;
		
		addCategory(Category.NO_MELEE);
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
	public boolean isEffective(IBlockState state) {
		return false;
	}

	@Override
	public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		return false;
	}
	
	@Override
	public NBTTagCompound buildTag(List<Material> materials) {
		return buildDefaultTag(materials).get();
	}
	
	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand){
		return ieHammer.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ, hand);
	}
}
