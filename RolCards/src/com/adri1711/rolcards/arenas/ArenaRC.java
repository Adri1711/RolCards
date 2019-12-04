package com.adri1711.rolcards.arenas;

import java.util.List;
import org.bukkit.Location;

public class ArenaRC {
  private Location spawn1;
  private Location spawn2;
  private Location spawndeathmatch1;
  private Location spawndeathmatch2;
  private List<Location> mobSpawn1;
  private List<Location> mobSpawn2;
  private Integer numArena;
  
  public ArenaRC(Integer nArena, Location spawn1, Location spawn2, Location spawndt1, Location spawndt2, List<Location> mobSpawn1, List<Location> mobSpawn2) {
    this.numArena = nArena;
    this.spawn1 = spawn1;
    this.spawn2 = spawn2;
    this.spawndeathmatch1 = spawndt1;
    this.spawndeathmatch2 = spawndt2;
    this.mobSpawn1 = mobSpawn1;
    this.mobSpawn2 = mobSpawn2;
  }
  
  public Location getSpawn1() { return this.spawn1; }

  
  public void setSpawn1(Location spawn1) { this.spawn1 = spawn1; }

  
  public Location getSpawn2() { return this.spawn2; }

  
  public void setSpawn2(Location spawn2) { this.spawn2 = spawn2; }

  
  public Integer getNumArena() { return this.numArena; }

  
  public void setNumArena(Integer numArena) { this.numArena = numArena; }

  
  public Location getSpawndeathmatch1() { return this.spawndeathmatch1; }

  
  public void setSpawndeathmatch1(Location spawndeathmatch1) { this.spawndeathmatch1 = spawndeathmatch1; }

  
  public Location getSpawndeathmatch2() { return this.spawndeathmatch2; }

  
  public void setSpawndeathmatch2(Location spawndeathmatch2) { this.spawndeathmatch2 = spawndeathmatch2; }

  
  public List<Location> getMobSpawn1() { return this.mobSpawn1; }

  
  public void setMobSpawn1(List<Location> mobSpawn1) { this.mobSpawn1 = mobSpawn1; }

  
  public List<Location> getMobSpawn2() { return this.mobSpawn2; }

  
  public void setMobSpawn2(List<Location> mobSpawn2) { this.mobSpawn2 = mobSpawn2; }
}
