package dev.mikchan.mcnp.badapple

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Subcommand
import org.bukkit.entity.Player

@CommandAlias("bad-apple")
internal class BadAppleCommand(private val plugin: BadApplePlugin) : BaseCommand() {
    @Default
    @Subcommand("start")
    @CommandPermission("mcn.bad-apple.start")
    fun start(player: Player) {
        plugin.start(player)
    }

    @Subcommand("stop")
    @CommandPermission("mcn.bad-apple.stop")
    fun stop(player: Player) {
        plugin.stop(player)
    }
}
