package com.tinkergration.integration;

import team.chisel.api.carving.CarvingUtils;
import team.chisel.api.carving.ICarvingGroup;
import team.chisel.api.carving.ICarvingRegistry;

public class ChiselIntegrations {

	public void integrate() {
		ICarvingRegistry chisel = CarvingUtils.getChiselRegistry();
		if (chisel == null) {
			return;
		}
	}
	
	private ICarvingGroup getGroup(ICarvingRegistry chisel, String name) {
		ICarvingGroup group = CarvingUtils.getDefaultGroupFor(name);
		chisel.addGroup(group);
		return group;
	}
}
