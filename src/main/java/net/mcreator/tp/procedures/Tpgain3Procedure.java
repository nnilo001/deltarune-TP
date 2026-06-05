package net.mcreator.tp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.tp.network.TpModVariables;

public class Tpgain3Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
				capability.tens = 100;
				capability.markSyncDirty();
			});
		}
	}
}