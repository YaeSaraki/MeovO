package dev.saraki.meovo.module.nomobgrief


import taboolib.common.platform.Plugin
import dev.saraki.meovo.module.Module
import dev.saraki.meovo.module.nomobgrief.listener.CreeperExplodeListener
import dev.saraki.meovo.module.nomobgrief.listener.EndermanPickupListener
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.EntityChangeBlockEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.ProxyListener
import taboolib.common.platform.function.registerBukkitListener
import taboolib.common.platform.function.unregisterListener

object NoMobGriefModule : Module() {
    private var creeperExplodeProxyListener: ProxyListener? = null
    private var endermanPickupProxyListener: ProxyListener? = null
    override fun onEnable(plugin: Plugin) {
        super.onEnable(plugin)
        creeperExplodeProxyListener = registerBukkitListener(EntityExplodeEvent::class.java, priority = EventPriority.NORMAL, ignoreCancelled = true,
            { CreeperExplodeListener.onExplode(it) })
        endermanPickupProxyListener = registerBukkitListener(EntityChangeBlockEvent::class.java, priority = EventPriority.NORMAL, ignoreCancelled = true,
            { EndermanPickupListener.onEndermanPickup(it) })
    }

    override fun onDisable(plugin: Plugin) {
        super.onDisable(plugin)
        if (creeperExplodeProxyListener != null) unregisterListener(creeperExplodeProxyListener!!)
        if (endermanPickupProxyListener != null) unregisterListener(endermanPickupProxyListener!!)
    }
}