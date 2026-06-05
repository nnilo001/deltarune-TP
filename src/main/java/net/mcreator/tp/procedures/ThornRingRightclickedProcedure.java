package net.mcreator.tp.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;

public class ThornRingRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack old = ItemStack.EMPTY;
		ItemStack newr = ItemStack.EMPTY;
		old = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
		if (entity instanceof LivingEntity _entity) {
			ItemStack _setstack1 = newr.copy();
			_setstack1.setCount(1);
			_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack1);
			ItemStack _setstack2 = old.copy();
			_setstack2.setCount(1);
			_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack2);
			if (_entity instanceof Player _player)
				_player.getInventory().setChanged();
		}
		newr = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
	}
}