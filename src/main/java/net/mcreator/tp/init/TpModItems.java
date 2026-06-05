/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.tp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.tp.item.ThornRingItem;
import net.mcreator.tp.item.TensionMaxItem;
import net.mcreator.tp.item.TensionBitItem;
import net.mcreator.tp.TpMod;

public class TpModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TpMod.MODID);
	public static final RegistryObject<Item> TENSION_BIT;
	public static final RegistryObject<Item> TENSION_MAX;
	public static final RegistryObject<Item> THORN_RING;
	static {
		TENSION_BIT = REGISTRY.register("tension_bit", TensionBitItem::new);
		TENSION_MAX = REGISTRY.register("tension_max", TensionMaxItem::new);
		THORN_RING = REGISTRY.register("thorn_ring", ThornRingItem::new);
	}
	// Start of user code block custom items
	// End of user code block custom items
}