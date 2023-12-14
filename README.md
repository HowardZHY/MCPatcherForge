MCPatcher's Custom Item Texture (CIT) as a 1.7.10 Forge mod using Mixins.

Based on mist475's MCPatcher Forge port: https://github.com/mist475/MCPatcherForge. If you don't use OptiFine you can try that.

[Original MCPatcher repo](https://bitbucket.org/prupe/mcpatcher/src/master/)

[Original MCPatcher wiki](https://bitbucket.org/prupe/mcpatcher/wiki/Home)

Original source code is under MIT, changes by mist475 are lgpl 3.0

If you find issues feel free to report, but it might take a while before I get around to it.

Known issue:

Incompatibility with https://github.com/quentin452/Multithreadingandtweaks, it @Overwrites methods used by this mod.

Items with golden in its name like golden_horse_armor or golden_hoe didn't show with default name, you have to use following format like below:

items=minecraft:golden_hoe

texture=golden_hoe

In the item's properties.

