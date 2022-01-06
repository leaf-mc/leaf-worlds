package mc.leaf.modules.worlds.listeners;

import mc.leaf.core.events.LeafListener;
import mc.leaf.modules.worlds.LeafWorldsModule;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class WorldListeners extends LeafListener {

    private static final int TELEPORTATION_TIMEOUT = 5 * 20;

    private final LeafWorldsModule module;

    public WorldListeners(LeafWorldsModule module) {

        this.module = module;
    }

    @Override
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {

        if (event.getItem().getType() == Material.POTION) {
            ItemMeta meta = event.getItem().getItemMeta();
            if (meta instanceof PotionMeta potion && potion.getBasePotionData().getType() == PotionType.MUNDANE) {

                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, TELEPORTATION_TIMEOUT * 20, 5));
                event.getPlayer().sendMessage("You took the Isekai potion...");

                Bukkit.getScheduler().runTaskLater(this.module.getPlugin(), () -> {
                    World world = this.module.getMiningWorld();
                    Block block;
                    int   rndX, rndZ;

                    do {
                        rndX = new Random().nextInt(20_000_000) - 10_000_000;
                        rndZ = new Random().nextInt(20_000_000) - 10_000_000;
                        int y = world.getHighestBlockYAt(rndX, rndZ);
                        block = world.getBlockAt(rndX, y, rndZ);
                    } while (!block.isCollidable() || block.getType() == Material.WATER);


                    event.getPlayer().teleport(block.getLocation().add(0, 1, 0));
                    event.getPlayer().removePotionEffect(PotionEffectType.CONFUSION);

                }, TELEPORTATION_TIMEOUT);
            }
        }
    }


    private String getDefaultWorld() {

        File       file = new File("server.properties");
        Properties pr   = new Properties();
        try {
            FileInputStream in = new FileInputStream(file);
            pr.load(in);
            return pr.getProperty("level-name");
        } catch (IOException ignored) {}
        return "";
    }

}
