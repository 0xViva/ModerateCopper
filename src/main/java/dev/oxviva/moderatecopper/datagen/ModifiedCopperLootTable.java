package dev.oxviva.moderatecopper.datagen;

import java.util.Set;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModifiedCopperLootTable extends BlockLootSubProvider {

  public ModifiedCopperLootTable(HolderLookup.Provider lookupProvider) {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
  }

  @Override
  protected void generate() {
    this.add(Blocks.COPPER_ORE, createCopperLoot());
    this.add(Blocks.DEEPSLATE_COPPER_ORE, createCopperLoot());
  }

  private LootTable.Builder createCopperLoot() {
    LootPool.Builder pool =
        LootPool.lootPool()
            .setRolls(ConstantValue.exactly(1))
            .add(
                LootItem.lootTableItem(Items.RAW_COPPER)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                    .apply(ApplyExplosionDecay.explosionDecay()));
    return LootTable.lootTable().withPool(pool);
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return Set.of(Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE);
  }
}
