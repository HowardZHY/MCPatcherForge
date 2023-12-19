package mist475.mcpatcherforge.mixins.optifine;

import com.prupe.mcpatcher.cit.CITUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("all")
@Pseudo
@Mixin(targets = {"ItemRendererOF", "net.minecraft.src.ItemRendererOF", "net.optifine.ItemRendererOF"})
public class MixinItemRendererOF {

    @Redirect(
        method = "func_78443_a(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/EntityLivingBase;func_70620_b(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/util/IIcon;"
        )
    )
    private IIcon modifyRenderItem(EntityLivingBase elb, ItemStack item, int renderPass, EntityLivingBase entity, ItemStack item2, int renderPass1) {
        return CITUtils.getIcon(entity.getItemIcon(item2, renderPass1), item2, renderPass1);
    }

    @Redirect(
        method = "renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/EntityLivingBase;getItemIcon(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/util/IIcon;" //func_70620_b
        )
    )
    private IIcon modifyRenderItemDev(EntityLivingBase elb, ItemStack item, int renderPass, EntityLivingBase entity, ItemStack item2, int renderPass1) {
        return CITUtils.getIcon(entity.getItemIcon(item2, renderPass1), item2, renderPass1);
    }

    @Redirect(
        method = "func_78443_a(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;func_77962_s()Z")
    )
    private boolean modifyRenderItemGlint(ItemStack item, EntityLivingBase entity, ItemStack itemStack, int renderPass) {
        return !CITUtils.renderEnchantmentHeld(item, renderPass) && item.hasEffect(renderPass);
    }

    @Redirect(
        method = "renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasEffect()Z") //func_77962_s
    )
    private boolean modifyRenderItemGlintDev(ItemStack item, EntityLivingBase entity, ItemStack itemStack, int renderPass) {
        return !CITUtils.renderEnchantmentHeld(item, renderPass) && item.hasEffect(renderPass);
    }
    
}
