package me.babarix.MoveInventory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;

public class MoveInventory extends JavaPlugin {
	private static Logger log = Logger.getLogger("Minecraft");
	private String path = "plugins" + File.separator + "MoveInventory";
	protected Map<String, Boolean> verbose;
	protected LWC lwc = null;
	PluginManager pm;

	@SuppressWarnings("unchecked")
	public void onEnable() {
		pm = getServer().getPluginManager();

		try {
			new File(path).mkdir();
			verbose = (HashMap<String, Boolean>) SLAPI.load(path
					+ File.separator + "data.dat");
			log.info("[MoveInventory] Data loadet");
		} catch (Exception e) {
			verbose = new HashMap<String, Boolean>();
			log.info("[MoveInventory] Data not found using default.");
		}
		getCommand("mi").setExecutor(new MoveInventoryCommandExecutor(this));
		
		

		Plugin lwcPlugin = getServer().getPluginManager().getPlugin("LWC");
		if(lwcPlugin != null) {
		    lwc = ((LWCPlugin) lwcPlugin).getLWC();
		    log.info("[MoveInventory] LWC found");
		}
		
		log.info("[MoveInventory] version [0.5.3] enabled");
	}

	public void onDisable() {
		try {
			SLAPI.save(verbose, path
					+ File.separator + "data.dat");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
