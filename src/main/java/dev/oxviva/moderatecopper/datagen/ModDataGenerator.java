package dev.oxviva.moderatecopper.datagen;

import dev.oxviva.moderatecopper.ModerateCopper;
import java.util.List;
import java.util.Set;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ModerateCopper.MODID)
public class ModDataGenerator {
  @SubscribeEvent
  public static void onGatherData(GatherDataEvent.Client event) {

    event.createProvider(
        (output, registries) ->
            new LootTableProvider(
                output,
                Set.of(),
                List.of(
                    new LootTableProvider.SubProviderEntry(
                        ModifiedCopperLootTable::new, LootContextParamSets.BLOCK)),
                registries));
  }
}
