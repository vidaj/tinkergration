package com.tinkergration.botania.fluids;

import java.util.ArrayList;
import java.util.List;

import com.tinkergration.TinkergrationColors;
import com.tinkergration.TinkergrationMod.CustomFluidStateMapper;
import com.tinkergration.fluids.TinkergrationFluids;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.fluid.FluidMolten;

public class BotaniaFluids {

	public FluidMolten manasteel;
	
	public FluidMolten elementium;
	
	public void registerFluids() {
		manasteel = TinkergrationFluids.createFluidMetal("Manasteel", TinkergrationColors.manasteel);
		elementium = TinkergrationFluids.createFluidMetal("Elementium", TinkergrationColors.elementium);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerFluidRenderer() {
		List<FluidMolten> fluids = new ArrayList<FluidMolten>();
		fluids.add(manasteel);
		fluids.add(elementium);
		
		for (FluidMolten fluid : fluids) {
			Block block = fluid.getBlock();
	        if(block != null) {
	          Item item = Item.getItemFromBlock(block);
	          CustomFluidStateMapper mapper = new CustomFluidStateMapper(fluid);

	          // item-model
	          if(item != null) {
	            ModelLoader.registerItemVariants(item);
	            ModelLoader.setCustomMeshDefinition(item, mapper);
	          }
	          // block-model
	          ModelLoader.setCustomStateMapper(block, mapper);
	        }	
		}
	}
}
