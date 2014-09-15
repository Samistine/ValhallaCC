package net.awesomepowered.valhallacc;

import com.google.common.base.Charsets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ValhallaCS extends JavaPlugin {

    public String prefix = "[Valhalla] ";

    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "ValhallaCC");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (s.equalsIgnoreCase("valhalla") && sender.hasPermission("valhalla.admin")) {
            if (Bukkit.getServer().getOnlinePlayers().size() == 0) {
                sender.sendMessage(ChatColor.RED + prefix + "Need at least one player online");
            } else {
                if (args.length >= 1) {
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < args.length; i++)
                    {
                        sb.append(' ').append(args[i]);
                    }
                    String theCommand = sb.toString().trim();
                    sendBungeeCommand(theCommand);
                    sender.sendMessage(ChatColor.GREEN + prefix + "Command sent!");
                } else {
                    sender.sendMessage(ChatColor.RED + prefix +  "Not enough arguments!");
                }
            }
        }
        return false;
    }

    public void sendBungeeCommand(String command) {
        Player p = getServer().getOnlinePlayers().iterator().next();
        p.sendPluginMessage(this, "ValhallaCC", command.getBytes(Charsets.UTF_8));
        System.out.println(prefix + "Sent " + command + " command to bungee");
    }


}
