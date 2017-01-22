package com.tinkergration.forestry.materials;


import java.util.List;

import com.google.common.collect.Lists;
import com.tinkergration.forestry.ForestryModule;

import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.client.CustomFontColor;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

public class GrafterHeadMaterialStats extends AbstractMaterialStats {

	public final static String LOC_Durability = "stat.head.durability.name";
	public final static String LOC_DurabilityDesc = "stat.head.durability.desc";

	public final static String LOC_Speed = "stat.grafter_head.speed.name";
	public final static String LOC_SpeedDesc = "stat.grafter_head.speed.desc";

	public final static String COLOR_Durability = CustomFontColor.valueToColorCode(1f);
	public final static String COLOR_Speed = CustomFontColor.encodeColor(120, 160, 205);

	public final int durability;

	public final float speed;

	public GrafterHeadMaterialStats(int durability, float speed) {
		super(ForestryModule.MaterialTypes.GrafterHead.GetName());

		this.durability = durability;
		this.speed = speed;
	}

	@Override
	public List<String> getLocalizedInfo() {
		List<String> info = Lists.newArrayList();

		info.add(formatDurability(durability));

	    info.add(formatMiningSpeed(speed));

		return info;
	}

	@Override
	public List<String> getLocalizedDesc() {
		List<String> info = Lists.newArrayList();

		info.add(Util.translate(LOC_DurabilityDesc));
	    info.add(Util.translate(LOC_SpeedDesc));

		return info;
	}

	public static String formatDurability(int durability) {
		return formatNumber(LOC_Durability, COLOR_Durability, durability);
	}
	
	 public static String formatMiningSpeed(float speed) {
	    return formatNumber(LOC_Speed, COLOR_Speed, speed);
	  }

}
