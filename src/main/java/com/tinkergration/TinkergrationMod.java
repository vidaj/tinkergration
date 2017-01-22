package com.tinkergration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;

import com.tinkergration.botania.BotaniaModule;
import com.tinkergration.forestry.ForestryModule;
import com.tinkergration.integration.ChiselIntegrations;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import slimeknights.tconstruct.library.MaterialIntegration;

@Mod(modid = Constants.ModId, name = "tinkergration", version = Constants.Version, dependencies = Constants.Dependencies)
public class TinkergrationMod {

	@SidedProxy
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static class CommonProxy {

		protected List<IModule> modules = new ArrayList<IModule>();

		public CommonProxy() {
		}

		public void preInit(FMLPreInitializationEvent e) {
			if (Loader.isModLoaded("forestry")) {
				modules.add(new ForestryModule());
			}
			if (Loader.isModLoaded("Botania")) {
				modules.add(new BotaniaModule());
			}

			for (IModule module : modules) {
				module.createFluids();
				module.createMaterials();
				module.registerModifiers();
				module.registerToolParts();
				module.registerTools();
				module.registerModels();

				List<MaterialIntegration> integrations = module.getIntegrations();
				for (MaterialIntegration integration : integrations) {
					integration.integrate();
				}
			}
		}

		public void init(FMLInitializationEvent e) {
			for (IModule module : modules) {
				module.registerRecipes();
				List<MaterialIntegration> integrations = module.getIntegrations();
				for (MaterialIntegration integration : integrations) {
					integration.integrateRecipes();
				}
				module.registerMaterials();
				module.registerToolBuilding();
			}
		}

		public void postInit(FMLPostInitializationEvent e) {
			if (Loader.isModLoaded("chisel")) {
				new ChiselIntegrations().integrate();
			}

			for (IModule module : modules) {
				List<MaterialIntegration> integrations = module.getIntegrations();
				for (MaterialIntegration integration : integrations) {
					integration.registerRepresentativeItem();
				}
			}
		}
	}

	public static class ClientProxy extends CommonProxy {

		public ClientProxy() {
		}

		@Override
		public void preInit(FMLPreInitializationEvent e) {
			super.preInit(e);

			for (IModule module : modules) {
				module.registerFluidRender();
			}
		}

		@Override
		public void init(FMLInitializationEvent e) {
			super.init(e);

			for (IModule module : modules) {
				module.registerToolBuildInfo();
			}
		}

		@Override
		public void postInit(FMLPostInitializationEvent e) {
			for (IModule module : modules) {
				module.registerRenderInfo();
			}
		}
	}

	public static class ServerProxy extends CommonProxy {

		public ServerProxy() {

		}
	}

	public static class CustomFluidStateMapper extends StateMapperBase implements ItemMeshDefinition {

		public final Fluid fluid;
		public final ModelResourceLocation location;

		public CustomFluidStateMapper(Fluid fluid) {
			this.fluid = fluid;

			// have each block hold its fluid per nbt? hm
			this.location = new ModelResourceLocation(new ResourceLocation("tinkergration", "fluid_block"),
					fluid.getName());
		}

		@Nonnull
		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			return location;
		}

		@Nonnull
		@Override
		public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
			return location;
		}
	}

	/**
	 * Sets the correct unlocalized name and registers the item.
	 */
	public static <T extends Item> T registerItem(T item, String name) {
		if (!name.equals(name.toLowerCase(Locale.US))) {
			throw new IllegalArgumentException(
					String.format("Unlocalized names need to be all lowercase! Item: %s", name));
		}

		item.setUnlocalizedName(String.format("%s.%s", Constants.ModId, name.toLowerCase(Locale.US)));
		item.setRegistryName(new ResourceLocation(Constants.ModId, name));
		GameRegistry.register(item);
		return item;
	}
}