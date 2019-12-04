package com.adri1711.rolcards.listeners;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.cards.CardEffect;
import com.adri1711.rolcards.cards.CreatedCard;
import com.adri1711.rolcards.jugador.Fase;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.utils.Utils;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class MatchChat
  implements Listener
{
  private RolCards plugin;
  
  public MatchChat(RolCards plugin) { this.plugin = plugin; }

  
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    Jugador j = Utils.buscaJugador(player, this.plugin);
    if (j.getFase() == Fase.NONE) {
      if (j.getPartida() != null) {
        Jugador j2 = j.getPartida().devuelveOtroJugador(j);
        event.getRecipients().clear();
        event.getRecipients().add(j2.getP());
        event.getRecipients().add(player);
        for (Player pla : Bukkit.getOnlinePlayers()) {
          if (pla.hasPermission("rolcards.socialspy")) {
            event.getRecipients().add(pla);
          }
        } 
      } else {
        event.getRecipients().clear();
        for (Jugador p : this.plugin.getJugadores()) {
          if (p.getPartida() == null) {
            event.getRecipients().add(p.getP());
          }
        } 
        for (Player pla : Bukkit.getOnlinePlayers()) {
          if (pla.hasPermission("rolcards.socialspy")) {
            event.getRecipients().add(pla);
          }
        } 
      } 
    } else {
      checkMessage(event.getMessage(), j);
      event.setCancelled(true);
    } 
  }
  
  private void checkMessage(String message, Jugador j) {
    if (message.equalsIgnoreCase("cancel")) {
      j.setFase(Fase.NONE);
    }
    else if (j.getFase() == Fase.CARDCLASS) {
      String str; switch ((str = message).hashCode()) { case 48: if (!str.equals("0"))
            break; 
          avanzaFase(j);
          j.setClaseCarta(CardClass.NORMAL); return;
        case 49:
          if (!str.equals("1"))
            break;  avanzaFase(j);
          j.setClaseCarta(CardClass.HUNTER); return;
        case 50:
          if (!str.equals("2"))
            break;  avanzaFase(j);
          j.setClaseCarta(CardClass.MAGE); return;
        case 51:
          if (!str.equals("3"))
            break;  avanzaFase(j);
          j.setClaseCarta(CardClass.WARRIOR);
          return; }
      
      enviaMensajes(j);

    
    }
    else if (j.getFase() == Fase.NAME) {
      avanzaFase(j);
      j.setCardName(message.replaceAll("&", "§"));
    } else if (j.getFase() == Fase.PRICE) {
      avanzaFase(j);
      j.setCardPrice(new Integer(message));
    } else if (j.getFase() == Fase.MANA_COST) {
      avanzaFase(j);
      j.setCardCost(new Integer(message));
    } else if (j.getFase() == Fase.PERM) {
      avanzaFase(j);
      j.setCardPermission(message);
    } else if (j.getFase() == Fase.LINE1) {
      if (message.equalsIgnoreCase("none")) {
        avanzaFase(j);
        j.setCardDescriptionLine1("");
      } else {
        avanzaFase(j);
        j.setCardDescriptionLine1(message.replaceAll("&", "§"));
      } 
    } else if (j.getFase() == Fase.LINE2) {
      if (message.equalsIgnoreCase("none")) {
        avanzaFase(j);
        j.setCardDescriptionLine2("");
      } else {
        avanzaFase(j);
        j.setCardDescriptionLine2(message.replaceAll("&", "§"));
      } 
    } else if (j.getFase() == Fase.LINE3) {
      if (message.equalsIgnoreCase("none")) {
        avanzaFase(j);
        j.setCardDescriptionLine3("");
      } else {
        avanzaFase(j);
        j.setCardDescriptionLine3(message.replaceAll("&", "§"));
      } 
    } else if (j.getFase() == Fase.LINE4) {
      if (message.equalsIgnoreCase("none")) {
        avanzaFase(j);
        j.setCardDescriptionLine4("");
      } else {
        avanzaFase(j);
        j.setCardDescriptionLine4(message.replaceAll("&", "§"));
      } 
    } else if (j.getFase() == Fase.LINE5) {
      if (message.equalsIgnoreCase("none")) {
        avanzaFase(j);
        j.setCardDescriptionLine5("");
      } else {
        avanzaFase(j);
        j.setCardDescriptionLine5(message.replaceAll("&", "§"));
      } 
    } else if (j.getFase() == Fase.TYPE) {
      String str; switch ((str = message).hashCode()) { case 48: if (!str.equals("0"))
            break; 
          j.setCardEffect(CardEffect.DAMAGE);
          avanzaFase(j); return;
        case 49:
          if (!str.equals("1"))
            break; 
          j.setCardEffect(CardEffect.HEAL);
          avanzaFase(j); return;
        case 50:
          if (!str.equals("2"))
            break; 
          j.setCardEffect(CardEffect.DRAW);
          avanzaFase(j); return;
        case 51:
          if (!str.equals("3"))
            break; 
          j.setCardEffect(CardEffect.SPAWN);
          avanzaFase(j);
          return; }

      
      enviaMensajes(j);
    
    }
    else if (j.getFase() == Fase.VALUE_TYPE) {
      j.setCardEffectValue(new Integer(message));
      if (j.getCardEffect() != CardEffect.SPAWN) {
        creaCarta(j);
      } else {
        avanzaFase(j);
      }
    
    } else if (j.getFase() == Fase.MOBNAME) {
      j.setCardMobName(message);
      avanzaFase(j);
    }
    else if (j.getFase() == Fase.DAMAGE) {
      j.setCardDamage(new Double(message));
      avanzaFase(j);
    }
    else if (j.getFase() == Fase.HEALTH) {
      j.setCardHealth(new Double(message));
      creaCarta(j);
    } 
  }

  
  private void avanzaFase(Jugador j) {
    switch (j.getFase()) {
      case CARDCLASS:
        j.setFase(Fase.NAME);
        enviaMensajes(j);
        break;
      case NAME:
        j.setFase(Fase.PRICE);
        enviaMensajes(j);
        break;
      case PRICE:
        j.setFase(Fase.MANA_COST);
        enviaMensajes(j);
        break;
      case MANA_COST:
        j.setFase(Fase.PERM);
        enviaMensajes(j);
        break;
      case PERM:
        j.setFase(Fase.LINE1);
        enviaMensajes(j);
        break;
      case LINE1:
        j.setFase(Fase.LINE2);
        enviaMensajes(j);
        break;
      case LINE2:
        j.setFase(Fase.LINE3);
        enviaMensajes(j);
        break;
      case LINE3:
        j.setFase(Fase.LINE4);
        enviaMensajes(j);
        break;
      case LINE4:
        j.setFase(Fase.LINE5);
        enviaMensajes(j);
        break;
      case LINE5:
        j.setFase(Fase.TYPE);
        enviaMensajes(j);
        break;
      case TYPE:
        j.setFase(Fase.VALUE_TYPE);
        enviaMensajes(j);
        break;
      case VALUE_TYPE:
        j.setFase(Fase.MOBNAME);
        enviaMensajes(j);
        break;
      case MOBNAME:
        j.setFase(Fase.DAMAGE);
        enviaMensajes(j);
        break;
      case DAMAGE:
        j.setFase(Fase.HEALTH);
        enviaMensajes(j);
        break;
    } 
  }





  
  private void enviaMensajes(Jugador j) {
    Player p = j.getP();
    switch (j.getFase()) {
      case CARDCLASS:
        p.sendMessage("§8[RolCards] §2Choose a class for the card.(Write the number)");
        p.sendMessage("     §a0 §e-> §aNormal");
        p.sendMessage("     §a1 §e-> §aHunter");
        p.sendMessage("     §a2 §e-> §aMage");
        p.sendMessage("     §a3 §e-> §aWarrior");
        break;
      case NAME:
        p.sendMessage("§8[RolCards] §2Write a name for the card.");
        break;
      case PRICE:
        p.sendMessage("§8[RolCards] §2Write a price for the card.(Write a number)");
        break;
      case MANA_COST:
        p.sendMessage("§8[RolCards] §2Write a mana cost for the card.(Write a number)");
        break;
      case PERM:
        p.sendMessage("§8[RolCards] §2Write the perm the card.");
        break;
      case LINE1:
        p.sendMessage("§8[RolCards] §2Write the 1 description line of the card.(Put 'none' if you dont want to put this line)");
        break;
      case LINE2:
        p.sendMessage("§8[RolCards] §2Write the 2 description line of the card.(Put 'none' if you dont want to put this line)");
        break;
      case LINE3:
        p.sendMessage("§8[RolCards] §2Write the 3 description line of the card.(Put 'none' if you dont want to put this line)");
        break;
      case LINE4:
        p.sendMessage("§8[RolCards] §2Write the 4 description line of the card.(Put 'none' if you dont want to put this line)");
        break;
      case LINE5:
        p.sendMessage("§8[RolCards] §2Write the 5 description line of the card.(Put 'none' if you dont want to put this line)");
        break;
      case TYPE:
        p.sendMessage("§8[RolCards] §2Choose an effect for the card.(Write the number)");
        p.sendMessage("     §a0 §e-> §aDamage");
        p.sendMessage("     §a1 §e-> §aHeal");
        p.sendMessage("     §a2 §e-> §aDraw");
        p.sendMessage("     §a3 §e-> §aSpawn Mob");
        break;
      case VALUE_TYPE:
        switch (j.getCardEffect()) {
          case DAMAGE:
            p.sendMessage("§8[RolCards] §2Set the amount of damage.(Write a number)");
            break;
          case HEAL:
            p.sendMessage("§8[RolCards] §2Set the amount of heal.(Write a number)");
            break;
          case DRAW:
            p.sendMessage("§8[RolCards] §2Set the amount of cards to draw.(Write a number)");
            break;
          case SPAWN:
            p.sendMessage("§8[RolCards] §2Set the entity to spawn.(Write a number)");
            p.sendMessage("     §a0 §e-> §aSpider");
            p.sendMessage("     §a1 §e-> §aCave Spider");
            p.sendMessage("     §a2 §e-> §aZombie");
            p.sendMessage("     §a3 §e-> §aPigZombie");
            p.sendMessage("     §a4 §e-> §aPig");
            p.sendMessage("     §a5 §e-> §aRabbit");
            p.sendMessage("     §a6 §e-> §aChicken");
            p.sendMessage("     §a7 §e-> §aCow");
            p.sendMessage("     §a8 §e-> §aSheep");
            p.sendMessage("     §a9 §e-> §aVillager");
            p.sendMessage("     §a10 §e-> §aSlime");
            p.sendMessage("     §a11 §e-> §aWitch");
            p.sendMessage("     §a12 §e-> §aSilverfish");
            p.sendMessage("     §a13 §e-> §aMagma Cube");
            p.sendMessage("     §a14 §e-> §aHorse");
            break;
        } 

        
        break;
      
      case MOBNAME:
        p.sendMessage("§8[RolCards] §2Set the name of the mob.");
        break;
      case DAMAGE:
        p.sendMessage("§8[RolCards] §2Set the damage of the mob.(Write the number)");
        break;
      case HEALTH:
        p.sendMessage("§8[RolCards] §2Set the health of the mob.(Write the number)");
        break;
    } 
  }



  
  private void creaCarta(Jugador j) {
    CreatedCard c;
    if (j.getCardEffect() == CardEffect.SPAWN) {
      if (j.getCardDescriptionLine5() == "") {
        if (j.getCardDescriptionLine4() == "") {
          if (j.getCardDescriptionLine3() == "") {
            if (j.getCardDescriptionLine2() == "") {
              c = new CreatedCard(j.getClaseCarta(), 
                  j.getCardName(), j.getCardPrice(), 
                  j.getCardCost(), j.getCardPermission(), 
                  j.getCardDescriptionLine1(), 
                  j.getCardEffect(), j.getCardEffectValue(), 
                  j.getCardMobName(), j.getCardDamage(), 
                  j.getCardHealth(), this.plugin);
            } else {
              c = new CreatedCard(j.getClaseCarta(), 
                  j.getCardName(), j.getCardPrice(), 
                  j.getCardCost(), j.getCardPermission(), 
                  j.getCardDescriptionLine1(), 
                  j.getCardDescriptionLine2(), 
                  j.getCardEffect(), j.getCardEffectValue(), 
                  j.getCardMobName(), j.getCardDamage(), 
                  j.getCardHealth(), this.plugin);
            } 
          } else {
            c = new CreatedCard(j.getClaseCarta(), j.getCardName(), 
                j.getCardPrice(), j.getCardCost(), 
                j.getCardPermission(), 
                j.getCardDescriptionLine1(), 
                j.getCardDescriptionLine2(), 
                j.getCardDescriptionLine3(), j.getCardEffect(), 
                j.getCardEffectValue(), j.getCardMobName(), 
                j.getCardDamage(), j.getCardHealth(), this.plugin);
          } 
        } else {
          c = new CreatedCard(j.getClaseCarta(), j.getCardName(), 
              j.getCardPrice(), j.getCardCost(), 
              j.getCardPermission(), j.getCardDescriptionLine1(), 
              j.getCardDescriptionLine2(), 
              j.getCardDescriptionLine3(), 
              j.getCardDescriptionLine4(), j.getCardEffect(), 
              j.getCardEffectValue(), j.getCardMobName(), 
              j.getCardDamage(), j.getCardHealth(), this.plugin);
        } 
      } else {
        c = new CreatedCard(j.getClaseCarta(), j.getCardName(), 
            j.getCardPrice(), j.getCardCost(), 
            j.getCardPermission(), j.getCardDescriptionLine1(), 
            j.getCardDescriptionLine2(), 
            j.getCardDescriptionLine3(), 
            j.getCardDescriptionLine4(), 
            j.getCardDescriptionLine5(), j.getCardEffect(), 
            j.getCardEffectValue(), j.getCardMobName(), 
            j.getCardDamage(), j.getCardHealth(), this.plugin);
      }
    
    } else if (j.getCardDescriptionLine5() == "") {
      if (j.getCardDescriptionLine4() == "") {
        if (j.getCardDescriptionLine3() == "") {
          if (j.getCardDescriptionLine2() == "") {
            c = new CreatedCard(j.getClaseCarta(), 
                j.getCardName(), j.getCardPrice(), 
                j.getCardCost(), j.getCardPermission(), 
                j.getCardDescriptionLine1(), 
                j.getCardEffect(), j.getCardEffectValue(), 
                this.plugin);
          } else {
            c = new CreatedCard(j.getClaseCarta(), 
                j.getCardName(), j.getCardPrice(), 
                j.getCardCost(), j.getCardPermission(), 
                j.getCardDescriptionLine1(), 
                j.getCardDescriptionLine2(), 
                j.getCardEffect(), j.getCardEffectValue(), 
                this.plugin);
          } 
        } else {
          c = new CreatedCard(j.getClaseCarta(), j.getCardName(), 
              j.getCardPrice(), j.getCardCost(), 
              j.getCardPermission(), 
              j.getCardDescriptionLine1(), 
              j.getCardDescriptionLine2(), 
              j.getCardDescriptionLine3(), j.getCardEffect(), 
              j.getCardEffectValue(), this.plugin);
        } 
      } else {
        c = new CreatedCard(j.getClaseCarta(), j.getCardName(), 
            j.getCardPrice(), j.getCardCost(), 
            j.getCardPermission(), j.getCardDescriptionLine1(), 
            j.getCardDescriptionLine2(), 
            j.getCardDescriptionLine3(), 
            j.getCardDescriptionLine4(), j.getCardEffect(), 
            j.getCardEffectValue(), this.plugin);
      } 
    } else {
      c = new CreatedCard(j.getClaseCarta(), j.getCardName(), 
          j.getCardPrice(), j.getCardCost(), 
          j.getCardPermission(), j.getCardDescriptionLine1(), 
          j.getCardDescriptionLine2(), 
          j.getCardDescriptionLine3(), 
          j.getCardDescriptionLine4(), 
          j.getCardDescriptionLine5(), j.getCardEffect(), 
          j.getCardEffectValue(), this.plugin);
    } 

    
    j.getP().sendMessage(
        "§8[RolCards] §2You have succesfully created the card.");
    j.setFase(Fase.NONE);
    List<String> lista = this.plugin.getConfig().getStringList("createdCards");
    switch (c.getClase()) {
      case NORMAL:
        lista.add("NORMAL");
        break;
      case HUNTER:
        lista.add("HUNTER");
        break;
      case MAGE:
        lista.add("MAGE");
        break;
      case WARRIOR:
        lista.add("WARRIOR");
        break;
    } 

    
    lista.add(c.getCardName());
    lista.add(c.getCardPrice().toString());
    lista.add(c.getCardCost().toString());
    lista.add(c.getCardPermission());
    lista.add(c.getCardDescriptionLine1());
    lista.add(c.getCardDescriptionLine2());
    lista.add(c.getCardDescriptionLine3());
    lista.add(c.getCardDescriptionLine4());
    lista.add(c.getCardDescriptionLine5());
    switch (c.getCardEffect()) {
      case DAMAGE:
        lista.add("DAMAGE");
        break;
      case HEAL:
        lista.add("HEAL");
        break;
      case DRAW:
        lista.add("DRAW");
        break;
      case SPAWN:
        lista.add("SPAWN");
        break;
    } 

    
    lista.add(c.getCardEffectValue().toString());
    if (c.getCardEffect() == CardEffect.SPAWN) {
      lista.add(c.getCardMobName());
      lista.add(c.getCardDamage().toString());
      lista.add(c.getCardHealth().toString());
    } 
    
    this.plugin.getConfig().set("createdCards", lista);
    this.plugin.saveConfig();
  }
}
