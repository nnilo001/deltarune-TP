package net.mcreator.tp.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import net.mcreator.tp.procedures.GettpProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class TpOverlay {
	private static final ResourceLocation IMAGE_0 = new ResourceLocation("tp:textures/screens/pc_computer-deltarune-userinterface-battlemenu-ezgif2.png");
	private static final ResourceLocation IMAGE_1 = new ResourceLocation("tp:textures/screens/john.png");

	// Client-side tracking variable for smooth animation interpolation
	private static double currentRenderTP = 0.0;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level();
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		
		if (true) {
			// 1. Fetch your target TP numbers from your active text procedure
			double targetTP = 0;
			if (entity != null) {
				try {
					String rawPoints = GettpProcedure.execute(entity);
					if (rawPoints != null && !rawPoints.isEmpty()) {
						rawPoints = rawPoints.replace("%", "").trim();
						targetTP = Double.parseDouble(rawPoints);
					}
				} catch (Exception e) {
					targetTP = 0;
				}
			}

			// 2. INTERPOLATION ENGINE: Slide currentRenderTP towards targetTP continuously
			// Lower numbers (like 0.05) make it slower/smoother. Higher numbers (like 0.2) make it faster.
			double animationSpeed = 0.12; 
			if (Math.abs(currentRenderTP - targetTP) > 0.05) {
				currentRenderTP += (targetTP - currentRenderTP) * animationSpeed;
			} else {
				currentRenderTP = targetTP; // Snap completely if it gets microscopically close
			}

			// 3. Calculate dynamic height scaling using our smooth variable (Max is 196 pixels)
			int maxHeight = 196;
			int fillHeight = (int) ((currentRenderTP / 100.0) * maxHeight);

			// 4. Draw your 1x1 orange pixel FIRST (Stays inside borders)
			if (fillHeight > 0) {
				int barX = w / 2 + -211;
				int barY = (h / 2 + -97) + (maxHeight - fillHeight);
				event.getGuiGraphics().blit(IMAGE_1, barX, barY, 0, 0, 25, fillHeight, 1, 1);
			}

			// 5. Draw the frame border SECOND (Sits nicely over the bar fill)
			event.getGuiGraphics().blit(IMAGE_0, w / 2 + -211, h / 2 + -97, 0, 0, 25, 196, 25, 196);

			// 6. Handle dynamic text display string logic (Using smooth calculation tracking for color shifts)
			String tpString;
			if (targetTP >= 100.0) {
				tpString = "MAX"; 
			} else {
				tpString = GettpProcedure.execute(entity) + "%"; 
			}

			// 7. Draw vertical text column character by character with font scaling
			if (tpString != null && !tpString.isEmpty()) {
				float scaleFactor = 3.0f; 
				
				int targetX = (w / 2 + -211) + 32; 
				int targetY = h / 2 + -97; 
				int spacingY = 25; 

				event.getGuiGraphics().pose().pushPose();
				event.getGuiGraphics().pose().scale(scaleFactor, scaleFactor, 1.0f);

				for (int i = 0; i < tpString.length(); i++) {
					String character = String.valueOf(tpString.charAt(i));
					int textColor = (targetTP >= 100.0) ? 0xFFFF9900 : -1;

					float renderX = targetX / scaleFactor;
					float renderY = (targetY + (i * spacingY)) / scaleFactor;

					event.getGuiGraphics().drawString(Minecraft.getInstance().font, character, (int) renderX, (int) renderY, textColor, false);
				}

				event.getGuiGraphics().pose().popPose();
			}
		}
		
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
