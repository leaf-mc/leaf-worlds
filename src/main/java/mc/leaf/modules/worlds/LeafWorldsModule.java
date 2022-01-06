package mc.leaf.modules.worlds;

import mc.leaf.core.interfaces.ILeafCore;
import mc.leaf.core.interfaces.ILeafModule;
import mc.leaf.modules.worlds.listeners.WorldListeners;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

public class LeafWorldsModule implements ILeafModule {

    private final JavaPlugin plugin;
    private final ILeafCore  core;
    private       World      miningWorld;
    private       boolean    enabled = false;

    public LeafWorldsModule(JavaPlugin plugin, ILeafCore core) {

        this.plugin = plugin;
        this.core   = core;
        this.core.registerModule(this);
    }

    @Override
    public void onEnable() {

        WorldCreator creator = new WorldCreator("mining");
        creator.type(WorldType.NORMAL);
        creator.environment(World.Environment.NORMAL);
        this.miningWorld = creator.createWorld();

        this.core.getEventBridge().register(this, new WorldListeners(this));
        this.enabled = true;
    }

    @Override
    public void onDisable() {

        this.core.getEventBridge().unregister(this);
        Bukkit.getServer().unloadWorld(this.miningWorld, true);
        this.enabled = false;
    }

    @Override
    public ILeafCore getCore() {

        return this.core;
    }

    @Override
    public String getName() {

        return "Worlds";
    }

    @Override
    public boolean isEnabled() {

        return this.enabled;
    }

    @Override
    public JavaPlugin getPlugin() {

        return this.plugin;
    }

    public World getMiningWorld() {

        return this.miningWorld;
    }

}
