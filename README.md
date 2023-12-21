# MCPatcher's Custom Item Texture (CIT) as a 1.7.2 Forge mod using Mixins.

Based on mist475's MCPatcher Forge port: https://github.com/mist475/MCPatcherForge. If you don't use OptiFine you can try that.

[Original MCPatcher repo](https://bitbucket.org/prupe/mcpatcher/src/master/)

[Original MCPatcher wiki](https://bitbucket.org/prupe/mcpatcher/wiki/Home)

Original source code is under MIT, changes by mist475 are lgpl 3.0

This branch cannot runClient, only for build

# IMPORTANT

## You MUST edit your version.json like 1.7.2-Forge_10.12.2.1161.json and replace
```"name": "org.ow2.asm:asm-all:4.1"```
## to
```"name": "org.ow2.asm:asm-all:5.0.3"```
## in it to make the game launch !

### Known issues:

Items with golden in its name like golden_horse_armor or golden_hoe didn't show with default name, you have to use the format below:

```
items=minecraft:golden_hoe
texture=golden_hoe
```

In the item's properties to make it working.
