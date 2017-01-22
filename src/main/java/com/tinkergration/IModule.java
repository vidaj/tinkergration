package com.tinkergration;

import java.util.List;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.MaterialIntegration;

public interface IModule {

	public void createMaterials();

	public void createFluids();

	public void registerRenderInfo();

	public void registerModifiers();

	public void registerRecipes();

	public void registerFluidRender();

	public void registerTools();

	public void registerToolParts();

	public void registerModels();

	public void registerToolBuilding();

	@SideOnly(Side.CLIENT)
	public void registerToolBuildInfo();

	public List<MaterialIntegration> getIntegrations();

	public void registerMaterials();
}
