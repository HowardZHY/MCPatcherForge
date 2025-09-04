package com.prupe.mcpatcher.mal.item;

import java.util.*;

import net.minecraft.item.Item;

import com.prupe.mcpatcher.MCPatcherUtils;

public class ItemAPI {

    public static HashMap<String, Integer> canonicalIdByName = new HashMap<>();

    public static HashMap<String, Integer> get() {
        return canonicalIdByName;
    }

    public static Item getFixedItem(String name) {
        Item item = parseItemName(name);
        if (item == null) {
            throw new IllegalArgumentException("unknown item " + name);
        } else {
            return item;
        }
    }

    public static Item parseItemName(String name) {
        if (MCPatcherUtils.isNullOrEmpty(name)) {
            return null;
        }
        if (name.matches("\\d+")) {
            int id = Integer.parseInt(name);
            return getItemById(id);
        }
        name = getFullName(name);
        return getItemByName(name);
    }

    public static String getItemName(Item item) {
        if (item == null) {
            return "(null)";
        }
        int id = item.itemID;
        for (Map.Entry<String, Integer> entry : get().entrySet()) {
            if (id == entry.getValue()) return entry.getKey();
        }
        String name = item.getUnlocalizedName();
        if (name == null) {
            return String.valueOf(id);
        }
        return name;
    }

    public static String getFullName(String name) {
        return name == null ? null : name.indexOf(':') >= 0 ? name : "minecraft:" + name;
    }

    public static Item getItemById(int id) {
        return (id >= 0 && id < Item.itemsList.length) ? Item.itemsList[id] : null;
    }

    public static Item getItemByName(String name) {
        Integer id = canonicalIdByName.get(name);
        return id == null ? null : getItemById(id);
    }

