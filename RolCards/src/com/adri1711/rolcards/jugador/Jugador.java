package com.adri1711.rolcards.jugador;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.cards.CardEffect;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.match.Partida;
import com.adri1711.rolcards.monsters.Monster;
import com.adri1711.rolcards.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;








public class Jugador
{
  private Player p;
  private List<Card> cartas;
  private List<Card> cartasCopia;
  private boolean turn;
  private Partida partida;
  private CardClass clase;
  private boolean usedSkill;
  private boolean playing;
  private int mana;
  private RolCards plugin;
  private List<Monster> monstruos;
  private LanguageMessages message;
  private Fase fase;
  private Integer page;
  private CardClass claseCarta;
  private String cardName;
  private String cardDescriptionLine1;
  private String cardDescriptionLine2;
  private String cardDescriptionLine3;
  private String cardDescriptionLine4;
  private String cardDescriptionLine5;
  private String cardPermission;
  private Integer cardCost;
  private Integer cardPrice;
  private CardEffect cardEffect;
  private Integer cardEffectValue;
  private Double cardDamage;
  private Double cardHealth;
  private String cardMobName;
  
  public Jugador(Player p, RolCards plugin) {
    this.p = p;
    this.cartas = new ArrayList<>();
    this.cartasCopia = new ArrayList<>();
    this.turn = false;
    this.partida = null;
    this.clase = CardClass.NORMAL;
    this.usedSkill = false;
    this.playing = false;
    this.mana = 0;
    this.plugin = plugin;
    this.monstruos = new ArrayList<>();
    this.message = plugin.getMessages();
    this.fase = Fase.NONE;
    this.page = Integer.valueOf(0);

    
    this.claseCarta = null;
    this.cardName = null;
    this.cardCost = null;
    this.cardPrice = null;
    this.cardDescriptionLine1 = "";
    this.cardDescriptionLine2 = "";
    this.cardDescriptionLine3 = "";
    this.cardDescriptionLine4 = "";
    this.cardDescriptionLine5 = "";
    this.cardPermission = "";
    this.cardDamage = null;
    this.cardHealth = null;
    this.cardMobName = null;
  }

  
  public Player getP() { return this.p; }


  
  public void setP(Player p) { this.p = p; }


  
  public List<Card> getCartas() { return this.cartas; }


  
  public void setCartas(List<Card> cartas) { this.cartas = cartas; }


  
  public List<Card> getCartasCopia() { return this.cartasCopia; }


  
  public void setCartasCopia(List<Card> cartasCopia) { this.cartasCopia = cartasCopia; }


  
  public boolean isTurn() { return this.turn; }


  
  public void setTurn(boolean turn) { this.turn = turn; }

  
  public void addCarta(Card carta) {
    if (this.cartas.size() < this.plugin.getDeckSize().intValue()) {
      this.cartas.add(carta);
      this.cartasCopia.add(carta);
    }
    else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
      this.p.sendMessage(ChatColor.RED + "Tu ya tienes " + this.plugin.getDeckSize() + " cartas.");
    } else {
      this.p.sendMessage(ChatColor.RED + this.message.getPlayerDeckFullMsg());
    } 
  }

  
  public void addCartaSolo(Card carta) {
    if (this.cartas.size() < this.plugin.getDeckSize().intValue()) {
      this.cartas.add(carta);
    }
    else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
      this.p.sendMessage(ChatColor.RED + "Tu ya tienes " + this.plugin.getDeckSize() + " cartas.");
    } else {
      this.p.sendMessage(ChatColor.RED + this.message.getPlayerDeckFullMsg());
    } 
  }



  
  public Partida getPartida() { return this.partida; }


  
  public void setPartida(Partida partida) { this.partida = partida; }


  
  public CardClass getClase() { return this.clase; }


  
  public void setClase(CardClass clase) { this.clase = clase; }


  
  public boolean isUsedSkill() { return this.usedSkill; }


  
  public void setUsedSkill(boolean usedSkill) { this.usedSkill = usedSkill; }



  
  public boolean isPlaying() { return this.playing; }


  
  public void setPlaying(boolean playing) { this.playing = playing; }



  
  public List<Monster> getMonstruos() { return this.monstruos; }


  
  public void setMonstruos(List<Monster> monstruos) { this.monstruos = monstruos; }

  
  public void drawACard() {
    if (getCartas().size() != 0) {
      Random r = new Random();
      int carta1 = r.nextInt(getCartas().size());
      ItemStack carta = Utils.transformaCarta(getCartas().get(carta1), this.plugin);
      for (int i = 2; i < 7; i++) {
        if (getP().getInventory().getItem(i) == null) {
          getP().getInventory().setItem(i, carta);
          break;
        } 
        if (i == 6 && getP().getInventory().getItem(i) != null) {
          if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
            getP().sendMessage(ChatColor.RED + "Tu mano esta llena, no puedes robar carta");
          } else {
            getP().sendMessage(ChatColor.RED + this.message.getPlayerHandFullMsg());
          } 
        }
      } 
      
      getCartas().remove(carta1);
    }
    else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
      getP().sendMessage(ChatColor.RED + "No tienes mas cartas que robar");
    } else {
      getP().sendMessage(ChatColor.RED + this.message.getPlayerNoCardsMsg());
    } 
  }



  
  public int getMana() { return this.mana; }


  
  public void setMana(int mana) { this.mana = mana; }

  
  public void ponTodoDefault() {
    this.p.getEquipment().setChestplate(null);
    this.cartas.clear();
    this.turn = false;
    this.partida = null;
    this.usedSkill = false;
    this.mana = 0;
    for (Monster mon : this.monstruos) {
      mon.despawn();
    }
    this.monstruos.clear();
    for (Card c : this.cartasCopia) {
      addCartaSolo(c);
    }
  }
  
  public void removeCard(Card c) {
    String name = c.getCardName();
    for (int i = 0; i < this.cartas.size(); i++) {
      if (name.equalsIgnoreCase(((Card)this.cartas.get(i)).getCardName())) {
        this.cartas.remove(i);
        this.cartasCopia.remove(i);
        break;
      } 
    } 
  }

  
  public LanguageMessages getMessage() { return this.message; }


  
  public void setMessage(LanguageMessages message) { this.message = message; }


  
  public Fase getFase() { return this.fase; }


  
  public void setFase(Fase fase) { this.fase = fase; }


  
  public CardClass getClaseCarta() { return this.claseCarta; }


  
  public void setClaseCarta(CardClass claseCarta) { this.claseCarta = claseCarta; }


  
  public String getCardName() { return this.cardName; }


  
  public void setCardName(String cardName) { this.cardName = cardName; }


  
  public String getCardDescriptionLine1() { return this.cardDescriptionLine1; }


  
  public void setCardDescriptionLine1(String cardDescriptionLine1) { this.cardDescriptionLine1 = cardDescriptionLine1; }


  
  public String getCardDescriptionLine2() { return this.cardDescriptionLine2; }


  
  public void setCardDescriptionLine2(String cardDescriptionLine2) { this.cardDescriptionLine2 = cardDescriptionLine2; }


  
  public String getCardDescriptionLine3() { return this.cardDescriptionLine3; }


  
  public void setCardDescriptionLine3(String cardDescriptionLine3) { this.cardDescriptionLine3 = cardDescriptionLine3; }


  
  public String getCardDescriptionLine4() { return this.cardDescriptionLine4; }


  
  public void setCardDescriptionLine4(String cardDescriptionLine4) { this.cardDescriptionLine4 = cardDescriptionLine4; }


  
  public String getCardDescriptionLine5() { return this.cardDescriptionLine5; }


  
  public void setCardDescriptionLine5(String cardDescriptionLine5) { this.cardDescriptionLine5 = cardDescriptionLine5; }


  
  public String getCardPermission() { return this.cardPermission; }


  
  public void setCardPermission(String cardPermission) { this.cardPermission = cardPermission; }


  
  public Integer getCardCost() { return this.cardCost; }


  
  public void setCardCost(Integer cardCost) { this.cardCost = cardCost; }


  
  public Integer getCardPrice() { return this.cardPrice; }


  
  public void setCardPrice(Integer cardPrice) { this.cardPrice = cardPrice; }


  
  public CardEffect getCardEffect() { return this.cardEffect; }


  
  public void setCardEffect(CardEffect cardEffect) { this.cardEffect = cardEffect; }


  
  public Integer getCardEffectValue() { return this.cardEffectValue; }


  
  public void setCardEffectValue(Integer cardEffectValue) { this.cardEffectValue = cardEffectValue; }


  
  public Integer getPage() { return this.page; }


  
  public void setPage(Integer page) { this.page = page; }


  
  public Double getCardDamage() { return this.cardDamage; }


  
  public void setCardDamage(Double cardDamage) { this.cardDamage = cardDamage; }


  
  public Double getCardHealth() { return this.cardHealth; }


  
  public void setCardHealth(Double cardHealth) { this.cardHealth = cardHealth; }


  
  public String getCardMobName() { return this.cardMobName; }


  
  public void setCardMobName(String cardMobName) { this.cardMobName = cardMobName; }
}
