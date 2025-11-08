package dev.oxviva.moderatecopper.datagen;

import dev.oxviva.moderatecopper.ModerateCopper;
import java.util.List;
import java.util.Set;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ModerateCopper.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenerator {

  @SubscribeEvent
  public static void onGatherData(GatherDataEvent event) {
    event
        .getGenerator()
        .addProvider(
            event.includeServer(),
            (DataProvider.Factory<LootTableProvider>)
                (output) ->
                    new LootTableProvider(
                        output,
                        Set.of(), // No validation tables
                        List.of(
                            new LootTableProvider.SubProviderEntry(
                                ModifiedCopperLootTable::new, LootContextParamSets.BLOCK)),
                        event.getLookupProvider()));
  }
}
