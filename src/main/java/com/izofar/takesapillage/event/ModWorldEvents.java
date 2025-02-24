package com.izofar.takesapillage.event;

import com.izofar.takesapillage.init.ModEntityTypes;
import com.izofar.takesapillage.world.PillageSiege;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public abstract class ModWorldEvents {

    public static final PillageSiege PILLAGE_SIEGE = new PillageSiege();

    @SubscribeEvent
    public static void onSpecialSpawn(TickEvent.WorldTickEvent event) {
        Level level = event.world;
        if (level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)
                && level instanceof ServerLevel serverWorld
                && serverWorld.dimension() == Level.OVERWORLD) {
            PILLAGE_SIEGE.tick(serverWorld, true, false);
        }
    }

    public static void addModdedRaiders() {
        Raid.RaiderType.create("ARCHER", ModEntityTypes.ARCHER.get(), new int[]{0, 2, 0, 0, 2, 3, 2, 4});
        Raid.RaiderType.create("SKIRMISHER", ModEntityTypes.SKIRMISHER.get(), new int[]{0, 0, 0, 2, 0, 1, 1, 2});
        Raid.RaiderType.create("LEGIONER", ModEntityTypes.LEGIONER.get(), new int[]{0, 0, 0, 0, 2, 0, 2, 3});
    }
}
