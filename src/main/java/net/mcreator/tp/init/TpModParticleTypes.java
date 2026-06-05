/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.tp.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.tp.TpMod;

public class TpModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TpMod.MODID);
	public static final RegistryObject<SimpleParticleType> HEALPRAYER = REGISTRY.register("healprayer", () -> new SimpleParticleType(true));
}