package net.mcreator.tp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.tp.network.TpModVariables;

public class SHOW2Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens >= 50;
	}
}