package net.mcreator.tp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.tp.network.TpModVariables;

public class Tpgain2Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.tens = entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens + 32;
				capability.markSyncDirty();
			});
		}
		if (entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens > 100) {
			{
				entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.tens = 100;
					capability.markSyncDirty();
				});
			}
		} else if (entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens < 0) {
			{
				entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.tens = 0;
					capability.markSyncDirty();
				});
			}
		}
	}
}