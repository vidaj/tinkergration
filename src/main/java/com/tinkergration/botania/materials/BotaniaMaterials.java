package com.tinkergration.botania.materials;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BotaniaMaterials {
	public MaterialManasteel manasteel;
	
	public MaterialElementium elementium;
	
	public void registerMaterials() {
		manasteel = new MaterialManasteel();
		manasteel.registerStats();
		
		elementium = new MaterialElementium();
		elementium.registerStats();
	}
	
	@SideOnly(Side.CLIENT)
	public void registerRenderInfo() {
		manasteel.setRenderInfo();
		elementium.setRenderInfo();
	}
}
