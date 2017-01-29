package com.tinkergration.immersiveengineering;

import java.util.ArrayList;
import java.util.List;

import com.tinkergration.IModule;
import com.tinkergration.TinkergrationMod;
import com.tinkergration.immersiveengineering.items.ItemToolEngineersHammer;

import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolPart;

public class ImmersiveEngineeringModule implements IModule{

	private static ItemToolEngineersHammer toolHammer;
	
	public static ToolPart toolHammerHead;

	@Override
	public void createMaterials() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createFluids() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRenderInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerModifiers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRecipes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerFluidRender() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerTools() {
		toolHammer = new ItemToolEngineersHammer();

		TinkergrationMod.registerItem(toolHammer, "toolhammer");
	}

	@Override
	public void registerToolParts() {
		toolHammerHead = new ToolPart(Material.VALUE_Ingot * 2);
		TinkergrationMod.registerItem(toolHammerHead, "toolhammer_head");
		TinkerRegistry.addPatternForItem(toolHammerHead);
		TinkerRegistry.addCastForItem(toolHammerHead);
	}

	@Override
	public void registerModels() {
		ModelRegisterUtil.registerPartModel(toolHammerHead);
		ModelRegisterUtil.registerToolModel(toolHammer);
	}

	@Override
	public void registerToolBuilding() {
		TinkerRegistry.registerToolCrafting(toolHammer);
	}

	@Override
	public void registerToolBuildInfo() {
		ToolBuildGuiInfo info;

	    info = new ToolBuildGuiInfo(toolHammer);
	    info.addSlotPosition(33 - 18, 42 + 18); // rod
	    info.addSlotPosition(33 + 20, 42 - 20); // pick head
	    info.addSlotPosition(33, 42); // binding
	    TinkerRegistryClient.addToolBuilding(info);
	}

	@Override
	public List<MaterialIntegration> getIntegrations() {
		return new ArrayList<MaterialIntegration>();
	}

	@Override
	public void registerMaterials() {
		// TODO Auto-generated method stub
		
	}

}
