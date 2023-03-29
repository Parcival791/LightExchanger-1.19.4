package net.parcival.lightexchanger.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExchangeLightCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Inventory playerInventory = player.getInventory();
                ItemStack torch = new ItemStack(Material.TORCH, 1);
                int amountExchanged = 0;
                for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                    if (playerInventory.contains(Material.TORCH)) {
                        playerInventory.setItem(playerInventory.first(Material.TORCH), new ItemStack(Material.TORCH, playerInventory.getItem(playerInventory.first(Material.TORCH)).getAmount() -1));
                        playerInventory.addItem(new ItemStack(Material.LIGHT));

                        amountExchanged++;
                    }
                }
                sender.sendMessage(ChatColor.GREEN + "Exchanged Amount: " + amountExchanged);
            }
            return true;
        } else if (args.length ==2) {
            if (sender.isOp()) {
                Player player = sender.getServer().getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Invalid Player");
                    return false;
                }
                Inventory playerInventory = player.getInventory();
                ItemStack torch = new ItemStack(Material.TORCH, 1);
                int amountExchanged = 0;
                for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                    if (playerInventory.contains(Material.TORCH)) {
                        playerInventory.setItem(playerInventory.first(Material.TORCH), new ItemStack(Material.TORCH, playerInventory.getItem(playerInventory.first(Material.TORCH)).getAmount() - 1));
                        playerInventory.addItem(new ItemStack(Material.LIGHT));
                        amountExchanged++;
                    }
                }
                sender.sendMessage(ChatColor.GREEN + "Exchanged Amount: " + amountExchanged);
            } else {
                sender.sendMessage(ChatColor.RED + "Du musst OP sein, um den Light exchange für andere zu machen!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Syntax: /exchangelight <amount> <player>");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        ArrayList<String> returnList = new ArrayList<>();
        if (args.length == 2) {
            for (Player onlinePlayer : sender.getServer().getOnlinePlayers()) {
                returnList.add(onlinePlayer.getName());
            }
        }

        return returnList;
    }
}
