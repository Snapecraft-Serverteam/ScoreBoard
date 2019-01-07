package de.limacity.Pepe44.Main;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PermissionProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.UUID;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        this.getServer().getPluginManager().registerEvents(this, this);
    }

    public void setScoreboard(Player p) {

        ScoreboardManager sm = Bukkit.getScoreboardManager();
        final Scoreboard board = sm.getNewScoreboard();
        final Objective o = board.registerNewObjective("test", "dummy");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§7» §6§lSnapecraft.ddns.net §7«");

        o.getScore("§a ").setScore(11);
        o.getScore("§6§lRang:").setScore(10);


        o.getScore(getRank(p)).setScore(9);




        o.getScore("§b ").setScore(8);
        o.getScore("§6§lOnline: ").setScore(7);
        o.getScore("§7»" + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()).setScore(6);
        o.getScore("§7 ").setScore(5);
        o.getScore("§6§lWebsite").setScore(4);
        o.getScore("§7»Snapecraft.ddns.net§c").setScore(3);
        o.getScore("").setScore(2);
        o.getScore("§6§lTeamSpeak:").setScore(1);
        o.getScore("§7»Snapecraft.ddns.net").setScore(0);

        p.setScoreboard(board);

    }

    private String getRank(Player p) {
        String team = "";
        UUID uuid = p.getUniqueId();
        if (PermissionProvider.isInGroup("Inhaber", CloudAPI.getInstance().getOfflinePlayer(uuid))) {
            team = "§4§lInhaber§r";
        } else if (PermissionProvider.isInGroup("Admin", CloudAPI.getInstance().getOfflinePlayer(uuid))) {
            team = "§c§lAdmin§r";
        } else if (PermissionProvider.isInGroup("Developer", CloudAPI.getInstance().getOfflinePlayer(uuid))) {
            team = "§b§lDeveloper§r";
        } else if (PermissionProvider.isInGroup("Moderator", CloudAPI.getInstance().getOfflinePlayer(uuid))) {
            team = "§1Moderator§r";
        } else if (PermissionProvider.isInGroup("Supporter", CloudAPI.getInstance().getOfflinePlayer(uuid))) {
            team = "§aSupporter§r";
        } else if (PermissionProvider.isInGroup("Builder", CloudAPI.getInstance().getOfflinePlayer(uuid))) {
            team = "§aBuilder§r";
        } else if (PermissionProvider.isInGroup("Premium", CloudAPI.getInstance().getOfflinePlayer(uuid))) {
            team = "§6Premium§r";
        } else {
            team = "§7Spieler§r";
        }
        team = "§7»" + team;
        return team;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        new BukkitRunnable() {
            @Override
            public void run() {

                for (Player all : Bukkit.getOnlinePlayers()) {
                    setScoreboard(all);
                }

            }
        }.runTaskLater(this, 1);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        new BukkitRunnable() {
            @Override
            public void run() {

                for (Player all : Bukkit.getOnlinePlayers()) {
                    setScoreboard(all);
                }

            }
        }.runTaskLater(this, 1);
    }

}