    @SuppressWarnings("unused")
    public static void putBlocks() {
        //canonicalIdByName.put("minecraft:air", 0);
        canonicalIdByName.put("minecraft:stone", 1);
        canonicalIdByName.put("minecraft:grass", 2);
        canonicalIdByName.put("minecraft:dirt", 3);
        canonicalIdByName.put("minecraft:cobblestone", 4);
        canonicalIdByName.put("minecraft:planks", 5);
        canonicalIdByName.put("minecraft:sapling", 6);
        canonicalIdByName.put("minecraft:bedrock", 7);
        canonicalIdByName.put("minecraft:flowing_water", 8);
        canonicalIdByName.put("minecraft:water", 9);
        canonicalIdByName.put("minecraft:flowing_lava", 10);
        canonicalIdByName.put("minecraft:lava", 11);
        canonicalIdByName.put("minecraft:sand", 12);
        canonicalIdByName.put("minecraft:gravel", 13);
        canonicalIdByName.put("minecraft:gold_ore", 14);
        canonicalIdByName.put("minecraft:iron_ore", 15);
        canonicalIdByName.put("minecraft:coal_ore", 16);
        canonicalIdByName.put("minecraft:log", 17);
        canonicalIdByName.put("minecraft:leaves", 18);
        canonicalIdByName.put("minecraft:sponge", 19);
        canonicalIdByName.put("minecraft:glass", 20);
        canonicalIdByName.put("minecraft:stained_glass", 20);
        canonicalIdByName.put("minecraft:lapis_ore", 21);
        canonicalIdByName.put("minecraft:lapis_block", 22);
        canonicalIdByName.put("minecraft:dispenser", 23);
        canonicalIdByName.put("minecraft:sandstone", 24);
        canonicalIdByName.put("minecraft:noteblock", 25);
        //canonicalIdByName.put("minecraft:bed", 26);
        canonicalIdByName.put("minecraft:golden_rail", 27);
        canonicalIdByName.put("minecraft:detector_rail", 28);
        canonicalIdByName.put("minecraft:sticky_piston", 29);
        canonicalIdByName.put("minecraft:web", 30);
        canonicalIdByName.put("minecraft:tallgrass", 31);
        canonicalIdByName.put("minecraft:deadbush", 32);
        canonicalIdByName.put("minecraft:piston", 33);
        canonicalIdByName.put("minecraft:piston_head", 34);
        canonicalIdByName.put("minecraft:wool", 35);
        canonicalIdByName.put("minecraft:piston_extension", 36);
        canonicalIdByName.put("minecraft:yellow_flower", 37);
        canonicalIdByName.put("minecraft:red_flower", 38);
        canonicalIdByName.put("minecraft:brown_mushroom", 39);
        canonicalIdByName.put("minecraft:red_mushroom", 40);
        canonicalIdByName.put("minecraft:gold_block", 41);
        canonicalIdByName.put("minecraft:iron_block", 42);
        canonicalIdByName.put("minecraft:double_stone_slab", 43);
        canonicalIdByName.put("minecraft:stone_slab", 44);
        canonicalIdByName.put("minecraft:brick_block", 45);
        canonicalIdByName.put("minecraft:tnt", 46);
        canonicalIdByName.put("minecraft:bookshelf", 47);
        canonicalIdByName.put("minecraft:mossy_cobblestone", 48);
        canonicalIdByName.put("minecraft:obsidian", 49);
        canonicalIdByName.put("minecraft:torch", 50);
        canonicalIdByName.put("minecraft:fire", 51);
        canonicalIdByName.put("minecraft:mob_spawner", 52);
        canonicalIdByName.put("minecraft:oak_stairs", 53);
        canonicalIdByName.put("minecraft:chest", 54);
        canonicalIdByName.put("minecraft:redstone_wire", 55);
        canonicalIdByName.put("minecraft:diamond_ore", 56);
        canonicalIdByName.put("minecraft:diamond_block", 57);
        canonicalIdByName.put("minecraft:crafting_table", 58);
        //canonicalIdByName.put("minecraft:wheat", 59);
        canonicalIdByName.put("minecraft:farmland", 60);
        canonicalIdByName.put("minecraft:furnace", 61);
        canonicalIdByName.put("minecraft:lit_furnace", 62);
        canonicalIdByName.put("minecraft:standing_sign", 63);
        //canonicalIdByName.put("minecraft:wooden_door", 64);
        canonicalIdByName.put("minecraft:ladder", 65);
        canonicalIdByName.put("minecraft:rail", 66);
        canonicalIdByName.put("minecraft:stone_stairs", 67);
        canonicalIdByName.put("minecraft:wall_sign", 68);
        canonicalIdByName.put("minecraft:lever", 69);
        canonicalIdByName.put("minecraft:stone_pressure_plate", 70);
        //canonicalIdByName.put("minecraft:iron_door", 71);
        canonicalIdByName.put("minecraft:wooden_pressure_plate", 72);
        canonicalIdByName.put("minecraft:redstone_ore", 73);
        canonicalIdByName.put("minecraft:lit_redstone_ore", 74);
        canonicalIdByName.put("minecraft:unlit_redstone_torch", 75);
        canonicalIdByName.put("minecraft:redstone_torch", 76);
        canonicalIdByName.put("minecraft:stone_button", 77);
        canonicalIdByName.put("minecraft:snow_layer", 78);
        canonicalIdByName.put("minecraft:ice", 79);
        canonicalIdByName.put("minecraft:snow", 80);
        canonicalIdByName.put("minecraft:cactus", 81);
        canonicalIdByName.put("minecraft:clay", 82);
        //canonicalIdByName.put("minecraft:reeds", 83);
        canonicalIdByName.put("minecraft:jukebox", 84);
        canonicalIdByName.put("minecraft:fence", 85);
        canonicalIdByName.put("minecraft:pumpkin", 86);
        canonicalIdByName.put("minecraft:netherrack", 87);
        canonicalIdByName.put("minecraft:soul_sand", 88);
        canonicalIdByName.put("minecraft:glowstone", 89);
        canonicalIdByName.put("minecraft:portal", 90);
        canonicalIdByName.put("minecraft:lit_pumpkin", 91);
        //canonicalIdByName.put("minecraft:cake", 92);
        canonicalIdByName.put("minecraft:unpowered_repeater", 93);
        canonicalIdByName.put("minecraft:powered_repeater", 94);
        canonicalIdByName.put("minecraft:chest_locked_aprilfools_super_old_legacy_we_should_not_even_have_this", 95);
        canonicalIdByName.put("minecraft:trapdoor", 96);
        canonicalIdByName.put("minecraft:monster_egg", 97);
        canonicalIdByName.put("minecraft:stonebrick", 98);
        canonicalIdByName.put("minecraft:brown_mushroom_block", 99);
        canonicalIdByName.put("minecraft:red_mushroom_block", 100);
        canonicalIdByName.put("minecraft:iron_bars", 101);
        canonicalIdByName.put("minecraft:glass_pane", 102);
        canonicalIdByName.put("minecraft:stained_glass_pane", 102);
        canonicalIdByName.put("minecraft:melon_block", 103);
        canonicalIdByName.put("minecraft:pumpkin_stem", 104);
        canonicalIdByName.put("minecraft:melon_stem", 105);
        canonicalIdByName.put("minecraft:vine", 106);
        canonicalIdByName.put("minecraft:fence_gate", 107);
        canonicalIdByName.put("minecraft:brick_stairs", 108);
        canonicalIdByName.put("minecraft:stone_brick_stairs", 109);
        canonicalIdByName.put("minecraft:mycelium", 110);
        canonicalIdByName.put("minecraft:waterlily", 111);
        canonicalIdByName.put("minecraft:nether_brick", 112);
        canonicalIdByName.put("minecraft:nether_brick_fence", 113);
        canonicalIdByName.put("minecraft:nether_brick_stairs", 114);
        //canonicalIdByName.put("minecraft:nether_wart", 115);
        canonicalIdByName.put("minecraft:enchanting_table", 116);
        //canonicalIdByName.put("minecraft:brewing_stand", 117);
        //canonicalIdByName.put("minecraft:cauldron", 118);
        canonicalIdByName.put("minecraft:end_portal", 119);
        canonicalIdByName.put("minecraft:end_portal_frame", 120);
        canonicalIdByName.put("minecraft:end_stone", 121);
        canonicalIdByName.put("minecraft:dragon_egg", 122);
        canonicalIdByName.put("minecraft:redstone_lamp", 123);
        canonicalIdByName.put("minecraft:lit_redstone_lamp", 124);
        canonicalIdByName.put("minecraft:double_wooden_slab", 125);
        canonicalIdByName.put("minecraft:wooden_slab", 126);
        canonicalIdByName.put("minecraft:cocoa", 127);
        canonicalIdByName.put("minecraft:sandstone_stairs", 128);
        canonicalIdByName.put("minecraft:emerald_ore", 129);
        canonicalIdByName.put("minecraft:ender_chest", 130);
        canonicalIdByName.put("minecraft:tripwire_hook", 131);
        canonicalIdByName.put("minecraft:tripwire", 132);
        canonicalIdByName.put("minecraft:emerald_block", 133);
        canonicalIdByName.put("minecraft:spruce_stairs", 134);
        canonicalIdByName.put("minecraft:birch_stairs", 135);
        canonicalIdByName.put("minecraft:jungle_stairs", 136);
        canonicalIdByName.put("minecraft:command_block", 137);
        canonicalIdByName.put("minecraft:beacon", 138);
        canonicalIdByName.put("minecraft:cobblestone_wall", 139);
        //canonicalIdByName.put("minecraft:flower_pot", 140);
        canonicalIdByName.put("minecraft:carrots", 141);
        canonicalIdByName.put("minecraft:potatoes", 142);
        canonicalIdByName.put("minecraft:wooden_button", 143);
        //canonicalIdByName.put("minecraft:skull", 144);
        canonicalIdByName.put("minecraft:anvil", 145);
        canonicalIdByName.put("minecraft:trapped_chest", 146);
        canonicalIdByName.put("minecraft:light_weighted_pressure_plate", 147);
        canonicalIdByName.put("minecraft:heavy_weighted_pressure_plate", 148);
        canonicalIdByName.put("minecraft:unpowered_comparator", 149);
        canonicalIdByName.put("minecraft:powered_comparator", 150);
        canonicalIdByName.put("minecraft:daylight_detector", 151);
        canonicalIdByName.put("minecraft:redstone_block", 152);
        canonicalIdByName.put("minecraft:quartz_ore", 153);
        canonicalIdByName.put("minecraft:hopper", 154);
        canonicalIdByName.put("minecraft:quartz_block", 155);
        canonicalIdByName.put("minecraft:quartz_stairs", 156);
        canonicalIdByName.put("minecraft:activator_rail", 157);
        canonicalIdByName.put("minecraft:dropper", 158);
        canonicalIdByName.put("minecraft:stained_hardened_clay", 159);
        canonicalIdByName.put("minecraft:hay_block", 170);
        canonicalIdByName.put("minecraft:carpet", 171);
        canonicalIdByName.put("minecraft:hardened_clay", 172);
        canonicalIdByName.put("minecraft:coal_block", 173);
        canonicalIdByName.put("minecraft:packed_ice", 174);
        canonicalIdByName.put("minecraft:double_plant", 175);
    }

