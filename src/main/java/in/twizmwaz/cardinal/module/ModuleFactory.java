package in.twizmwaz.cardinal.module;

import in.twizmwaz.cardinal.match.Match;
import in.twizmwaz.cardinal.module.modules.buildHeight.BuildHeightBuilder;
import in.twizmwaz.cardinal.module.modules.cores.CoreObjectiveBuilder;
import in.twizmwaz.cardinal.module.modules.destroyable.DestroyableObjectiveBuilder;
import in.twizmwaz.cardinal.module.modules.difficulty.MapDifficultyBuilder;
import in.twizmwaz.cardinal.module.modules.disableDamage.DisableDamageBuilder;
import in.twizmwaz.cardinal.module.modules.friendlyFire.FriendlyFireBuilder;
import in.twizmwaz.cardinal.module.modules.gameScoreboard.GameScoreboardBuilder;
import in.twizmwaz.cardinal.module.modules.gamerules.GamerulesBuilder;
import in.twizmwaz.cardinal.module.modules.hunger.HungerBuilder;
import in.twizmwaz.cardinal.module.modules.itemRemove.ItemRemoveBuilder;
import in.twizmwaz.cardinal.module.modules.killStreakCount.KillStreakBuilder;
import in.twizmwaz.cardinal.module.modules.kit.KitBuilder;
import in.twizmwaz.cardinal.module.modules.mapInfo.InfoBuilder;
import in.twizmwaz.cardinal.module.modules.motd.MOTDBuilder;
import in.twizmwaz.cardinal.module.modules.observers.ObserverModuleBuilder;
import in.twizmwaz.cardinal.module.modules.projectiles.ProjectilesBuilder;
import in.twizmwaz.cardinal.module.modules.respawn.RespawnModuleBuilder;
import in.twizmwaz.cardinal.module.modules.spawn.SpawnModuleBuilder;
import in.twizmwaz.cardinal.module.modules.team.TeamModuleBuilder;
import in.twizmwaz.cardinal.module.modules.teamManager.TeamManagerModuleBuilder;
import in.twizmwaz.cardinal.module.modules.teamPicker.TeamPickerBuilder;
import in.twizmwaz.cardinal.module.modules.timeLock.TimeLockBuilder;
import in.twizmwaz.cardinal.module.modules.tntTracker.TntTrackerBuilder;
import in.twizmwaz.cardinal.module.modules.toolRepair.ToolRepairBuilder;
import in.twizmwaz.cardinal.module.modules.visibility.VisibilityBuilder;
import in.twizmwaz.cardinal.module.modules.wools.WoolObjectiveBuilder;
import in.twizmwaz.cardinal.module.modules.worldFreeze.WorldFreezeBuilder;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ModuleFactory {

    private final Match match;
    private final List<Class<? extends ModuleBuilder>> builderClasses;
    private final List<ModuleBuilder> builders;

    @SuppressWarnings("unchecked")
    public ModuleFactory(Match match) {
        this.match = match;
        this.builderClasses = new ArrayList<>();
        this.builders = new ArrayList<>();
        registerBuilders();
        for (Class clazz : builderClasses) {
            try {
                builders.add((ModuleBuilder) clazz.getConstructor().newInstance());
            } catch (NoSuchMethodException e) {
                Bukkit.getLogger().log(Level.SEVERE, clazz.getName() + " is an invalid ModuleBuilder.");
                e.printStackTrace();
            } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public ModuleCollection<Module> build(ModuleLoadTime time) {
        ModuleCollection results = new ModuleCollection();
        for (ModuleBuilder builder : builders) {
            try {
                if (builder.getClass().getAnnotation(BuilderData.class).load().equals(time)) {
                    results.addAll(builder.load(match));
                }
            } catch (NullPointerException e) {
                if (time != ModuleLoadTime.NORMAL) ;
                else results.addAll(builder.load(match));
            }
        }
        return results;
    }

    private void registerBuilders() {
        builderClasses.add(BuildHeightBuilder.class);
        builderClasses.add(WoolObjectiveBuilder.class);
        builderClasses.add(CoreObjectiveBuilder.class);
        builderClasses.add(DestroyableObjectiveBuilder.class);
        builderClasses.add(ItemRemoveBuilder.class);
        builderClasses.add(ToolRepairBuilder.class);
        builderClasses.add(DisableDamageBuilder.class);
        builderClasses.add(GamerulesBuilder.class);
        builderClasses.add(KitBuilder.class);
        builderClasses.add(TimeLockBuilder.class);
        builderClasses.add(FriendlyFireBuilder.class);
        builderClasses.add(HungerBuilder.class);
        builderClasses.add(MapDifficultyBuilder.class);
        builderClasses.add(HungerBuilder.class);
        builderClasses.add(ProjectilesBuilder.class);
        builderClasses.add(TntTrackerBuilder.class);
        builderClasses.add(VisibilityBuilder.class);
        builderClasses.add(MOTDBuilder.class);
        builderClasses.add(WorldFreezeBuilder.class);
        builderClasses.add(TeamManagerModuleBuilder.class);
        builderClasses.add(RespawnModuleBuilder.class);
        builderClasses.add(ObserverModuleBuilder.class);
        builderClasses.add(KillStreakBuilder.class);
        builderClasses.add(TeamPickerBuilder.class);
        builderClasses.add(InfoBuilder.class);
        builderClasses.add(GameScoreboardBuilder.class);
        builderClasses.add(TeamModuleBuilder.class);
        builderClasses.add(SpawnModuleBuilder.class);
    }
}
