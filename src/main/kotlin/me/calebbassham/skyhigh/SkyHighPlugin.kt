package me.calebbassham.skyhigh

import me.calebbassham.scenariomanager.api.scenarioManager
import org.bukkit.plugin.java.JavaPlugin

class SkyHighPlugin : JavaPlugin() {

    override fun onEnable() {
        scenarioManager.register(SkyHigh(), this)
    }

}