package com.bartuabihd.oplist;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
public class UpdateChecker {

    private com.bartuabihd.oplist.OPList plugin;
    private int resourceId;

    public UpdateChecker(com.bartuabihd.oplist.OPList plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                 Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
            }
        } catch (IOException exception) {
                this.plugin.getLogger().info("[OperatorsList] Cannot call updates: " + exception.getMessage());
            }
        });
    }
}
