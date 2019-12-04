package com.adri1711.rolcards.listeners;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Teleport
  implements Listener {
  private RolCards plugin;
  
  public Teleport(RolCards plugin) { this.plugin = plugin; }
  
  @EventHandler
  public void alIrAlSpawn(PlayerTeleportEvent evt) {
    if (evt.getTo().equals(this.plugin.getSpawnLocation())) {
      Jugador j = Utils.buscaJugador(evt.getPlayer(), this.plugin);
      if (j.isPlaying()) {
        Utils.updateScoreboard(evt.getPlayer(), j, null, this.plugin);
      }
    } 
  }
  
  public RolCards getPlugin() { return this.plugin; }

  
  public void setPlugin(RolCards plugin) { this.plugin = plugin; }
}
