package mist475.mcpatcherforge.mixins.early.renderpass;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.EntityLivingBase;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.prupe.mcpatcher.renderpass.RenderPass;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Shadow
    private Minecraft mc;

    @Shadow
    protected abstract void renderRainSnow(float p_78474_1_);

    @WrapWithCondition(
        method = "renderWorld(FJ)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glShadeModel(I)V", remap = false, ordinal = 0))
    private boolean modifyRenderWorld1(int i) {
        return RenderPass.setAmbientOcclusion(this.mc.gameSettings.ambientOcclusion != 0);
    }

    @WrapWithCondition(
        method = "renderWorld(FJ)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glShadeModel(I)V", remap = false, ordinal = 2))
    private boolean modifyRenderWorld2(int i) {
        return RenderPass.setAmbientOcclusion(this.mc.gameSettings.ambientOcclusion != 0);
    }

    @Redirect(
        method = "renderWorld(FJ)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/RenderGlobal;sortAndRender(Lnet/minecraft/entity/EntityLivingBase;ID)I",
            ordinal = 0))
    private int modifyRenderWorld3(RenderGlobal instance, EntityLivingBase entitylivingbase, int k, double i1) {
        int returnValue = instance.sortAndRender(entitylivingbase, k, i1);
        instance.sortAndRender(entitylivingbase, 4, i1);
        return returnValue;
    }

    @Inject(
        method = "renderWorld(FJ)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V", ordinal = 3, remap = false))
    private void modifyRenderWorld4(float partialTickTime, long p_78471_2_, CallbackInfo ci) {
        this.mc.renderGlobal.sortAndRender(this.mc.renderViewEntity, 5, partialTickTime);
        this.renderRainSnow(partialTickTime);
    }
}
