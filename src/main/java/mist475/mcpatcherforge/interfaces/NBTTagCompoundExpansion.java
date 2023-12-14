package mist475.mcpatcherforge.interfaces;

import java.util.Collection;

import net.minecraft.nbt.NBTBase;

public interface NBTTagCompoundExpansion {

    Collection<NBTBase> getTags();
}
