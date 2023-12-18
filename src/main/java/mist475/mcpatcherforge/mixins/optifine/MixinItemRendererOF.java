package mist475.mcpatcherforge.mixins.optifine;

import com.prupe.mcpatcher.cit.CITUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("all")
@Pseudo
@Mixin(targets = {"ItemRendererOF", "net.minecraft.src.ItemRendererOF", "net.optifine.ItemRendererOF"})
public class MixinItemRendererOF {

    @Redirect(
        method = "renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/EntityLivingBase;getItemIcon(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/util/IIcon;"
        ),
        remap = false)
    private IIcon modifyRenderItem(EntityLivingBase instance, ItemStack item, int renderPass, EntityLivingBase entity, ItemStack item2, int renderPass1) {
        return CITUtils.getIcon(entity.getItemIcon(item2, renderPass1), item2, renderPass1);
    }

    @Inject(
        method = "renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDepthFunc(I)V"))
    private void modifyRenderItemGlint(EntityLivingBase entity, ItemStack item,  int renderPass, CallbackInfo ci) {
        if (!CITUtils.renderEnchantmentHeld(item, renderPass) && item.hasEffect(renderPass)) {

        }
    }
}
