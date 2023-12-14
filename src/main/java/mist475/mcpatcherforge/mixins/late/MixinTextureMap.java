package mist475.mcpatcherforge.mixins.late;

import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TextureMap.class, priority = 500)
public class MixinTextureMap {

    /**
     * @author HowardZHY
     * @reason F*ck OptiFine
     */
    @Overwrite(remap = false)
    private boolean isAbsoluteLocationPath(String resPath) {
        String path = resPath.toLowerCase();
        if (path.startsWith("mcpatcher/")) {
            return !path.startsWith("mcpatcher/cit");
        }
        return path.startsWith("optifine/");
    }

    /*public ResourceLocation completeResourceLocation(ResourceLocation p_147634_1_, int p_147634_2_) {
        ResourceLocation rl;
        if (this.isAbsoluteLocation(p_147634_1_)) {
            if (p_147634_2_ == 0) {
                return new ResourceLocation(p_147634_1_.getResourceDomain(), TileLoader.getOverridePath("", this.basePath, p_147634_1_.getResourcePath(), ".png"));
            }
            return new ResourceLocation(p_147634_1_.getResourceDomain(), p_147634_1_.getResourcePath() + "mipmap" + p_147634_2_ + ".png");
        }
        if (p_147634_2_ == 0) {
            rl = new ResourceLocation(p_147634_1_.getResourceDomain(), TileLoader.getOverridePath("", this.basePath, p_147634_1_.getResourcePath(), ".png"));

        } else {
            rl = new ResourceLocation(p_147634_1_.getResourceDomain(), String.format("%s/mipmaps/%s.%d%s", this.basePath, p_147634_1_.getResourcePath(), p_147634_2_, ".png"));
        }
        return rl;
    }*/
}
