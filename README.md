# Moderate Copper

Changes Minecraft's Copper Ore uniform output from [3 to 5] down to just [1], same as iron ores.

This is done by overwriting the `data/minecraft/loot_table` json for the blocks `minecraft:blocks/copper_ore` and `minecraft:blocks/copper_ore`

The tweaked loot table is generated using a data provider called `LootTableProvider` from the Minecraft modding API NeoForged:
https://docs.neoforged.net/docs/resources/server/loottables/#datagen

