package com.adri1711.rolcards.cards.normal;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.language.LanguageMessages;
import java.util.ArrayList;
import java.util.List;

public class NormalCards
{
  private List<Card> normalCards;
  private Card woodWeapon;
  private Card insectBite;
  private Card manaSet;
  private Card minorHealing;
  private Card majorHealing;
  private Card equality;
  private Card tigerBite;
  private Card bearScratch;
  private Card elephantStomp;
  private Card shieldBearer;
  private Card greatWeapon;
  private Card incantation;
  private Card flame;
  private Card takeARest;
  private Card presentForYou;
  private Card vampire;
  private Card exiledZombie;
  
  public NormalCards(RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    if (plugin.getLanguage().equalsIgnoreCase("es")) {
      this.flame = new Card(CardClass.NORMAL, "§f§lLlamas", Integer.valueOf(0), Integer.valueOf(1), 
          "rolcards.class.normal", "§7Envuelve a tu enemigo en llamas por 1 segundo");
      this.woodWeapon = new Card(CardClass.NORMAL, "§f§lArma de Madera", Integer.valueOf(0), Integer.valueOf(1), 
          "rolcards.class.normal", "§7Consigues una espada de madera");
      this.greatWeapon = new Card(CardClass.NORMAL, "§f§lGran Arma", Integer.valueOf(0), Integer.valueOf(4), 
          "rolcards.class.normal", "§7Consigues una espada de hierro");
      this.insectBite = new Card(CardClass.NORMAL, "§f§lPicadura de Insecto", Integer.valueOf(0), Integer.valueOf(1), 
          "rolcards.class.normal", "§7Ejerce 0.5 de daño al enemigo");
      this.tigerBite = new Card(CardClass.NORMAL, "§f§lMordedura de Tigre", Integer.valueOf(0), Integer.valueOf(3), 
          "rolcards.class.normal", "§7Ejerce 1 de daño al enemigo");
      this.bearScratch = new Card(CardClass.NORMAL, "§f§lArañazo de Oso", Integer.valueOf(0), Integer.valueOf(4), 
          "rolcards.class.normal", "§7Ejerce 1.5 de daño al enemigo");
      this.elephantStomp = new Card(CardClass.NORMAL, "§f§lPisoton de Elefante", 
          Integer.valueOf(0), Integer.valueOf(7), "rolcards.class.normal", "§7Ejerce 3 de daño al enemigo");
      this.manaSet = new Card(CardClass.NORMAL, "§f§lSet de Mana", Integer.valueOf(0), Integer.valueOf(4), 
          "rolcards.class.normal", "§7Pone tu mana a 5 por este turno");
      this.minorHealing = new Card(CardClass.NORMAL, "§f§lPoca Cura", Integer.valueOf(0), 
          Integer.valueOf(2), "rolcards.class.normal", "§7Te curas 1");
      this.majorHealing = new Card(CardClass.NORMAL, "§f§lGran Cura", Integer.valueOf(0), 
          Integer.valueOf(4), "rolcards.class.normal", "§7Te curas 2");
      this.equality = new Card(CardClass.NORMAL, "§f§lIgualdad", Integer.valueOf(0), Integer.valueOf(8), 
          "rolcards.class.normal", "§7Consigues la misma vida que tu oponente");
      this.shieldBearer = new Card(CardClass.NORMAL, "§f§lEscudero", Integer.valueOf(0), 
          Integer.valueOf(3), "rolcards.class.normal", "§7Consigues una pechera de cuero", 
          "§7Te ayudara en ataques fisicos");
      this.incantation = new Card(CardClass.NORMAL, "§f§lBrujeria", Integer.valueOf(0), Integer.valueOf(4), 
          "rolcards.class.normal", 
          "§7Daña a tu enemigo 1 y te curas 0.5");
      this.takeARest = new Card(CardClass.NORMAL, "§f§lDescanso", Integer.valueOf(0), Integer.valueOf(5), 
          "rolcards.class.normal", 
          "§7Te curas 3 y acaba tu turno");
      this.presentForYou = new Card(CardClass.NORMAL, "§f§lRegalito para ti", 
          Integer.valueOf(0), Integer.valueOf(9), "rolcards.class.normal", 
          "§7Lanza una bomba al enemigo que daña 3.5");
      this.vampire = new Card(CardClass.NORMAL, "§f§lVampiro", 
          Integer.valueOf(0), Integer.valueOf(4), "rolcards.class.normal", 
          "§7Invoca un vampiro de 3 de ataque y 3 de salud");
      this.exiledZombie = new Card(CardClass.NORMAL, "§f§lZombie Exiliado", 
          Integer.valueOf(0), Integer.valueOf(2), "rolcards.class.normal", 
          "§7Invoca un zombie de 1 de ataque y 2 de salud");
    } else {
      this.flame = new Card(CardClass.NORMAL, message.getNormalFlameMsg(), Integer.valueOf(0), Integer.valueOf(1), 
          "rolcards.class.normal", message.getNormalFlameMsg1());
      this.woodWeapon = new Card(CardClass.NORMAL, message.getNormalWoodWeaponMsg(), Integer.valueOf(0), Integer.valueOf(1), 
          "rolcards.class.normal", message.getNormalWoodWeaponMsg1());
      this.greatWeapon = new Card(CardClass.NORMAL, message.getNormalGreatWeaponMsg(), Integer.valueOf(0), Integer.valueOf(4), 
          "rolcards.class.normal", message.getNormalGreatWeaponMsg1());
      this.insectBite = new Card(CardClass.NORMAL, message.getNormalInsectBiteMsg(), Integer.valueOf(0), Integer.valueOf(1), 
          "rolcards.class.normal", message.getNormalInsectBiteMsg1());
      this.tigerBite = new Card(CardClass.NORMAL, message.getNormalTigerBiteMsg(), Integer.valueOf(0), Integer.valueOf(3), 
          "rolcards.class.normal", message.getNormalTigerBiteMsg1());
      this.bearScratch = new Card(CardClass.NORMAL, message.getNormalBearScratchMsg(), Integer.valueOf(0), Integer.valueOf(4), 
          "rolcards.class.normal", message.getNormalBearScratchMsg1());
      this.elephantStomp = new Card(CardClass.NORMAL, message.getNormalElephantStompMsg(), 
          Integer.valueOf(0), Integer.valueOf(7), "rolcards.class.normal", message.getNormalElephantStompMsg1());
      this.manaSet = new Card(CardClass.NORMAL, message.getNormalManaSetMsg(), Integer.valueOf(0), Integer.valueOf(4), 
          "rolcards.class.normal", message.getNormalManaSetMsg1());
      this.minorHealing = new Card(CardClass.NORMAL, message.getNormalMinorHealingMsg(), Integer.valueOf(0), 
          Integer.valueOf(2), "rolcards.class.normal", message.getNormalMinorHealingMsg1());
      this.majorHealing = new Card(CardClass.NORMAL, message.getNormalMajorHealingMsg(), Integer.valueOf(0), 
          Integer.valueOf(4), "rolcards.class.normal", message.getNormalMajorHealingMsg1());
      this.equality = new Card(CardClass.NORMAL, message.getNormalEqualityMsg(), Integer.valueOf(0), Integer.valueOf(8), 
          "rolcards.class.normal", message.getNormalEqualityMsg1());
      this.shieldBearer = new Card(CardClass.NORMAL, message.getNormalShieldBearerMsg(), Integer.valueOf(0), 
          Integer.valueOf(3), "rolcards.class.normal", message.getNormalShieldBearerMsg1(), 
          "§7It can help you on physical attacks");
      this.incantation = new Card(CardClass.NORMAL, message.getNormalIncantationMsg(), Integer.valueOf(0), Integer.valueOf(4), 
          "rolcards.class.normal", 
          message.getNormalIncantationMsg1());
      this.takeARest = new Card(CardClass.NORMAL, message.getNormalTakeARestMsg(), Integer.valueOf(0), Integer.valueOf(5), 
          "rolcards.class.normal", 
          message.getNormalTakeARestMsg1());
      this.presentForYou = new Card(CardClass.NORMAL, message.getNormalPresentForYouMsg(), 
          Integer.valueOf(0), Integer.valueOf(9), "rolcards.class.normal", 
          message.getNormalPresentForYouMsg1());
      this.vampire = new Card(CardClass.NORMAL, message.getNormalVampireMsg(), 
          Integer.valueOf(0), Integer.valueOf(4), "rolcards.class.normal", 
          message.getNormalVampireMsg1());
      this.exiledZombie = new Card(CardClass.NORMAL, message.getNormalExiledZombieMsg(), 
          Integer.valueOf(0), Integer.valueOf(2), "rolcards.class.normal", 
          message.getNormalExiledZombieMsg1());
    } 
    this.normalCards = new ArrayList<>();
    this.normalCards.add(this.flame);
    this.normalCards.add(this.woodWeapon);
    this.normalCards.add(this.greatWeapon);
    this.normalCards.add(this.insectBite);
    this.normalCards.add(this.tigerBite);
    this.normalCards.add(this.bearScratch);
    this.normalCards.add(this.elephantStomp);
    this.normalCards.add(this.manaSet);
    this.normalCards.add(this.minorHealing);
    this.normalCards.add(this.majorHealing);
    this.normalCards.add(this.equality);
    this.normalCards.add(this.shieldBearer);
    this.normalCards.add(this.incantation);
    this.normalCards.add(this.takeARest);
    this.normalCards.add(this.presentForYou);
    this.normalCards.add(this.vampire);
    this.normalCards.add(this.exiledZombie);
  }

  
  public List<Card> getNormalCards() { return this.normalCards; }


  
  public void setNormalCards(List<Card> normalCards) { this.normalCards = normalCards; }


  
  public Card getWoodWeapon() { return this.woodWeapon; }


  
  public void setWoodWeapon(Card woodWeapon) { this.woodWeapon = woodWeapon; }


  
  public Card getInsectBite() { return this.insectBite; }


  
  public void setInsectBite(Card insectBite) { this.insectBite = insectBite; }


  
  public Card getManaSet() { return this.manaSet; }


  
  public void setManaSet(Card manaSet) { this.manaSet = manaSet; }


  
  public Card getMinorHealing() { return this.minorHealing; }


  
  public void setMinorHealing(Card minorHealing) { this.minorHealing = minorHealing; }


  
  public Card getMajorHealing() { return this.majorHealing; }


  
  public void setMajorHealing(Card majorHealing) { this.majorHealing = majorHealing; }


  
  public Card getEquality() { return this.equality; }


  
  public void setEquality(Card equality) { this.equality = equality; }


  
  public Card getTigerBite() { return this.tigerBite; }


  
  public void setTigerBite(Card tigerBite) { this.tigerBite = tigerBite; }


  
  public Card getBearScratch() { return this.bearScratch; }


  
  public void setBearScratch(Card bearScratch) { this.bearScratch = bearScratch; }


  
  public Card getElephantStomp() { return this.elephantStomp; }


  
  public void setElephantStomp(Card elephantStomp) { this.elephantStomp = elephantStomp; }


  
  public Card getShieldBearer() { return this.shieldBearer; }


  
  public void setShieldBearer(Card shieldBearer) { this.shieldBearer = shieldBearer; }


  
  public Card getGreatWeapon() { return this.greatWeapon; }


  
  public void setGreatWeapon(Card greatWeapon) { this.greatWeapon = greatWeapon; }


  
  public Card getIncantation() { return this.incantation; }


  
  public void setIncantation(Card incantation) { this.incantation = incantation; }


  
  public Card getFlame() { return this.flame; }


  
  public void setFlame(Card flame) { this.flame = flame; }


  
  public Card getTakeARest() { return this.takeARest; }


  
  public void setTakeARest(Card takeARest) { this.takeARest = takeARest; }


  
  public Card getPresentForYou() { return this.presentForYou; }


  
  public void setPresentForYou(Card presentForYou) { this.presentForYou = presentForYou; }


  
  public Card getVampire() { return this.vampire; }


  
  public void setVampire(Card vampire) { this.vampire = vampire; }


  
  public Card getExiledZombie() { return this.exiledZombie; }


  
  public void setExiledZombie(Card exiledZombie) { this.exiledZombie = exiledZombie; }
}
