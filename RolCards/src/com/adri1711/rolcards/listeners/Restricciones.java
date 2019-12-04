package com.adri1711.rolcards.listeners;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Restricciones
  implements Listener {
  private RolCards plugin;
  private LanguageMessages message;
  
  public Restricciones(RolCards pl) { this.plugin = pl; }

  
  @EventHandler
  public void regenHealth(EntityRegainHealthEvent e) {
    if (e.getEntity() instanceof Player) {
      Player player = (Player)e.getEntity();
      Jugador j = Utils.buscaJugador(player, this.plugin);
      if (j.isPlaying()) {
        e.setCancelled(true);
      } else {
        e.setCancelled(false);
      } 
    } 
  }
  @EventHandler
  public void onItemDrop(PlayerDropItemEvent e) {
    if (this.message == null) {
      this.message = this.plugin.getMessages();
    }
    Player player = e.getPlayer();
    Jugador j = Utils.buscaJugador(player, this.plugin);
    if (j.isPlaying()) {
      if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
        player.sendMessage(ChatColor.RED + "No puedes dropear items jugando.");
      } else {
        player.sendMessage(ChatColor.RED + this.message.getNodropMsg());
      } 
      e.setCancelled(true);
    } else {
      e.setCancelled(false);
    } 
  }
  @EventHandler
  public void onPickupItem(PlayerPickupItemEvent e) {
    Player p = e.getPlayer();
    Jugador j = Utils.buscaJugador(p, this.plugin);
    if (j.isPlaying()) {
      e.setCancelled(true);
    } else {
      e.setCancelled(false);
    } 
  }
  
  @EventHandler
  public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
    if (this.message == null) {
      this.message = this.plugin.getMessages();
    }
    Player p = event.getPlayer();
    Jugador j = Utils.buscaJugador(p, this.plugin);
    if (j.isPlaying() && 
      !event.getMessage().toLowerCase().contains("/rolcards") && !p.hasPermission("rolcards.bypass")) {
      event.setCancelled(true);
      if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.RED + "No puedes usar comandos que no sean de rolcards mientras juegas.");
      } else {
        p.sendMessage(ChatColor.RED + this.message.getNoCommands());
      } 
    } 
  }
}
