package com.adri1711.rolcards.cards;

import com.adri1711.rolcards.RolCards;

public class CreatedCard
  extends Card {
  private CardEffect cardEffect;
  private Integer cardEffectValue;
  private Double cardDamage;
  private Double cardHealth;
  private String cardMobName;
  
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, CardEffect effect, Integer value, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, "", "", "", "");
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = null;
    this.cardHealth = null;
    this.cardMobName = null;
    meteCarta(this, plugin);
  }
  
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, CardEffect effect, Integer value, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, line2, "", "", "");
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = null;
    this.cardHealth = null;
    this.cardMobName = null;
    meteCarta(this, plugin);
  }
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3, CardEffect effect, Integer value, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, line2, line3, "", "");
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = null;
    this.cardHealth = null;
    this.cardMobName = null;
    meteCarta(this, plugin);
  }
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3, String line4, CardEffect effect, Integer value, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, line2, line3, line4, "");
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = null;
    this.cardHealth = null;
    this.cardMobName = null;
    meteCarta(this, plugin);
  }
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3, String line4, String line5, CardEffect effect, Integer value, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, line2, line3, line4, line5);
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = null;
    this.cardHealth = null;
    this.cardMobName = null;
    meteCarta(this, plugin);
  }
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3, String line4, String line5, CardEffect effect, Integer value, String mobName, Double cardDamage, Double cardHealth, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, line2, line3, line4, line5);
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = cardDamage;
    this.cardHealth = cardHealth;
    this.cardMobName = mobName;
    meteCarta(this, plugin);
  }
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3, String line4, CardEffect effect, Integer value, String mobName, Double cardDamage, Double cardHealth, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, line2, line3, line4, "");
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = cardDamage;
    this.cardHealth = cardHealth;
    this.cardMobName = mobName;
    meteCarta(this, plugin);
  }
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3, CardEffect effect, Integer value, String mobName, Double cardDamage, Double cardHealth, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, line2, line3, "", "");
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = cardDamage;
    this.cardHealth = cardHealth;
    this.cardMobName = mobName;
    meteCarta(this, plugin);
  }
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, CardEffect effect, Integer value, String mobName, Double cardDamage, Double cardHealth, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, line2, "", "", "");
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = cardDamage;
    this.cardHealth = cardHealth;
    this.cardMobName = mobName;
    meteCarta(this, plugin);
  }
  public CreatedCard(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, CardEffect effect, Integer value, String mobName, Double cardDamage, Double cardHealth, RolCards plugin) {
    super(clase, name, price, cost, perm, line1, "", "", "", "");
    this.cardEffect = effect;
    this.cardEffectValue = value;
    this.cardDamage = cardDamage;
    this.cardHealth = cardHealth;
    this.cardMobName = mobName;
    meteCarta(this, plugin);
  }

  
  public CardEffect getCardEffect() { return this.cardEffect; }

  
  public void setCardEffect(CardEffect cardEffect) { this.cardEffect = cardEffect; }

  
  public Integer getCardEffectValue() { return this.cardEffectValue; }

  
  public void setCardEffectValue(Integer cardEffectValue) { this.cardEffectValue = cardEffectValue; }

  
  private void meteCarta(CreatedCard createdCard, RolCards plugin) { plugin.getListaCreatedCard().add(createdCard); }



  
  public Double getCardDamage() { return this.cardDamage; }


  
  public void setCardDamage(Double cardDamage) { this.cardDamage = cardDamage; }


  
  public Double getCardHealth() { return this.cardHealth; }


  
  public void setCardHealth(Double cardHealth) { this.cardHealth = cardHealth; }


  
  public String getCardMobName() { return this.cardMobName; }


  
  public void setCardMobName(String cardMobName) { this.cardMobName = cardMobName; }
}
