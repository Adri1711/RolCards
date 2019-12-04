package com.adri1711.rolcards.utils;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.monsters.Monster;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;



public class EntitySkill
{
  public static void entityAttackEntity(Jugador j, Jugador enemy, Monster jmon, Monster enemymon, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Player p = j.getP();
    if (j.isTurn()) {
      if (jmon.isReady()) {
        if (plugin.getLanguage().equalsIgnoreCase("es")) {
          p.sendMessage(ChatColor.GREEN + jmon.getName() + 
              " ataco a " + enemymon.getName());
          enemy.getP().sendMessage(
              ChatColor.GREEN + jmon.getName() + " ataco a " + 
              enemymon.getName());
        } else {
          p.sendMessage(ChatColor.GREEN + jmon.getName() + 
              message.getEntityAttackMsg() + enemymon.getName());
          enemy.getP().sendMessage(
              ChatColor.GREEN + jmon.getName() + 
              message.getEntityAttackMsg() + 
              enemymon.getName());
        } 
        Double saludRes = Double.valueOf(enemymon.getSalud().doubleValue() - jmon.getDanio().doubleValue());
        if (saludRes.doubleValue() <= 0.0D) {
          Utils.mataMonstruo(j, enemy, enemymon, plugin);
        } else {
          Utils.actualizaSalud(j, enemy, enemymon, saludRes);
        } 
        Double saludRes2 = Double.valueOf(jmon.getSalud().doubleValue() - enemymon.getDanio().doubleValue());
        if (saludRes.doubleValue() <= 0.0D) {
          Utils.mataMonstruo(j, enemy, jmon, plugin);
        } else {
          Utils.actualizaSalud(j, enemy, jmon, saludRes2);
        } 
        jmon.setReady(false);
      }
      else if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.RED + 
            "Este mob ya ataco este turno");
      } else {
        p.sendMessage(ChatColor.RED + 
            message.getEntityAlreadyAttackedMsg());
      }
    
    }
    else if (plugin.getLanguage().equalsIgnoreCase("es")) {
      p.sendMessage(ChatColor.RED + "Espera tu turno");
    } else {
      p.sendMessage(ChatColor.RED + message.getEntityWaitMsg());
    } 
  }


  
  public static void entityAttackPlayer(Jugador j, Jugador enemy, Monster jmon, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Player p = j.getP();
    Player enemigo = enemy.getP();
    if (j.isTurn()) {
      if (jmon.isReady()) {
        if (plugin.getLanguage().equalsIgnoreCase("es")) {
          p.sendMessage(ChatColor.GREEN + jmon.getName() + 
              " ataco a " + enemigo.getName());
          enemigo.sendMessage(ChatColor.GREEN + jmon.getName() + 
              " ataco a " + enemigo.getName());
        } else {
          p.sendMessage(ChatColor.GREEN + jmon.getName() + 
              message.getEntityAttackMsg() + enemigo.getName());
          enemigo.sendMessage(ChatColor.GREEN + jmon.getName() + 
              message.getEntityAttackMsg() + enemigo.getName());
        } 
        enemigo.damage(jmon.getDanio().doubleValue());
        jmon.setReady(false);
      }
      else if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.RED + 
            "Este mob ya ataco este turno");
      } else {
        p.sendMessage(ChatColor.RED + 
            message.getEntityAlreadyAttackedMsg());
      }
    
    }
    else if (plugin.getLanguage().equalsIgnoreCase("es")) {
      p.sendMessage(ChatColor.RED + "Espera tu turno");
    } else {
      p.sendMessage(ChatColor.RED + message.getEntityWaitMsg());
    } 
  }
}
