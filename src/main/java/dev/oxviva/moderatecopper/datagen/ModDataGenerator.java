package dev.oxviva.moderatecopper.datagen;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
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

public class ModDataGenerator implements DataGeneratorEntrypoint {
  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
    pack.addProvider(ModifiedCopperLootTable::new);
  }

  public static class ModifiedCopperLootTable extends FabricBlockLootTableProvider {

    private Holder<Enchantment> fortune;

    protected ModifiedCopperLootTable(
        FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
      super(dataOutput, registryLookup);
      registryLookup
          .thenAccept(
              provider ->
                  this.fortune =
                      provider
                          .lookupOrThrow(Registries.ENCHANTMENT)
                          .getOrThrow(Enchantments.FORTUNE))
          .join();
    }

    @Override
    public void generate() {
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
  }
}
