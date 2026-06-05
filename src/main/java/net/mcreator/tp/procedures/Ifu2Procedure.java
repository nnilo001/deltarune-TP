package net.mcreator.tp.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import net.mcreator.tp.init.TpModItems;

public class Ifu2Procedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() == TpModItems.THORN_RING.get()) {
			return "Ice Shock | 8% TP";
		} else {
			return "Ice Shock | 16% TP";
		}
	}
}