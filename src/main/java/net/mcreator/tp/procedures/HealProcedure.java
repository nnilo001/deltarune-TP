package net.mcreator.tp.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import net.mcreator.tp.network.TpModVariables;
import net.mcreator.tp.init.TpModParticleTypes;

public class HealProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens >= 32) {
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + 6);
			{
				entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.tens = entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens - 32;
					capability.markSyncDirty();
				});
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (TpModParticleTypes.HEALPRAYER.get()), x, y, z, 10, 0.5, 0, 0.5, 1);
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}