package dev.mikchan.mcnp.badapple

import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class MikChanNoBadApple : JavaPlugin() {
    override fun onEnable() {
        logger.info("Plugin has started")
    }

    override fun onDisable() {
        logger.info("Bye!")
    }
}
