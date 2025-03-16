# MCPatcher's Custom Item Texture (CIT) as a 1.6.4 Forge mod using Mixins.

Based on mist475's MCPatcher Forge port: https://github.com/mist475/MCPatcherForge.

[Original MCPatcher repo](https://bitbucket.org/prupe/mcpatcher/src/master/)

[Original MCPatcher wiki](https://bitbucket.org/prupe/mcpatcher/wiki/Home)

Original source code is under MIT, changes by mist475 are lgpl 3.0

# TODO:

Make a No Mixin Shaded build?

## For CIT pack authors & server owners: Only copy or rename your optifine/cit folder to mcpatcher/cit is not enough, you have to edit some properties if you use them (see below)

## You can still use optifine/cit folder for 1.8+ only textures in your pack, MCPatcher CIT won't load them.

### Known issues?

In properties, the texture line with golden in its item name like texture.golden_horse_armor or texture.golden_hoe didn't show with default name, you have to remove .item_name.

see the format below:

```
items=minecraft:golden_hoe
texture=golden_hoe
```

In the item's properties to make it working.
