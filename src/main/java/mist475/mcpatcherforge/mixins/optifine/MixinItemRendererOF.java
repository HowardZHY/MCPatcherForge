package mist475.mcpatcherforge.mixins.optifine;

import com.prupe.mcpatcher.cit.CITUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("all")
@Pseudo
@Mixin(targets = {"ItemRendererOF", "net.minecraft.src.ItemRendererOF", "net.optifine.ItemRendererOF"}, remap = false)
public class MixinItemRendererOF extends ItemRenderer {

    public MixinItemRendererOF(Minecraft p_i1247_1_) {
        super(p_i1247_1_);
    }

    @Redirect(
        method = "func_78443_a", //func_78443_a
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/EntityLivingBase;func_70620_b(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/util/IIcon;"
        )
    )
    private IIcon modifyRenderItem(EntityLivingBase elb, ItemStack item, int renderPass, EntityLivingBase entity, ItemStack item2, int renderPass1) {
        return CITUtils.getIcon(entity.getItemIcon(item2, renderPass1), item2, renderPass1);
    }

    @Redirect(
        method = "func_78443_a",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;func_77962_s()Z")
    )
    private boolean modifyRenderItemGlint(ItemStack item, EntityLivingBase entity, ItemStack itemStack, int renderPass) {
        return !CITUtils.renderEnchantmentHeld(item, renderPass) && item.hasEffect(renderPass);
    }
}
