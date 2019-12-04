package com.adri1711.rolcards.listeners;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.monsters.Monster;
import com.adri1711.rolcards.peticiones.Peticion;
import com.adri1711.rolcards.utils.EntitySkill;
import com.adri1711.rolcards.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class CardGUI implements Listener {
  private RolCards plugin;
  private static RolCards pluginStatic;
  private LanguageMessages message;
  
  public CardGUI(RolCards plugin) {
    this.plugin = plugin;
    setPluginStatic(plugin);
  }
  public static void openGUI(Player p) {
    LanguageMessages messages = pluginStatic.getMessages();
    p.playSound(p.getLocation(), 
        Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    Jugador j = Utils.buscaJugador(p, getPluginStatic());
    
    if (j != null) {
      Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + 
          "Cards Selector");
      if (j.getClase() == CardClass.NORMAL) {
        ItemStack buscar = new ItemStack(Material.REDSTONE);
        ItemMeta buscarMeta = buscar.getItemMeta();
        if (pluginStatic.getLanguage().equalsIgnoreCase("es")) {
          buscarMeta.setDisplayName(ChatColor.RED + "Guerrero");
        } else {
          buscarMeta.setDisplayName(ChatColor.RED + messages.getGuiClassWarriorMsg());
        } 
        buscar.setItemMeta(buscarMeta);
        ItemStack borrar = new ItemStack(Material.WHEAT);
        ItemMeta borrarMeta = borrar.getItemMeta();
        if (pluginStatic.getLanguage().equalsIgnoreCase("es")) {
          borrarMeta.setDisplayName(ChatColor.GREEN + "Cazador");
        } else {
          borrarMeta.setDisplayName(ChatColor.GREEN + messages.getGuiClassHunterMsg());
        } 
        borrar.setItemMeta(borrarMeta);
        ItemStack peticiones = new ItemStack(Material.GLOWSTONE_DUST);
        ItemMeta peticionesMeta = peticiones.getItemMeta();
        if (pluginStatic.getLanguage().equalsIgnoreCase("es")) {
          peticionesMeta.setDisplayName(ChatColor.BLUE + "Mago");
        } else {
          peticionesMeta.setDisplayName(ChatColor.BLUE + messages.getGuiClassMageMsg());
        } 
        peticiones.setItemMeta(peticionesMeta);
        inv.setItem(0, buscar);
        inv.setItem(4, peticiones);
        inv.setItem(8, borrar);
      } else {
        ItemStack comp = new ItemStack(Material.GOLD_INGOT);
        ItemMeta compMeta = comp.getItemMeta();
        if (pluginStatic.getLanguage().equalsIgnoreCase("es")) {
          compMeta.setDisplayName(ChatColor.GREEN + "Tienda de Cartas");
        } else {
          compMeta.setDisplayName(ChatColor.GREEN + messages.getGuiCardShopMsg());
        } 
        comp.setItemMeta(compMeta);
        ItemStack abandonar = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta abandonarMeta = abandonar.getItemMeta();
        if (pluginStatic.getLanguage().equalsIgnoreCase("es")) {
          abandonarMeta.setDisplayName(ChatColor.RED + "Borra el mazo");
        } else {
          abandonarMeta.setDisplayName(ChatColor.RED + messages.getGuiClearDeckMsg());
        } 
        abandonar.setItemMeta(abandonarMeta);
        ItemStack abandonarclase = new ItemStack(Material.BARRIER);
        ItemMeta abandonarclaseMeta = abandonarclase.getItemMeta();
        if (pluginStatic.getLanguage().equalsIgnoreCase("es")) {
          abandonarclaseMeta.setDisplayName(ChatColor.RED + "Vuelve a la clase normal");
        } else {
          abandonarclaseMeta.setDisplayName(ChatColor.RED + messages.getGuiReturnMsg());
        } 
        abandonarclase.setItemMeta(abandonarclaseMeta);
        ItemStack bbj = new ItemStack(Material.ENDER_PEARL);
        ItemMeta bbjMeta = bbj.getItemMeta();
        if (pluginStatic.getLanguage().equalsIgnoreCase("es")) {
          bbjMeta.setDisplayName(ChatColor.YELLOW + "Escoge cartas de clase");
        } else {
          bbjMeta.setDisplayName(ChatColor.YELLOW + messages.getGuiClassCardsMsg());
        } 
        bbj.setItemMeta(bbjMeta);
        ItemStack bbtj = new ItemStack(Material.EYE_OF_ENDER);
        ItemMeta bbtjMeta = bbtj.getItemMeta();
        if (pluginStatic.getLanguage().equalsIgnoreCase("es")) {
          bbtjMeta.setDisplayName(ChatColor.YELLOW + "Escoge cartas normales");
        } else {
          bbtjMeta.setDisplayName(ChatColor.YELLOW + messages.getGuiNormalCardsMsg());
        } 
        bbtj.setItemMeta(bbtjMeta);
        inv.setItem(0, comp);
        inv.setItem(8, abandonar);
        inv.setItem(7, abandonarclase);
        inv.setItem(3, bbj);
        inv.setItem(5, bbtj);
      } 
      p.openInventory(inv);
    } 
  }
  @EventHandler
  public void onInventoryClick(InventoryClickEvent event) {
    if (this.message == null) {
      this.message = this.plugin.getMessages();
    }
    
    if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Cards Selector") && 
      !ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Requests") && 
      !ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Cards Shop") && 
      !ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Target") && 
      !ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Cards Menu") && 
      !ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Mobs Menu")) {
      Player player = (Player)event.getWhoClicked();
      Jugador j = Utils.buscaJugador(player, this.plugin);
      if (j.isPlaying())
        event.setCancelled(true); 
      return;
    } 
    if (ChatColor.stripColor(event.getInventory().getName())
      .equalsIgnoreCase("Cards Selector")) {
      Player player = (Player)event.getWhoClicked();
      Jugador j = Utils.buscaJugador(player, this.plugin);
      event.setCancelled(true);
      if (event.getCurrentItem() == null || 
        event.getCurrentItem().getType() == Material.AIR || 
        !event.getCurrentItem().hasItemMeta()) {
        player.closeInventory();
        return;
      } 
      switch (event.getCurrentItem().getType()) {
        case REDSTONE:
          j.setClase(CardClass.WARRIOR);
          if (!player.hasPermission("rolcards.class.warrior")) {
            this.plugin.addPerm("rolcards.class.warrior", player);
          }
          player.closeInventory();
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          player.closeInventory();
          openGUI(player);
          return;
        case GLOWSTONE_DUST:
          j.setClase(CardClass.MAGE);
          if (!player.hasPermission("rolcards.class.mage")) {
            this.plugin.addPerm("rolcards.class.mage", player);
          }
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          player.closeInventory();
          openGUI(player);
          return;
        case WHEAT:
          j.setClase(CardClass.HUNTER);
          if (!player.hasPermission("rolcards.class.hunter")) {
            this.plugin.addPerm("rolcards.class.hunter", player);
          }
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          player.closeInventory();
          openGUI(player);
          return;
        case GOLD_INGOT:
          player.closeInventory();
          openShopGUI(player, j.getClase(), this.plugin, Integer.valueOf(0));
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          return;
        case ENDER_PEARL:
          player.closeInventory();
          openCardGUI(player, j.getClase(), this.plugin, Integer.valueOf(0));
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          return;
        case EYE_OF_ENDER:
          player.closeInventory();
          openCardGUI(player, CardClass.NORMAL, this.plugin, Integer.valueOf(0));
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          return;
        case REDSTONE_BLOCK:
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 1.0F);
          if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
            player.sendMessage(ChatColor.YELLOW + "Mazo borrado");
          } else {
            player.sendMessage(ChatColor.YELLOW + this.message.getGuiDeckDeletedMsg());
          } 
          j.getCartas().clear();
          j.getCartasCopia().clear();
          player.closeInventory();
          return;
        case BARRIER:
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 1.0F);
          if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
            player.sendMessage(ChatColor.YELLOW + "Has vuelto a la clase normal");
          } else {
            player.sendMessage(ChatColor.YELLOW + this.message.getGuiReturnedMsg());
          } 
          j.getCartas().clear();
          j.getCartasCopia().clear();
          j.setClase(CardClass.NORMAL);
          player.closeInventory();
          return;
      } 
      player.playSound(player.getLocation(), 
          Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
      player.closeInventory();
    
    }
    else if (ChatColor.stripColor(event.getInventory().getName())
      .equalsIgnoreCase("Requests")) {
      Peticion pet; Player aux, player = (Player)event.getWhoClicked();
      event.setCancelled(true);
      if (event.getCurrentItem() == null || 
        event.getCurrentItem().getType() == Material.AIR || 
        !event.getCurrentItem().hasItemMeta()) {
        player.closeInventory();
        return;
      } 
      switch (event.getCurrentItem().getType()) {
        case SKULL_ITEM:
          aux = null;
          pet = null;
          for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equals(event.getCurrentItem().getItemMeta().getDisplayName())) {
              aux = p;
              break;
            } 
          } 
          for (Peticion c : RolCards.getPeticiones()) {
            if (c.getTo().getName().equalsIgnoreCase(player.getName()) && c.getFrom().getName().equalsIgnoreCase(event.getCurrentItem().getItemMeta().getDisplayName())) {
              pet = c;
              break;
            } 
          } 
          RolCards.getPeticiones().remove(pet);
          if (aux != null) {
            Jugador j = Utils.buscaJugador(aux, this.plugin);
            Jugador j2 = Utils.buscaJugador(player, this.plugin);
            if (j.isPlaying() && j2.isPlaying()) {
              if (this.plugin.getJoinMatchFee().intValue() > 0) {
                if (puedenPagar(aux, player, this.plugin)) {
                  Utils.buscaPartida(aux, player, this.plugin);
                }
                else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
                  player.sendMessage(ChatColor.RED + "Uno de los dos jugadores no pueden pagar la cuota de entrada");
                } else {
                  player.sendMessage(ChatColor.RED + this.message.getGuiNoMoneyFeeMsg());
                } 
              } else {
                
                Utils.buscaPartida(aux, player, this.plugin);
              }
            
            } else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
              player.sendMessage(ChatColor.RED + "Ambos deben estar jugando RolCards");
            } else {
              player.sendMessage(ChatColor.RED + this.message.getGuiBothPlayingMsg());
            }
          
          }
          else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
            player.sendMessage(ChatColor.RED + "Este jugador no esta online");
          } else {
            player.sendMessage(ChatColor.RED + this.message.getGuiOfflinePlayerMsg());
          } 
          
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          player.closeInventory();
          return;
      } 
      player.playSound(player.getLocation(), 
          Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
      player.closeInventory();
    
    }
    else if (ChatColor.stripColor(event.getInventory().getName())
      .equalsIgnoreCase("Cards Menu")) {
      Card c2, c1, c; Player player = (Player)event.getWhoClicked();
      event.setCancelled(true);
      if (event.getCurrentItem() == null || 
        event.getCurrentItem().getType() == Material.AIR || 
        !event.getCurrentItem().hasItemMeta()) {
        player.closeInventory();
        return;
      } 
      Jugador j = Utils.buscaJugador(player, this.plugin);
      switch (event.getCurrentItem().getType()) {
        
        case BOOK:
          c = Utils.buscaCarta(event.getCurrentItem().getItemMeta().getDisplayName(), this.plugin);
          if (event.isLeftClick()) {
            if (event.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.LUCK) != 2) {
              j.addCarta(c);
            }
            else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
              player.sendMessage("Ya tienes dos copias de esta carta en tu mazo");
            } else {
              player.sendMessage(this.message.getGuiTwoCopiesMsg());
            }
          
          } else if (event.isRightClick()) {
            if (event.getCurrentItem().getItemMeta().getEnchantLevel(Enchantment.LUCK) != 0) {
              j.removeCard(c);
            }
            else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
              player.sendMessage("No tienes ninguna carta de este tipo");
            } else {
              player.sendMessage(this.message.getGuiNoCardInDeckMsg());
            } 
          } 
          
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);

          
          updateCardGUI(event.getInventory(), player, c.getClase(), this.plugin, j.getPage());
          return;
        case EMERALD_BLOCK:
          c1 = Utils.buscaCarta(event.getInventory().getItem(0).getItemMeta().getDisplayName(), this.plugin);
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          updateCardGUI(event.getInventory(), player, c1.getClase(), this.plugin, Integer.valueOf(j.getPage().intValue() + 1));
          return;
        case REDSTONE_BLOCK:
          c2 = Utils.buscaCarta(event.getInventory().getItem(0).getItemMeta().getDisplayName(), this.plugin);
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          updateCardGUI(event.getInventory(), player, c2.getClase(), this.plugin, Integer.valueOf(j.getPage().intValue() - 1));
          return;
      } 
      player.playSound(player.getLocation(), 
          Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
      player.closeInventory();
    
    }
    else if (ChatColor.stripColor(event.getInventory().getName())
      .equalsIgnoreCase("Cards Shop")) {
      Card c; Player player1, player = (Player)event.getWhoClicked();
      event.setCancelled(true);
      if (event.getCurrentItem() == null || 
        event.getCurrentItem().getType() == Material.AIR || 
        !event.getCurrentItem().hasItemMeta()) {
        player.closeInventory();
        return;
      } 
      Jugador j = Utils.buscaJugador(player, this.plugin);
      switch (event.getCurrentItem().getType()) {
        case BOOK:
          player1 = player;
          c = Utils.buscaCarta(event.getCurrentItem().getItemMeta().getDisplayName(), this.plugin);
          if (RolCards.getEconomy().getBalance((OfflinePlayer)player1) >= c.getCardPrice().intValue()) {
            this.plugin.addPerm(c.getCardPermission(), player);
            RolCards.getEconomy().withdrawPlayer((OfflinePlayer)player1, c.getCardPrice().intValue());
            if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
              player.sendMessage(ChatColor.GREEN + "Has comprado " + c.getCardName());
            } else {
              player.sendMessage(ChatColor.GREEN + this.message.getGuiCardBuyMsg() + c.getCardName());
            }
          
          } else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
            player.sendMessage(ChatColor.RED + "No tienes suficiente dinero");
          } else {
            player.sendMessage(ChatColor.RED + this.message.getGuiCardNoMoneyMsg());
          } 
          
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          player.closeInventory();
          openShopGUI(player, j.getClase(), this.plugin, j.getPage());
          return;
        case EMERALD_BLOCK:
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          openShopGUI(player, j.getClase(), this.plugin, Integer.valueOf(j.getPage().intValue() - 1));
          return;
        case REDSTONE_BLOCK:
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          openShopGUI(player, j.getClase(), this.plugin, Integer.valueOf(j.getPage().intValue() - 1));
          return;
        case STICK:
          if (RolCards.getEconomy().getBalance((OfflinePlayer)player) >= this.plugin.getLotMoney().doubleValue()) {
            RolCards.getEconomy().withdrawPlayer((OfflinePlayer)player, this.plugin.getLotMoney().doubleValue());
            temporizador(player, player.getLocation(), Integer.valueOf(7), Color.RED);
          }
          else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
            player.sendMessage(ChatColor.RED + "No tienes suficiente dinero");
          } else {
            player.sendMessage(ChatColor.RED + this.message.getGuiCardNoMoneyMsg());
          } 
          
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          player.closeInventory();
          return;
      } 
      
      player.playSound(player.getLocation(), 
          Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
      player.closeInventory();
    
    }
    else if (ChatColor.stripColor(event.getInventory().getName())
      .equalsIgnoreCase("Target")) {
      Monster mon; Jugador enemy; Player player = (Player)event.getWhoClicked();
      Jugador j = Utils.buscaJugador(player, this.plugin);
      event.setCancelled(true);
      if (event.getCurrentItem() == null || 
        event.getCurrentItem().getType() == Material.AIR || 
        !event.getCurrentItem().hasItemMeta()) {
        player.closeInventory();
        return;
      } 
      switch (event.getCurrentItem().getType()) {
        case SKULL_ITEM:
          Utils.doSkillToPlayer(j, j.getPartida().devuelveOtroJugador(j), Utils.buscaCarta(event.getInventory().getItem(7).getItemMeta().getDisplayName(), this.plugin), this.plugin);
          player.closeInventory();
          return;
        case GOLDEN_CARROT:
          enemy = j.getPartida().devuelveOtroJugador(j);
          mon = Utils.buscaMonstruo(enemy, event.getCurrentItem().getItemMeta().getLore().get(0));
          if (mon == null) {
            mon = Utils.buscaMonstruo(j, event.getCurrentItem().getItemMeta().getLore().get(0));
          }
          if (mon != null) {
            Utils.doSkillToEntity(j, enemy, mon, Utils.buscaCarta(event.getInventory().getItem(7).getItemMeta().getDisplayName(), this.plugin), this.plugin);
          }
          player.closeInventory();
          return;
      } 
      player.playSound(player.getLocation(), 
          Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
      player.closeInventory();
    
    }
    else if (ChatColor.stripColor(event.getInventory().getName())
      .equalsIgnoreCase("Mobs Menu")) {
      Jugador enemy; Player player = (Player)event.getWhoClicked();
      Jugador j = Utils.buscaJugador(player, this.plugin);
      event.setCancelled(true);
      if (event.getCurrentItem() == null || 
        event.getCurrentItem().getType() == Material.AIR || 
        !event.getCurrentItem().hasItemMeta()) {
        player.closeInventory();
        return;
      } 
      switch (event.getCurrentItem().getType()) {
        case SKULL_ITEM:
          EntitySkill.entityAttackPlayer(j, j.getPartida().devuelveOtroJugador(j), Utils.buscaMonstruo(j, event.getInventory().getItem(7).getItemMeta().getLore().get(0)), this.plugin);
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          player.closeInventory();
          return;
        case GOLDEN_CARROT:
          enemy = j.getPartida().devuelveOtroJugador(j);
          openEnemyMobsGUI(j, enemy, event.getCurrentItem());
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          return;
        case CARROT:
          EntitySkill.entityAttackEntity(j, j.getPartida().devuelveOtroJugador(j), Utils.buscaMonstruo(j, event.getInventory().getItem(7).getItemMeta().getLore().get(0)), Utils.buscaMonstruo(j.getPartida().devuelveOtroJugador(j), event.getCurrentItem().getItemMeta().getLore().get(0)), this.plugin);
          player.playSound(player.getLocation(), 
              Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
          player.closeInventory();
          return;
      } 
      player.playSound(player.getLocation(), 
          Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
      player.closeInventory();
    } 
  }


  
  private boolean puedenPagar(Player aux, Player player, RolCards plugin) {
    boolean res = false;
    Player player1 = player;
    Player player2 = aux;
    if (RolCards.getEconomy().getBalance((OfflinePlayer)player1) >= plugin.getJoinMatchFee().intValue() && RolCards.getEconomy().getBalance((OfflinePlayer)player2) >= plugin.getJoinMatchFee().intValue()) {
      res = true;
      RolCards.getEconomy().withdrawPlayer((OfflinePlayer)player1, plugin.getJoinMatchFee().intValue());
      RolCards.getEconomy().withdrawPlayer((OfflinePlayer)player2, plugin.getJoinMatchFee().intValue());
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        player.sendMessage(ChatColor.GREEN + "Has pagado " + plugin.getJoinMatchFee() + " por entrar en partida");
        aux.sendMessage(ChatColor.GREEN + "Has pagado " + plugin.getJoinMatchFee() + " por entrar en partida");
      } else {
        player.sendMessage(ChatColor.GREEN + this.message.getGuiPaidFeeMsg() + plugin.getJoinMatchFee());
        aux.sendMessage(ChatColor.GREEN + this.message.getGuiPaidFeeMsg() + plugin.getJoinMatchFee());
      } 
    } 
    return res;
  }
  private void openCardGUI(Player player, CardClass clase, RolCards plugin, Integer numero) {
    Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_GREEN + 
        "Cards Menu");
    LanguageMessages messages = plugin.getMessages();
    Jugador j = Utils.buscaJugador(player, plugin);
    j.setPage(numero);
    List<List<Card>> listaCartas = new ArrayList<>();
    List<Card> cartas = null;
    List<Card> cartas2 = null;
    List<Card> cards = null;
    
    if (clase == CardClass.NORMAL) {
      cartas2 = plugin.getNormalCards().getNormalCards();
    } else if (clase == CardClass.HUNTER) {
      cartas2 = plugin.getHunterCards().getHunterCards();
    } else if (clase == CardClass.MAGE) {
      cartas2 = plugin.getMageCards().getMageCards();
    } else if (clase == CardClass.WARRIOR) {
      cartas2 = plugin.getWarriorCards().getWarriorCards();
    } 
    cartas = cartasTienePermiso(player, cartas2);
    if (cartas.size() > 34) {
      int i2 = 0;
      while (cartas.size() - i2 > 33) {
        if (i2 == 0 || cartas.size() - i2 == 34) {
          listaCartas.add(cartas.subList(i2, i2 + 34));
          i2 += 34; continue;
        } 
        listaCartas.add(cartas.subList(i2, i2 + 33));
        i2 += 33;
      } 
      
      listaCartas.add(cartas.subList(i2, cartas.size()));
      cards = listaCartas.get(numero.intValue());
      if (cards != null) {
        int i = 0;
        if (cards.size() == 34) {
          for (Card c : cards) {
            if (player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 1, carta);
              } 
              i++;
            } 
          } 
          if (listaCartas.indexOf(cards) == 0) {
            ItemStack postpage = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta postpageMeta = postpage.getItemMeta();
            postpageMeta.setDisplayName(messages.getGuiItemPostpageMsg());
            postpage.setItemMeta(postpageMeta);
            inv.setItem(27, postpage);
          } else {
            ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta prepageMeta = prepage.getItemMeta();
            prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
            prepage.setItemMeta(prepageMeta);
            inv.setItem(27, prepage);
          }
        
        } else if (listaCartas.indexOf(cards) == listaCartas.size() - 1) {
          for (Card c : cards) {
            if (player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 1, carta);
              } 
              i++;
            } 
          } 
          ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
          ItemMeta prepageMeta = prepage.getItemMeta();
          prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
          prepage.setItemMeta(prepageMeta);
          inv.setItem(27, prepage);
        } else {
          for (Card c : cards) {
            if (player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 2, carta);
              } 
              i++;
            } 
          } 
          ItemStack postpage = new ItemStack(Material.EMERALD_BLOCK);
          ItemMeta postpageMeta = postpage.getItemMeta();
          postpageMeta.setDisplayName(messages.getGuiItemPostpageMsg());
          postpage.setItemMeta(postpageMeta);
          inv.setItem(28, postpage);
          ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
          ItemMeta prepageMeta = prepage.getItemMeta();
          prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
          prepage.setItemMeta(prepageMeta);
          inv.setItem(27, prepage);
        }
      
      }
    
    } else {
      
      cards = cartas;
      int i = 0;
      for (Card c : cards) {
        if (player.hasPermission(c.getCardPermission()) && 
          i < 35) {
          ItemStack carta = Utils.transformaCartaGUI(c, plugin);
          if (!j.getCartas().isEmpty()) {
            for (Card c1 : j.getCartas()) {
              if (c1.getCardName().equals(c.getCardName())) {
                ItemMeta cartaMeta = carta.getItemMeta();
                cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                carta.setItemMeta(cartaMeta);
              } 
            } 
          }
          inv.setItem(i, carta);
          i++;
        } 
      } 
    } 

    
    ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK);
    goldBlock.setAmount(j.getCartas().size());
    inv.setItem(inv.getSize() - 1, goldBlock);
    player.openInventory(inv);
  }


  
  private void updateCardGUI(Inventory inv, Player player, CardClass clase, RolCards plugin, Integer numero) {
    inv.clear();
    LanguageMessages messages = plugin.getMessages();
    Jugador j = Utils.buscaJugador(player, plugin);
    j.setPage(numero);
    List<List<Card>> listaCartas = new ArrayList<>();
    List<Card> cartas = null;
    List<Card> cartas2 = null;
    List<Card> cards = null;
    
    if (clase == CardClass.NORMAL) {
      cartas2 = plugin.getNormalCards().getNormalCards();
    } else if (clase == CardClass.HUNTER) {
      cartas2 = plugin.getHunterCards().getHunterCards();
    } else if (clase == CardClass.MAGE) {
      cartas2 = plugin.getMageCards().getMageCards();
    } else if (clase == CardClass.WARRIOR) {
      cartas2 = plugin.getWarriorCards().getWarriorCards();
    } 
    cartas = cartasTienePermiso(player, cartas2);
    if (cartas.size() > 34) {
      int i2 = 0;
      while (cartas.size() - i2 > 33) {
        if (i2 == 0 || cartas.size() - i2 == 34) {
          listaCartas.add(cartas.subList(i2, i2 + 34));
          i2 += 34; continue;
        } 
        listaCartas.add(cartas.subList(i2, i2 + 33));
        i2 += 33;
      } 
      
      listaCartas.add(cartas.subList(i2, cartas.size()));
      cards = listaCartas.get(numero.intValue());
      if (cards != null) {
        int i = 0;
        if (cards.size() == 34) {
          for (Card c : cards) {
            if (player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 1, carta);
              } 
              i++;
            } 
          } 
          if (listaCartas.indexOf(cards) == 0) {
            ItemStack postpage = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta postpageMeta = postpage.getItemMeta();
            postpageMeta.setDisplayName(messages.getGuiItemPostpageMsg());
            postpage.setItemMeta(postpageMeta);
            inv.setItem(27, postpage);
          } else {
            ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta prepageMeta = prepage.getItemMeta();
            prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
            prepage.setItemMeta(prepageMeta);
            inv.setItem(27, prepage);
          }
        
        } else if (listaCartas.indexOf(cards) == listaCartas.size() - 1) {
          for (Card c : cards) {
            if (player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 1, carta);
              } 
              i++;
            } 
          } 
          ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
          ItemMeta prepageMeta = prepage.getItemMeta();
          prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
          prepage.setItemMeta(prepageMeta);
          inv.setItem(27, prepage);
        } else {
          for (Card c : cards) {
            if (player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 2, carta);
              } 
              i++;
            } 
          } 
          ItemStack postpage = new ItemStack(Material.EMERALD_BLOCK);
          ItemMeta postpageMeta = postpage.getItemMeta();
          postpageMeta.setDisplayName(messages.getGuiItemPostpageMsg());
          postpage.setItemMeta(postpageMeta);
          inv.setItem(28, postpage);
          ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
          ItemMeta prepageMeta = prepage.getItemMeta();
          prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
          prepage.setItemMeta(prepageMeta);
          inv.setItem(27, prepage);
        }
      
      }
    
    } else {
      
      cards = cartas;
      int i = 0;
      for (Card c : cards) {
        if (player.hasPermission(c.getCardPermission()) && 
          i < 35) {
          ItemStack carta = Utils.transformaCartaGUI(c, plugin);
          if (!j.getCartas().isEmpty()) {
            for (Card c1 : j.getCartas()) {
              if (c1.getCardName().equals(c.getCardName())) {
                ItemMeta cartaMeta = carta.getItemMeta();
                cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                carta.setItemMeta(cartaMeta);
              } 
            } 
          }
          inv.setItem(i, carta);
          i++;
        } 
      } 
    } 

    
    ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK);
    goldBlock.setAmount(j.getCartas().size());
    inv.setItem(inv.getSize() - 1, goldBlock);
    player.updateInventory();
  }
  
  private void openShopGUI(Player player, CardClass clase, RolCards plugin, Integer numero) {
    Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_GREEN + 
        "Cards Shop");
    LanguageMessages messages = plugin.getMessages();
    Jugador j = Utils.buscaJugador(player, plugin);
    j.setPage(numero);
    List<List<Card>> listaCartas = new ArrayList<>();
    List<Card> cartas = null;
    List<Card> cartas2 = new ArrayList<>();
    ItemStack lotteryItem = new ItemStack(Material.STICK);
    ItemMeta lotteryMeta = lotteryItem.getItemMeta();
    lotteryMeta.setDisplayName(this.message.getGuiLotteryMsg());
    List<String> lotteryLore = new ArrayList<>();
    lotteryLore.add("§1" + plugin.getLotMoney() + "$");
    lotteryMeta.setLore(lotteryLore);
    lotteryItem.setItemMeta(lotteryMeta);
    List<Card> cards = new ArrayList<>();
    cartas2.addAll(plugin.getNormalCards().getNormalCards());
    if (clase == CardClass.HUNTER) {
      cartas2.addAll(plugin.getHunterCards().getHunterCards());
    } else if (clase == CardClass.MAGE) {
      cartas2.addAll(plugin.getMageCards().getMageCards());
    } else if (clase == CardClass.WARRIOR) {
      cartas2.addAll(plugin.getWarriorCards().getWarriorCards());
    } 
    cartas = cartasNoTienePermiso(player, cartas2);
    if (cartas.size() > 34) {
      int i2 = 0;
      while (cartas.size() - i2 > 33) {
        if (i2 == 0 || cartas.size() - i2 == 34) {
          listaCartas.add(cartas.subList(i2, i2 + 34));
          i2 += 34; continue;
        } 
        listaCartas.add(cartas.subList(i2, i2 + 33));
        i2 += 33;
      } 
      
      listaCartas.add(cartas.subList(i2, cartas.size()));
      cards = listaCartas.get(numero.intValue());
      if (cards != null) {
        int i = 0;
        if (cards.size() == 34) {
          for (Card c : cards) {
            if (!player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaShopGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaShopGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 1, carta);
              } 
              i++;
            } 
          } 
          if (listaCartas.indexOf(cards) == 0) {
            ItemStack postpage = new ItemStack(Material.EMERALD_BLOCK);
            ItemMeta postpageMeta = postpage.getItemMeta();
            postpageMeta.setDisplayName(messages.getGuiItemPostpageMsg());
            postpage.setItemMeta(postpageMeta);
            inv.setItem(27, postpage);
          } else {
            ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
            ItemMeta prepageMeta = prepage.getItemMeta();
            prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
            prepage.setItemMeta(prepageMeta);
            inv.setItem(27, prepage);
          }
        
        } else if (listaCartas.indexOf(cards) == listaCartas.size() - 1) {
          for (Card c : cards) {
            if (!player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaShopGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaShopGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 1, carta);
              } 
              i++;
            } 
          } 
          ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
          ItemMeta prepageMeta = prepage.getItemMeta();
          prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
          prepage.setItemMeta(prepageMeta);
          inv.setItem(27, prepage);
        } else {
          for (Card c : cards) {
            if (!player.hasPermission(c.getCardPermission())) {
              if (i < 27) {
                ItemStack carta = Utils.transformaCartaShopGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i, carta);
              } else {
                ItemStack carta = Utils.transformaCartaShopGUI(c, plugin);
                if (!j.getCartas().isEmpty()) {
                  for (Card c1 : j.getCartas()) {
                    if (c1.getCardName().equals(c.getCardName())) {
                      ItemMeta cartaMeta = carta.getItemMeta();
                      cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                      carta.setItemMeta(cartaMeta);
                    } 
                  } 
                }
                inv.setItem(i + 2, carta);
              } 
              i++;
            } 
          } 
          ItemStack postpage = new ItemStack(Material.EMERALD_BLOCK);
          ItemMeta postpageMeta = postpage.getItemMeta();
          postpageMeta.setDisplayName(messages.getGuiItemPostpageMsg());
          postpage.setItemMeta(postpageMeta);
          inv.setItem(28, postpage);
          ItemStack prepage = new ItemStack(Material.REDSTONE_BLOCK);
          ItemMeta prepageMeta = prepage.getItemMeta();
          prepageMeta.setDisplayName(messages.getGuiItemPrepageMsg());
          prepage.setItemMeta(prepageMeta);
          inv.setItem(27, prepage);
        }
      
      }
    
    } else {
      
      cards = cartas;
      int i = 0;
      for (Card c : cards) {
        if (!player.hasPermission(c.getCardPermission()) && 
          i < 35) {
          ItemStack carta = Utils.transformaCartaShopGUI(c, plugin);
          if (!j.getCartas().isEmpty()) {
            for (Card c1 : j.getCartas()) {
              if (c1.getCardName().equals(c.getCardName())) {
                ItemMeta cartaMeta = carta.getItemMeta();
                cartaMeta.addEnchant(Enchantment.LUCK, carta.getEnchantmentLevel(Enchantment.LUCK) + 1, true);
                carta.setItemMeta(cartaMeta);
              } 
            } 
          }
          inv.setItem(i, carta);
          i++;
        } 
      } 
    } 
    
    if (inv.getItem(0) != null) {
      inv.setItem(inv.getSize() - 1, lotteryItem);
    }
    player.openInventory(inv);
  }

  
  public static void openChallengGUI(Player player) {
    Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_GREEN + 
        "Requests");
    List<Player> listaPeticiones = new ArrayList<>();
    for (Peticion c : RolCards.getPeticiones()) {
      if (c.getTo().equals(player)) {
        listaPeticiones.add(c.getFrom());
      }
    } 
    ItemStack comp = new ItemStack(Material.SKULL_ITEM);
    comp.setDurability((short)3);
    SkullMeta compMeta = (SkullMeta)comp.getItemMeta();
    int i = 0;
    for (Player p : listaPeticiones) {
      if (i < 36) {
        compMeta.setOwner(p.getName());
        compMeta.setDisplayName(p.getName());
        comp.setItemMeta((ItemMeta)compMeta);
        inv.setItem(i, comp);
      } 
      i++;
    } 
    player.openInventory(inv);
  }
  public static void openTargetGUI(Jugador j, Jugador enemy, Card c) {
    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + 
        "Target");
    ItemStack comp = new ItemStack(Material.SKULL_ITEM);
    ItemStack ent = new ItemStack(Material.GOLDEN_CARROT);
    ItemMeta entMeta = ent.getItemMeta();
    comp.setDurability((short)3);
    SkullMeta compMeta = (SkullMeta)comp.getItemMeta();
    compMeta.setOwner(enemy.getP().getName());
    compMeta.setDisplayName(enemy.getP().getName());
    comp.setItemMeta((ItemMeta)compMeta);
    inv.setItem(8, comp);
    inv.setItem(7, Utils.transformaCartaGUI(c, pluginStatic));
    for (int i = 0; i < enemy.getMonstruos().size(); i++) {
      List<String> lore = new ArrayList<>();
      entMeta.setDisplayName(((Monster)enemy.getMonstruos().get(i)).getName());
      lore.add(((Monster)enemy.getMonstruos().get(i)).getMonsterType().getUniqueId().toString());
      entMeta.setLore(lore);
      ent.setItemMeta(entMeta);
      inv.setItem(i, ent);
    } 
    j.getP().openInventory(inv);
  }
  
  public RolCards getPlugin() { return this.plugin; }

  
  public void setPlugin(RolCards plugin) { this.plugin = plugin; }

  
  public static RolCards getPluginStatic() { return pluginStatic; }

  
  public static void setPluginStatic(RolCards pluginStatic) { CardGUI.pluginStatic = pluginStatic; }
  
  public static void openFriendlyTargetGUI(Jugador j, Jugador enemy, Card c) {
    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + 
        "Target");
    ItemStack comp = new ItemStack(Material.SKULL_ITEM);
    ItemStack ent = new ItemStack(Material.GOLDEN_CARROT);
    ItemMeta entMeta = ent.getItemMeta();
    comp.setDurability((short)3);
    SkullMeta compMeta = (SkullMeta)comp.getItemMeta();
    compMeta.setOwner(j.getP().getName());
    compMeta.setDisplayName(j.getP().getName());
    comp.setItemMeta((ItemMeta)compMeta);
    inv.setItem(8, comp);
    inv.setItem(7, Utils.transformaCartaGUI(c, pluginStatic));
    for (int i = 0; i < j.getMonstruos().size(); i++) {
      List<String> lore = new ArrayList<>();
      entMeta.setDisplayName(((Monster)j.getMonstruos().get(i)).getName());
      lore.add(((Monster)j.getMonstruos().get(i)).getMonsterType().getUniqueId().toString());
      entMeta.setLore(lore);
      ent.setItemMeta(entMeta);
      inv.setItem(i, ent);
    } 
    j.getP().openInventory(inv);
  }
  
  public static void openMobsGUI(Jugador j) {
    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + 
        "Mobs Menu");
    ItemStack ent = new ItemStack(Material.GOLDEN_CARROT);
    ItemMeta entMeta = ent.getItemMeta();
    for (int i = 0; i < j.getMonstruos().size(); i++) {
      List<String> lore = new ArrayList<>();
      entMeta.setDisplayName(((Monster)j.getMonstruos().get(i)).getName());
      lore.add(((Monster)j.getMonstruos().get(i)).getMonsterType().getUniqueId().toString());
      entMeta.setLore(lore);
      ent.setItemMeta(entMeta);
      inv.setItem(i, ent);
    } 
    j.getP().openInventory(inv);
  }
  private static void openEnemyMobsGUI(Jugador j, Jugador enemy, ItemStack currentItem) {
    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + 
        "Mobs Menu");
    ItemStack comp = new ItemStack(Material.SKULL_ITEM);
    ItemStack ent = new ItemStack(Material.CARROT);
    ItemMeta entMeta = ent.getItemMeta();
    comp.setDurability((short)3);
    SkullMeta compMeta = (SkullMeta)comp.getItemMeta();
    compMeta.setOwner(enemy.getP().getName());
    compMeta.setDisplayName(enemy.getP().getName());
    comp.setItemMeta((ItemMeta)compMeta);
    inv.setItem(8, comp);
    for (int i = 0; i < enemy.getMonstruos().size(); i++) {
      List<String> lore = new ArrayList<>();
      entMeta.setDisplayName(((Monster)enemy.getMonstruos().get(i)).getName());
      lore.add(((Monster)enemy.getMonstruos().get(i)).getMonsterType().getUniqueId().toString());
      entMeta.setLore(lore);
      ent.setItemMeta(entMeta);
      inv.setItem(i, ent);
    } 
    inv.setItem(7, currentItem);
    j.getP().openInventory(inv);
  }
  
  public LanguageMessages getMessage() { return this.message; }

  
  public void setMessage(LanguageMessages message) { this.message = message; }

  
  private void temporizador(final Player p, final Location l, final Integer tiempo, final Color randomcolor) {
    Bukkit.getServer().getScheduler().runTaskLater((Plugin)this.plugin, new Runnable()
        {
          public void run() {
            CardGUI.this.fireEffect(new Location(l.getWorld(), l.getX() + 0.5D, l.getY() + tiempo.intValue(), l.getZ() + 0.5D), FireworkEffect.Type.BURST, randomcolor);
            if (tiempo.intValue() == 1) {
              CardGUI.this.sortea(p, l);
            } else {
              CardGUI.this.temporizador(p, l, Integer.valueOf(tiempo.intValue() - 1), randomcolor);
            } 
          }
        },5L);
  }
  private void sortea(final Player p, Location l) {
    Bukkit.getServer().getScheduler().runTaskLater((Plugin)this.plugin, new Runnable() {
          public void run() {
            Random r1 = new Random();
            List<Card> lista = CardGUI.this.encuentraNoCompradas(p, CardGUI.this.plugin);
            int i = r1.nextInt(lista.size());
            CardGUI.this.plugin.addPerm(((Card)lista.get(i)).getCardPermission(), p);
            p.sendMessage(String.valueOf(CardGUI.this.plugin.getMessages().getMatchPrizeMsg()) + " " + ((Card)lista.get(i)).getCardName());
          }
        },5L);
  }
  
  public List<Card> encuentraNoCompradas(Player p, RolCards plugin) {
    List<Card> cartas = plugin.getCartas();
    List<Card> cartasNoDisponibles = new ArrayList<>();
    for (Card c : cartas) {
      if (!p.hasPermission(c.getCardPermission())) {
        cartasNoDisponibles.add(c);
      }
    } 
    return cartasNoDisponibles;
  }

  
  public void fireEffect(Location location, FireworkEffect.Type type, Color color) {
    FireworkEffect effect = FireworkEffect.builder().trail(false).flicker(false).withColor(color).withFade(color).with(type).build();
    final Firework firework = (Firework)location.getWorld().spawn(location, Firework.class);
    FireworkMeta meta = firework.getFireworkMeta();
    meta.addEffect(effect);
    meta.setPower(0);
    firework.setFireworkMeta(meta);
    Bukkit.getServer().getScheduler().runTaskLater((Plugin)this.plugin, new Runnable()
        {
          public void run() {
            firework.detonate();
          }
        },  1L);
  }
  private List<Card> cartasTienePermiso(Player player, List<Card> cartas2) {
    List<Card> lista = new ArrayList<>();
    for (Card c : cartas2) {
      if (player.hasPermission(c.getCardPermission())) {
        lista.add(c);
      }
    } 
    return lista;
  }
  private List<Card> cartasNoTienePermiso(Player player, List<Card> cartas2) {
    List<Card> lista = new ArrayList<>();
    for (Card c : cartas2) {
      if (!player.hasPermission(c.getCardPermission())) {
        lista.add(c);
      }
    } 
    return lista;
  }
}
