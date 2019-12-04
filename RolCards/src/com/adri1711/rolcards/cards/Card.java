package com.adri1711.rolcards.cards;




public class Card
{
  private CardClass clase;
  private String cardName;
  private String cardDescriptionLine1;
  private String cardDescriptionLine2;
  private String cardDescriptionLine3;
  
  public Card(CardClass clase, String name, Integer price, Integer cost, String perm, String line1) {
    this.clase = clase;
    this.cardName = name;
    this.cardCost = cost;
    this.cardPrice = price;
    this.cardDescriptionLine1 = line1;
    this.cardDescriptionLine2 = "";
    this.cardDescriptionLine3 = "";
    this.cardDescriptionLine4 = "";
    this.cardDescriptionLine5 = "";
    this.cardPermission = perm;
  } private String cardDescriptionLine4; private String cardDescriptionLine5; private String cardPermission; private Integer cardCost; private Integer cardPrice;
  public Card(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2) {
    this.clase = clase;
    this.cardName = name;
    this.cardCost = cost;
    this.cardPrice = price;
    this.cardDescriptionLine1 = line1;
    this.cardDescriptionLine2 = line2;
    this.cardDescriptionLine3 = "";
    this.cardDescriptionLine4 = "";
    this.cardDescriptionLine5 = "";
    this.cardPermission = perm;
  }
  public Card(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3) {
    this.clase = clase;
    this.cardName = name;
    this.cardCost = cost;
    this.cardPrice = price;
    this.cardDescriptionLine1 = line1;
    this.cardDescriptionLine2 = line2;
    this.cardDescriptionLine3 = line3;
    this.cardDescriptionLine4 = "";
    this.cardDescriptionLine5 = "";
    this.cardPermission = perm;
  }
  public Card(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3, String line4) {
    this.clase = clase;
    this.cardName = name;
    this.cardCost = cost;
    this.cardPrice = price;
    this.cardDescriptionLine1 = line1;
    this.cardDescriptionLine2 = line2;
    this.cardDescriptionLine3 = line3;
    this.cardDescriptionLine4 = line4;
    this.cardDescriptionLine5 = "";
    this.cardPermission = perm;
  }
  public Card(CardClass clase, String name, Integer price, Integer cost, String perm, String line1, String line2, String line3, String line4, String line5) {
    this.clase = clase;
    this.cardName = name;
    this.cardCost = cost;
    this.cardPrice = price;
    this.cardDescriptionLine1 = line1;
    this.cardDescriptionLine2 = line2;
    this.cardDescriptionLine3 = line3;
    this.cardDescriptionLine4 = line4;
    this.cardDescriptionLine5 = line5;
    this.cardPermission = perm;
  }
  
  public CardClass getClase() { return this.clase; }

  
  public void setClase(CardClass clase) { this.clase = clase; }

  
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

  
  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + (
      (this.cardCost == null) ? 0 : this.cardCost.hashCode());
    result = 31 * 
      result + (
      (this.cardDescriptionLine1 == null) ? 0 : this.cardDescriptionLine1
      .hashCode());
    result = 31 * 
      result + (
      (this.cardDescriptionLine2 == null) ? 0 : this.cardDescriptionLine2
      .hashCode());
    result = 31 * 
      result + (
      (this.cardDescriptionLine3 == null) ? 0 : this.cardDescriptionLine3
      .hashCode());
    result = 31 * 
      result + (
      (this.cardDescriptionLine4 == null) ? 0 : this.cardDescriptionLine4
      .hashCode());
    result = 31 * 
      result + (
      (this.cardDescriptionLine5 == null) ? 0 : this.cardDescriptionLine5
      .hashCode());
    result = 31 * result + (
      (this.cardName == null) ? 0 : this.cardName.hashCode());
    result = 31 * result + (
      (this.cardPermission == null) ? 0 : this.cardPermission.hashCode());
    result = 31 * result + (
      (this.cardPrice == null) ? 0 : this.cardPrice.hashCode());
    result = 31 * result + ((this.clase == null) ? 0 : this.clase.hashCode());
    return result;
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null)
      return false; 
    if (getClass() != obj.getClass())
      return false; 
    Card other = (Card)obj;
    if (this.cardCost == null) {
      if (other.cardCost != null)
        return false; 
    } else if (!this.cardCost.equals(other.cardCost)) {
      return false;
    }  if (this.cardDescriptionLine1 == null) {
      if (other.cardDescriptionLine1 != null)
        return false; 
    } else if (!this.cardDescriptionLine1.equals(other.cardDescriptionLine1)) {
      return false;
    }  if (this.cardDescriptionLine2 == null) {
      if (other.cardDescriptionLine2 != null)
        return false; 
    } else if (!this.cardDescriptionLine2.equals(other.cardDescriptionLine2)) {
      return false;
    }  if (this.cardDescriptionLine3 == null) {
      if (other.cardDescriptionLine3 != null)
        return false; 
    } else if (!this.cardDescriptionLine3.equals(other.cardDescriptionLine3)) {
      return false;
    }  if (this.cardDescriptionLine4 == null) {
      if (other.cardDescriptionLine4 != null)
        return false; 
    } else if (!this.cardDescriptionLine4.equals(other.cardDescriptionLine4)) {
      return false;
    }  if (this.cardDescriptionLine5 == null) {
      if (other.cardDescriptionLine5 != null)
        return false; 
    } else if (!this.cardDescriptionLine5.equals(other.cardDescriptionLine5)) {
      return false;
    }  if (this.cardName == null) {
      if (other.cardName != null)
        return false; 
    } else if (!this.cardName.equals(other.cardName)) {
      return false;
    }  if (this.cardPermission == null) {
      if (other.cardPermission != null)
        return false; 
    } else if (!this.cardPermission.equals(other.cardPermission)) {
      return false;
    }  if (this.cardPrice == null) {
      if (other.cardPrice != null)
        return false; 
    } else if (!this.cardPrice.equals(other.cardPrice)) {
      return false;
    }  if (this.clase != other.clase)
      return false; 
    return true;
  }
}
