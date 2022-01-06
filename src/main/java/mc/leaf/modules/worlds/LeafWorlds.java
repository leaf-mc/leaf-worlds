package mc.leaf.modules.worlds;

import mc.leaf.core.interfaces.ILeafCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class LeafWorlds extends JavaPlugin {

    public static final String PREFIX = "§l[§aLeaf§bWorlds§r§l]§r";

    @Override
    public void onEnable() {

        Plugin plugin = Bukkit.getPluginManager().getPlugin("LeafCore");
        if (plugin instanceof ILeafCore core) {
            new LeafWorldsModule(this, core);
        } else {
            this.getLogger().severe("Unable to find LeafCore instance.");
        }
    }

}
