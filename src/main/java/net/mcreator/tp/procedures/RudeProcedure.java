package net.mcreator.tp.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.tp.network.TpModVariables;

public class RudeProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens >= 50) {
			{
				entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.tens = entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens - 50;
					capability.rudebusting = true;
					capability.markSyncDirty();
				});
			}
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}