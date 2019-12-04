package com.adri1711.rolcards.listeners;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Death
  implements Listener {
  private RolCards plugin;
  
  public Death(RolCards plugin) { this.plugin = plugin; }
  
  @EventHandler
  public void onDeath(PlayerDeathEvent evt) {
    Jugador j = Utils.buscaJugador(evt.getEntity(), this.plugin);
    if (j.getPartida() != null) {
      evt.getEntity().getInventory().clear();
      evt.getEntity().getEquipment().clear();
      evt.getEntity().updateInventory();
      evt.setKeepLevel(true);
      evt.setKeepInventory(true);
      Utils.comunicaPerdedor(j, j.getPartida(), this.plugin);
    } 
  }
  
  @EventHandler
  public void onSpawn(PlayerRespawnEvent evt) {
    Player p = evt.getPlayer();
    Jugador j = Utils.buscaJugador(p, this.plugin);
    if (j.isPlaying()) {
      Utils.setInventarioDefault(p, this.plugin);
    }
  }
  
  public RolCards getPlugin() { return this.plugin; }

  
  public void setPlugin(RolCards plugin) { this.plugin = plugin; }
}
