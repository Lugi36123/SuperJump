package org.lugi.superJump

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.destroystokyo.paper.profile.ProfileProperty
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.profile.PlayerProfile
import org.bukkit.scheduler.BukkitRunnable

object SuperJumpSystem : Listener{

    @EventHandler
    fun superJump(e: PlayerJumpEvent){
        var p = e.player

        if (p.isOnGround && p.isSneaking){
            if (p.world.name.equals("world")){
                p.playSound(p, Sound.ENTITY_WIND_CHARGE_WIND_BURST, 1F, 1F)

                val vector = p.location.apply {
                    pitch = 0.0F
                }.direction.multiply(1.5).apply { y += 0.75 }

                p.velocity = vector

                if (superJump.containsKey(e.player.uniqueId)) superJump.remove(e.player.uniqueId)
                superJump.put(p.uniqueId, true)
            }
        }
    }
    @EventHandler
    fun onFalling(e: EntityDamageEvent){
        if (e.entity is Player){
            if (e.cause.equals(EntityDamageEvent.DamageCause.FALL)){
                if (superJump.get(e.entity.uniqueId) == true){
                    e.isCancelled = true

                    if (superJump.containsKey(e.entity.uniqueId)) superJump.remove(e.entity.uniqueId)
                    superJump.put(e.entity.uniqueId, false)
                }
            }
        }
    }
}