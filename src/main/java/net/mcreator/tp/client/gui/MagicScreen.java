package net.mcreator.tp.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.tp.world.inventory.MagicMenu;
import net.mcreator.tp.procedures.SHOWProcedure;
import net.mcreator.tp.procedures.SHOW3Procedure;
import net.mcreator.tp.procedures.SHOW2Procedure;
import net.mcreator.tp.procedures.IfuProcedure;
import net.mcreator.tp.procedures.Ifu2Procedure;
import net.mcreator.tp.network.MagicButtonMessage;
import net.mcreator.tp.init.TpModScreens;
import net.mcreator.tp.TpMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class MagicScreen extends AbstractContainerScreen<MagicMenu> implements TpModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_heal_prayer_32_tp;
	private Button button_rude_buster_50_tp;
	private Button button_ice_shock_16_tp;
	private Button button_snowgrave_200_tp;
	private static final ResourceLocation BACKGROUND = new ResourceLocation("tp:textures/screens/magic.png");

	public MagicScreen(MagicMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 300;
		this.imageHeight = 200;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.tp.magic.label_magic"), 131, 6, -12829636, false);
		guiGraphics.drawString(this.font, IfuProcedure.execute(entity), 91, 29, -1, true);
		if (SHOW3Procedure.execute(entity))
			guiGraphics.drawString(this.font, Ifu2Procedure.execute(entity), 91, 110, -1, true);
	}

	@Override
	public void init() {
		super.init();
		button_heal_prayer_32_tp = Button.builder(Component.translatable("gui.tp.magic.button_heal_prayer_32_tp"), e -> {
			int x = MagicScreen.this.x;
			int y = MagicScreen.this.y;
			if (SHOWProcedure.execute(entity)) {
				TpMod.PACKET_HANDLER.sendToServer(new MagicButtonMessage(0, x, y, z));
				MagicButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 86, this.topPos + 78, 130, 20).build();
		this.addRenderableWidget(button_heal_prayer_32_tp);
		button_rude_buster_50_tp = Button.builder(Component.translatable("gui.tp.magic.button_rude_buster_50_tp"), e -> {
			int x = MagicScreen.this.x;
			int y = MagicScreen.this.y;
			if (SHOW2Procedure.execute(entity)) {
				TpMod.PACKET_HANDLER.sendToServer(new MagicButtonMessage(1, x, y, z));
				MagicButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 86, this.topPos + 51, 125, 20).build();
		this.addRenderableWidget(button_rude_buster_50_tp);
		button_ice_shock_16_tp = Button.builder(Component.translatable("gui.tp.magic.button_ice_shock_16_tp"), e -> {
			int x = MagicScreen.this.x;
			int y = MagicScreen.this.y;
			if (SHOW3Procedure.execute(entity)) {
				TpMod.PACKET_HANDLER.sendToServer(new MagicButtonMessage(2, x, y, z));
				MagicButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 86, this.topPos + 105, 115, 20).build();
		this.addRenderableWidget(button_ice_shock_16_tp);
		button_snowgrave_200_tp = Button.builder(Component.translatable("gui.tp.magic.button_snowgrave_200_tp"), e -> {
			int x = MagicScreen.this.x;
			int y = MagicScreen.this.y;
			if (true) {
				TpMod.PACKET_HANDLER.sendToServer(new MagicButtonMessage(3, x, y, z));
				MagicButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 86, this.topPos + 24, 120, 20).build();
		this.addRenderableWidget(button_snowgrave_200_tp);
	}

	@Override
	protected void containerTick() {
		super.containerTick();
		this.button_heal_prayer_32_tp.visible = SHOWProcedure.execute(entity);
		this.button_rude_buster_50_tp.visible = SHOW2Procedure.execute(entity);
		this.button_ice_shock_16_tp.visible = SHOW3Procedure.execute(entity);
	}
}