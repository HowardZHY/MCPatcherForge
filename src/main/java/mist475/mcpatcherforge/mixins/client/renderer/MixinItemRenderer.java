package mist475.mcpatcherforge.mixins.client.renderer;

import com.prupe.mcpatcher.cit.CITUtils;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Redirect(
        method = "renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/EntityLivingBase;getItemIcon(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/util/IIcon;"))
    private IIcon modifyRenderItem(EntityLivingBase instance, ItemStack item, int renderPass, EntityLivingBase entity,
                                   ItemStack item2, int renderPass1, IItemRenderer.ItemRenderType type) {
        return CITUtils.getIcon(entity.getItemIcon(item2, renderPass1), item2, renderPass1);
    }

    @Redirect(
        method = "renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasEffect(I)Z"),
        remap = false)
    private boolean modifyRenderItemGlint(ItemStack item, int pass, EntityLivingBase entity, ItemStack itemStack,
        int renderPass, IItemRenderer.ItemRenderType type) {
        return !CITUtils.renderEnchantmentHeld(item, renderPass) && item.hasEffect(pass);
    }
}
