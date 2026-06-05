package net.mcreator.tp.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.tp.network.TpModVariables;
import net.mcreator.tp.init.TpModItems;

public class IceProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
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
		if (entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens >= entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).ok) {
			{
				entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.tens = entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens - entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).ok;
					capability.iceshcoking = true;
					capability.markSyncDirty();
				});
			}
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}