package net.mcreator.tp.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.registries.Registries;

import net.mcreator.tp.network.TpModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class TpgainProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingAttackEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getSource().getEntity(), event.getAmount());
		}
	}

	public static void execute(LevelAccessor world, Entity sourceentity, double amount) {
		execute(null, world, sourceentity, amount);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity sourceentity, double amount) {
		if (sourceentity == null)
			return;
		if (sourceentity instanceof Player) {
			{
				sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.tens = sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens + 8;
					capability.markSyncDirty();
				});
			}
			if (sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).rudebusting == true) {
				sourceentity.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), (float) (amount * 2));
				{
					sourceentity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
						capability.rudebusting = false;
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