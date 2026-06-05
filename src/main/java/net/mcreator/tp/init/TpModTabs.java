/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.tp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.tp.TpMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TpModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TpMod.MODID);
	public static final RegistryObject<CreativeModeTab> DELTARUNE = REGISTRY.register("deltarune",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.tp.deltarune")).icon(() -> new ItemStack(TpModItems.TENSION_BIT.get())).displayItems((parameters, tabData) -> {
				tabData.accept(TpModItems.TENSION_BIT.get());
				tabData.accept(TpModItems.TENSION_MAX.get());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			tabData.accept(TpModItems.TENSION_BIT.get());
			tabData.accept(TpModItems.TENSION_MAX.get());
		}
	}
}