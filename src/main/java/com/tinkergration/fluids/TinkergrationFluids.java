package com.tinkergration.fluids;

import java.util.Locale;

import com.tinkergration.blocks.fluids.MoltenBotania;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.smeltery.block.BlockMolten;

public class TinkergrationFluids {

	public static FluidMolten createFluidMetal(String name, int color) {
		FluidMolten fluid = fluidMetal(name, color);
		FluidRegistry.addBucketForFluid(fluid);
		registerMoltenBlock(fluid);
		
		return fluid;
	}
	
	private static FluidMolten fluidMetal(String name, int color) {
		FluidMolten fluid = new FluidMolten(name, color);
		return registerFluid(fluid);
	}

	protected static <T extends Fluid> T registerFluid(T fluid) {
		fluid.setUnlocalizedName("tinkergration." + fluid.getName());
		FluidRegistry.registerFluid(fluid);

		return fluid;
	}

	private static BlockMolten registerMoltenBlock(Fluid fluid) {
		BlockMolten block = new MoltenBotania(fluid);
		return registerBlock(block, "molten_" + fluid.getName()); // molten_foobar prefix
	}

	protected static <T extends Block> T registerBlock(T block, String name) {
		ItemBlock itemBlock = new ItemBlock(block);
		registerBlock(block, itemBlock, name);
		return block;
	}

	protected static <T extends Block> T registerBlock(T block, ItemBlock itemBlock, String name) {
		if (!name.equals(name.toLowerCase(Locale.US))) {
			throw new IllegalArgumentException(
				String.format("Unlocalized names need to be all lowercase! Block: %s", name));
		}

		String prefixedName = "tinkergration." + name;
		block.setUnlocalizedName(prefixedName);
		itemBlock.setUnlocalizedName(prefixedName);

		register(block, name);
		register(itemBlock, name);
		return block;
	}

	protected static <T extends IForgeRegistryEntry<?>> T register(T thing, String name) {
		thing.setRegistryName(new ResourceLocation("tinkergration", name));
		GameRegistry.register(thing);
		return thing;
	}
}
