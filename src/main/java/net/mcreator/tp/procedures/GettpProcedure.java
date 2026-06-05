package net.mcreator.tp.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.tp.network.TpModVariables;

public class GettpProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return new java.text.DecimalFormat("##").format(entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens);
	}
}