package com.bartuabihd.oplist;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class OPList extends JavaPlugin {
	
	OPList plugin;
	
	int resourceID;
    
    public OPList() {
        this.resourceID = 126579;
        
    }
	
	public void onEnable()
	{
		this.getLoggerColored(ChatColor.GREEN + "Plugin is enabled!");
		
		new com.bartuabihd.oplist.UpdateChecker(this, this.resourceID).getLatestVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                this.getLoggerColored(ChatColor.GRAY + "[" + ChatColor.BLUE + "OperatorsList" + ChatColor.GRAY + "] " + ChatColor.RED + "A new update of the plugin is not available.");
            }
            else {
                this.getLoggerColored(ChatColor.GRAY + "[" + ChatColor.BLUE + "OperatorsList" + ChatColor.GRAY + "] " + ChatColor.GREEN + "The plugin has a new update. Download link: http://bit.ly/mcpldownloadermc");
            }
        });
	}
	
	public void onDisable()
	{
		this.getLoggerColored(ChatColor.RED + "Plugin is disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command komut, String label, String[] args) {
	    // Hem /oplist hem de /op list için kontrol
	    if (komut.getName().equalsIgnoreCase("oplist") || label.equalsIgnoreCase("list") || komut.getName().equalsIgnoreCase("op list")) {
	        Set<OfflinePlayer> list = (Bukkit.getOperators());
	         
	        if (sender.hasPermission("operators.list")) {
	            if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("about"))) {
	                if (args.length == 0) {
	                    sender.sendMessage(ChatColor.YELLOW + "Operators:");
	                    for (OfflinePlayer p : list) {
	                        sender.sendMessage("   - " + p.getName());
	                    }
	                } else if (args[0].equalsIgnoreCase("about")) {
	                    sender.sendMessage(ChatColor.GREEN + "Coder by " + ChatColor.RED + "BartuAbiHD");
	                    sender.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.RED + this.getDescription().getVersion());
	                }
	            } else {
	                // Geçersiz argüman durumu
	                return false;
	            }
	        } else {
	            // Yetki yok
	            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
	            return true;
	        }
	        return true;
	    }
	    return false;
	}
	
	private void getLoggerColored(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
	
}
