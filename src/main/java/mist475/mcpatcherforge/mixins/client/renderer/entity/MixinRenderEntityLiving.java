package mist475.mcpatcherforge.mixins.client.renderer.entity;

import com.prupe.mcpatcher.cit.CITUtils;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RendererLivingEntity.class)
public abstract class MixinRenderEntityLiving extends Render {

    @Final
    @Shadow
    private static ResourceLocation RES_ITEM_GLINT;
    @Shadow
    protected ModelBase mainModel;
    @Shadow
    protected ModelBase renderPassModel;

    @Shadow
    protected abstract float interpolateRotation(float angle1, float angle2, float p_77034_3_);

    @Shadow
    protected abstract void renderModel(EntityLivingBase entityLivingBase, float p_77036_2_, float p_77036_3_,
        float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_);

    @Shadow
    protected abstract void renderLivingAt(EntityLivingBase entityLivingBase, double p_77039_2_, double p_77039_4_,
        double p_77039_6_);

    @Shadow
    protected abstract void rotateCorpse(EntityLivingBase entityLivingBase, float p_77043_2_, float p_77043_3_,
        float p_77043_4_);

    @Shadow
    protected abstract float renderSwingProgress(EntityLivingBase entityLivingBase, float p_77040_2_);

    @Shadow
    protected abstract float handleRotationFloat(EntityLivingBase entityLivingBase, float p_77044_2_);

    @Shadow
    protected abstract void renderEquippedItems(EntityLivingBase entityLivingBase, float p_77029_2_);

    @Shadow
    protected abstract int inheritRenderPass(EntityLivingBase entityLivingBase, int p_77035_2_, float p_77035_3_);

    @Shadow
    protected abstract int shouldRenderPass(EntityLivingBase entityLivingBase, int p_77032_2_, float p_77032_3_);

    @Shadow
    protected abstract void func_82408_c(EntityLivingBase entityLivingBase, int p_82408_2_, float p_82408_3_);

    @Shadow
    protected abstract int getColorMultiplier(EntityLivingBase entityLivingBase, float p_77030_2_, float p_77030_3_);

    @Shadow
    protected abstract void preRenderCallback(EntityLivingBase entityLivingBase, float p_77041_2_);

    @Shadow
    protected abstract void passSpecialRender(EntityLivingBase entityLivingBase, double p_77033_2_, double p_77033_4_,
        double p_77033_6_);

    /**
     * @author Mist475 (adapted from Paul Rupe)
     * @reason if statement modified into else-if
     */
    @Overwrite
    public void doRenderLiving(EntityLivingBase entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        if (MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Pre(entity, (RendererLivingEntity) (Object) this))) return;
        GL11.glPushMatrix();
        GL11.glDisable(2884);
        this.mainModel.onGround = this.renderSwingProgress(entity, p_76986_9_);
        if (this.renderPassModel != null) {
            this.renderPassModel.onGround = this.mainModel.onGround;
        }
        this.mainModel.isRiding = entity.isRiding();
        if (this.renderPassModel != null) {
            this.renderPassModel.isRiding = this.mainModel.isRiding;
        }
        this.mainModel.isChild = entity.isChild();
        if (this.renderPassModel != null) {
            this.renderPassModel.isChild = this.mainModel.isChild;
        }
        try {
            float f2 = this.interpolateRotation(entity.prevRenderYawOffset, entity.renderYawOffset, p_76986_9_);
            float f3 = this.interpolateRotation(entity.prevRotationYawHead, entity.rotationYawHead, p_76986_9_);
            float f4;
            if (entity.isRiding() && entity.ridingEntity instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase) entity.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, p_76986_9_);
                f4 = MathHelper.wrapAngleTo180_float(f3 - f2);
                if (f4 < -85.0F) {
                    f4 = -85.0F;
                }

                if (f4 >= 85.0F) {
                    f4 = 85.0F;
                }

