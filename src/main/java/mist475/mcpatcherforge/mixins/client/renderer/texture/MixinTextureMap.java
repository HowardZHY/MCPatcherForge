package mist475.mcpatcherforge.mixins.client.renderer.texture;

import com.prupe.mcpatcher.mal.tile.TileLoader;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.ResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(TextureMap.class)
public abstract class MixinTextureMap extends AbstractTexture {

    @Shadow
    @Final
    private Map<String, TextureAtlasSprite> mapRegisteredSprites;
    @Shadow
    @Final
    public String basePath;

    @Shadow
    protected abstract void registerIcons();

    @Inject(
        method = "loadTextureAtlas",
        at = @At(value = "INVOKE", target = "Ljava/util/List;clear()V", remap = false, shift = At.Shift.AFTER))
    private void modifyLoadTextureAtlas(ResourceManager manager, CallbackInfo ci) {
        this.registerIcons();
        TileLoader.registerIcons((TextureMap) (Object) this, this.basePath, this.mapRegisteredSprites);
    }

    @Redirect(
        method = "loadTextureAtlas",
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;",
            ordinal = 0,
            remap = false))
    private String modifyCompleteResourceLocation(String format, Object[] args) {
        return TileLoader.getOverridePath("", this.basePath, (String) args[1], ".png");
    }

    // Base game has s.indexOf(47) != -1 || s.indexOf(92) != -1
    // However, forge already removes this, so we don't have to patch that
    @Redirect(
        method = "registerIcon(Ljava/lang/String;)Lnet/minecraft/util/Icon;",
        at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"))
    private boolean modifyRegisterIcon(String instance, Object toCompare) {
        return TileLoader.isSpecialTexture((TextureMap) (Object) this, toCompare.toString(), instance);
    }
}
