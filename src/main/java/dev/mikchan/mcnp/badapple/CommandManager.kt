package dev.mikchan.mcnp.badapple

import co.aikar.commands.PaperCommandManager

internal class CommandManager(private val plugin: BadApplePlugin) {
    private val manager: PaperCommandManager by lazy { PaperCommandManager(plugin) }

    fun register() {
        unregister()
        manager.registerCommand(BadAppleCommand(plugin))
    }

    fun unregister() {
        manager.unregisterCommands()
    }
}
