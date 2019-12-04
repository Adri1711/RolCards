package com.adri1711.rolcards.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;





public class Projectile
  implements Listener
{
  @EventHandler
  public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
    Entity entity = event.getDamager();
    if (entity instanceof org.bukkit.entity.Snowball) {
      Entity player = event.getEntity();
      if (player instanceof Player) {
        Player p = (Player)player;
        p.damage(3.0D);
      } 
    } 
  }
}