                f2 = f3 - f4;
                if (f4 * f4 > 2500.0F) {
                    f2 += f4 * 0.2F;
                }
            }
            float f5 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * p_76986_9_;
            this.renderLivingAt(entity, x, y, z);
            f4 = this.handleRotationFloat(entity, p_76986_9_);
            this.rotateCorpse(entity, f4, f2, p_76986_9_);
            float f6 = 0.0625F;
            GL11.glEnable(32826);
            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback(entity, p_76986_9_);
            GL11.glTranslatef(0.0F, -24.0F * f6 - 0.0078125F, 0.0F);
            float f7 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * p_76986_9_;
            float f8 = entity.limbSwing - entity.limbSwingAmount * (1.0F - p_76986_9_);
            if (entity.isChild()) {
                f8 *= 3.0F;
            }
            if (f7 > 1.0F) {
                f7 = 1.0F;
            }
            GL11.glEnable(3008);
            this.mainModel.setLivingAnimations(entity, f8, f7, p_76986_9_);
            this.renderModel(entity, f8, f7, f4, f3 - f2, f5, f6);
            int i;
            float f9;
            float f10;
            float f11;
            int k;
            for (int j = 0; j < 4; ++j) {
                i = this.shouldRenderPass(entity, j, p_76986_9_);
                if (i > 0) {
                    this.renderPassModel.setLivingAnimations(entity, f8, f7, p_76986_9_);
                    this.renderPassModel.render(entity, f8, f7, f4, f3 - f2, f5, f6);
                    if ((i & 240) == 16) {
                        this.func_82408_c(entity, j, p_76986_9_);
                        this.renderPassModel.render(entity, f8, f7, f4, f3 - f2, f5, f6);
                    }
                    if (CITUtils.setupArmorEnchantments(entity, j)) {
                        while (CITUtils.preRenderArmorEnchantment()) {
                            this.renderPassModel.render(entity, f8, f7, f4, f3 - f2, f5, f6);
                            CITUtils.postRenderArmorEnchantment();
                        }
                    } else if ((i & 15) == 15) {
                        f9 = (float) entity.ticksExisted + p_76986_9_;
                        this.bindTexture(RES_ITEM_GLINT);
                        GL11.glEnable(3042);
                        f10 = 0.5F;
                        GL11.glColor4f(f10, f10, f10, 1.0F);
                        GL11.glDepthFunc(514);
                        GL11.glDepthMask(false);

                        for (k = 0; k < 2; ++k) {
                            GL11.glDisable(2896);
                            f11 = 0.76F;
                            GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
                            GL11.glBlendFunc(768, 1);
                            GL11.glMatrixMode(5890);
                            GL11.glLoadIdentity();
                            float f12 = f9 * (0.001F + (float) k * 0.003F) * 20.0F;
                            float f13 = 0.33333334F;
                            GL11.glScalef(f13, f13, f13);
                            GL11.glRotatef(30.0F - (float) k * 60.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glTranslatef(0.0F, f12, 0.0F);
                            GL11.glMatrixMode(5888);
                            this.renderPassModel.render(entity, f8, f7, f4, f3 - f2, f5, f6);
                        }
                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glMatrixMode(5890);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(5888);
                        GL11.glEnable(2896);
                        GL11.glDisable(3042);
                        GL11.glDepthFunc(515);
                    }
                    GL11.glDisable(3042);
                    GL11.glEnable(3008);
                }
            }
            GL11.glDepthMask(true);
            this.renderEquippedItems(entity, p_76986_9_);
            float f14 = entity.getBrightness(p_76986_9_);
            i = this.getColorMultiplier(entity, f14, p_76986_9_);
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glDisable(3553);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
            if ((i >> 24 & 255) > 0 || entity.hurtTime > 0 || entity.deathTime > 0) {
                GL11.glDisable(3553);
                GL11.glDisable(3008);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glDepthFunc(514);
                if (entity.hurtTime > 0 || entity.deathTime > 0) {
                    GL11.glColor4f(f14, 0.0F, 0.0F, 0.4F);
                    this.mainModel.render(entity, f8, f7, f4, f3 - f2, f5, f6);
                    for (k = 0; k < 4; ++k) {
                        if (this.inheritRenderPass(entity, k, p_76986_9_) >= 0) {
                            GL11.glColor4f(f14, 0.0F, 0.0F, 0.4F);
                            this.renderPassModel.render(entity, f8, f7, f4, f3 - f2, f5, f6);
                        }
                    }
                }
                if ((i >> 24 & 255) > 0) {
                    f9 = (float) (i >> 16 & 255) / 255.0F;
                    f10 = (float) (i >> 8 & 255) / 255.0F;
                    float f15 = (float) (i & 255) / 255.0F;
                    f11 = (float) (i >> 24 & 255) / 255.0F;
                    GL11.glColor4f(f9, f10, f15, f11);
                    this.mainModel.render(entity, f8, f7, f4, f3 - f2, f5, f6);
                    for (int i1 = 0; i1 < 4; ++i1) {
                        if (this.inheritRenderPass(entity, i1, p_76986_9_) >= 0) {
                            GL11.glColor4f(f9, f10, f15, f11);
                            this.renderPassModel.render(entity, f8, f7, f4, f3 - f2, f5, f6);
                        }
                    }
                }
                GL11.glDepthFunc(515);
                GL11.glDisable(3042);
                GL11.glEnable(3008);
                GL11.glEnable(3553);
            }
            GL11.glDisable(32826);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(2884);
        GL11.glPopMatrix();
        this.passSpecialRender(entity, x, y, z);
        MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(entity, (RendererLivingEntity) (Object) this));
    }
}
