package com.bartuabihd.oplist;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

public class OPList extends JavaPlugin {
	
	public void onEnable()
	{
		getLoggerColored(ChatColor.GREEN + "Plugin is enabled!");
	}
	
	public void onDisable()
	{
		getLoggerColored(ChatColor.RED + "Plugin is disabled!");
	}
	
	public boolean onCommand(CommandSender sender,Command komut,String label, String[] args)
	{
		if(komut.getName().equalsIgnoreCase("oplist") || komut.getName().equalsIgnoreCase("op list")){
			Set<OfflinePlayer> list = (Bukkit.getOperators());
		     
			if (sender.hasPermission("operators.list")){
				if(args.length == 0){
					sender.sendMessage(ChatColor.YELLOW + "Operators:");
					for (OfflinePlayer p : list) {
						sender.sendMessage("   - " + p.getName());
					}
					//sender.sendMessage("Usage /oplist");
		 	} else if(args.length >= 1){
		 		
		 		if(args[0].equalsIgnoreCase("about")){
		 			sender.sendMessage(ChatColor.GREEN + "Coder by " + ChatColor.RED + "BartuAbiHD");
		 			sender.sendMessage(ChatColor.GREEN + "Version: " + ChatColor.RED + this.getDescription().getVersion());
		 		}
		 		
	 		}
				
		 }
	  }
	  return true;
	}
	
	private void getLoggerColored(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
	
}
