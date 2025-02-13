package mist475.mcpatcherforge.mixins.client;

import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import mist475.mcpatcherforge.interfaces.NBTTagListExpansion;

@Mixin(NBTTagList.class)
public class MixinNBTTagList implements NBTTagListExpansion {

    @Shadow
    private List<NBTBase> tagList;

    public NBTBase tagAt(final int n) {
        return this.tagList.get(n);
    }
}
