package in.twizmwaz.cardinal.match.listeners;

import com.sk89q.minecraft.util.commands.ChatColor;
import in.twizmwaz.cardinal.event.CycleCompleteEvent;
import in.twizmwaz.cardinal.event.MatchEndEvent;
import in.twizmwaz.cardinal.event.MatchStartEvent;
import in.twizmwaz.cardinal.match.Match;
import in.twizmwaz.cardinal.util.TeamUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MatchListener implements Listener {

    private final JavaPlugin plugin;
    private final Match match;

    public MatchListener(JavaPlugin plugin, Match match) {
        this.plugin = plugin;
        this.match = match;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMatchStart(MatchStartEvent event) {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "# # # # # # # # # # # # # # # #");
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "# # " + ChatColor.GOLD + "The match has started!" + ChatColor.DARK_PURPLE + " # #");
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "# # # # # # # # # # # # # # # #");
    }

    @EventHandler
    public void onMatchEnd(MatchEndEvent event) {
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "# # # # # # # # # # # #");
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "# #    " + ChatColor.GOLD + "Game over!" + ChatColor.DARK_PURPLE + "    # #");
        try {
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "# # " + event.getTeam().getColor() + event.getTeam().getName() + " wins!" + ChatColor.DARK_PURPLE + " # #");
        } catch (NullPointerException ex) {

        }
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "# # # # # # # # # # # #");
    }

    @EventHandler
    public void onCycle(CycleCompleteEvent event) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            TeamUtils.getTeamById("observers").add(player, true);
            //player.setScoreboard(TeamUtil.getTeamById("observers").getScoreboard());
        }
    }
}
