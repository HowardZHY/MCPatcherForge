package mist475.mcpatcherforge.mixins.client;

import com.google.common.collect.Multimap;
import com.prupe.mcpatcher.MCPatcherUtils;
import com.prupe.mcpatcher.cit.CITUtils;
import com.prupe.mcpatcher.mal.resource.TexturePackChangeHandler;
import com.prupe.mcpatcher.mal.tile.TileLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.net.Proxy;

@SuppressWarnings("all")
@Mixin(Minecraft.class)
public abstract class MixinMinecraft{

    @Inject(
        method = "<init>(Lnet/minecraft/util/Session;IIZZLjava/io/File;Ljava/io/File;Ljava/io/File;Ljava/net/Proxy;Ljava/lang/String;)V",
        at = @At("RETURN"))
    private void modifyConstructor(Session sessionIn, int displayWidth, int displayHeight, boolean fullscreen, boolean isDemo, File dataDir, File assetsDir, File resourcePackDir, Proxy proxy, String version, CallbackInfo ci) {
        MCPatcherUtils.setMinecraft(dataDir);
    }

    @Inject(
        method = "startGame()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/resources/IReloadableResourceManager;registerReloadListener(Lnet/minecraft/client/resources/IResourceManagerReloadListener;)V",
            ordinal = 0))
    private void modifyStartGame1(CallbackInfo ci) {
        TileLoader.init();
        CITUtils.init();
    }

    @Inject(
        method = "startGame()V",
        at = @At(
            value = "INVOKE",
            target = "Lcpw/mods/fml/client/FMLClientHandler;beginMinecraftLoading(Lnet/minecraft/client/Minecraft;Ljava/util/List;Lnet/minecraft/client/resources/IReloadableResourceManager;)V",
            remap = false,
            shift = At.Shift.AFTER))
    private void modifyStartGame2(CallbackInfo ci) {
        TexturePackChangeHandler.beforeChange1();
    }

    @Inject(
        method = "startGame()V",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glViewport(IIII)V",
            remap = false,
            shift = At.Shift.AFTER))
    private void modifyStartGame3(CallbackInfo ci) {
        TexturePackChangeHandler.afterChange1();
    }

    @Inject(method = "runGameLoop()V", at = @At(value = "HEAD"))
    private void modifyRunGameLoop(CallbackInfo ci) {
        TexturePackChangeHandler.checkForTexturePackChange();
    }
}
