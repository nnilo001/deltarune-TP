package net.mcreator.tp.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.tp.network.TpModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TpgainProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
		execute(null, world, entity, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, double amount) {
		if (entity == null || sourceentity == null)
			return;
		if (sourceentity instanceof Player) {
			{
				sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.tens = sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens + 8;
					capability.markSyncDirty();
				});
			}
			if (sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).rudebusting == true) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MAGIC)), (float) (amount * 1.5));
				if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 2));
				entity.setTicksFrozen(140);
				{
					sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.rudebusting = false;
						capability.markSyncDirty();
					});
				}
			}
			if (sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).iceshcoking == true) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.FREEZE)), (float) (amount * 0.7));
				{
					sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.iceshcoking = false;
						capability.markSyncDirty();
					});
				}
			}
			if (sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).snewing == true) {
				entity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("tp:snowgrave")))), 2147483647);
				{
					sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.snewing = false;
						capability.markSyncDirty();
					});
				}
			}
			if (sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens > 100) {
				{
					sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.tens = 100;
						capability.markSyncDirty();
					});
				}
			} else if (sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens < 0) {
				{
					sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.tens = 0;
						capability.markSyncDirty();
					});
				}
			}
		}
	}
}