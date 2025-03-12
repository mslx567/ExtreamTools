package net.mslx.extreamtools

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    override fun onEnable() {
        logger.info("ExteraTools has been Enabled!")
    }

    override fun onDisable() {
        logger.info("ExteraTools has been Disabled!")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Component.text("Only players can use this command!").color(NamedTextColor.RED))
            return true
        }

        val player: Player = sender
        when (command.name) {
            "spawn" -> {
                val spawnLocation = Bukkit.getWorld("world")?.spawnLocation
                if (spawnLocation != null) {
                    player.teleport(spawnLocation)
                    player.sendMessage(Component.text("Teleported to spawn!").color(NamedTextColor.GREEN))
                } else {
                    player.sendMessage(Component.text("Spawn location is not set!").color(NamedTextColor.RED))
                }
                return true
            }

            "setspawn" -> {
                val world = player.world
                world.setSpawnLocation(player.location.blockX, player.location.blockY, player.location.blockZ)
                player.sendMessage(Component.text("Spawn location set!").color(NamedTextColor.GREEN))
                return true
            }

            "fly" -> {
                player.allowFlight = !player.allowFlight
                player.sendMessage(
                    Component.text("Flight mode: ${if (player.allowFlight) "Enabled" else "Disabled"}")
                        .color(NamedTextColor.YELLOW)
                )
                return true
            }

            "god" -> {
                player.isInvulnerable = !player.isInvulnerable
                player.sendMessage(
                    Component.text("God mode: ${if (player.isInvulnerable) "Enabled" else "Disabled"}")
                        .color(NamedTextColor.GOLD)
                )
                return true
            }

            "heal" -> {
                player.health = 20.0
                player.sendMessage(Component.text("You have been healed!").color(NamedTextColor.GREEN))
                return true
            }

            "feed" -> {
                val playerFoodLevel = player.foodLevel
                player.foodLevel = Math.min(20, player.foodLevel)
                player.sendMessage(Component.text("You are no longer hungry!").color(NamedTextColor.GREEN))
                return true
            }
            else -> return false
        }
    }
}
