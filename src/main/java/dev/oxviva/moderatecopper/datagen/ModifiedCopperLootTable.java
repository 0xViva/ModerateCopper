package dev.oxviva.moderatecopper.datagen;

import java.util.Set;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModifiedCopperLootTable extends BlockLootSubProvider {

  private final Holder<Enchantment> fortune;

  public ModifiedCopperLootTable(HolderLookup.Provider lookupProvider) {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
    this.fortune =
        lookupProvider.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE);
  }

  @Override
  protected void generate() {
    this.add(Blocks.COPPER_ORE, createCopperLoot(Blocks.COPPER_ORE));
    this.add(Blocks.DEEPSLATE_COPPER_ORE, createCopperLoot(Blocks.DEEPSLATE_COPPER_ORE));
  }

  private LootTable.Builder createCopperLoot(Block block) {
    return createSilkTouchDispatchTable(
        block,
        LootItem.lootTableItem(Items.RAW_COPPER)
            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
            .apply(ApplyBonusCount.addOreBonusCount(fortune))
            .apply(ApplyExplosionDecay.explosionDecay()));
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {
    return Set.of(Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE);
  }
}
