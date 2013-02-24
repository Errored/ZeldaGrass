package pck.Errored.ZeldaGrass;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public final class ZeldaGrass extends JavaPlugin implements Listener {
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
 
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
    	// gets long string of data of block destroyed
    	final Block origin = event.getBlock();
    	// gets x,y,z location of the block destroyed
    	final Location xyz = event.getBlock().getLocation();
    	// gets the ID of the block destroyed
    	final int type = origin.getTypeId();
    	// gets what item used that destroyed the block
    	final int InHand = event.getPlayer().getItemInHand().getTypeId();
    	// ID 31 == Tall Grass // if tall grass was destroyed
    	if (type == 31) {
    		// 276,283,267,272,268
    		// Diamond,Gold,Iron,Stone,Wood Sword
    		// if any kind of Sword in-hand when block was destroyed
    		if (InHand  == 276 || InHand == 283 || InHand == 267 || InHand == 272 || InHand == 268) {
    			// generate a random number between 1 and 1000
    			int EmeraldChance = (int)(1000*Math.random());
    			// if random number is 1, they beat the 1 in 1000 odds of this event happening.
    			if (EmeraldChance == 1) {
        			// Gives the ItemStack data of an Emerald
    				ItemStack emerald = new ItemStack(Material.EMERALD);
        			// drops an emerald in the x,y,z coordinates
        			event.getPlayer().getWorld().dropItem(xyz,emerald);
        			// send message to player who it happened too
        			event.getPlayer().sendMessage(ChatColor.GREEN+"You just found a 'Rupee' from Tall Grass! Lucky you!");
        			// debug info
        			//event.getPlayer().sendMessage(playerName+" just broke block type "+type+" with "+InHand+" and got "+emerald+" from "+xyz);
    			}
    			// generate a random number between 1 and 500
    			int HealthChance = (int)(500*Math.random());
    			if (HealthChance == 1) {
    				// find out what the max health is // I hope this is what it does... lol
    				int maxHealth = event.getPlayer().getMaxHealth();
    				// get the current health of the player
    				int currentHealth = event.getPlayer().getHealth();
    				// if current health less than the max amount of available 
    				if (currentHealth < maxHealth) {
    					// increase current health by two (since one is only half a heart)
    					int newHealth = currentHealth + 2;
    					// set new health 'one' new heart
    					event.getPlayer().setHealth(newHealth);
    					// send message to player who it happened too
    					event.getPlayer().sendMessage(ChatColor.GREEN+"You just earned a 'Heart' from Tall Grass! Nice!");
    				}
    			}
    		}
    	}
    }
}