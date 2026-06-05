package net.mcreator.tp.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.tp.network.TpModVariables;
import net.mcreator.tp.init.TpModItems;

public class IcerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.getCapability(TpModVariables.PLAYER_VARIABLES).orElseGet(TpModVariables.PlayerVariables::new).tens == 100
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TpModItems.THORN_RING.get()) {
			{
				entity.getCapability(TpModVariables.PLAYER_VARIABLES).ifPresent(capability -> {
					capability.tens = 0;
					capability.snewing = true;
					capability.markSyncDirty();
				});
			}
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}