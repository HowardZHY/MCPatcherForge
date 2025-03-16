package mist475.mcpatcherforge.mixins.optifine;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = TextureMap.class, priority = 500)
public class MixinTextureMap {

    /**
     * @author HowardZHY
     * @reason F*ck OptiFine
     */
    @Dynamic
    @Overwrite(remap = false)
    public boolean isAbsoluteLocation(ResourceLocation loc) {
        String path = loc.getResourcePath().toLowerCase();
        if (path.startsWith("mcpatcher/")) {
            return !path.startsWith("mcpatcher/cit");
        }
        return path.startsWith("optifine/");
    }
}