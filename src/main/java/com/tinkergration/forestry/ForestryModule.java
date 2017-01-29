package com.tinkergration.forestry;

import java.util.ArrayList;
import java.util.List;

import com.tinkergration.IModule;
import com.tinkergration.TinkergrationMod;
import com.tinkergration.forestry.items.ItemToolGrafter;
import com.tinkergration.forestry.items.ItemToolScoop;
import com.tinkergration.forestry.materials.GrafterHeadMaterialStats;
import com.tinkergration.forestry.materials.ScoopHeadMaterialStats;

import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerTools;

public class ForestryModule implements IModule {
	
	public static enum MaterialTypes {
		GrafterHead("grafter_head"),
		ScoopHead("scoop_head");
		
		private final String name;
		private MaterialTypes(String name) {
			this.name = name;
		}
		
		public String GetName() {
			return name;
		}
	}
	
	public static ItemToolGrafter grafter;
	public static ItemToolScoop scoop;
	
	public static ToolPart grafterHead;
	public static ToolPart scoopHead;

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
		
		scoop = new ItemToolScoop();
		TinkergrationMod.registerItem(scoop, "scoop");
	}

	@Override
	public void registerModels() {
		ModelRegisterUtil.registerPartModel(grafterHead);
		ModelRegisterUtil.registerToolModel(grafter);
		
		ModelRegisterUtil.registerPartModel(scoopHead);
		ModelRegisterUtil.registerToolModel(scoop);
	}

	@Override
	public void registerToolBuilding() {
		TinkerRegistry.registerToolCrafting(grafter);
		TinkerRegistry.registerToolCrafting(scoop);
	}

	@Override
	public void registerToolBuildInfo() {
		ToolBuildGuiInfo info;

	    info = new ToolBuildGuiInfo(grafter);
	    info.addSlotPosition(33 - 20 - 1, 42 + 20);
	    info.addSlotPosition(33 + 2 - 1, 42 - 6);
	    TinkerRegistryClient.addToolBuilding(info);
	    
	    info = new ToolBuildGuiInfo(scoop);
	    info.addSlotPosition(33 - 18, 42 + 18); // rod
	    info.addSlotPosition(33 + 20, 42 - 20); // pick head
	    info.addSlotPosition(33, 42); // binding
	    TinkerRegistryClient.addToolBuilding(info);
	}
	
	@Override
	public void registerToolParts() {
		grafterHead = new ToolPart(Material.VALUE_Ingot * 2);
		TinkergrationMod.registerItem(grafterHead, "grafter_head");
		TinkerRegistry.addPatternForItem(grafterHead);
		TinkerRegistry.addCastForItem(grafterHead);
		
		scoopHead = new ToolPart(Material.VALUE_Ingot * 2);
		TinkergrationMod.registerItem(scoopHead, "scoop_head");
		TinkerRegistry.addPatternForItem(scoopHead);
		
		TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), grafterHead));
		TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), scoopHead));
	}

	@Override
	public List<MaterialIntegration> getIntegrations() {
		return new ArrayList<MaterialIntegration>();
	}

	@Override
	public void registerMaterials() {
		Material.UNKNOWN.addStats(new GrafterHeadMaterialStats(1, 1));
		Material.UNKNOWN.addStats(new ScoopHeadMaterialStats(1, 1));
		
		TinkerRegistry.addMaterialStats(TinkerMaterials.bronze,
				new GrafterHeadMaterialStats(35, 2));
		
		TinkerRegistry.addMaterialStats(TinkerMaterials.string, new ScoopHeadMaterialStats(25, 3));
	    TinkerRegistry.addMaterialStats(TinkerMaterials.vine, new ScoopHeadMaterialStats(40, 4));
	    TinkerRegistry.addMaterialStats(TinkerMaterials.slimevine_blue, new ScoopHeadMaterialStats(50, 5));
	    TinkerRegistry.addMaterialStats(TinkerMaterials.slimevine_purple, new ScoopHeadMaterialStats(50, 6));
		
	}
}