package mist475.mcpatcherforge.core;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;
import java.io.IOException;

public class CITAccessTransformer extends AccessTransformer {
    public CITAccessTransformer() throws IOException {
        super("cit_at.cfg");
    }
}
