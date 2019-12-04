package com.adri1711.rolcards.listeners;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.utils.Utils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Ataque
  implements Listener
{
  private RolCards plugin;
  
  public Ataque(RolCards plugin) { this.plugin = plugin; }
  
  @EventHandler
  public void onDamaged(EntityDamageEvent evt) {
    if (evt.getEntityType() != EntityType.PLAYER && (
      evt.getCause() == EntityDamageEvent.DamageCause.FIRE || evt.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
      evt.setCancelled(true);
    }
  }
  
  @EventHandler
  public void onAttack(EntityDamageByEntityEvent evt) {
    if (evt.getDamager() instanceof Player && 
      !(evt.getEntity() instanceof Player)) {
      Player p = (Player)evt.getDamager();
      Jugador j = Utils.buscaJugador(p, this.plugin);
      if (j != null && 
        j.isPlaying())
        evt.setCancelled(true); 
    } 
  }
}
