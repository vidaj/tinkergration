package com.tinkergration.botania;

import java.util.ArrayList;
import java.util.List;

import com.tinkergration.IModule;
import com.tinkergration.botania.fluids.BotaniaFluids;
import com.tinkergration.botania.materials.BotaniaMaterials;
import com.tinkergration.botania.modifiers.BotaniaModifiers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.MaterialIntegration;

public class BotaniaModule implements IModule {

	public final BotaniaFluids fluids = new BotaniaFluids();
	
	public final BotaniaMaterials materials = new BotaniaMaterials();
	
	public final BotaniaModifiers modifiers = new BotaniaModifiers();
	
	private List<MaterialIntegration> integrations;
	
	@Override
	public void createMaterials() {
		if (!OreDictionary.doesOreNameExist("blockManasteel")) {
			OreDictionary.registerOre("blockManasteel", new ItemStack(vazkii.botania.common.block.ModBlocks.storage, 1, 0));
		}
		if (!OreDictionary.doesOreNameExist("blockElvenElementium")) {
			OreDictionary.registerOre("blockElvenElementium", new ItemStack(vazkii.botania.common.block.ModBlocks.storage, 1, 2));
		}
		
		materials.registerMaterials();
	}

	@Override
	public void createFluids() {
		fluids.registerFluids();
	}

	@Override
	public void registerRenderInfo() {
		materials.registerRenderInfo();
	}

	@Override
	public void registerModifiers() {
		modifiers.registerModifiers();
	}

	@Override
	public void registerRecipes() {
		
	}
	
	@Override
	public List<MaterialIntegration> getIntegrations() {
		if (integrations == null) {
			integrations = new ArrayList<MaterialIntegration>();
			
			integrations.add(new MaterialIntegration(materials.manasteel, fluids.manasteel, "Manasteel"));
			integrations.add(new MaterialIntegration(materials.elementium, fluids.elementium, "Elementium"));
		}
		
		return integrations;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerFluidRender() {
		fluids.registerFluidRenderer();	
	}

	@Override
	public void registerTools() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerToolParts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerToolBuilding() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerToolBuildInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerMaterials() {
		// TODO Auto-generated method stub
		
	}

}
