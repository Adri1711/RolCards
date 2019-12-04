package com.adri1711.rolcards.peticiones;

import org.bukkit.entity.Player;

public class Peticion {
  private Player from;
  
  public Peticion(Player p1, Player p2) {
    this.from = p1;
    this.to = p2;
  }
  private Player to;
  public Player getFrom() { return this.from; }

  
  public void setFrom(Player from) { this.from = from; }

  
  public Player getTo() { return this.to; }

  
  public void setTo(Player to) { this.to = to; }
}
