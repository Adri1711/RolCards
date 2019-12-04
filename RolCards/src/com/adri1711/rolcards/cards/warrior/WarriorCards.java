package com.adri1711.rolcards.cards.warrior;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.language.LanguageMessages;
import java.util.ArrayList;
import java.util.List;

public class WarriorCards
{
  private List<Card> warriorCards;
  private Card warriorSkill;
  private Card brutalHit;
  private Card berserker;
  private Card insanity;
  private Card ironBall;
  private Card disarm;
  private Card snatchAway;
  private Card lastTry;
  private Card beast;
  private Card limo;
  
  public WarriorCards(RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    if (plugin.getLanguage().equalsIgnoreCase("es")) {
      this.warriorSkill = new Card(CardClass.WARRIOR, "�c�lHabilidad de Guerrero", Integer.valueOf(0), 
          Integer.valueOf(2), "rolcards.class.warrior", "�7Ejerce 0.5 de daño al enemigo");
      this.brutalHit = new Card(CardClass.WARRIOR, "�c�lGolpe Brutal", Integer.valueOf(4000), Integer.valueOf(4), 
          "rolcards.class.warrior.brutalhit", 
          "�7Ejerce 3 de daño al enemigo");
      this.berserker = new Card(CardClass.WARRIOR, "�c�lBerserker", Integer.valueOf(3000), Integer.valueOf(3), 
          "rolcards.class.warrior.berserker", 
          "�7Daña de forma aleatoria 2-4 al enemigo");
      this.insanity = new Card(CardClass.WARRIOR, "�c�lLocura", Integer.valueOf(2000), Integer.valueOf(3), 
          "rolcards.class.warrior.insanity", 
          "�7Ejerce 2 de daño al enemigo o a ti mismo aleatoriamente");
      this.ironBall = new Card(CardClass.WARRIOR, "�c�lBola de Hierro", Integer.valueOf(3000), Integer.valueOf(2), 
          "rolcards.class.warrior.ironball", 
          "�7Ganas una bola de hierro, si le das al enemigo", 
          "�7al tirarla, ejerce 3 de daño");
      this.disarm = new Card(CardClass.WARRIOR, "�c�lDesquite", Integer.valueOf(2000), Integer.valueOf(2), 
          "rolcards.class.warrior.disarm", 
          "�7Rompe el arma de tu enemigo");
      this.snatchAway = new Card(CardClass.WARRIOR, "�c�lArrebatar", Integer.valueOf(3000), Integer.valueOf(3), 
          "rolcards.class.warrior.snatchaway", 
          "�7Roba el arma de tu enemigo");
      this.lastTry = new Card(CardClass.WARRIOR, "�c�lLast Try", Integer.valueOf(3000), Integer.valueOf(5), 
          "rolcards.class.warrior.lasttry", 
          "�7Si tu vida es menos que 2 corazones haces", "�75 de daño, en caso contrario haces 2 de daño");
      this.beast = new Card(CardClass.WARRIOR, "�c�lBestia", Integer.valueOf(7000), Integer.valueOf(3), 
          "rolcards.class.warrior.beast", 
          "�7Invoca una bestia de 4 de daño y 2 de vida");
      this.limo = new Card(CardClass.WARRIOR, "�c�lSlime", Integer.valueOf(5000), Integer.valueOf(1), 
          "rolcards.class.warrior.slime", 
          "�7Invoca un pequeño slime con 1 de daño y 1 de vida");
    } else {
      this.warriorSkill = new Card(CardClass.WARRIOR, message.getWarriorSkillMsg(), Integer.valueOf(0), 
          Integer.valueOf(2), "rolcards.class.warrior", message.getWarriorSkillMsg1());
      this.brutalHit = new Card(CardClass.WARRIOR, message.getWarriorBrutalHitMsg(), Integer.valueOf(4000), Integer.valueOf(4), 
          "rolcards.class.warrior.brutalhit", 
          message.getWarriorBrutalHitMsg1());
      this.berserker = new Card(CardClass.WARRIOR, message.getWarriorBerserkerMsg(), Integer.valueOf(3000), Integer.valueOf(3), 
          "rolcards.class.warrior.berserker", 
          message.getWarriorBerserkerMsg1());
      this.insanity = new Card(CardClass.WARRIOR, message.getWarriorInsanityMsg(), Integer.valueOf(2000), Integer.valueOf(3), 
          "rolcards.class.warrior.insanity", 
          message.getWarriorInsanityMsg1());
      this.ironBall = new Card(CardClass.WARRIOR, message.getWarriorIronBallMsg(), Integer.valueOf(3000), Integer.valueOf(2), 
          "rolcards.class.warrior.ironball", 
          message.getWarriorIronBallMsg1(), 
          message.getWarriorIronBallMsg2());
      this.disarm = new Card(CardClass.WARRIOR, message.getWarriorDisarmMsg(), Integer.valueOf(2000), Integer.valueOf(2), 
          "rolcards.class.warrior.disarm", 
          message.getWarriorDisarmMsg1());
      this.snatchAway = new Card(CardClass.WARRIOR, message.getWarriorSnatchAwayMsg(), Integer.valueOf(3000), Integer.valueOf(3), 
          "rolcards.class.warrior.snatchaway", 
          message.getWarriorSnatchAwayMsg1());
      this.lastTry = new Card(CardClass.WARRIOR, message.getWarriorLastTryMsg(), Integer.valueOf(3000), Integer.valueOf(5), 
          "rolcards.class.warrior.lasttry", 
          message.getWarriorLastTryMsg1(), message.getWarriorLastTryMsg2());
      this.beast = new Card(CardClass.WARRIOR, message.getWarriorBeastMsg(), Integer.valueOf(7000), Integer.valueOf(3), 
          "rolcards.class.warrior.beast", 
          message.getWarriorBeastMsg1());
      this.limo = new Card(CardClass.WARRIOR, message.getWarriorSlimeMsg(), Integer.valueOf(5000), Integer.valueOf(1), 
          "rolcards.class.warrior.slime", 
          message.getWarriorSlimeMsg1());
    } 
    this.warriorCards = new ArrayList<>();
    this.warriorCards.add(this.brutalHit);
    this.warriorCards.add(this.berserker);
    this.warriorCards.add(this.insanity);
    this.warriorCards.add(this.ironBall);
    this.warriorCards.add(this.disarm);
    this.warriorCards.add(this.snatchAway);
    this.warriorCards.add(this.lastTry);
    this.warriorCards.add(this.beast);
    this.warriorCards.add(this.limo);
  }

  
  public List<Card> getWarriorCards() { return this.warriorCards; }


  
  public void setWarriorCards(List<Card> warriorCards) { this.warriorCards = warriorCards; }


  
  public Card getWarriorSkill() { return this.warriorSkill; }


  
  public void setWarriorSkill(Card warriorSkill) { this.warriorSkill = warriorSkill; }


  
  public Card getBrutalHit() { return this.brutalHit; }


  
  public void setBrutalHit(Card brutalHit) { this.brutalHit = brutalHit; }


  
  public Card getBerserker() { return this.berserker; }


  
  public void setBerserker(Card berserker) { this.berserker = berserker; }


  
  public Card getInsanity() { return this.insanity; }


  
  public void setInsanity(Card insanity) { this.insanity = insanity; }


  
  public Card getIronBall() { return this.ironBall; }


  
  public void setIronBall(Card ironBall) { this.ironBall = ironBall; }


  
  public Card getDisarm() { return this.disarm; }


  
  public void setDisarm(Card disarm) { this.disarm = disarm; }


  
  public Card getSnatchAway() { return this.snatchAway; }


  
  public void setSnatchAway(Card snatchAway) { this.snatchAway = snatchAway; }


  
  public Card getLastTry() { return this.lastTry; }


  
  public void setLastTry(Card lastTry) { this.lastTry = lastTry; }


  
  public Card getBeast() { return this.beast; }


  
  public void setBeast(Card beast) { this.beast = beast; }


  
  public Card getLimo() { return this.limo; }


  
  public void setLimo(Card limo) { this.limo = limo; }
}
