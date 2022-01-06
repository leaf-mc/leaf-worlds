package mc.leaf.modules.worlds;

import mc.leaf.core.async.LeafManager;
import mc.leaf.core.interfaces.ILeafModule;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class WorldManager extends LeafManager<World> {

    public WorldManager(ILeafModule module) {

        super(module);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    /**
     * When an object implementing interface {@code Runnable} is used to create a thread, starting the thread causes the
     * object's {@code run} method to be called in that separately executing thread.
     * <p>
     * The general contract of the method {@code run} is that it may take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

    }

}
