package com.tinkergration.forestry.materials;

import java.util.List;

import com.google.common.collect.Lists;
import com.tinkergration.forestry.ForestryModule;

import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

public class ScoopHeadMaterialStats extends AbstractMaterialStats {

//	  public final static String LOC_Durability = "stat.head.durability.name";
//
//	  public final static String LOC_DurabilityDesc = "stat.head.durability.desc";
//
//	  public final static String COLOR_Durability = HeadMaterialStats.COLOR_Durability;

	  public final int durability; // usually between 1 and 1000
	  
	  public final float miningSpeed; // usually between 1 and 10

	  public ScoopHeadMaterialStats(int durability, float miningSpeed) {
	    super(ForestryModule.MaterialTypes.ScoopHead.GetName());
	    this.durability = durability;
	    this.miningSpeed = miningSpeed;
	  }

	  @Override
	  public List<String> getLocalizedInfo() {
		  List<String> info = Lists.newArrayList();

	      info.add(HeadMaterialStats.formatDurability(durability));
		  info.add(HeadMaterialStats.formatMiningSpeed(miningSpeed));

		  return info;
	  }

	  @Override
	  public List<String> getLocalizedDesc() {
		  List<String> info = Lists.newArrayList();

		    info.add(Util.translate(HeadMaterialStats.LOC_DurabilityDesc));
		    info.add(Util.translate(HeadMaterialStats.LOC_MiningSpeedDesc));

		    return info;
	  }
	}