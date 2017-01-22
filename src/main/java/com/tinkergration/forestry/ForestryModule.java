package com.tinkergration.forestry;

import java.util.ArrayList;
import java.util.List;

import com.tinkergration.IModule;
import com.tinkergration.TinkergrationMod;
import com.tinkergration.forestry.items.ItemToolGrafter;
import com.tinkergration.forestry.materials.GrafterHeadMaterialStats;

import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerMaterials;

public class ForestryModule implements IModule {
	
	public static enum MaterialTypes {
		GrafterHead("grafter_head");
		
		private final String name;
		private MaterialTypes(String name) {
			this.name = name;
		}
		
		public String GetName() {
			return name;
		}
	}
	
	public static ItemToolGrafter grafter;
	
	public static ToolPart grafterHead;

	@Override
	public void createMaterials() {
		
	}

	@Override
	public void createFluids() {
		
	}

	@Override
	public void registerRenderInfo() {
		
	}

	@Override
	public void registerModifiers() {
		
	}

	@Override
	public void registerRecipes() {
		
	}

	@Override
	public void registerFluidRender() {
		
	}

	@Override
	public void registerTools() {
		
		grafter = new ItemToolGrafter();
		TinkergrationMod.registerItem(grafter, "grafter");
	}

	@Override
	public void registerModels() {
		ModelRegisterUtil.registerPartModel(grafterHead);
		ModelRegisterUtil.registerToolModel(grafter);
	}

	@Override
	public void registerToolBuilding() {
		TinkerRegistry.registerToolCrafting(grafter);
	}

	@Override
	public void registerToolBuildInfo() {
		ToolBuildGuiInfo info;

	    info = new ToolBuildGuiInfo(grafter);
	    info.addSlotPosition(33 - 20 - 1, 42 + 20);
	    info.addSlotPosition(33 + 2 - 1, 42 - 6);
	    TinkerRegistryClient.addToolBuilding(info);
	}
	
	@Override
	public void registerToolParts() {
		grafterHead = new ToolPart(Material.VALUE_Ingot * 2);
		TinkergrationMod.registerItem(grafterHead, "grafter_head");
		TinkerRegistry.addPatternForItem(grafterHead);
		TinkerRegistry.addCastForItem(grafterHead);
	}

	@Override
	public List<MaterialIntegration> getIntegrations() {
		return new ArrayList<MaterialIntegration>();
	}

	@Override
	public void registerMaterials() {
		Material.UNKNOWN.addStats(new GrafterHeadMaterialStats(1, 1));
		
		TinkerRegistry.addMaterialStats(TinkerMaterials.bronze,
				new GrafterHeadMaterialStats(35, 2));
		
	}
}