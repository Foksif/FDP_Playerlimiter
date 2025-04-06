package me.foksik.fDP_Playerlimiter

import me.foksik.fDP_Playerlimiter.Utils.ChatUtil
import me.foksik.fDP_Playerlimiter.events.onLogin
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin

class FDP_Playerlimiter : JavaPlugin() {
    private var maxPlayers: Int = 0
    private var allowedPermission: String = ""
    private var kickMessage: String = ""

    override fun onEnable() {
        saveDefaultConfig()
        loadConfig()
        server.pluginManager.registerEvents(onLogin(this), this)

        logger.info("FDP_Playerlimiter успешно запущенн")
    }

    private fun loadConfig() {
        val config: FileConfiguration = config
        maxPlayers = config.getInt("max-players", 50)
        allowedPermission = config.getString("allowed-permission", "fdp.playerlimit.bypass") ?: "playerlimit.bypass"

        val kickMessageList = config.getStringList("kick-message")
        kickMessage = kickMessageList.joinToString("\n") { ChatUtil.format(it) }
    }

    override fun onDisable() {
        logger.info("FDP_Playerlimiter успешно выключен")
    }

    fun getMaxPlayer(): Int {
        return maxPlayers
    }

    fun getAllowedPermission(): String {
        return allowedPermission
    }

    fun getKickMessage(): String {
        return kickMessage
    }

}