    static {
        canonicalIdByName.put("minecraft:iron_shovel", 256);
        canonicalIdByName.put("minecraft:iron_pickaxe", 257);
        canonicalIdByName.put("minecraft:iron_axe", 258);
        canonicalIdByName.put("minecraft:flint_and_steel", 259);
        canonicalIdByName.put("minecraft:apple", 260);
        canonicalIdByName.put("minecraft:bow", 261);
        canonicalIdByName.put("minecraft:arrow", 262);
        canonicalIdByName.put("minecraft:coal", 263);
        canonicalIdByName.put("minecraft:diamond", 264);
        canonicalIdByName.put("minecraft:iron_ingot", 265);
        canonicalIdByName.put("minecraft:gold_ingot", 266);
        canonicalIdByName.put("minecraft:iron_sword", 267);
        canonicalIdByName.put("minecraft:wooden_sword", 268);
        canonicalIdByName.put("minecraft:wooden_shovel", 269);
        canonicalIdByName.put("minecraft:wooden_pickaxe", 270);
        canonicalIdByName.put("minecraft:wooden_axe", 271);
        canonicalIdByName.put("minecraft:stone_sword", 272);
        canonicalIdByName.put("minecraft:stone_shovel", 273);
        canonicalIdByName.put("minecraft:stone_pickaxe", 274);
        canonicalIdByName.put("minecraft:stone_axe", 275);
        canonicalIdByName.put("minecraft:diamond_sword", 276);
        canonicalIdByName.put("minecraft:diamond_shovel", 277);
        canonicalIdByName.put("minecraft:diamond_pickaxe", 278);
        canonicalIdByName.put("minecraft:diamond_axe", 279);
        canonicalIdByName.put("minecraft:stick", 280);
        canonicalIdByName.put("minecraft:bowl", 281);
        canonicalIdByName.put("minecraft:mushroom_stew", 282);
        canonicalIdByName.put("minecraft:golden_sword", 283);
        canonicalIdByName.put("minecraft:golden_shovel", 284);
        canonicalIdByName.put("minecraft:golden_pickaxe", 285);
        canonicalIdByName.put("minecraft:golden_axe", 286);
        canonicalIdByName.put("minecraft:string", 287);
        canonicalIdByName.put("minecraft:feather", 288);
        canonicalIdByName.put("minecraft:gunpowder", 289);
        canonicalIdByName.put("minecraft:wooden_hoe", 290);
        canonicalIdByName.put("minecraft:stone_hoe", 291);
        canonicalIdByName.put("minecraft:iron_hoe", 292);
        canonicalIdByName.put("minecraft:diamond_hoe", 293);
        canonicalIdByName.put("minecraft:golden_hoe", 294);
        canonicalIdByName.put("minecraft:wheat_seeds", 295);
        canonicalIdByName.put("minecraft:wheat", 296);
        canonicalIdByName.put("minecraft:bread", 297);
        canonicalIdByName.put("minecraft:leather_helmet", 298);
        canonicalIdByName.put("minecraft:leather_chestplate", 299);
        canonicalIdByName.put("minecraft:leather_leggings", 300);
        canonicalIdByName.put("minecraft:leather_boots", 301);
        canonicalIdByName.put("minecraft:chainmail_helmet", 302);
        canonicalIdByName.put("minecraft:chainmail_chestplate", 303);
        canonicalIdByName.put("minecraft:chainmail_leggings", 304);
        canonicalIdByName.put("minecraft:chainmail_boots", 305);
        canonicalIdByName.put("minecraft:iron_helmet", 306);
        canonicalIdByName.put("minecraft:iron_chestplate", 307);
        canonicalIdByName.put("minecraft:iron_leggings", 308);
        canonicalIdByName.put("minecraft:iron_boots", 309);
        canonicalIdByName.put("minecraft:diamond_helmet", 310);
        canonicalIdByName.put("minecraft:diamond_chestplate", 311);
        canonicalIdByName.put("minecraft:diamond_leggings", 312);
        canonicalIdByName.put("minecraft:diamond_boots", 313);
        canonicalIdByName.put("minecraft:golden_helmet", 314);
        canonicalIdByName.put("minecraft:golden_chestplate", 315);
        canonicalIdByName.put("minecraft:golden_leggings", 316);
        canonicalIdByName.put("minecraft:golden_boots", 317);
        canonicalIdByName.put("minecraft:flint", 318);
        canonicalIdByName.put("minecraft:porkchop", 319);
        canonicalIdByName.put("minecraft:cooked_porkchop", 320);
        canonicalIdByName.put("minecraft:painting", 321);
        canonicalIdByName.put("minecraft:golden_apple", 322);
        canonicalIdByName.put("minecraft:sign", 323);
        canonicalIdByName.put("minecraft:wooden_door", 324);
        canonicalIdByName.put("minecraft:bucket", 325);
        canonicalIdByName.put("minecraft:water_bucket", 326);
        canonicalIdByName.put("minecraft:lava_bucket", 327);
        canonicalIdByName.put("minecraft:minecart", 328);
        canonicalIdByName.put("minecraft:saddle", 329);
        canonicalIdByName.put("minecraft:iron_door", 330);
        canonicalIdByName.put("minecraft:redstone", 331);
        canonicalIdByName.put("minecraft:snowball", 332);
        canonicalIdByName.put("minecraft:boat", 333);
        canonicalIdByName.put("minecraft:leather", 334);
        canonicalIdByName.put("minecraft:milk_bucket", 335);
        canonicalIdByName.put("minecraft:brick", 336);
        canonicalIdByName.put("minecraft:clay_ball", 337);
        canonicalIdByName.put("minecraft:reeds", 338);
        canonicalIdByName.put("minecraft:paper", 339);
        canonicalIdByName.put("minecraft:book", 340);
        canonicalIdByName.put("minecraft:slime_ball", 341);
        canonicalIdByName.put("minecraft:chest_minecart", 342);
        canonicalIdByName.put("minecraft:furnace_minecart", 343);
        canonicalIdByName.put("minecraft:egg", 344);
        canonicalIdByName.put("minecraft:compass", 345);
        canonicalIdByName.put("minecraft:fishing_rod", 346);
        canonicalIdByName.put("minecraft:clock", 347);
        canonicalIdByName.put("minecraft:glowstone_dust", 348);
        canonicalIdByName.put("minecraft:fish", 349);
        canonicalIdByName.put("minecraft:cooked_fished", 350);
        canonicalIdByName.put("minecraft:dye", 351);
        canonicalIdByName.put("minecraft:bone", 352);
        canonicalIdByName.put("minecraft:sugar", 353);
        canonicalIdByName.put("minecraft:cake", 354);
        canonicalIdByName.put("minecraft:bed", 355);
        canonicalIdByName.put("minecraft:repeater", 356);
        canonicalIdByName.put("minecraft:cookie", 357);
        canonicalIdByName.put("minecraft:filled_map", 358);
        canonicalIdByName.put("minecraft:shears", 359);
        canonicalIdByName.put("minecraft:melon", 360);
        canonicalIdByName.put("minecraft:pumpkin_seeds", 361);
        canonicalIdByName.put("minecraft:melon_seeds", 362);
        canonicalIdByName.put("minecraft:beef", 363);
        canonicalIdByName.put("minecraft:cooked_beef", 364);
        canonicalIdByName.put("minecraft:chicken", 365);
        canonicalIdByName.put("minecraft:cooked_chicken", 366);
        canonicalIdByName.put("minecraft:rotten_flesh", 367);
        canonicalIdByName.put("minecraft:ender_pearl", 368);
        canonicalIdByName.put("minecraft:blaze_rod", 369);
        canonicalIdByName.put("minecraft:ghast_tear", 370);
        canonicalIdByName.put("minecraft:gold_nugget", 371);
        canonicalIdByName.put("minecraft:nether_wart", 372);
        canonicalIdByName.put("minecraft:potion", 373);
        canonicalIdByName.put("minecraft:glass_bottle", 374);
        canonicalIdByName.put("minecraft:spider_eye", 375);
        canonicalIdByName.put("minecraft:fermented_spider_eye", 376);
        canonicalIdByName.put("minecraft:blaze_powder", 377);
        canonicalIdByName.put("minecraft:magma_cream", 378);
        canonicalIdByName.put("minecraft:brewing_stand", 379);
        canonicalIdByName.put("minecraft:cauldron", 380);
        canonicalIdByName.put("minecraft:ender_eye", 381);
        canonicalIdByName.put("minecraft:speckled_melon", 382);
        canonicalIdByName.put("minecraft:spawn_egg", 383);
        canonicalIdByName.put("minecraft:experience_bottle", 384);
        canonicalIdByName.put("minecraft:fire_charge", 385);
        canonicalIdByName.put("minecraft:writable_book", 386);
        canonicalIdByName.put("minecraft:written_book", 387);
        canonicalIdByName.put("minecraft:emerald", 388);
        canonicalIdByName.put("minecraft:item_frame", 389);
        canonicalIdByName.put("minecraft:flower_pot", 390);
        canonicalIdByName.put("minecraft:carrot", 391);
        canonicalIdByName.put("minecraft:potato", 392);
        canonicalIdByName.put("minecraft:baked_potato", 393);
        canonicalIdByName.put("minecraft:poisonous_potato", 394);
        canonicalIdByName.put("minecraft:map", 395);
        canonicalIdByName.put("minecraft:golden_carrot", 396);
        canonicalIdByName.put("minecraft:skull", 397);
        canonicalIdByName.put("minecraft:carrot_on_a_stick", 398);
        canonicalIdByName.put("minecraft:nether_star", 399);
        canonicalIdByName.put("minecraft:pumpkin_pie", 400);
        canonicalIdByName.put("minecraft:fireworks", 401);
        canonicalIdByName.put("minecraft:firework_charge", 402);
        canonicalIdByName.put("minecraft:enchanted_book", 403);
        canonicalIdByName.put("minecraft:comparator", 404);
        canonicalIdByName.put("minecraft:netherbrick", 405);
        canonicalIdByName.put("minecraft:quartz", 406);
        canonicalIdByName.put("minecraft:tnt_minecart", 407);
        canonicalIdByName.put("minecraft:hopper_minecart", 408);
        canonicalIdByName.put("minecraft:iron_horse_armor", 417);
        canonicalIdByName.put("minecraft:golden_horse_armor", 418);
        canonicalIdByName.put("minecraft:diamond_horse_armor", 419);
        canonicalIdByName.put("minecraft:lead", 420);
        canonicalIdByName.put("minecraft:name_tag", 421);
        canonicalIdByName.put("minecraft:record_13", 2256);
        canonicalIdByName.put("minecraft:record_cat", 2257);
        canonicalIdByName.put("minecraft:record_blocks", 2258);
        canonicalIdByName.put("minecraft:record_chirp", 2259);
        canonicalIdByName.put("minecraft:record_far", 2260);
        canonicalIdByName.put("minecraft:record_mall", 2261);
        canonicalIdByName.put("minecraft:record_mellohi", 2262);
        canonicalIdByName.put("minecraft:record_stal", 2263);
        canonicalIdByName.put("minecraft:record_strad", 2264);
        canonicalIdByName.put("minecraft:record_ward", 2265);
        canonicalIdByName.put("minecraft:record_11", 2266);
        canonicalIdByName.put("minecraft:record_wait", 2267);
    }
}
