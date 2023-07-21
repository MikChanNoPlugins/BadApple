package dev.mikchan.mcnp.badapple

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class BadApplePlugin : JavaPlugin() {
    private val commandManager: CommandManager by lazy { CommandManager(this) }

    private var data: ByteArray? = null
    private var subscribers = emptySet<Player>()
    private var taskId: Int? = null

    private var frameCounter = 0
    private val frame get() = Frame.createFrame(data, frameCounter)

    private fun load() {
        if (data != null) return

        val resource = getResource("bad_apple.dat")
        data = resource?.readAllBytes()

        if (taskId == null) {
            taskId = server.scheduler.scheduleSyncRepeatingTask(this, {
                val frame = frame?.getComponents()
                if (frame == null || subscribers.isEmpty()) {
                    unload()
                    return@scheduleSyncRepeatingTask
                }

                for (subscriber in subscribers) {
                    for (row in frame) {
                        subscriber.spigot().sendMessage(*row)
                    }
                }

                frameCounter += 1
            }, 1, 1)
        }
    }

    private fun unload() {
        data = null
        taskId?.let { server.scheduler.cancelTask(it) }
        subscribers = emptySet()
    }

    fun start(player: Player) {
        subscribers += player
        load()
    }

    fun stop(player: Player) {
        subscribers -= player
        if (subscribers.isEmpty()) {
            unload()
        }
    }

    override fun onEnable() {
        commandManager.register()
    }

    override fun onDisable() {
        commandManager.unregister()
    }
}
