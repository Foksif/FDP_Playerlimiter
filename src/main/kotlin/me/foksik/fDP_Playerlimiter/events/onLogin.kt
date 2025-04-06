package me.foksik.fDP_Playerlimiter.events

import me.foksik.fDP_Playerlimiter.FDP_Playerlimiter
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class onLogin(private val plugin: FDP_Playerlimiter): Listener {

    @EventHandler
    fun onPlayerLogin(event: PlayerLoginEvent) {
        val onlinePlayers = Bukkit.getOnlinePlayers().size

        if (onlinePlayers >= plugin.getMaxPlayer() && !event.player.hasPermission(plugin.getAllowedPermission())) {
            event.disallow(PlayerLoginEvent.Result.KICK_FULL, plugin.getKickMessage())
        }
    }
}