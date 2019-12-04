package com.adri1711.rolcards.listeners;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class Quit
  implements Listener {
  private RolCards plugin;
  
  public Quit(RolCards plugin) { this.plugin = plugin; }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent evt) {
    Player p = evt.getPlayer();
    final Jugador j = Utils.buscaJugador(p, this.plugin);
    if (j.isPlaying()) {
      p.getInventory().clear();
      p.updateInventory();
    } 
    if (j.getPartida() != null) {
      if (j.getPartida().devuelveOtroJugador(j) != null) {
        Utils.comunicaPerdedor(j, j.getPartida(), this.plugin);
      } else {
        j.getPartida().setJugador1(null);
      } 
    }
    Bukkit.getServer().getScheduler().runTaskLater((Plugin)this.plugin, new Runnable() {
          public void run() {
            if (j != null) {
              Quit.this.plugin.getJugadores().remove(j);
            }
          }
        },  80L);
    guardaDatos(p, j);
  }

  
  public RolCards getPlugin() { return this.plugin; }

  
  public void setPlugin(RolCards plugin) { this.plugin = plugin; }
  
  private void guardaDatos(Player p, Jugador j) {
    if (this.plugin.getCards().get(p.getName()) == null) {
      List<String> listaCartas = new ArrayList<>();
      for (Card c : j.getCartas()) {
        listaCartas.add(c.getCardName());
      }
      String clase = "";
      switch (j.getClase()) {
        case NORMAL:
          clase = "normal";
          break;
        case WARRIOR:
          clase = "warrior";
          break;
        case MAGE:
          clase = "mage";
          break;
        case HUNTER:
          clase = "hunter";
          break;
        default:
          clase = "normal";
          break;
      } 
      this.plugin.getCards().addDefault(String.valueOf(p.getName()) + ".class", clase);
      this.plugin.getCards().addDefault(String.valueOf(p.getName()) + ".cards", listaCartas);
      try {
        this.plugin.getCards().save(this.plugin.getCardsFile());
      } catch (IOException e) {
        
        e.printStackTrace();
      } 
    } else {
      List<String> listaCartas = new ArrayList<>();
      for (Card c : j.getCartas()) {
        listaCartas.add(c.getCardName());
      }
      String clase = "";
      switch (j.getClase()) {
        case NORMAL:
          clase = "normal";
          break;
        case WARRIOR:
          clase = "warrior";
          break;
        case MAGE:
          clase = "mage";
          break;
        case HUNTER:
          clase = "hunter";
          break;
        default:
          clase = "normal";
          break;
      } 
      this.plugin.getCards().set(String.valueOf(p.getName()) + ".class", clase);
      this.plugin.getCards().set(String.valueOf(p.getName()) + ".cards", listaCartas);
      try {
        this.plugin.getCards().save(this.plugin.getCardsFile());
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
    try {
      this.plugin.getCards().save(this.plugin.getCardsFile());
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
