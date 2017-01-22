package com.tinkergration;

import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

/**
 * Some code is liberally borrowed from the TConstruct Scythe class.
 * 
 * @author vidaj, slimeknights
 *
 */
public class TinkerShears extends ToolCore {

	public static final ImmutableSet<net.minecraft.block.material.Material> effective_materials = ImmutableSet.of(
			net.minecraft.block.material.Material.WEB, net.minecraft.block.material.Material.LEAVES,
			net.minecraft.block.material.Material.PLANTS, net.minecraft.block.material.Material.VINE,
			net.minecraft.block.material.Material.GOURD, net.minecraft.block.material.Material.CACTUS);

	private static PartMaterialType shearPMT = new PartMaterialType(TinkerTools.knifeBlade, HeadMaterialStats.TYPE);

	public TinkerShears() {
		super(shearPMT, shearPMT);
		addCategory(Category.HARVEST);
	}

	@Override
	public float damagePotential() {
		return 1;
	}

	@Override
	public double attackSpeed() {
		return 1;
	}

	@Override
	public NBTTagCompound buildTag(List<Material> materials) {
		return buildDefaultTag(materials).get();
	}

	@Override
	public boolean isEffective(IBlockState state) {
		return effective_materials.contains(state.getMaterial());
	}

	protected void breakBlock(ItemStack stack, EntityPlayer player, BlockPos pos, BlockPos refPos) {

		ToolHelper.breakExtraBlock(stack, player.getEntityWorld(), player, pos, refPos);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {
		if (ToolHelper.shearBlock(stack, player.getEntityWorld(), player, pos)) {
			return true;
		}

		return super.onBlockStartBreak(stack, pos, player);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		if (!(target instanceof IShearable)) {
			return false;
		}

		boolean shorn = false;

		int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack);
		shorn |= shearEntity(stack, player.getEntityWorld(), player, target, fortune);

		if (shorn) {
			player.getEntityWorld().playSound(null, player.posX, player.posY, player.posZ,
					SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
			player.spawnSweepParticles();
		}

		return shorn;
	}

	public boolean shearEntity(ItemStack stack, World world, EntityPlayer player, Entity entity, int fortune) {
		if (!(entity instanceof IShearable)) {
			return false;
		}

		IShearable shearable = (IShearable) entity;
		if (shearable.isShearable(stack, world, entity.getPosition())) {
			if (!world.isRemote) {
				List<ItemStack> drops = shearable.onSheared(stack, world, entity.getPosition(), fortune);
				Random rand = world.rand;
				for (ItemStack drop : drops) {
					net.minecraft.entity.item.EntityItem ent = entity.entityDropItem(drop, 1.0F);
					ent.motionY += rand.nextFloat() * 0.05F;
					ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
					ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
				}
			}
			ToolHelper.damageTool(stack, 1, player);

			return true;
		}

		return false;
	}
}
