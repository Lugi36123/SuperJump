package org.lugi.superJump

import org.bukkit.plugin.java.JavaPlugin

class SuperJump : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(SuperJumpSystem, this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
