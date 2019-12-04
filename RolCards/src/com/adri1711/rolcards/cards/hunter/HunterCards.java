package com.adri1711.rolcards.cards.hunter;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.language.LanguageMessages;
import java.util.ArrayList;
import java.util.List;

public class HunterCards
{
  private List<Card> hunterCards;
  private Card hunterSkill;
  private Card poisonGas;
  private Card finishHim;
  private Card getACopy;
  private Card toTheHead;
  private Card instantPoison;
  private Card divineBow;
  private Card legendaryBow;
  private Card firstAid;
  private Card mortalTrap;
  private Card lealCompanion;
  
  public HunterCards(RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    if (plugin.getLanguage().equalsIgnoreCase("es")) {
      this.hunterSkill = new Card(CardClass.HUNTER, "ß2ßlHabilidad de Cazador", Integer.valueOf(0), Integer.valueOf(2), 
          "rolcards.class.hunter", "ß7Consigues una flecha");
      this.poisonGas = new Card(CardClass.HUNTER, "ß2ßlGas venenoso", Integer.valueOf(2000), 
          Integer.valueOf(4), "rolcards.class.hunter.poisongas", 
          "ß7El enemigo se envenena durante 4 segundos");
      this.getACopy = new Card(CardClass.HUNTER, "ß2ßlConsigue la copia", Integer.valueOf(5000), Integer.valueOf(1), 
          "rolcards.class.hunter.getacopy", 
          "ß7Consigue una copia aleatoria de una carta del oponente");
      this.toTheHead = new Card(CardClass.HUNTER, "ß2ßlA la cabeza", Integer.valueOf(2000), Integer.valueOf(2), 
          "rolcards.class.hunter.tothehead", "ß7Ejerce 1 de da√±o al enemigo");
      this.instantPoison = new Card(CardClass.HUNTER, "ß2ßlVeneno instantaneo", 
          Integer.valueOf(3000), Integer.valueOf(1), "rolcards.class.hunter.instantpoison", 
          "ß7Envenena al enemigo los siguientes 2 segundos");
      this.divineBow = new Card(CardClass.HUNTER, "ß2ßlArco Divino", Integer.valueOf(5000), Integer.valueOf(7), 
          "rolcards.class.hunter.divinebow", 
          "ß7Consigue un arco con Power I");
      this.legendaryBow = new Card(CardClass.HUNTER, "ß2ßlArco Legendario", 
          Integer.valueOf(6000), Integer.valueOf(9), "rolcards.class.hunter.legendarybow", 
          "ß7Consigue un arco con Power III");
      this.mortalTrap = new Card(CardClass.HUNTER, "ß2ßlTrampa mortal", Integer.valueOf(5000), 
          Integer.valueOf(6), "rolcards.class.hunter.mortaltrap", 
          "ß7Pone una trampa que hace 4 de da√±o al enemigo");
      this.firstAid = new Card(CardClass.HUNTER, "ß2ßlPrimeros auxilios", Integer.valueOf(1000), Integer.valueOf(1), 
          "rolcards.class.hunter.firstaid", "ß7Te curas 1");
      this.finishHim = new Card(CardClass.HUNTER, "ß2ßlAcaba con el", Integer.valueOf(8000), Integer.valueOf(8), 
          "rolcards.class.hunter.finishhim", "ß7Hace 6 de da√±o al enemigo");
      this.lealCompanion = new Card(CardClass.HUNTER, "ß2ßlFiel Acompa√±ante", Integer.valueOf(8000), Integer.valueOf(5), 
          "rolcards.class.hunter.lealcompanion", "ß7Invoca un acompa√±ante de 4 de vida y 4 de da√±o");
    } else {
      this.hunterSkill = new Card(CardClass.HUNTER, message.getHunterSkillMsg(), Integer.valueOf(0), Integer.valueOf(2), 
          "rolcards.class.hunter", message.getHunterSkillMsg1());
      this.poisonGas = new Card(CardClass.HUNTER, message.getHunterPoisonGasMsg(), Integer.valueOf(2000), 
          Integer.valueOf(4), "rolcards.class.hunter.poisongas", 
          message.getHunterPoisonGasMsg1());
      this.getACopy = new Card(CardClass.HUNTER, message.getHunterGetCopyMsg(), Integer.valueOf(5000), Integer.valueOf(1), 
          "rolcards.class.hunter.getacopy", 
          message.getHunterGetCopyMsg1());
      this.toTheHead = new Card(CardClass.HUNTER, message.getHunterToTheHeadMsg(), Integer.valueOf(2000), Integer.valueOf(2), 
          "rolcards.class.hunter.tothehead", message.getHunterToTheHeadMsg1());
      this.instantPoison = new Card(CardClass.HUNTER, message.getHunterIPoisonMsg(), 
          Integer.valueOf(3000), Integer.valueOf(1), "rolcards.class.hunter.instantpoison", 
          message.getHunterIPoisonMsg1());
      this.divineBow = new Card(CardClass.HUNTER, message.getHunterDivineBowMsg(), Integer.valueOf(5000), Integer.valueOf(7), 
          "rolcards.class.hunter.divinebow", 
          message.getHunterDivineBowMsg1());
      this.legendaryBow = new Card(CardClass.HUNTER, message.getHunterLBowMsg(), 
          Integer.valueOf(6000), Integer.valueOf(9), "rolcards.class.hunter.legendarybow", 
          message.getHunterLBowMsg1());
      this.mortalTrap = new Card(CardClass.HUNTER, message.getHunterMortalTrapMsg(), Integer.valueOf(5000), 
          Integer.valueOf(6), "rolcards.class.hunter.mortaltrap", 
          message.getHunterMortalTrapMsg1());
      this.firstAid = new Card(CardClass.HUNTER, message.getHunterFirstAidMsg(), Integer.valueOf(1000), Integer.valueOf(1), 
          "rolcards.class.hunter.firstaid", message.getHunterFirstAidMsg1());
      this.finishHim = new Card(CardClass.HUNTER, message.getHunterFinishHimMsg(), Integer.valueOf(8000), Integer.valueOf(8), 
          "rolcards.class.hunter.finishhim", message.getHunterFinishHimMsg1());
      this.lealCompanion = new Card(CardClass.HUNTER, message.getHunterLealCompanionMsg(), Integer.valueOf(8000), Integer.valueOf(5), 
          "rolcards.class.hunter.lealcompanion", message.getHunterLealCompanionMsg1());
    } 
    this.hunterCards = new ArrayList<>();
    this.hunterCards.add(this.poisonGas);
    this.hunterCards.add(this.getACopy);
    this.hunterCards.add(this.toTheHead);
    this.hunterCards.add(this.instantPoison);
    this.hunterCards.add(this.divineBow);
    this.hunterCards.add(this.legendaryBow);
    this.hunterCards.add(this.mortalTrap);
    this.hunterCards.add(this.firstAid);
    this.hunterCards.add(this.finishHim);
    this.hunterCards.add(this.lealCompanion);
  }

  
  public List<Card> getHunterCards() { return this.hunterCards; }


  
  public void setHunterCards(List<Card> hunterCards) { this.hunterCards = hunterCards; }


  
  public Card getHunterSkill() { return this.hunterSkill; }


  
  public void setHunterSkill(Card hunterSkill) { this.hunterSkill = hunterSkill; }


  
  public Card getPoisonGas() { return this.poisonGas; }


  
  public void setPoisonGas(Card poisonGas) { this.poisonGas = poisonGas; }


  
  public Card getFinishHim() { return this.finishHim; }


  
  public void setFinishHim(Card finishHim) { this.finishHim = finishHim; }


  
  public Card getGetACopy() { return this.getACopy; }


  
  public void setGetACopy(Card getACopy) { this.getACopy = getACopy; }


  
  public Card getToTheHead() { return this.toTheHead; }


  
  public void setToTheHead(Card toTheHead) { this.toTheHead = toTheHead; }


  
  public Card getInstantPoison() { return this.instantPoison; }


  
  public void setInstantPoison(Card instantPoison) { this.instantPoison = instantPoison; }


  
  public Card getDivineBow() { return this.divineBow; }


  
  public void setDivineBow(Card divineBow) { this.divineBow = divineBow; }


  
  public Card getLegendaryBow() { return this.legendaryBow; }


  
  public void setLegendaryBow(Card legendaryBow) { this.legendaryBow = legendaryBow; }


  
  public Card getFirstAid() { return this.firstAid; }


  
  public void setFirstAid(Card firstAid) { this.firstAid = firstAid; }


  
  public Card getMortalTrap() { return this.mortalTrap; }


  
  public void setMortalTrap(Card mortalTrap) { this.mortalTrap = mortalTrap; }


  
  public Card getLealCompanion() { return this.lealCompanion; }


  
  public void setLealCompanion(Card lealCompanion) { this.lealCompanion = lealCompanion; }
}
