package mist475.mcpatcherforge.core;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.*;

@SuppressWarnings("unused")
@IFMLLoadingPlugin.TransformerExclusions({"mist475.mcpatcherforge.core", "mist475.mcpatcherforge.mixins"})
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE + 2)
public class CITForgeCoreLess implements IFMLLoadingPlugin {

    public CITForgeCoreLess() {}

    static {
        MixinBootstrap.init();
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
        org.spongepowered.asm.mixin.Mixins.addConfiguration("mixins.cit.client.json");
        org.spongepowered.asm.mixin.Mixins.addConfiguration("mixins.cit.optifine.json");
    }

    @Override
    public String[] getLibraryRequestClass() {
        return new String[0];
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"mist475.mcpatcherforge.core.CITAccessTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

}
