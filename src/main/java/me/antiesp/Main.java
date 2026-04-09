package me.antiesp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("AntiESP Enabled!");
        startEntityHider();
    }

    public void startEntityHider() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player viewer : Bukkit.getOnlinePlayers()) {
                    for (Entity target : viewer.getWorld().getEntities()) {

                        if (!(target instanceof Player)) continue;
                        Player t = (Player) target;

                        if (viewer.equals(t)) continue;

                        if (!viewer.hasLineOfSight(t)) {
                            viewer.hidePlayer(Main.this, t);
                        } else {
                            viewer.showPlayer(Main.this, t);
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L);
    }
}
