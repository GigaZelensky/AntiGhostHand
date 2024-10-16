package com.example.antighosthand;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;
import org.bukkit.block.Block;

public class BlockInteractListener implements Listener {

    private AntiGhostHand plugin;

    public BlockInteractListener(AntiGhostHand plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Check if the player has bypass permission
        if (player.hasPermission("antighosthand.bypass")) {
            return; // Skip if player has bypass permission
        }

        // Raytrace to check for interaction
        RayTraceResult result = player.rayTraceBlocks(5); // 5 block range
        if (result == null || result.getHitBlock() == null) {
            return; // No block in sight
        }

        Block targetBlock = result.getHitBlock();
        Material blockMaterial = targetBlock.getType();
        
        // Check if the block type is allowed in the config
        if (plugin.getPluginConfig().getStringList("whitelist").contains(blockMaterial.toString())) {
            return; // Allowed block
        }

        // Block the interaction
        player.sendMessage("You cannot interact with " + blockMaterial.toString() + " through walls!");
        event.setCancelled(true);
    }
}
