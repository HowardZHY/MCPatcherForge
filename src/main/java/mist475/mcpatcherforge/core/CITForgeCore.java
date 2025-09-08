package mist475.mcpatcherforge.core;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.*;
import java.util.*;

@SuppressWarnings({"unused", "unchecked"})
@IFMLLoadingPlugin.TransformerExclusions({"mist475.mcpatcherforge.core", "mist475.mcpatcherforge.mixins"})
@IFMLLoadingPlugin.SortingIndex(Integer.MIN_VALUE + 2)
public class CITForgeCore implements IFMLLoadingPlugin {

    public CITForgeCore() {
        fixMixinClasspathOrder();
        try {
            Field f = LaunchClassLoader.class.getDeclaredField("transformerExceptions");
            f.setAccessible(true);
            Set<String> exs = (Set<String>) f.get(Launch.classLoader);
            System.out.println(exs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        injectMixinTweaker();
        MixinBootstrap.init();
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
    }

    private static void injectMixinTweaker() {
        String tweakClass = "org.spongepowered.asm.launch.MixinTweaker";
        List<String> tweakClasses = (List<String>) Launch.blackboard.get("TweakClasses");
        List<ITweaker> tweakers = (List<ITweaker>) Launch.blackboard.get("Tweaks");
        if (tweakClasses.contains(tweakClass)) {
            return;
        }
        for (ITweaker existingTweaker : tweakers) {
            if (tweakClass.equals(existingTweaker.getClass().getName())) {
                return;
            }
        }
        tweakClasses.add(tweakClass);
    }

    private static void fixMixinClasspathOrder() {
        // Borrowed from VanillaFix -- Move jar up in the classloader's URLs to make sure that the latest version of Mixin is used
        URL url = CITForgeCore.class.getProtectionDomain().getCodeSource().getLocation();
        givePriorityInClasspath(url, Launch.classLoader);
        givePriorityInClasspath(url, (URLClassLoader) ClassLoader.getSystemClassLoader());
    }

    private static void givePriorityInClasspath(URL url, URLClassLoader classLoader) {
        try {
            Field ucpField = URLClassLoader.class.getDeclaredField("ucp");
            ucpField.setAccessible(true);
            List<URL> urls = new ArrayList<>(Arrays.asList(classLoader.getURLs()));
            urls.remove(url);
            urls.add(0, url);
            Class<?> ucp = Class.forName("sun.misc.URLClassPath");
            Constructor<?> c = ucp.getDeclaredConstructor(URL[].class);
            ucpField.set(classLoader, c.newInstance((Object) urls.toArray(new URL[0])));
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String[] getLibraryRequestClass() {
        return new String[0];
    }

    @Override
    public String[] getASMTransformerClass() {
        Launch.classLoader.registerTransformer("mist475.mcpatcherforge.core.FMLTransformer");
        org.spongepowered.asm.mixin.Mixins.addConfiguration("mixins.cit.client.json");
        org.spongepowered.asm.mixin.Mixins.addConfiguration("mixins.cit.optifine.json");
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
