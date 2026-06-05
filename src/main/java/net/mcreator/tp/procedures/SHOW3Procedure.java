package net.mcreator.tp.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.tp.network.TpModVariables;
import net.mcreator.tp.init.TpModItems;

public class SHOW3Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TpModItems.THORN_RING.get()) {
			{
				entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.ok = 8;
					capability.markSyncDirty();
				});
			}
		} else {
			{
				entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.ok = 16;
					capability.markSyncDirty();
				});
			}
		}
		return entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens >= entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).ok;
	}
}