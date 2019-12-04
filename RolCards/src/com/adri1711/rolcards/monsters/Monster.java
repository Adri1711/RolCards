package com.adri1711.rolcards.monsters;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class Monster
{
  private LivingEntity monsterType;
  private Double salud;
  private Double danio;
  private Double saludMax;
  
  public Monster(LivingEntity monster, Double sal, Double dan, Double salmax, String n, Location loc, int numeroLocation) {
    this.monsterType = monster;
    this.salud = sal;
    this.danio = dan;
    this.saludMax = salmax;
    this.name = n;
    this.localizacion = loc;
    this.ready = true;
    this.lugar = numeroLocation;
  }
  private String name; private Location localizacion; private boolean ready; private int lugar;
  public LivingEntity getMonsterType() { return this.monsterType; }

  
  public void setMonsterType(LivingEntity monsterType) { this.monsterType = monsterType; }

  
  public Double getSalud() { return this.salud; }

  
  public void setSalud(Double salud) { this.salud = salud; }

  
  public Double getDanio() { return this.danio; }

  
  public void setDanio(Double danio) { this.danio = danio; }

  
  public Double getSaludMax() { return this.saludMax; }

  
  public void setSaludMax(Double saludMax) { this.saludMax = saludMax; }

  
  public String getName() { return this.name; }

  
  public void setName(String name) { this.name = name; }

  
  public Location getLocalizacion() { return this.localizacion; }

  
  public void setLocalizacion(Location localizacion) { this.localizacion = localizacion; }

  
  public boolean isReady() { return this.ready; }

  
  public void setReady(boolean ready) { this.ready = ready; }

  
  public int getLugar() { return this.lugar; }

  
  public void setLugar(int lugar) { this.lugar = lugar; }
  
  public void spawn() {
    LivingEntity ent = (LivingEntity)this.localizacion.getWorld().spawnEntity(this.localizacion, this.monsterType.getType());
    ent.setCustomName(String.valueOf(this.name) + " " + getDanio() + "☢  " + getSalud() + "♥");
    ent.setRemoveWhenFarAway(false);
    ent.setCustomNameVisible(true);
    ent.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 
          20000000, 254));
    ent.setMaxHealth(this.saludMax.doubleValue());
    ent.setHealth(this.salud.doubleValue());
    this.monsterType = ent;
  }
  
  public void despawn() { this.monsterType.remove(); }
}
