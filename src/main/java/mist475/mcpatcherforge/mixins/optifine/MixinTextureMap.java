package mist475.mcpatcherforge.mixins.optifine;

import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
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
}
