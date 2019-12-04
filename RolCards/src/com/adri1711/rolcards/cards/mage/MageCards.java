package com.adri1711.rolcards.cards.mage;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.language.LanguageMessages;
import java.util.ArrayList;
import java.util.List;

public class MageCards
{
  private List<Card> mageCards;
  private Card mageSkill;
  private Card burn;
  private Card arcaneIntellect;
  private Card fearOfHeights;
  private Card divineHealer;
  private Card nuclearBomb;
  private Card turningTheTables;
  private Card newLife;
  private Card manaSupply;
  private Card lifeChange;
  private Card witchCat;
  
  public MageCards(RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    if (plugin.getLanguage().equalsIgnoreCase("es")) {
      this.mageSkill = new Card(CardClass.MAGE, "ß1ßlHabilidad de Mago", Integer.valueOf(0), Integer.valueOf(2), 
          "rolcards.class.mage", 
          "ß7Te curas 0.5");
      this.burn = new Card(CardClass.MAGE, "ß1ßlArde", Integer.valueOf(1000), Integer.valueOf(1), 
          "rolcards.class.mage.burn", "ß7Envuelve a tu enemigo en llamas por 2 segundos");
      this.arcaneIntellect = new Card(CardClass.MAGE, "ß1ßlInteligencia Arcana", 
          Integer.valueOf(1500), Integer.valueOf(3), "rolcards.class.mage.arcaneintellect", 
          "ß7Roba dos cartas");
      this.fearOfHeights = new Card(CardClass.MAGE, "ß1ßlMiedo a las Alturas", 
          Integer.valueOf(3000), Integer.valueOf(5), "rolcards.class.mage.fearofheights", 
          "ß7Tu enemigo sufrira una caida", "ß7que le hara 3 de da√±o");
      this.divineHealer = new Card(CardClass.MAGE, "ß1ßlCura Divina", Integer.valueOf(4000), 
          Integer.valueOf(4), "rolcards.class.mage.divinehealer", "ß7Te curas 3");
      this.nuclearBomb = new Card(CardClass.MAGE, "ß1ßlBomba Nuclear", Integer.valueOf(3500), 
          Integer.valueOf(4), "rolcards.class.mage.nuclearbomb", 
          "ß7Explota la zona del enemigo haciendole", 
          "ß72 de da√±o y envenenandolo por 2 segundos");
      this.turningTheTables = new Card(CardClass.MAGE, 
          "ß1ßlCambiar las Tornas", Integer.valueOf(2000), Integer.valueOf(2), 
          "rolcards.class.mage.turningthetables", 
          "ß7Ejerce 1 de da√±o y", "ß7te curas 1");
      this.newLife = new Card(CardClass.MAGE, "ß1ßlNueva Vida", Integer.valueOf(6000), Integer.valueOf(8), 
          "rolcards.class.mage.newlife", 
          "ß7Te curas totalmente tu y tu enemigo");
      this.manaSupply = new Card(CardClass.MAGE, "ß1ßlProveedor de Mana", Integer.valueOf(3000), Integer.valueOf(0), 
          "rolcards.class.mage.manasupply", "ß7Ganas uno de mana este turno");
      this.lifeChange = new Card(CardClass.MAGE, "ß1ßlCambio de Vida", Integer.valueOf(5000), Integer.valueOf(7), 
          "rolcards.class.mage.lifechange", 
          "ß7Cambia tu vida con la del enemigo");
      this.witchCat = new Card(CardClass.MAGE, "ß1ßlGato Brujo", Integer.valueOf(5000), Integer.valueOf(3), 
          "rolcards.class.mage.witchcat", 
          "ß7Invoca un gato con 2 de da√±o y 3 de vida");
    } else {
      this.mageSkill = new Card(CardClass.MAGE, message.getMageSkillMsg(), Integer.valueOf(0), Integer.valueOf(2), 
          "rolcards.class.mage", 
          message.getMageSkillMsg1());
      this.burn = new Card(CardClass.MAGE, message.getMageBurnMsg(), Integer.valueOf(1000), Integer.valueOf(1), 
          "rolcards.class.mage.burn", message.getMageBurnMsg1());
      this.arcaneIntellect = new Card(CardClass.MAGE, message.getMageArcaneIntellectMsg(), 
          Integer.valueOf(1500), Integer.valueOf(3), "rolcards.class.mage.arcaneintellect", 
          message.getMageArcaneIntellectMsg1());
      this.fearOfHeights = new Card(CardClass.MAGE, message.getMageFearOfHeightsMsg(), 
          Integer.valueOf(3000), Integer.valueOf(5), "rolcards.class.mage.fearofheights", 
          message.getMageFearOfHeightsMsg1(), message.getMageFearOfHeightsMsg2());
      this.divineHealer = new Card(CardClass.MAGE, message.getMageDivineHealerMsg(), Integer.valueOf(4000), 
          Integer.valueOf(4), "rolcards.class.mage.divinehealer", message.getMageDivineHealerMsg1());
      this.nuclearBomb = new Card(CardClass.MAGE, message.getMageNuclearBombMsg(), Integer.valueOf(3500), 
          Integer.valueOf(4), "rolcards.class.mage.nuclearbomb", 
          message.getMageNuclearBombMsg1(), 
          message.getMageNuclearBombMsg2());
      this.turningTheTables = new Card(CardClass.MAGE, 
          message.getMageTurningTheTablesMsg(), Integer.valueOf(2000), Integer.valueOf(2), 
          "rolcards.class.mage.turningthetables", 
          message.getMageTurningTheTablesMsg1(), message.getMageTurningTheTablesMsg2());
      this.newLife = new Card(CardClass.MAGE, message.getMageNewLifeMsg(), Integer.valueOf(6000), Integer.valueOf(8), 
          "rolcards.class.mage.newlife", 
          message.getMageNewLifeMsg1());
      this.manaSupply = new Card(CardClass.MAGE, message.getMageManaSupplyMsg(), Integer.valueOf(3000), Integer.valueOf(0), 
          "rolcards.class.mage.manasupply", message.getMageManaSupplyMsg1());
      this.lifeChange = new Card(CardClass.MAGE, message.getMageLifeChangeMsg(), Integer.valueOf(5000), Integer.valueOf(7), 
          "rolcards.class.mage.lifechange", 
          message.getMageLifeChangeMsg1());
      this.witchCat = new Card(CardClass.MAGE, message.getMageWitchCatMsg(), Integer.valueOf(5000), Integer.valueOf(3), 
          "rolcards.class.mage.witchcat", 
          message.getMageWitchCatMsg1());
    } 
    this.mageCards = new ArrayList<>();
    this.mageCards.add(this.burn);
    this.mageCards.add(this.arcaneIntellect);
    this.mageCards.add(this.fearOfHeights);
    this.mageCards.add(this.divineHealer);
    this.mageCards.add(this.nuclearBomb);
    this.mageCards.add(this.turningTheTables);
    this.mageCards.add(this.newLife);
    this.mageCards.add(this.manaSupply);
    this.mageCards.add(this.lifeChange);
    this.mageCards.add(this.witchCat);
  }

  
  public List<Card> getMageCards() { return this.mageCards; }


  
  public void setMageCards(List<Card> mageCards) { this.mageCards = mageCards; }


  
  public Card getMageSkill() { return this.mageSkill; }


  
  public void setMageSkill(Card mageSkill) { this.mageSkill = mageSkill; }


  
  public Card getBurn() { return this.burn; }


  
  public void setBurn(Card burn) { this.burn = burn; }


  
  public Card getArcaneIntellect() { return this.arcaneIntellect; }


  
  public void setArcaneIntellect(Card arcaneIntellect) { this.arcaneIntellect = arcaneIntellect; }


  
  public Card getFearOfHeights() { return this.fearOfHeights; }


  
  public void setFearOfHeights(Card fearOfHeights) { this.fearOfHeights = fearOfHeights; }


  
  public Card getDivineHealer() { return this.divineHealer; }


  
  public void setDivineHealer(Card divineHealer) { this.divineHealer = divineHealer; }


  
  public Card getNuclearBomb() { return this.nuclearBomb; }


  
  public void setNuclearBomb(Card nuclearBomb) { this.nuclearBomb = nuclearBomb; }


  
  public Card getTurningTheTables() { return this.turningTheTables; }


  
  public void setTurningTheTables(Card turningTheTables) { this.turningTheTables = turningTheTables; }


  
  public Card getNewLife() { return this.newLife; }


  
  public void setNewLife(Card newLife) { this.newLife = newLife; }


  
  public Card getManaSupply() { return this.manaSupply; }


  
  public void setManaSupply(Card manaSupply) { this.manaSupply = manaSupply; }


  
  public Card getLifeChange() { return this.lifeChange; }


  
  public void setLifeChange(Card lifeChange) { this.lifeChange = lifeChange; }


  
  public Card getWitchCat() { return this.witchCat; }


  
  public void setWitchCat(Card witchCat) { this.witchCat = witchCat; }
}
