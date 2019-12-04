package com.adri1711.rolcards.utils;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.CreatedCard;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.monsters.Monster;
import java.util.Random;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;





public class Skills
{
  public static void makeTurningTheTablesEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(2.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    if (p.getHealth() + 2.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 2.0D);
    } 
  }
  
  public static void makeNuclearBombEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    en.damage(4.0D);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CLOUD, 29);
    en.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
    en.getWorld().createExplosion(en.getLocation(), 0.0F);
  }
  
  public static void makeNewLifeEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.setHealth(20.0D);
    p.setHealth(20.0D);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.HEART, 34);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }
  
  public static void makeManaSupplyEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    j.setMana(j.getMana() + 1);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HAPPY_VILLAGER, 34);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }

  
  public static void makeLifeChangeEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double enemyH = Double.valueOf(en.getHealth());
    Double pH = Double.valueOf(p.getHealth());
    en.setHealth(pH.doubleValue());
    p.setHealth(enemyH.doubleValue());
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.PORTAL, 24);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.PORTAL, 24);
    p.playSound(p.getLocation(), Utils.buscaSonido("PORTAL", "PORTAL"), 10.0F, 
        1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("PORTAL", "PORTAL"), 
        10.0F, 1.0F);
  }
  
  public static void makeFearOfHeightsEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(6.0D);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.FOOTSTEP, 28);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.FOOTSTEP, 28);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.FOOTSTEP, 28);
    p.playSound(p.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 1.0F);
  }

  
  public static void makeDivineHealerEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    if (p.getHealth() + 6.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 6.0D);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }
  
  public static void makeBurnEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.setFireTicks(60);
    p.playSound(p.getLocation(), 
        Utils.buscaSonido2("ENTITY_GENERIC_BURN", "FIRE"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), 
        Utils.buscaSonido2("ENTITY_GENERIC_BURN", "FIRE"), 10.0F, 1.0F);
  }
  
  public static void makeArcaneIntellectEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    j.drawACard();
    j.drawACard();
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HAPPY_VILLAGER, 21);
    p.playSound(p.getLocation(), Utils.buscaSonido("NOTE", "PLING"), 10.0F, 1.0F);
  }
  
  public static void makeMageSkillEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    if (p.getHealth() + 1.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 1.0D);
    } 
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    p.playSound(p.getLocation(), Utils.buscaSonido("NOTE", "PLING"), 10.0F, 1.0F);
  }
  
  public static void makeHunterSkillEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    if (p.getInventory().getItem(1) == null) {
      p.getInventory().setItem(1, new ItemStack(Material.ARROW));
    }
    else if (p.getInventory().getItem(1).getType() != Material.ARROW) {
      p.getInventory().setItem(1, new ItemStack(Material.ARROW));
    } else {
      p.getInventory().getItem(1)
        .setAmount(p.getInventory().getItem(1).getAmount() + 1);
    } 
    
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.updateInventory();
  }
  
  public static void makeDivineBowEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    ItemStack arco = new ItemStack(Material.BOW);
    ItemMeta arcoMeta = arco.getItemMeta();
    arcoMeta.setDisplayName(ChatColor.GREEN + ""+ ChatColor.BOLD + 
        "Divine Bow");
    arco.setItemMeta(arcoMeta);
    
    arco.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
    p.getInventory().setItem(0, arco);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.updateInventory();
  }
  
  public static void makeFinishHimEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(12.0D);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
    p.playSound(p.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
  }

  
  public static void makeFirstAidEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    if (p.getHealth() + 2.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 2.0D);
    } 
  }


  
  public static void makeGetACopyEffect(Jugador j, Jugador enemy, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Player p = j.getP();
    if (enemy.getCartas().size() != 0) {
      Random r = new Random();
      int carta1 = r.nextInt(enemy.getCartas().size());
      ItemStack carta = Utils.transformaCarta(
          enemy.getCartas().get(carta1), plugin);
      for (int i = 2; i < 7; i++) {
        if (p.getInventory().getItem(i) == null) {
          p.getInventory().setItem(i, carta);
          break;
        } 
        if (i == 6 && p.getInventory().getItem(i) != null) {
          if (plugin.getLanguage().equalsIgnoreCase("es")) {
            p.sendMessage(ChatColor.RED + 
                "Tu mano esta llena, no puedes robar una carta");
          } else {
            p.sendMessage(ChatColor.RED + 
                message.getPlayerHandFullMsg());
          }
        
        }
      } 
    } else if (plugin.getLanguage().equalsIgnoreCase("es")) {
      p.sendMessage(ChatColor.RED + "El enemigo no tiene mas cartas");
    } else {
      p.sendMessage(ChatColor.RED + 
          message.getPlayerEnemyNoCardsMsg());
    } 
    
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
  }
  
  public static void makeInstantPoisonEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 1));
    p.playSound(p.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
  }
  
  public static void makeLegendaryBowEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    ItemStack arco = new ItemStack(Material.BOW);
    ItemMeta arcoMeta = arco.getItemMeta();
    arcoMeta.setDisplayName(ChatColor.GREEN + ""+ ChatColor.BOLD + 
        "Legendary Bow");
    arco.setItemMeta(arcoMeta);
    
    arco.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
    p.getInventory().setItem(0, arco);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.updateInventory();
  }
  
  public static void makeMortalTrapEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(8.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.INSTANT_SPELL, 14);
  }

  
  public static void makePoisonGasEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    Player en = enemy.getP();
    en.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1));
    p.playSound(p.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CLOUD, 29);
  }
  
  public static void makeToTheHeadEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(2.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("ARROW", "HIT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }

  
  public static void makeWarriorSkillEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    Player en = enemy.getP();
    en.damage(1.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }

  
  public static void makeBerserkerEffect(Jugador j, Jugador enemy, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Player en = enemy.getP();
    Player p = j.getP();
    Random r = new Random();
    int chance = r.nextInt(3);
    if (chance == 0) {
      en.damage(4.0D);
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.GREEN + "2 daÃ±o");
      } else {
        p.sendMessage(ChatColor.GREEN + "2" + 
            message.getWarriorBerserkerEffectMsg());
      } 
    } else if (chance == 1) {
      en.damage(6.0D);
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.GREEN + "3 daÃ±o");
      } else {
        p.sendMessage(ChatColor.GREEN + "3" + 
            message.getWarriorBerserkerEffectMsg());
      } 
    } else if (chance == 2) {
      en.damage(8.0D);
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.GREEN + "4 daÃ±o");
      } else {
        p.sendMessage(ChatColor.GREEN + "4" + 
            message.getWarriorBerserkerEffectMsg());
      } 
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }

  
  public static void makeBrutalHitEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(6.0D);
    p.playSound(p.getLocation(), 
        Utils.buscaSonido2("ENTITY_BLAZE_HURT", "BLAZE_HIT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), 
        Utils.buscaSonido2("ENTITY_BLAZE_HURT", "BLAZE_HIT"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }


  
  public static void makeInsanityEffect(Jugador j, Jugador enemy, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    
    Player en = enemy.getP();
    Player p = j.getP();
    Random r = new Random();
    int chance = r.nextInt(2);
    if (chance == 0) {
      en.damage(4.0D);
      en.getWorld()
        .playEffect(
          new Location(en.getWorld(), 
            en.getLocation().getX(), en.getLocation()
            .getY() + 2.0D, en.getLocation()
            .getZ()), Effect.CRIT, 9);
    } else if (chance == 1) {
      p.damage(4.0D);
      p.getWorld().playEffect(
          new Location(p.getWorld(), p.getLocation().getX(), p
            .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
          Effect.CRIT, 9);
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.GREEN + "Te daÃ±aste a ti mismo");
        en.sendMessage(ChatColor.GREEN + p.getName() + 
            " se daÃ±o a si mismo");
      } else {
        p.sendMessage(ChatColor.GREEN + 
            message.getWarriorInsanityEffectYourselfMsg());
        en.sendMessage(ChatColor.GREEN + p.getName() + 
            message.getWarriorInsanityEffectHisselfMsg());
      } 
    } 
    p.playSound(p.getLocation(), 
        Utils.buscaSonido2("ENTITY_BLAZE_HURT", "BLAZE_HIT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), 
        Utils.buscaSonido2("ENTITY_BLAZE_HURT", "BLAZE_HIT"), 10.0F, 1.0F);
  }

  
  public static void makeIronBallEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    ItemStack ball = new ItemStack(Material.SNOW_BALL);
    ItemMeta ballMeta = ball.getItemMeta();
    ballMeta.setDisplayName(ChatColor.RED + ""+ChatColor.BOLD + 
        "Iron Ball");
    ball.setItemMeta(ballMeta);
    p.getInventory().setItem(0, ball);
    p.updateInventory();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
  }

  
  public static void makeWoodWeaponEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    ItemStack sword = new ItemStack(Material.WOOD_SWORD);
    ItemMeta swordMeta = sword.getItemMeta();
    swordMeta.setDisplayName(ChatColor.WHITE +""+ ChatColor.BOLD + 
        "Wood Weapon");
    sword.setItemMeta(swordMeta);
    
    p.getInventory().setItem(0, sword);
    p.updateInventory();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
  }
  
  public static void makeTigerBiteEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    Player en = enemy.getP();
    en.damage(2.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("CAT", "PURR"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("CAT", "PURR"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }


  
  public static void makeTakeARestEffect(Jugador j, Jugador enemy, RolCards plugin) {
    Player p = j.getP();
    if (p.getHealth() + 6.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 6.0D);
    } 
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    Utils.pasaTurno(j, plugin);
  }
  
  public static void makeShieldBearerEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    ItemStack ball = new ItemStack(Material.LEATHER_CHESTPLATE);
    p.getEquipment().setChestplate(ball);
    p.updateInventory();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }
  
  public static void makePresentForYouEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    en.damage(7.0D);
    en.getWorld().createExplosion(en.getLocation(), 0.0F);
  }
  
  public static void makeMinorHealingEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    if (p.getHealth() + 2.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 2.0D);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
  }

  
  public static void makeManaSetEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    j.setMana(5);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }

  
  public static void makeMajorHealingEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    if (p.getHealth() + 4.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 4.0D);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
  }

  
  public static void makeInsectBiteEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(1.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }
  
  public static void makeIncantationEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(2.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    if (p.getHealth() + 1.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 1.0D);
    } 
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
  }
  
  public static void makeGreatWeaponEffect(Jugador j, Jugador enemy) {
    Player p = j.getP();
    ItemStack sword = new ItemStack(Material.IRON_SWORD);
    ItemMeta swordMeta = sword.getItemMeta();
    swordMeta.setDisplayName(ChatColor.WHITE +""+ ChatColor.BOLD + 
        "Great Weapon");
    sword.setItemMeta(swordMeta);
    
    p.getInventory().setItem(0, sword);
    p.updateInventory();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
  }
  
  public static void makeFlameEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.setFireTicks(30);
    p.playSound(p.getLocation(), 
        Utils.buscaSonido2("ENTITY_GENERIC_BURN", "FIRE"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), 
        Utils.buscaSonido2("ENTITY_GENERIC_BURN", "FIRE"), 10.0F, 1.0F);
  }

  
  public static void makeEqualityEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    p.setHealth(en.getHealth());
    p.playSound(p.getLocation(), Utils.buscaSonido("PORTAL", "PORTAL"), 10.0F, 
        1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("PORTAL", "PORTAL"), 
        10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.PORTAL, 24);
  }
  
  public static void makeElephantStompEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(6.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }
  
  public static void makeBearScratchEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage(3.0D);
    p.playSound(p.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 10.0F, 
        1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }
  
  public static void makeSnatchAwayEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    p.playSound(p.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    en.getInventory().setItem(0, null);
    en.updateInventory();
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
  }

  
  public static void makeDisarmEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    
    p.getInventory().setItem(0, en.getInventory().getItem(0));
    p.getInventory().setItem(1, en.getInventory().getItem(1));
    p.updateInventory();
    en.getInventory().setItem(0, null);
    en.getInventory().setItem(1, null);
    en.updateInventory();
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
  }

  
  public static void makeLastTryEffect(Jugador j, Jugador enemy) {
    Player en = enemy.getP();
    Player p = j.getP();
    if (p.getHealth() <= 4.0D) {
      en.damage(10.0D);
      p.playSound(p.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 
          1.0F);
      en.playSound(en.getLocation(), Utils.buscaSonido("BIG", "FALL"), 
          10.0F, 1.0F);
    } else {
      en.damage(4.0D);
      p.playSound(p.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 
          10.0F, 1.0F);
      en.playSound(en.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 
          10.0F, 1.0F);
    } 
    
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }



  
  public static void makeTurningTheTablesEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 1.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(en.getWorld(), mon.getMonsterType()
          .getLocation().getX(), mon.getMonsterType()
          .getLocation().getY() + 2.0D, mon.getMonsterType()
          .getLocation().getZ()), Effect.MAGIC_CRIT, 10);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    if (p.getHealth() + 2.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 2.0D);
    } 
  }

  
  public static void makeNuclearBombEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 2.5D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    
    mon.getMonsterType().getWorld()
      .createExplosion(mon.getMonsterType().getLocation(), 0.0F);
  }
  
  public static void makeNewLifeEffect(Jugador j, Jugador enemy, Monster mon) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.setHealth(20.0D);
    p.setHealth(20.0D);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.HEART, 34);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }

  
  public static void makeManaSupplyEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    j.setMana(j.getMana() + 1);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HAPPY_VILLAGER, 34);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }


  
  public static void makeLifeChangeEffect(Jugador j, Jugador enemy, Monster mon) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double enemyH = Double.valueOf(en.getHealth());
    Double pH = Double.valueOf(p.getHealth());
    en.setHealth(pH.doubleValue());
    p.setHealth(enemyH.doubleValue());
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.PORTAL, 24);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.PORTAL, 24);
    p.playSound(p.getLocation(), Utils.buscaSonido("PORTAL", "PORTAL"), 10.0F, 
        1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("PORTAL", "PORTAL"), 
        10.0F, 1.0F);
  }

  
  public static void makeFearOfHeightsEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 3.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.FOOTSTEP, 28);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.FOOTSTEP, 28);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.FOOTSTEP, 28);
    p.playSound(p.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 1.0F);
  }


  
  public static void makeDivineHealerEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() + 3.0D);
    if (saludRes.doubleValue() <= mon.getSaludMax().doubleValue()) {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } else {
      Utils.actualizaSalud(j, enemy, mon, mon.getSaludMax());
    } 
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.HEART, 34);
    
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }
  
  public static void makeBurnEffect(Jugador j, Jugador enemy, Monster mon) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.setFireTicks(60);
    p.playSound(p.getLocation(), 
        Utils.buscaSonido2("ENTITY_GENERIC_BURN", "FIRE"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), 
        Utils.buscaSonido2("ENTITY_GENERIC_BURN", "FIRE"), 10.0F, 1.0F);
  }

  
  public static void makeArcaneIntellectEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    j.drawACard();
    j.drawACard();
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HAPPY_VILLAGER, 21);
    p.playSound(p.getLocation(), Utils.buscaSonido("NOTE", "PLING"), 10.0F, 1.0F);
  }
  
  public static void makeMageSkillEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() + 0.5D);
    if (saludRes.doubleValue() <= mon.getSaludMax().doubleValue()) {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } else {
      Utils.actualizaSalud(j, enemy, mon, mon.getSaludMax());
    } 
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.HEART, 34);
    p.playSound(p.getLocation(), Utils.buscaSonido("NOTE", "PLING"), 10.0F, 1.0F);
  }

  
  public static void makeHunterSkillEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    if (p.getInventory().getItem(1) == null) {
      p.getInventory().setItem(1, new ItemStack(Material.ARROW));
    }
    else if (p.getInventory().getItem(1).getType() != Material.ARROW) {
      p.getInventory().setItem(1, new ItemStack(Material.ARROW));
    } else {
      p.getInventory().getItem(1)
        .setAmount(p.getInventory().getItem(1).getAmount() + 1);
    } 
    
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.updateInventory();
  }
  
  public static void makeDivineBowEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    ItemStack arco = new ItemStack(Material.BOW);
    ItemMeta arcoMeta = arco.getItemMeta();
    arcoMeta.setDisplayName(ChatColor.GREEN + ""+ChatColor.BOLD + 
        "Divine Bow");
    arco.setItemMeta(arcoMeta);
    
    arco.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
    p.getInventory().setItem(0, arco);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.updateInventory();
  }

  
  public static void makeFinishHimEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 6.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
    p.playSound(p.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
  }

  
  public static void makeFirstAidEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.HEART, 34);
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() + 1.0D);
    if (saludRes.doubleValue() <= mon.getSaludMax().doubleValue()) {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } else {
      Utils.actualizaSalud(j, enemy, mon, mon.getSaludMax());
    } 
  }


  
  public static void makeGetACopyEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Player p = j.getP();
    if (enemy.getCartas().size() != 0) {
      Random r = new Random();
      int carta1 = r.nextInt(enemy.getCartas().size());
      ItemStack carta = Utils.transformaCarta(
          enemy.getCartas().get(carta1), plugin);
      for (int i = 2; i < 7; i++) {
        if (p.getInventory().getItem(i) == null) {
          p.getInventory().setItem(i, carta);
          break;
        } 
        if (i == 6 && p.getInventory().getItem(i) != null) {
          if (plugin.getLanguage().equalsIgnoreCase("es")) {
            p.sendMessage(ChatColor.RED + 
                "Tu mano esta llena, no puedes robar una carta");
          } else {
            p.sendMessage(ChatColor.RED + 
                message.getPlayerHandFullMsg());
          }
        
        }
      } 
    } else if (plugin.getLanguage().equalsIgnoreCase("es")) {
      p.sendMessage(ChatColor.RED + "El enemigo no tiene mas cartas");
    } else {
      p.sendMessage(ChatColor.RED + 
          message.getPlayerEnemyNoCardsMsg());
    } 
    
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
  }

  
  public static void makeInstantPoisonEffect(Jugador j, Jugador enemy, Monster mon) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 50, 1));
    p.playSound(p.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
  }

  
  public static void makeLegendaryBowEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    ItemStack arco = new ItemStack(Material.BOW);
    ItemMeta arcoMeta = arco.getItemMeta();
    arcoMeta.setDisplayName(ChatColor.GREEN + ""+ChatColor.BOLD + 
        "Legendary Bow");
    arco.setItemMeta(arcoMeta);
    
    arco.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
    p.getInventory().setItem(0, arco);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.updateInventory();
  }

  
  public static void makeMortalTrapEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 4.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.INSTANT_SPELL, 14);
  }

  
  public static void makePoisonGasEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    Player en = enemy.getP();
    en.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1));
    p.playSound(p.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CLOUD, 29);
  }

  
  public static void makeToTheHeadEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 1.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("ARROW", "HIT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }


  
  public static void makeWarriorSkillEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 0.5D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }

  
  public static void makeBerserkerEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    
    Player p = j.getP();
    Random r = new Random();
    int chance = r.nextInt(3);
    if (chance == 0) {
      Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 2.0D);
      if (saludRes.doubleValue() <= 0.0D) {
        Utils.mataMonstruo(j, enemy, mon, plugin);
      } else {
        Utils.actualizaSalud(j, enemy, mon, saludRes);
      } 
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.GREEN + "2 daÃ±o");
      } else {
        p.sendMessage(ChatColor.GREEN + "2" + 
            message.getWarriorBerserkerEffectMsg());
      } 
    } else if (chance == 1) {
      Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 3.0D);
      if (saludRes.doubleValue() <= 0.0D) {
        Utils.mataMonstruo(j, enemy, mon, plugin);
      } else {
        Utils.actualizaSalud(j, enemy, mon, saludRes);
      } 
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.GREEN + "3 daÃ±o");
      } else {
        p.sendMessage(ChatColor.GREEN + "3" + 
            message.getWarriorBerserkerEffectMsg());
      } 
    } else if (chance == 2) {
      Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 4.0D);
      if (saludRes.doubleValue() <= 0.0D) {
        Utils.mataMonstruo(j, enemy, mon, plugin);
      } else {
        Utils.actualizaSalud(j, enemy, mon, saludRes);
      } 
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.GREEN + "4 daÃ±o");
      } else {
        p.sendMessage(ChatColor.GREEN + "4" + 
            message.getWarriorBerserkerEffectMsg());
      } 
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }


  
  public static void makeBrutalHitEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 3.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), 
        Utils.buscaSonido2("ENTITY_BLAZE_HURT", "BLAZE_HIT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), 
        Utils.buscaSonido2("ENTITY_BLAZE_HURT", "BLAZE_HIT"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }


  
  public static void makeInsanityEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    
    Player en = enemy.getP();
    Player p = j.getP();
    Random r = new Random();
    int chance = r.nextInt(2);
    if (chance == 0) {
      Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 2.0D);
      if (saludRes.doubleValue() <= 0.0D) {
        Utils.mataMonstruo(j, enemy, mon, plugin);
      } else {
        Utils.actualizaSalud(j, enemy, mon, saludRes);
      } 
      mon.getMonsterType()
        .getWorld()
        .playEffect(
          new Location(mon.getMonsterType().getWorld(), mon
            .getMonsterType().getLocation().getX(), mon
            .getMonsterType().getLocation().getY() + 2.0D, 
            mon.getMonsterType().getLocation().getZ()), 
          Effect.CRIT, 9);
    } else if (chance == 1) {
      p.damage(4.0D);
      p.getWorld().playEffect(
          new Location(p.getWorld(), p.getLocation().getX(), p
            .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
          Effect.CRIT, 9);
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        p.sendMessage(ChatColor.GREEN + "Te daÃ±aste a ti mismo");
        en.sendMessage(ChatColor.GREEN + p.getName() + 
            " se daÃ±o a si mismo");
      } else {
        p.sendMessage(ChatColor.GREEN + 
            message.getWarriorInsanityEffectYourselfMsg());
        en.sendMessage(ChatColor.GREEN + p.getName() + 
            message.getWarriorInsanityEffectHisselfMsg());
      } 
    } 
    p.playSound(p.getLocation(), 
        Utils.buscaSonido2("ENTITY_BLAZE_HURT", "BLAZE_HIT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), 
        Utils.buscaSonido2("ENTITY_BLAZE_HURT", "BLAZE_HIT"), 10.0F, 1.0F);
  }

  
  public static void makeIronBallEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    ItemStack ball = new ItemStack(Material.SNOW_BALL);
    ItemMeta ballMeta = ball.getItemMeta();
    ballMeta.setDisplayName(ChatColor.RED + ""+ChatColor.BOLD + 
        "Iron Ball");
    ball.setItemMeta(ballMeta);
    p.getInventory().setItem(0, ball);
    p.updateInventory();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
  }


  
  public static void makeWoodWeaponEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    ItemStack sword = new ItemStack(Material.WOOD_SWORD);
    ItemMeta swordMeta = sword.getItemMeta();
    swordMeta.setDisplayName(ChatColor.WHITE + ""+ChatColor.BOLD + 
        "Wood Weapon");
    sword.setItemMeta(swordMeta);
    
    p.getInventory().setItem(0, sword);
    p.updateInventory();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
  }

  
  public static void makeTigerBiteEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player p = j.getP();
    Player en = enemy.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 1.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("CAT", "PURR"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("CAT", "PURR"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }


  
  public static void makeTakeARestEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player p = j.getP();
    if (p.getHealth() + 6.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 6.0D);
    } 
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    Utils.pasaTurno(j, plugin);
  }

  
  public static void makeShieldBearerEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    ItemStack ball = new ItemStack(Material.LEATHER_CHESTPLATE);
    p.getEquipment().setChestplate(ball);
    p.updateInventory();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }


  
  public static void makePresentForYouEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 3.5D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    mon.getMonsterType().getWorld()
      .createExplosion(mon.getMonsterType().getLocation(), 0.0F);
  }

  
  public static void makeMinorHealingEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() + 1.0D);
    if (saludRes.doubleValue() <= mon.getSaludMax().doubleValue()) {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } else {
      Utils.actualizaSalud(j, enemy, mon, mon.getSaludMax());
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.HEART, 34);
  }

  
  public static void makeManaSetEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    j.setMana(5);
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }


  
  public static void makeMajorHealingEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() + 2.0D);
    if (saludRes.doubleValue() <= mon.getSaludMax().doubleValue()) {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } else {
      Utils.actualizaSalud(j, enemy, mon, mon.getSaludMax());
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.HEART, 34);
  }


  
  public static void makeInsectBiteEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 0.5D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }

  
  public static void makeIncantationEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 1.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    if (p.getHealth() + 1.0D > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + 1.0D);
    } 
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
  }

  
  public static void makeGreatWeaponEffect(Jugador j, Jugador enemy, Monster mon) {
    Player p = j.getP();
    ItemStack sword = new ItemStack(Material.IRON_SWORD);
    ItemMeta swordMeta = sword.getItemMeta();
    swordMeta.setDisplayName(ChatColor.WHITE +""+ ChatColor.BOLD + 
        "Great Weapon");
    sword.setItemMeta(swordMeta);
    
    p.getInventory().setItem(0, sword);
    p.updateInventory();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.NOTE, 23);
  }
  
  public static void makeFlameEffect(Jugador j, Jugador enemy, Monster mon) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.setFireTicks(30);
    p.playSound(p.getLocation(), 
        Utils.buscaSonido2("ENTITY_GENERIC_BURN", "FIRE"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), 
        Utils.buscaSonido2("ENTITY_GENERIC_BURN", "FIRE"), 10.0F, 1.0F);
  }

  
  public static void makeEqualityEffect(Jugador j, Jugador enemy, Monster mon) {
    Player en = enemy.getP();
    Player p = j.getP();
    p.setHealth(en.getHealth());
    p.playSound(p.getLocation(), Utils.buscaSonido("PORTAL", "PORTAL"), 10.0F, 
        1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("PORTAL", "PORTAL"), 
        10.0F, 1.0F);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.PORTAL, 24);
  }

  
  public static void makeElephantStompEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 3.0D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }

  
  public static void makeBearScratchEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 1.5D);
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 10.0F, 
        1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }

  
  public static void makeSnatchAwayEffect(Jugador j, Jugador enemy, Monster mon) {
    Player en = enemy.getP();
    Player p = j.getP();
    p.playSound(p.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("ANVIL", "BREAK"), 10.0F, 
        1.0F);
    en.getInventory().setItem(0, null);
    en.updateInventory();
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
  }

  
  public static void makeDisarmEffect(Jugador j, Jugador enemy, Monster mon) {
    Player en = enemy.getP();
    Player p = j.getP();
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("BAT", "HURT"), 10.0F, 1.0F);
    
    p.getInventory().setItem(0, en.getInventory().getItem(0));
    p.getInventory().setItem(1, en.getInventory().getItem(1));
    p.updateInventory();
    en.getInventory().setItem(0, null);
    en.getInventory().setItem(1, null);
    en.updateInventory();
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.MAGIC_CRIT, 10);
  }


  
  public static void makeLastTryEffect(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    if (p.getHealth() <= 4.0D) {
      Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 5.0D);
      if (saludRes.doubleValue() <= 0.0D) {
        Utils.mataMonstruo(j, enemy, mon, plugin);
      } else {
        Utils.actualizaSalud(j, enemy, mon, saludRes);
      } 
      p.playSound(p.getLocation(), Utils.buscaSonido("BIG", "FALL"), 10.0F, 
          1.0F);
      en.playSound(en.getLocation(), Utils.buscaSonido("BIG", "FALL"), 
          10.0F, 1.0F);
    } else {
      Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - 2.0D);
      if (saludRes.doubleValue() <= 0.0D) {
        Utils.mataMonstruo(j, enemy, mon, plugin);
      } else {
        Utils.actualizaSalud(j, enemy, mon, saludRes);
      } 
      p.playSound(p.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 
          10.0F, 1.0F);
      en.playSound(en.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 
          10.0F, 1.0F);
    } 
    
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }

  
  public static void invokesVampire(Jugador j, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Integer lugar = Utils.buscaLugarLibre(j);
    if (j.getPartida().getJugador1().getP().getName()
      .equals(j.getP().getName())) {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn1()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn1().get(lugar.intValue()), 
          EntityType.ZOMBIE);
      String name = message.getMonsterVampireMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§f§lVampiro";
      }
      Monster mon = new Monster(ent, Double.valueOf(3.0D), Double.valueOf(3.0D), Double.valueOf(3.0D), name, j.getPartida()
          .getArena().getMobSpawn1().get(lugar.intValue()), lugar.intValue());
      ent.remove();
      ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
      LeatherArmorMeta lch = (LeatherArmorMeta)lchest.getItemMeta();
      lch.setColor(Color.fromRGB(0, 0, 0));
      lchest.setItemMeta((ItemMeta)lch);
      ItemStack lhat = new ItemStack(Material.LEATHER_HELMET, 1);
      LeatherArmorMeta lha = (LeatherArmorMeta)lhat.getItemMeta();
      lha.setColor(Color.fromRGB(255, 0, 0));
      lhat.setItemMeta((ItemMeta)lha);
      ItemStack lleg = new ItemStack(Material.LEATHER_LEGGINGS, 1);
      LeatherArmorMeta lle = (LeatherArmorMeta)lleg.getItemMeta();
      lle.setColor(Color.fromRGB(0, 0, 0));
      lleg.setItemMeta((ItemMeta)lle);
      ItemStack lboots = new ItemStack(Material.LEATHER_BOOTS, 1);
      LeatherArmorMeta lboot = (LeatherArmorMeta)lboots.getItemMeta();
      lboot.setColor(Color.fromRGB(0, 0, 0));
      lboots.setItemMeta((ItemMeta)lboot);
      mon.getMonsterType().getEquipment().setHelmet(lhat);
      mon.getMonsterType().getEquipment().setChestplate(lchest);
      mon.getMonsterType().getEquipment().setLeggings(lleg);
      mon.getMonsterType().getEquipment().setBoots(lboots);
      mon.spawn();
      j.getMonstruos().add(mon);
    } else {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn2()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn2().get(lugar.intValue()), 
          EntityType.ZOMBIE);
      String name = message.getMonsterVampireMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§f§lVampiro";
      }
      Monster mon = new Monster(ent, Double.valueOf(3.0D), Double.valueOf(3.0D), Double.valueOf(3.0D), name, j.getPartida()
          .getArena().getMobSpawn2().get(lugar.intValue()), lugar.intValue());
      ent.remove();
      ItemStack lchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
      LeatherArmorMeta lch = (LeatherArmorMeta)lchest.getItemMeta();
      lch.setColor(Color.fromRGB(0, 0, 0));
      lchest.setItemMeta((ItemMeta)lch);
      ItemStack lhat = new ItemStack(Material.LEATHER_HELMET, 1);
      LeatherArmorMeta lha = (LeatherArmorMeta)lhat.getItemMeta();
      lha.setColor(Color.fromRGB(255, 0, 0));
      lhat.setItemMeta((ItemMeta)lha);
      ItemStack lleg = new ItemStack(Material.LEATHER_LEGGINGS, 1);
      LeatherArmorMeta lle = (LeatherArmorMeta)lleg.getItemMeta();
      lle.setColor(Color.fromRGB(0, 0, 0));
      lleg.setItemMeta((ItemMeta)lle);
      ItemStack lboots = new ItemStack(Material.LEATHER_BOOTS, 1);
      LeatherArmorMeta lboot = (LeatherArmorMeta)lboots.getItemMeta();
      lboot.setColor(Color.fromRGB(0, 0, 0));
      lboots.setItemMeta((ItemMeta)lboot);
      mon.getMonsterType().getEquipment().setHelmet(lhat);
      mon.getMonsterType().getEquipment().setChestplate(lchest);
      mon.getMonsterType().getEquipment().setLeggings(lleg);
      mon.getMonsterType().getEquipment().setBoots(lboots);
      mon.spawn();
      j.getMonstruos().add(mon);
    } 
  }


  
  public static void invokesExiledZombie(Jugador j, RolCards plugin) {
    Integer lugar = Utils.buscaLugarLibre(j);
    LanguageMessages message = plugin.getMessages();
    
    if (j.getPartida().getJugador1().getP().getName()
      .equals(j.getP().getName())) {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn1()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn1().get(lugar.intValue()), 
          EntityType.PIG_ZOMBIE);
      String name = message.getMonsterExiledZombieMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§f§lZombie Exiliado";
      }
      Monster mon = new Monster(ent, Double.valueOf(2.0D), Double.valueOf(1.0D), Double.valueOf(2.0D), name, j.getPartida()
          .getArena().getMobSpawn1().get(lugar.intValue()), lugar.intValue());
      ent.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } else {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn2()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn2().get(lugar.intValue()), 
          EntityType.PIG_ZOMBIE);
      String name = message.getMonsterExiledZombieMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§f§lZombie Exiliado";
      }
      Monster mon = new Monster(ent, Double.valueOf(2.0D), Double.valueOf(1.0D), Double.valueOf(2.0D), name, j.getPartida()
          .getArena().getMobSpawn2().get(lugar.intValue()), lugar.intValue());
      ent.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } 
  }
  
  public static void invokesBeast(Jugador j, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Integer lugar = Utils.buscaLugarLibre(j);
    if (j.getPartida().getJugador1().getP().getName()
      .equals(j.getP().getName())) {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn1()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn1().get(lugar.intValue()), 
          EntityType.IRON_GOLEM);
      String name = message.getMonsterBeastMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§c§lBestia";
      }
      Monster mon = new Monster(ent, Double.valueOf(2.0D), Double.valueOf(4.0D), Double.valueOf(2.0D), name, j.getPartida()
          .getArena().getMobSpawn1().get(lugar.intValue()), lugar.intValue());
      ent.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } else {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn2()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn2().get(lugar.intValue()), 
          EntityType.IRON_GOLEM);
      String name = message.getMonsterBeastMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§c§lBestia";
      }
      Monster mon = new Monster(ent, Double.valueOf(2.0D), Double.valueOf(4.0D), Double.valueOf(2.0D), name, j.getPartida()
          .getArena().getMobSpawn2().get(lugar.intValue()), lugar.intValue());
      ent.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } 
  }

  
  public static void invokesLealCompanion(Jugador j, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Integer lugar = Utils.buscaLugarLibre(j);
    if (j.getPartida().getJugador1().getP().getName()
      .equals(j.getP().getName())) {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn1()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn1().get(lugar.intValue()), 
          EntityType.WOLF);
      String name = message.getMonsterLealCompanionMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§2§lFiel AcompaÃ±ante";
      }
      Monster mon = new Monster(ent, Double.valueOf(4.0D), Double.valueOf(4.0D), Double.valueOf(4.0D), name, j.getPartida()
          .getArena().getMobSpawn1().get(lugar.intValue()), lugar.intValue());
      ent.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } else {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn2()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn2().get(lugar.intValue()), 
          EntityType.WOLF);
      String name = message.getMonsterLealCompanionMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§2§lFiel AcompaÃ±ante";
      }
      Monster mon = new Monster(ent, Double.valueOf(4.0D), Double.valueOf(4.0D), Double.valueOf(4.0D), name, j.getPartida()
          .getArena().getMobSpawn2().get(lugar.intValue()), lugar.intValue());
      ent.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } 
  }

  
  public static void invokesSlime(Jugador j, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Integer lugar = Utils.buscaLugarLibre(j);
    if (j.getPartida().getJugador1().getP().getName()
      .equals(j.getP().getName())) {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn1()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn1().get(lugar.intValue()), 
          EntityType.SLIME);
      Slime slime = (Slime)ent;
      slime.setSize(1);
      String name = message.getMonsterSlimeMsg();
      Monster mon = new Monster((LivingEntity)slime, Double.valueOf(1.0D), Double.valueOf(1.0D), Double.valueOf(1.0D), name, j.getPartida()
          .getArena().getMobSpawn1().get(lugar.intValue()), lugar.intValue());
      slime.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } else {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn2()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn2().get(lugar.intValue()), 
          EntityType.SLIME);
      String name = message.getMonsterSlimeMsg();
      Slime slime = (Slime)ent;
      slime.setSize(1);
      Monster mon = new Monster((LivingEntity)slime, Double.valueOf(1.0D), Double.valueOf(1.0D), Double.valueOf(1.0D), name, j.getPartida()
          .getArena().getMobSpawn2().get(lugar.intValue()), lugar.intValue());
      slime.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } 
  }

  
  public static void invokesCat(Jugador j, RolCards plugin) {
    LanguageMessages message = plugin.getMessages();
    Integer lugar = Utils.buscaLugarLibre(j);
    if (j.getPartida().getJugador1().getP().getName()
      .equals(j.getP().getName())) {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn1()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn1().get(lugar.intValue()), 
          EntityType.OCELOT);
      String name = message.getMonsterWitchCatMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§1§lGato Brujo";
      }
      Ocelot cat = (Ocelot)ent;
      cat.setAdult();
      cat.setCatType(Ocelot.Type.BLACK_CAT);
      Monster mon = new Monster((LivingEntity)cat, Double.valueOf(3.0D), Double.valueOf(2.0D), Double.valueOf(3.0D), name, j.getPartida()
          .getArena().getMobSpawn1().get(lugar.intValue()), lugar.intValue());
      cat.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } else {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn2()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn2().get(lugar.intValue()), 
          EntityType.OCELOT);
      String name = message.getMonsterWitchCatMsg();
      if (plugin.getLanguage().equalsIgnoreCase("es")) {
        name = "§1§lGato Brujo";
      }
      Ocelot cat = (Ocelot)ent;
      cat.setAdult();
      cat.setCatType(Ocelot.Type.BLACK_CAT);
      Monster mon = new Monster((LivingEntity)cat, Double.valueOf(3.0D), Double.valueOf(2.0D), Double.valueOf(3.0D), name, j.getPartida()
          .getArena().getMobSpawn2().get(lugar.intValue()), lugar.intValue());
      cat.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } 
  }

  
  public static void makeDrawEffect(Jugador j, Jugador enemy, CreatedCard cc) {
    Player p = j.getP();
    for (int i = 0; i < cc.getCardEffectValue().intValue(); i++) {
      j.drawACard();
    }
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HAPPY_VILLAGER, 21);
    p.playSound(p.getLocation(), Utils.buscaSonido("NOTE", "PLING"), 10.0F, 1.0F);
  }

  
  public static void makeDamageEffect(Jugador j, Jugador enemy, CreatedCard cc) {
    Player en = enemy.getP();
    Player p = j.getP();
    en.damage((cc.getCardEffectValue().intValue() * 2));
    p.playSound(p.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 10.0F, 
        1.0F);
    en.getWorld().playEffect(
        new Location(en.getWorld(), en.getLocation().getX(), en
          .getLocation().getY() + 2.0D, en.getLocation().getZ()), 
        Effect.CRIT, 9);
  }


  
  public static void makeDamageEffect(Jugador j, Jugador enemy, CreatedCard cc, Monster mon, RolCards plugin) {
    Player en = enemy.getP();
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() - cc.getCardEffectValue().intValue());
    if (saludRes.doubleValue() <= 0.0D) {
      Utils.mataMonstruo(j, enemy, mon, plugin);
    } else {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 10.0F, 1.0F);
    en.playSound(en.getLocation(), Utils.buscaSonido("FALL", "SMALL"), 10.0F, 
        1.0F);
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.CRIT, 9);
  }


  
  public static void makeHealEffect(Jugador j, Jugador enemy, CreatedCard cc, Monster mon, RolCards plugin) {
    Player p = j.getP();
    Double saludRes = Double.valueOf(mon.getSalud().doubleValue() + cc.getCardEffectValue().intValue());
    if (saludRes.doubleValue() <= mon.getSaludMax().doubleValue()) {
      Utils.actualizaSalud(j, enemy, mon, saludRes);
    } else {
      Utils.actualizaSalud(j, enemy, mon, mon.getSaludMax());
    } 
    mon.getMonsterType()
      .getWorld()
      .playEffect(
        new Location(mon.getMonsterType().getWorld(), mon
          .getMonsterType().getLocation().getX(), mon
          .getMonsterType().getLocation().getY() + 2.0D, mon
          .getMonsterType().getLocation().getZ()), 
        Effect.HEART, 34);
    
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }

  
  public static void makeHealEffect(Jugador j, Jugador enemy, CreatedCard cc) {
    Player p = j.getP();
    p.getWorld().playEffect(
        new Location(p.getWorld(), p.getLocation().getX(), p
          .getLocation().getY() + 2.0D, p.getLocation().getZ()), 
        Effect.HEART, 34);
    if (p.getHealth() + (cc.getCardEffectValue().intValue() * 2) > 20.0D) {
      p.setHealth(20.0D);
    } else {
      p.setHealth(p.getHealth() + (cc.getCardEffectValue().intValue() * 2));
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }

  
  public static void makeSpawnEffect(Jugador j, Jugador enemy, CreatedCard cc) {
    Player p = j.getP();
    Integer lugar = Utils.buscaLugarLibre(j);
    EntityType type = EntityType.SPIDER;
    switch (cc.getCardEffectValue().intValue()) {
      case 0:
        type = EntityType.SPIDER;
        break;
      case 1:
        type = EntityType.CAVE_SPIDER;
        break;
      case 2:
        type = EntityType.ZOMBIE;
        break;
      case 3:
        type = EntityType.PIG_ZOMBIE;
        break;
      case 4:
        type = EntityType.PIG;
        break;
      case 5:
        type = EntityType.RABBIT;
        break;
      case 6:
        type = EntityType.CHICKEN;
        break;
      case 7:
        type = EntityType.COW;
        break;
      case 8:
        type = EntityType.SHEEP;
        break;
      case 9:
        type = EntityType.VILLAGER;
        break;
      case 10:
        type = EntityType.SLIME;
        break;
      case 11:
        type = EntityType.WITCH;
        break;
      case 12:
        type = EntityType.SILVERFISH;
        break;
      case 13:
        type = EntityType.MAGMA_CUBE;
        break;
      case 14:
        type = EntityType.HORSE;
        break;
    } 

    
    if (j.getPartida().getJugador1().getP().getName()
      .equals(j.getP().getName())) {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn1()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn1().get(lugar.intValue()), 
          type);
      String name = cc.getCardMobName();
      
      Monster mon = new Monster(ent, cc.getCardHealth(), 
          cc.getCardDamage(), cc.getCardHealth(), name, j
          .getPartida().getArena().getMobSpawn1().get(lugar.intValue()), 
          lugar.intValue());
      ent.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } else {
      LivingEntity ent = (LivingEntity)((Location)j
        .getPartida()
        .getArena()
        .getMobSpawn2()
        .get(lugar.intValue()))
        .getWorld()
        .spawnEntity(
          j.getPartida().getArena().getMobSpawn2().get(lugar.intValue()), 
          type);
      String name = cc.getCardMobName();
      
      Monster mon = new Monster(ent, cc.getCardHealth(), 
          cc.getCardDamage(), cc.getCardHealth(), name, j
          .getPartida().getArena().getMobSpawn2().get(lugar.intValue()), 
          lugar.intValue());
      ent.remove();
      mon.spawn();
      j.getMonstruos().add(mon);
    } 
    p.playSound(p.getLocation(), Utils.buscaSonido("LEVEL", "UP"), 10.0F, 1.0F);
  }
}
