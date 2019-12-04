package com.adri1711.rolcards.language;

import com.adri1711.rolcards.RolCards;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageMessages
{
  private File file;
  private FileConfiguration fileConfig;
  private RolCards plugin;
  private String challengItemName;
  private List<String> challengItemLore;
  private String announcement;
  private String requestItemName;
  private String cardMenuItemName;
  private String infoItemName;
  private List<String> infoItemLore;
  private String enemyHealthInfo;
  private String manaCostMsg;
  private String nodropMsg;
  private String usingCardMsg;
  private String usingCardTargetMsg;
  private String alreadyUsedSkillMsg;
  private String entityDeathMsg;
  private String entityAttackMsg;
  private String entityAlreadyAttackedMsg;
  private String entityWaitMsg;
  private String scoreboardWinMsg;
  private String scoreboardLoseMsg;
  private String scoreboardMoneyMsg;
  private String scoreboardTurnOfMsg;
  private String matchEloMsg;
  private String matchPrizeMsg;
  private String matchMatchMsg;
  private String matchLoseMsg;
  private String matchDeathmatchMsg;
  private String matchFoundWaitMsg;
  private String matchFoundBeginMsg;
  private String playerTurnOfMsg;
  private String playerHandFullMsg;
  private String playerDeckFullMsg;
  private String playerNoCardsMsg;
  private String playerEnemyNoCardsMsg;
  private String matchRequestsNoMatchMsg;
  private String matchRequestsNoCardsMsg;
  private String matchRequestsNoClassMsg;
  private String matchRequestsPlayerOnMatchMsg;
  private String matchRequestsReceivedMsg;
  private String hunterSkillMsg;
  private String hunterSkillMsg1;
  private String hunterPoisonGasMsg;
  private String hunterPoisonGasMsg1;
  private String hunterGetCopyMsg;
  private String hunterGetCopyMsg1;
  private String hunterToTheHeadMsg;
  private String hunterToTheHeadMsg1;
  private String hunterIPoisonMsg;
  private String hunterIPoisonMsg1;
  private String hunterDivineBowMsg;
  private String hunterDivineBowMsg1;
  private String hunterLBowMsg;
  private String hunterLBowMsg1;
  private String hunterMortalTrapMsg;
  private String hunterMortalTrapMsg1;
  private String hunterFirstAidMsg;
  private String hunterFirstAidMsg1;
  private String hunterFinishHimMsg;
  private String hunterFinishHimMsg1;
  private String hunterLealCompanionMsg;
  private String hunterLealCompanionMsg1;
  private String mageSkillMsg;
  private String mageSkillMsg1;
  private String mageBurnMsg;
  private String mageBurnMsg1;
  private String mageArcaneIntellectMsg;
  private String mageArcaneIntellectMsg1;
  private String mageFearOfHeightsMsg;
  private String mageFearOfHeightsMsg1;
  private String mageFearOfHeightsMsg2;
  private String mageDivineHealerMsg;
  private String mageDivineHealerMsg1;
  private String mageNuclearBombMsg;
  private String mageNuclearBombMsg1;
  private String mageNuclearBombMsg2;
  private String mageTurningTheTablesMsg;
  private String mageTurningTheTablesMsg1;
  private String mageTurningTheTablesMsg2;
  private String mageNewLifeMsg;
  private String mageNewLifeMsg1;
  private String mageManaSupplyMsg;
  private String mageManaSupplyMsg1;
  private String mageLifeChangeMsg;
  private String mageLifeChangeMsg1;
  private String mageWitchCatMsg;
  private String mageWitchCatMsg1;
  private String warriorSkillMsg;
  private String warriorSkillMsg1;
  private String warriorBrutalHitMsg;
  private String warriorBrutalHitMsg1;
  private String warriorBerserkerMsg;
  private String warriorBerserkerMsg1;
  private String warriorBerserkerEffectMsg;
  private String warriorInsanityMsg;
  private String warriorInsanityMsg1;
  private String warriorInsanityEffectYourselfMsg;
  private String warriorInsanityEffectHisselfMsg;
  private String warriorIronBallMsg;
  private String warriorIronBallMsg1;
  private String warriorIronBallMsg2;
  private String warriorDisarmMsg;
  private String warriorDisarmMsg1;
  private String warriorSnatchAwayMsg;
  private String warriorSnatchAwayMsg1;
  private String warriorLastTryMsg;
  private String warriorLastTryMsg1;
  private String warriorLastTryMsg2;
  private String warriorBeastMsg;
  private String warriorBeastMsg1;
  private String warriorSlimeMsg;
  private String warriorSlimeMsg1;
  private String normalFlameMsg;
  private String normalFlameMsg1;
  private String normalWoodWeaponMsg;
  private String normalWoodWeaponMsg1;
  private String normalGreatWeaponMsg;
  private String normalGreatWeaponMsg1;
  private String normalInsectBiteMsg;
  private String normalInsectBiteMsg1;
  private String normalTigerBiteMsg;
  private String normalTigerBiteMsg1;
  private String normalBearScratchMsg;
  private String normalBearScratchMsg1;
  private String normalElephantStompMsg;
  private String normalElephantStompMsg1;
  private String normalManaSetMsg;
  private String normalManaSetMsg1;
  private String normalMinorHealingMsg;
  private String normalMinorHealingMsg1;
  private String normalMajorHealingMsg;
  private String normalMajorHealingMsg1;
  private String normalEqualityMsg;
  private String normalEqualityMsg1;
  private String normalShieldBearerMsg;
  private String normalShieldBearerMsg1;
  private String normalShieldBearerMsg2;
  private String normalIncantationMsg;
  private String normalIncantationMsg1;
  private String normalTakeARestMsg;
  private String normalTakeARestMsg1;
  private String normalPresentForYouMsg;
  private String normalPresentForYouMsg1;
  private String normalVampireMsg;
  private String normalVampireMsg1;
  private String normalExiledZombieMsg;
  private String normalExiledZombieMsg1;
  private String monsterVampireMsg;
  private String monsterExiledZombieMsg;
  private String monsterBeastMsg;
  private String monsterLealCompanionMsg;
  private String monsterSlimeMsg;
  private String monsterWitchCatMsg;
  private String guiClassWarriorMsg;
  private String guiClassMageMsg;
  private String guiClassHunterMsg;
  private String guiCardShopMsg;
  private String guiClearDeckMsg;
  private String guiReturnMsg;
  private String guiClassCardsMsg;
  private String guiNormalCardsMsg;
  private String guiDeckDeletedMsg;
  private String guiReturnedMsg;
  private String guiPaidFeeMsg;
  private String guiNoMoneyFeeMsg;
  private String guiBothPlayingMsg;
  private String guiOfflinePlayerMsg;
  private String guiTwoCopiesMsg;
  private String guiNoCardInDeckMsg;
  private String guiCardBuyMsg;
  private String guiCardNoMoneyMsg;
  private String alreadyExistChallenge;
  private String monsterFiveMsg;
  private String noEnoughMana;
  private String noCommands;
  private String matchRequestsSentMsg;
  private String guiLotteryMsg;
  private String guiItemPostpageMsg;
  private String guiItemPrepageMsg;
  
  public LanguageMessages(RolCards plugin) {
    this.file = new File(plugin.getDataFolder(), "messages.yml");
    this.fileConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
    
    this.plugin = plugin;
    if (!this.file.exists() || this.fileConfig.getString("announcement") == null) {
      
      try {
        this.file.createNewFile();

        
        this.fileConfig.set("announcement", " &edestroyed &6");


        
        this.fileConfig.set("challengItem.name", "&c&lChallenge");
        List<String> listaLore1 = new ArrayList<>();
        listaLore1.add("&7Left click to join a random queue");
        listaLore1.add("&7Right click to someone to challeng them");
        this.fileConfig.set("challengItem.lore", listaLore1);
        this.fileConfig.set("requestItem", "&2&lRequests");
        this.fileConfig.set("cardMenuItem", "&2&lCard Menu");
        this.fileConfig.set("infoItem.name", "&6Enemy Info / Mobs Menu");
        List<String> listaLore2 = new ArrayList<>();
        listaLore2.add("&7Right click to see the enemy info");
        listaLore2.add("&7Left click to open the mobs menu");
        this.fileConfig.set("infoItem.lore", listaLore2);
        
        this.fileConfig.set("enemyHealthInfo", "&2Your enemy has ");

        
        this.fileConfig.set("manaCost", "&1Mana Cost");
        this.fileConfig.set("noDrop", "You cant drop items playing.");
        this.fileConfig.set("alreadyExistChallenge", "This challeng already exist wait until he accepts");
        this.fileConfig.set("noEnoughMana", "You dont have enough mana to use this (You have ");
        this.fileConfig.set("noCommands", "You cant do commands that are not from rolcards while you are playing.");


        
        this.fileConfig.set("usingCard.use", " used ");
        this.fileConfig.set("usingCard.target", " on ");
        this.fileConfig.set("alreadyUsedSkill", "You have already used your skill");


        
        this.fileConfig.set("entity.death", " died");
        this.fileConfig.set("entity.attack", " attacked ");
        this.fileConfig.set("entity.alreadyattacked", "This mob has already attacked this turn");
        this.fileConfig.set("entity.wait", "Wait your turn");


        
        this.fileConfig.set("scoreboard.wins", "Wins:");
        this.fileConfig.set("scoreboard.loses", "Loses:");
        this.fileConfig.set("scoreboard.money", "Money:");
        this.fileConfig.set("scoreboard.turnof", "Turn of:");


        
        this.fileConfig.set("match.winelo", "&2You have won ");
        this.fileConfig.set("match.winprize", "&2You won ");
        this.fileConfig.set("match.winmatch", "&2 won the game");
        this.fileConfig.set("match.lose", "&4You have lost ");
        this.fileConfig.set("match.deathmatch", "DEATHMATCH!");
        this.fileConfig.set("match.foundwait", "&2You are now in a match, wait your opponent to start");
        this.fileConfig.set("match.foundbegin", "&2You found an opponent, lets start!");


        
        this.fileConfig.set("player.turnof", "Turn of");
        this.fileConfig.set("player.handfull", "Your hand is full, you cant draw a card");
        this.fileConfig.set("player.deckfull", "You already have 20 cards.");
        this.fileConfig.set("player.nocards", "You dont have more cards to draw");
        this.fileConfig.set("player.enemynocards", "The enemy dont have more cards");


        
        this.fileConfig.set("matchRequest.nomatch", "&4All the matches are occupied, wait until one ends");
        this.fileConfig.set("matchRequest.nocards", "&4Both players need to have 20 cards");
        this.fileConfig.set("matchRequest.noclass", "&4Both players must have a class");
        this.fileConfig.set("matchRequest.playeronmatch", "&4The other player is in a match");
        this.fileConfig.set("matchRequest.received", "&2Challeng received from ");
        this.fileConfig.set("matchRequest.sent", "§2Challeng sent to ");

        
        this.fileConfig.set("card.hunter.skill.name", "&2&lHunter Skill");
        this.fileConfig.set("card.hunter.skill.explanation", "&7Win an arrow");
        this.fileConfig.set("card.hunter.poisongas.name", "&2&lPoison Gas");
        this.fileConfig.set("card.hunter.poisongas.explanation", "&7The enemy got poisoned for 4 seconds");
        this.fileConfig.set("card.hunter.getacopy.name", "&2&lGet a Copy");
        this.fileConfig.set("card.hunter.getacopy.explanation", "&7Get a copy of a random card of the enemy");
        this.fileConfig.set("card.hunter.tothehead.name", "&2&lTo the Head");
        this.fileConfig.set("card.hunter.tothehead.explanation", "&7Deals 1 damage to enemy");
        this.fileConfig.set("card.hunter.instantpoison.name", "&2&lInstant Poison");
        this.fileConfig.set("card.hunter.instantpoison.explanation", "&7Poison your enemy during next 2 seconds");
        this.fileConfig.set("card.hunter.divinebow.name", "&2&lDivine Bow");
        this.fileConfig.set("card.hunter.divinebow.explanation", "&7Get a bow with Power I");
        this.fileConfig.set("card.hunter.legendarybow.name", "&2&lLegendary Bow");
        this.fileConfig.set("card.hunter.legendarybow.explanation", "&7Get a bow with Power III");
        this.fileConfig.set("card.hunter.mortaltrap.name", "&2&lMortal Trap");
        this.fileConfig.set("card.hunter.mortaltrap.explanation", "&7Make a trap that deals 4 damage to the enemy");
        this.fileConfig.set("card.hunter.firstaid.name", "&2&lFirst Aid");
        this.fileConfig.set("card.hunter.firstaid.explanation", "&7Heal yourself 1");
        this.fileConfig.set("card.hunter.finishhim.name", "&2&lFinish Him");
        this.fileConfig.set("card.hunter.finishhim.explanation", "&7Deals the enemy 6 damage");
        this.fileConfig.set("card.hunter.lealcompanion.name", "&2&lLeal Companion");
        this.fileConfig.set("card.hunter.lealcompanion.explanation", "&7Invokes a companion with 4 health and 4 damage");


        
        this.fileConfig.set("card.mage.skill.name", "&1&lMage Skill");
        this.fileConfig.set("card.mage.skill.explanation", "&7Heal yourself 0.5");
        this.fileConfig.set("card.mage.burn.name", "&1&lBurn");
        this.fileConfig.set("card.mage.burn.explanation", "&7Burn your enemy for two seconds");
        this.fileConfig.set("card.mage.arcaneintellect.name", "&1&lArcane Intellect");
        this.fileConfig.set("card.mage.arcaneintellect.explanation", "&7Draw two cards");
        this.fileConfig.set("card.mage.fearofheights.name", "&1&lFear of Heights");
        this.fileConfig.set("card.mage.fearofheights.explanation1", "&7Your enemy will have a fall that");
        this.fileConfig.set("card.mage.fearofheights.explanation2", "&7deals 3 damage");
        this.fileConfig.set("card.mage.divinehealer.name", "&1&lDivine Healer");
        this.fileConfig.set("card.mage.divinehealer.explanation", "&7Heal yourself 3");
        this.fileConfig.set("card.mage.nuclearbomb.name", "&1&lNuclear Bomb");
        this.fileConfig.set("card.mage.nuclearbomb.explanation1", "&7Make a explosion that deals your");
        this.fileConfig.set("card.mage.nuclearbomb.explanation2", "&7enemy 2 damage and poison for two seconds");
        this.fileConfig.set("card.mage.turningthetables.name", "&1&lTurning the Tables");
        this.fileConfig.set("card.mage.turningthetables.explanation1", "&7Deals your enemy 1 damage and");
        this.fileConfig.set("card.mage.turningthetables.explanation2", "&7heal yourself 1");
        this.fileConfig.set("card.mage.newlife.name", "&1&lNew Life");
        this.fileConfig.set("card.mage.newlife.explanation", "&7Totally heals you and your enemy");
        this.fileConfig.set("card.mage.manasupply.name", "&1&lMana Supply");
        this.fileConfig.set("card.mage.manasupply.explanation", "&7You win 1 mana this turn");
        this.fileConfig.set("card.mage.lifechange.name", "&1&lLife Change");
        this.fileConfig.set("card.mage.lifechange.explanation", "&7Change your health with the enemy");
        this.fileConfig.set("card.mage.witchcat.name", "&1&lWitch Cat");
        this.fileConfig.set("card.mage.witchcat.explanation", "&7Invokes a cat with 2 damage and 3 health");


        
        this.fileConfig.set("card.warrior.skill.name", "&c&lWarrior Skill");
        this.fileConfig.set("card.warrior.skill.explanation", "&7Deals 0.5 damage to the enemy");
        this.fileConfig.set("card.warrior.brutalhit.name", "&c&lBrutal Hit");
        this.fileConfig.set("card.warrior.brutalhit.explanation", "&7Deals 3 damage to the enemy");
        this.fileConfig.set("card.warrior.berserker.name", "&c&lBerserker");
        this.fileConfig.set("card.warrior.berserker.explanation", "&7Deals randomly 2-4 damage to the enemy");
        this.fileConfig.set("card.warrior.berserker.effect.damage", "damage");
        this.fileConfig.set("card.warrior.insanity.name", "&c&lInsanity");
        this.fileConfig.set("card.warrior.insanity.explanation", "&7Deals 2 damage randomly to the enemy or yourself");
        this.fileConfig.set("card.warrior.insanity.effect.damageyourself", "You damaged yourself");
        this.fileConfig.set("card.warrior.insanity.effect.damagehisself", " damaged hisself");
        this.fileConfig.set("card.warrior.ironball.name", "&c&lIron Ball");
        this.fileConfig.set("card.warrior.ironball.explanation1", "&7Gives you a iron ball, if you hit");
        this.fileConfig.set("card.warrior.ironball.explanation2", "&7the enemy with it, it deals 3 damage");
        this.fileConfig.set("card.warrior.disarm.name", "&c&lDisarm");
        this.fileConfig.set("card.warrior.disarm.explanation", "&7Break the weapon of your enemy");
        this.fileConfig.set("card.warrior.snatchaway.name", "&c&lSnatch Away");
        this.fileConfig.set("card.warrior.snatchaway.explanation", "&7Steal the weapon of your enemy");
        this.fileConfig.set("card.warrior.lasttry.name", "&c&lLast Try");
        this.fileConfig.set("card.warrior.lasttry.explanation1", "&7If you are under 2 hearts you will deal");
        this.fileConfig.set("card.warrior.lasttry.explanation2", "&7your enemy 5 damage,if you are not you deal 2");
        this.fileConfig.set("card.warrior.beast.name", "&c&lBeast");
        this.fileConfig.set("card.warrior.beast.explanation", "&7Invokes a beast with 4 damage and 2 health");
        this.fileConfig.set("card.warrior.slime.name", "&c&lSlime");
        this.fileConfig.set("card.warrior.slime.explanation", "&7Invokes a little slime with 1 damage and 1 health");


        
        this.fileConfig.set("card.normal.flame.name", "&f&lFlame");
        this.fileConfig.set("card.normal.flame.explanation", "&7Burn your enemy for one second");
        this.fileConfig.set("card.normal.woodweapon.name", "&f&lWood Weapon");
        this.fileConfig.set("card.normal.woodweapon.explanation", "&7Win a wood sword");
        this.fileConfig.set("card.normal.greatweapon.name", "&f&lGreat Weapon");
        this.fileConfig.set("card.normal.greatweapon.explanation", "&7Win an iron sword");
        this.fileConfig.set("card.normal.insectbite.name", "&f&lInsect Bite");
        this.fileConfig.set("card.normal.insectbite.explanation", "&7Deals 0.5 damage to the enemy");
        this.fileConfig.set("card.normal.tigerbite.name", "&f&lTiger Bite");
        this.fileConfig.set("card.normal.tigerbite.explanation", "&7Deals 1 damage to the enemy");
        this.fileConfig.set("card.normal.bearscratch.name", "&f&lBear Scratch");
        this.fileConfig.set("card.normal.bearscratch.explanation", "&7Deals 1.5 damage to the enemy");
        this.fileConfig.set("card.normal.elephantstomp.name", "&f&lElephant Stomp");
        this.fileConfig.set("card.normal.elephantstomp.explanation", "&7Deals 3 damage to the enemy");
        this.fileConfig.set("card.normal.manaset.name", "&f&lMana Set");
        this.fileConfig.set("card.normal.manaset.explanation", "&7Set your mana to 5 for this turn");
        this.fileConfig.set("card.normal.minorhealing.name", "&f&lMinor Healing");
        this.fileConfig.set("card.normal.minorhealing.explanation", "&7Heals yourself 1");
        this.fileConfig.set("card.normal.majorhealing.name", "&f&lMajor Healing");
        this.fileConfig.set("card.normal.majorhealing.explanation", "&7Heals yourself 2");
        this.fileConfig.set("card.normal.equality.name", "&f&lEquality");
        this.fileConfig.set("card.normal.equality.explanation", "&7Get the same HP as your opponent");
        this.fileConfig.set("card.normal.shieldbearer.name", "&f&lShield Bearer");
        this.fileConfig.set("card.normal.shieldbearer.explanation1", "&7Gives you a leather chestplate");
        this.fileConfig.set("card.normal.shieldbearer.explanation2", "&7It can help you on physical attacks");
        this.fileConfig.set("card.normal.incantation.name", "&f&lIncantation");
        this.fileConfig.set("card.normal.incantation.explanation", "&7Deals your enemy 1 damage and heals you 0.5");
        this.fileConfig.set("card.normal.takearest.name", "&f&lTake a Rest");
        this.fileConfig.set("card.normal.takearest.explanation", "&7Heals yourself 3 and ends your turn");
        this.fileConfig.set("card.normal.presentforyou.name", "&f&lPresent for you");
        this.fileConfig.set("card.normal.presentforyou.explanation", "&7Throw a bomb that deals 3.5 damage to the enemy");
        this.fileConfig.set("card.normal.vampire.name", "&f&lVampire");
        this.fileConfig.set("card.normal.vampire.explanation", "&7Invokes a vampire with 3 damage and 3 health");
        this.fileConfig.set("card.normal.exiledzombie.name", "&f&lExiled Zombie");
        this.fileConfig.set("card.normal.exiledzombie.explanation", "&7Invokes a zombie with 1 damage and 2 health");


        
        this.fileConfig.set("monster.vampire", "&f&lVampire");
        this.fileConfig.set("monster.exiledZombie", "&f&lExiled Zombie");
        this.fileConfig.set("monster.beast", "&c&lBeast");
        this.fileConfig.set("monster.lealCompanion", "&2&lLeal Companion");
        this.fileConfig.set("monster.slime", "&c&lSlime");
        this.fileConfig.set("monster.witchCat", "&1&lWitch Cat");
        this.fileConfig.set("monster.alreadyfive", " you already have 5 monsters ");


        
        this.fileConfig.set("gui.class.warrior", "Warrior");
        this.fileConfig.set("gui.class.mage", "Mage");
        this.fileConfig.set("gui.class.hunter", "Hunter");
        this.fileConfig.set("gui.cardmenu.cardshop", "Card Shop");
        this.fileConfig.set("gui.cardmenu.cleardeck", "Clear the deck");
        this.fileConfig.set("gui.cardmenu.return", "Return to class selection");
        this.fileConfig.set("gui.cardmenu.classcards", "Choose class cards");
        this.fileConfig.set("gui.cardmenu.normalcards", "Choose normal cards");
        this.fileConfig.set("gui.cardmenu.deckdeleted", "Deck deleted");
        this.fileConfig.set("gui.cardmenu.returned", "Returned to normal class");
        this.fileConfig.set("gui.request.paidfee", "You have paid a fee of ");
        this.fileConfig.set("gui.request.nomoneyfee", "One of the players cant pay the fee");
        this.fileConfig.set("gui.request.bothplaying", "Both must be playing RolCards");
        this.fileConfig.set("gui.request.offlineplayer", "This player is not online");
        this.fileConfig.set("gui.card.alreadytwocopies", "You already have two copies of this card in your deck");
        this.fileConfig.set("gui.card.nocardindeck", "You dont have this card in your deck");
        this.fileConfig.set("gui.card.buy", "You have bought ");
        this.fileConfig.set("gui.card.nomoney", "You dont have enough money");
        this.fileConfig.set("gui.item.prepage", "&4Previous Page");
        this.fileConfig.set("gui.item.postpage", "&2Next Page");
        this.fileConfig.set("guiLottery", "&2Lottery");
      
      }
      catch (IOException e) {
        e.printStackTrace();
      } 
    }
    for (int i = 0; i < 20; i++) {
      this.fileConfig.options().copyDefaults(true);
      saveYamls();
    } 
    
    this.announcement = this.fileConfig.getString("announcement");


    
    this.challengItemName = this.fileConfig.getString("challengItem.name");
    
    this.challengItemLore = this.fileConfig.getStringList("challengItem.lore");
    this.requestItemName = this.fileConfig.getString("requestItem");
    this.cardMenuItemName = this.fileConfig.getString("cardMenuItem");
    this.infoItemName = this.fileConfig.getString("infoItem.name");
    this.infoItemLore = this.fileConfig.getStringList("infoItem.lore");
    
    this.enemyHealthInfo = this.fileConfig.getString("enemyHealthInfo");

    
    this.manaCostMsg = this.fileConfig.getString("manaCost");
    this.nodropMsg = this.fileConfig.getString("noDrop");
    this.alreadyExistChallenge = this.fileConfig.getString("alreadyExistChallenge");
    this.noEnoughMana = this.fileConfig.getString("noEnoughMana");
    this.noCommands = this.fileConfig.getString("noCommands");


    
    this.usingCardMsg = this.fileConfig.getString("usingCard.use");
    this.usingCardTargetMsg = this.fileConfig.getString("usingCard.target");
    this.alreadyUsedSkillMsg = this.fileConfig.getString("alreadyUsedSkill");


    
    this.entityDeathMsg = this.fileConfig.getString("entity.death");
    this.entityAttackMsg = this.fileConfig.getString("entity.attack");
    this.entityAlreadyAttackedMsg = this.fileConfig.getString("entity.alreadyattacked");
    this.entityWaitMsg = this.fileConfig.getString("entity.wait");


    
    this.scoreboardWinMsg = this.fileConfig.getString("scoreboard.wins");
    this.scoreboardLoseMsg = this.fileConfig.getString("scoreboard.loses");
    this.scoreboardMoneyMsg = this.fileConfig.getString("scoreboard.money");
    this.scoreboardTurnOfMsg = this.fileConfig.getString("scoreboard.turnof");


    
    this.matchEloMsg = this.fileConfig.getString("match.winelo");
    this.matchPrizeMsg = this.fileConfig.getString("match.winprize");
    this.matchMatchMsg = this.fileConfig.getString("match.winmatch");
    this.matchLoseMsg = this.fileConfig.getString("match.lose");
    this.matchDeathmatchMsg = this.fileConfig.getString("match.deathmatch");
    this.matchFoundWaitMsg = this.fileConfig.getString("match.foundwait");
    this.matchFoundBeginMsg = this.fileConfig.getString("match.foundbegin");

    
    this.playerTurnOfMsg = this.fileConfig.getString("player.turnof");
    this.playerHandFullMsg = this.fileConfig.getString("player.handfull");
    this.playerDeckFullMsg = this.fileConfig.getString("player.deckfull");
    this.playerNoCardsMsg = this.fileConfig.getString("player.nocards");
    this.playerEnemyNoCardsMsg = this.fileConfig.getString("player.enemynocards");


    
    this.matchRequestsNoMatchMsg = this.fileConfig.getString("matchRequest.nomatch");
    this.matchRequestsNoCardsMsg = this.fileConfig.getString("matchRequest.nocards");
    this.matchRequestsNoClassMsg = this.fileConfig.getString("matchRequest.noclass");
    this.matchRequestsPlayerOnMatchMsg = this.fileConfig.getString("matchRequest.playeronmatch");
    this.matchRequestsReceivedMsg = this.fileConfig.getString("matchRequest.received");
    this.matchRequestsSentMsg = this.fileConfig.getString("matchRequest.sent");


    
    this.hunterSkillMsg = this.fileConfig.getString("card.hunter.skill.name");
    this.hunterSkillMsg1 = this.fileConfig.getString("card.hunter.skill.explanation");
    this.hunterPoisonGasMsg = this.fileConfig.getString("card.hunter.poisongas.name");
    this.hunterPoisonGasMsg1 = this.fileConfig.getString("card.hunter.poisongas.explanation");
    this.hunterGetCopyMsg = this.fileConfig.getString("card.hunter.getacopy.name");
    this.hunterGetCopyMsg1 = this.fileConfig.getString("card.hunter.getacopy.explanation");
    this.hunterToTheHeadMsg = this.fileConfig.getString("card.hunter.tothehead.name");
    this.hunterToTheHeadMsg1 = this.fileConfig.getString("card.hunter.tothehead.explanation");
    this.hunterIPoisonMsg = this.fileConfig.getString("card.hunter.instantpoison.name");
    this.hunterIPoisonMsg1 = this.fileConfig.getString("card.hunter.instantpoison.explanation");
    this.hunterDivineBowMsg = this.fileConfig.getString("card.hunter.divinebow.name");
    this.hunterDivineBowMsg1 = this.fileConfig.getString("card.hunter.divinebow.explanation");
    this.hunterLBowMsg = this.fileConfig.getString("card.hunter.legendarybow.name");
    this.hunterLBowMsg1 = this.fileConfig.getString("card.hunter.legendarybow.explanation");
    this.hunterMortalTrapMsg = this.fileConfig.getString("card.hunter.mortaltrap.name");
    this.hunterMortalTrapMsg1 = this.fileConfig.getString("card.hunter.mortaltrap.explanation");
    this.hunterFirstAidMsg = this.fileConfig.getString("card.hunter.firstaid.name");
    this.hunterFirstAidMsg1 = this.fileConfig.getString("card.hunter.firstaid.explanation");
    this.hunterFinishHimMsg = this.fileConfig.getString("card.hunter.finishhim.name");
    this.hunterFinishHimMsg1 = this.fileConfig.getString("card.hunter.finishhim.explanation");
    this.hunterLealCompanionMsg = this.fileConfig.getString("card.hunter.lealcompanion.name");
    this.hunterLealCompanionMsg1 = this.fileConfig.getString("card.hunter.lealcompanion.explanation");


    
    this.mageSkillMsg = this.fileConfig.getString("card.mage.skill.name");
    this.mageSkillMsg1 = this.fileConfig.getString("card.mage.skill.explanation");
    this.mageBurnMsg = this.fileConfig.getString("card.mage.burn.name");
    this.mageBurnMsg1 = this.fileConfig.getString("card.mage.burn.explanation");
    this.mageArcaneIntellectMsg = this.fileConfig.getString("card.mage.arcaneintellect.name");
    this.mageArcaneIntellectMsg1 = this.fileConfig.getString("card.mage.arcaneintellect.explanation");
    this.mageFearOfHeightsMsg = this.fileConfig.getString("card.mage.fearofheights.name");
    this.mageFearOfHeightsMsg1 = this.fileConfig.getString("card.mage.fearofheights.explanation1");
    this.mageFearOfHeightsMsg2 = this.fileConfig.getString("card.mage.fearofheights.explanation2");
    this.mageDivineHealerMsg = this.fileConfig.getString("card.mage.divinehealer.name");
    this.mageDivineHealerMsg1 = this.fileConfig.getString("card.mage.divinehealer.explanation");
    this.mageNuclearBombMsg = this.fileConfig.getString("card.mage.nuclearbomb.name");
    this.mageNuclearBombMsg1 = this.fileConfig.getString("card.mage.nuclearbomb.explanation1");
    this.mageNuclearBombMsg2 = this.fileConfig.getString("card.mage.nuclearbomb.explanation2");
    this.mageTurningTheTablesMsg = this.fileConfig.getString("card.mage.turningthetables.name");
    this.mageTurningTheTablesMsg1 = this.fileConfig.getString("card.mage.turningthetables.explanation1");
    this.mageTurningTheTablesMsg2 = this.fileConfig.getString("card.mage.turningthetables.explanation2");
    this.mageNewLifeMsg = this.fileConfig.getString("card.mage.newlife.name");
    this.mageNewLifeMsg1 = this.fileConfig.getString("card.mage.newlife.explanation");
    this.mageManaSupplyMsg = this.fileConfig.getString("card.mage.manasupply.name");
    this.mageManaSupplyMsg1 = this.fileConfig.getString("card.mage.manasupply.explanation");
    this.mageLifeChangeMsg = this.fileConfig.getString("card.mage.lifechange.name");
    this.mageLifeChangeMsg1 = this.fileConfig.getString("card.mage.lifechange.explanation");
    this.mageWitchCatMsg = this.fileConfig.getString("card.mage.witchcat.name");
    this.mageWitchCatMsg1 = this.fileConfig.getString("card.mage.witchcat.explanation");


    
    this.warriorSkillMsg = this.fileConfig.getString("card.warrior.skill.name");
    this.warriorSkillMsg1 = this.fileConfig.getString("card.warrior.skill.explanation");
    this.warriorBrutalHitMsg = this.fileConfig.getString("card.warrior.brutalhit.name");
    this.warriorBrutalHitMsg1 = this.fileConfig.getString("card.warrior.brutalhit.explanation");
    this.warriorBerserkerMsg = this.fileConfig.getString("card.warrior.berserker.name");
    this.warriorBerserkerMsg1 = this.fileConfig.getString("card.warrior.berserker.explanation");
    this.warriorBerserkerEffectMsg = this.fileConfig.getString("card.warrior.berserker.effect.damage");
    this.warriorInsanityMsg = this.fileConfig.getString("card.warrior.insanity.name");
    this.warriorInsanityMsg1 = this.fileConfig.getString("card.warrior.insanity.explanation");
    this.warriorInsanityEffectYourselfMsg = this.fileConfig.getString("card.warrior.insanity.effect.damageyourself");
    this.warriorInsanityEffectHisselfMsg = this.fileConfig.getString("card.warrior.insanity.effect.damagehisself");
    this.warriorIronBallMsg = this.fileConfig.getString("card.warrior.ironball.name");
    this.warriorIronBallMsg1 = this.fileConfig.getString("card.warrior.ironball.explanation1");
    this.warriorIronBallMsg2 = this.fileConfig.getString("card.warrior.ironball.explanation2");
    this.warriorDisarmMsg = this.fileConfig.getString("card.warrior.disarm.name", "&c&lDisarm");
    this.warriorDisarmMsg1 = this.fileConfig.getString("card.warrior.disarm.explanation");
    this.warriorSnatchAwayMsg = this.fileConfig.getString("card.warrior.snatchaway.name");
    this.warriorSnatchAwayMsg1 = this.fileConfig.getString("card.warrior.snatchaway.explanation");
    this.warriorLastTryMsg = this.fileConfig.getString("card.warrior.lasttry.name");
    this.warriorLastTryMsg1 = this.fileConfig.getString("card.warrior.lasttry.explanation1");
    this.warriorLastTryMsg2 = this.fileConfig.getString("card.warrior.lasttry.explanation2");
    this.warriorBeastMsg = this.fileConfig.getString("card.warrior.beast.name");
    this.warriorBeastMsg1 = this.fileConfig.getString("card.warrior.beast.explanation");
    this.warriorSlimeMsg = this.fileConfig.getString("card.warrior.slime.name");
    this.warriorSlimeMsg1 = this.fileConfig.getString("card.warrior.slime.explanation");


    
    this.normalFlameMsg = this.fileConfig.getString("card.normal.flame.name");
    this.normalFlameMsg1 = this.fileConfig.getString("card.normal.flame.explanation");
    this.normalWoodWeaponMsg = this.fileConfig.getString("card.normal.woodweapon.name");
    this.normalWoodWeaponMsg1 = this.fileConfig.getString("card.normal.woodweapon.explanation");
    this.normalGreatWeaponMsg = this.fileConfig.getString("card.normal.greatweapon.name");
    this.normalGreatWeaponMsg1 = this.fileConfig.getString("card.normal.greatweapon.explanation");
    this.normalInsectBiteMsg = this.fileConfig.getString("card.normal.insectbite.name");
    this.normalInsectBiteMsg1 = this.fileConfig.getString("card.normal.insectbite.explanation");
    this.normalTigerBiteMsg = this.fileConfig.getString("card.normal.tigerbite.name");
    this.normalTigerBiteMsg1 = this.fileConfig.getString("card.normal.tigerbite.explanation");
    this.normalBearScratchMsg = this.fileConfig.getString("card.normal.bearscratch.name");
    this.normalBearScratchMsg1 = this.fileConfig.getString("card.normal.bearscratch.explanation");
    this.normalElephantStompMsg = this.fileConfig.getString("card.normal.elephantstomp.name");
    this.normalElephantStompMsg1 = this.fileConfig.getString("card.normal.elephantstomp.explanation");
    this.normalManaSetMsg = this.fileConfig.getString("card.normal.manaset.name");
    this.normalManaSetMsg1 = this.fileConfig.getString("card.normal.manaset.explanation");
    this.normalMinorHealingMsg = this.fileConfig.getString("card.normal.minorhealing.name");
    this.normalMinorHealingMsg1 = this.fileConfig.getString("card.normal.minorhealing.explanation");
    this.normalMajorHealingMsg = this.fileConfig.getString("card.normal.majorhealing.name");
    this.normalMajorHealingMsg1 = this.fileConfig.getString("card.normal.majorhealing.explanation");
    this.normalEqualityMsg = this.fileConfig.getString("card.normal.equality.name");
    this.normalEqualityMsg1 = this.fileConfig.getString("card.normal.equality.explanation");
    this.normalShieldBearerMsg = this.fileConfig.getString("card.normal.shieldbearer.name");
    this.normalShieldBearerMsg1 = this.fileConfig.getString("card.normal.shieldbearer.explanation1");
    this.normalShieldBearerMsg2 = this.fileConfig.getString("card.normal.shieldbearer.explanation2");
    this.normalIncantationMsg = this.fileConfig.getString("card.normal.incantation.name");
    this.normalIncantationMsg1 = this.fileConfig.getString("card.normal.incantation.explanation");
    this.normalTakeARestMsg = this.fileConfig.getString("card.normal.takearest.name");
    this.normalTakeARestMsg1 = this.fileConfig.getString("card.normal.takearest.explanation");
    this.normalPresentForYouMsg = this.fileConfig.getString("card.normal.presentforyou.name");
    this.normalPresentForYouMsg1 = this.fileConfig.getString("card.normal.presentforyou.explanation");
    this.normalVampireMsg = this.fileConfig.getString("card.normal.vampire.name");
    this.normalVampireMsg1 = this.fileConfig.getString("card.normal.vampire.explanation");
    this.normalExiledZombieMsg = this.fileConfig.getString("card.normal.exiledzombie.name");
    this.normalExiledZombieMsg1 = this.fileConfig.getString("card.normal.exiledzombie.explanation");


    
    this.monsterVampireMsg = this.fileConfig.getString("monster.vampire");
    this.monsterExiledZombieMsg = this.fileConfig.getString("monster.exiledZombie");
    this.monsterBeastMsg = this.fileConfig.getString("monster.beast");
    this.monsterLealCompanionMsg = this.fileConfig.getString("monster.lealCompanion");
    this.monsterSlimeMsg = this.fileConfig.getString("monster.slime");
    this.monsterWitchCatMsg = this.fileConfig.getString("monster.witchCat");
    this.monsterFiveMsg = this.fileConfig.getString("monster.alreadyfive");


    
    this.guiClassWarriorMsg = this.fileConfig.getString("gui.class.warrior");
    this.guiClassMageMsg = this.fileConfig.getString("gui.class.mage");
    this.guiClassHunterMsg = this.fileConfig.getString("gui.class.hunter");
    this.guiCardShopMsg = this.fileConfig.getString("gui.cardmenu.cardshop");
    this.guiClearDeckMsg = this.fileConfig.getString("gui.cardmenu.cleardeck");
    this.guiReturnMsg = this.fileConfig.getString("gui.cardmenu.return");
    this.guiClassCardsMsg = this.fileConfig.getString("gui.cardmenu.classcards");
    this.guiNormalCardsMsg = this.fileConfig.getString("gui.cardmenu.normalcards");
    this.guiDeckDeletedMsg = this.fileConfig.getString("gui.cardmenu.deckdeleted");
    this.guiReturnedMsg = this.fileConfig.getString("gui.cardmenu.returned");
    this.guiPaidFeeMsg = this.fileConfig.getString("gui.request.paidfee");
    this.guiNoMoneyFeeMsg = this.fileConfig.getString("gui.request.nomoneyfee");
    this.guiBothPlayingMsg = this.fileConfig.getString("gui.request.bothplaying");
    this.guiOfflinePlayerMsg = this.fileConfig.getString("gui.request.offlineplayer");
    this.guiTwoCopiesMsg = this.fileConfig.getString("gui.card.alreadytwocopies");
    this.guiNoCardInDeckMsg = this.fileConfig.getString("gui.card.nocardindeck");
    this.guiCardBuyMsg = this.fileConfig.getString("gui.card.buy");
    this.guiCardNoMoneyMsg = this.fileConfig.getString("gui.card.nomoney");
    this.guiItemPrepageMsg = this.fileConfig.getString("gui.item.prepage");
    this.guiItemPostpageMsg = this.fileConfig.getString("gui.item.postpage");
    this.guiLotteryMsg = this.fileConfig.getString("guiLottery");
  }





  
  public File getFile() { return this.file; }

  
  public void setFile(File file) { this.file = file; }

  
  public RolCards getPlugin() { return this.plugin; }

  
  public void setPlugin(RolCards plugin) { this.plugin = plugin; }



  
  public FileConfiguration getFileConfig() { return this.fileConfig; }



  
  public String getChallengItemName() { return this.challengItemName.replaceAll("&", "§"); }


  
  public List<String> getChallengItemLore() {
    List<String> lista = this.challengItemLore;
    if (lista.size() > 0) {
      lista.set(0, ((String)lista.get(0)).replaceAll("&", "§"));
    }
    if (lista.size() > 1) {
      lista.set(1, ((String)lista.get(1)).replaceAll("&", "§"));
    }
    if (lista.size() > 2) {
      lista.set(2, ((String)lista.get(2)).replaceAll("&", "§"));
    }
    return lista;
  }


  
  public String getAnnouncement() { return this.announcement.replaceAll("&", "§"); }



  
  public String getRequestItemName() { return this.requestItemName.replaceAll("&", "§"); }



  
  public String getCardMenuItemName() { return this.cardMenuItemName.replaceAll("&", "§"); }



  
  public String getInfoItemName() { return this.infoItemName.replaceAll("&", "§"); }


  
  public List<String> getInfoItemLore() {
    List<String> lista = this.infoItemLore;
    if (lista.size() > 0) {
      lista.set(0, ((String)lista.get(0)).replaceAll("&", "§"));
    }
    if (lista.size() > 1) {
      lista.set(1, ((String)lista.get(1)).replaceAll("&", "§"));
    }
    if (lista.size() > 2) {
      lista.set(2, ((String)lista.get(2)).replaceAll("&", "§"));
    }
    return lista;
  }


  
  public String getEnemyHealthInfo() { return this.enemyHealthInfo.replaceAll("&", "§"); }



  
  public String getManaCostMsg() { return this.manaCostMsg.replaceAll("&", "§"); }



  
  public String getNodropMsg() { return this.nodropMsg.replaceAll("&", "§"); }



  
  public String getUsingCardMsg() { return this.usingCardMsg.replaceAll("&", "§"); }



  
  public String getUsingCardTargetMsg() { return this.usingCardTargetMsg.replaceAll("&", "§"); }



  
  public String getAlreadyUsedSkillMsg() { return this.alreadyUsedSkillMsg.replaceAll("&", "§"); }



  
  public String getEntityDeathMsg() { return this.entityDeathMsg.replaceAll("&", "§"); }



  
  public String getEntityAttackMsg() { return this.entityAttackMsg.replaceAll("&", "§"); }



  
  public String getEntityAlreadyAttackedMsg() { return this.entityAlreadyAttackedMsg.replaceAll("&", "§"); }



  
  public String getEntityWaitMsg() { return this.entityWaitMsg.replaceAll("&", "§"); }



  
  public String getScoreboardWinMsg() { return this.scoreboardWinMsg.replaceAll("&", "§"); }



  
  public String getScoreboardLoseMsg() { return this.scoreboardLoseMsg.replaceAll("&", "§"); }



  
  public String getScoreboardMoneyMsg() { return this.scoreboardMoneyMsg.replaceAll("&", "§"); }



  
  public String getScoreboardTurnOfMsg() { return this.scoreboardTurnOfMsg.replaceAll("&", "§"); }



  
  public String getMatchEloMsg() { return this.matchEloMsg.replaceAll("&", "§"); }



  
  public String getMatchPrizeMsg() { return this.matchPrizeMsg.replaceAll("&", "§"); }



  
  public String getMatchMatchMsg() { return this.matchMatchMsg.replaceAll("&", "§"); }



  
  public String getMatchLoseMsg() { return this.matchLoseMsg.replaceAll("&", "§"); }



  
  public String getMatchDeathmatchMsg() { return this.matchDeathmatchMsg.replaceAll("&", "§"); }



  
  public String getPlayerTurnOfMsg() { return this.playerTurnOfMsg.replaceAll("&", "§"); }



  
  public String getPlayerHandFullMsg() { return this.playerHandFullMsg.replaceAll("&", "§"); }



  
  public String getPlayerDeckFullMsg() { return this.playerDeckFullMsg.replaceAll("&", "§"); }



  
  public String getPlayerNoCardsMsg() { return this.playerNoCardsMsg.replaceAll("&", "§"); }



  
  public String getPlayerEnemyNoCardsMsg() { return this.playerEnemyNoCardsMsg.replaceAll("&", "§"); }



  
  public String getMatchRequestsNoMatchMsg() { return this.matchRequestsNoMatchMsg.replaceAll("&", "§"); }



  
  public String getMatchRequestsNoCardsMsg() { return this.matchRequestsNoCardsMsg.replaceAll("&", "§"); }



  
  public String getMatchRequestsNoClassMsg() { return this.matchRequestsNoClassMsg.replaceAll("&", "§"); }



  
  public String getMatchRequestsPlayerOnMatchMsg() { return this.matchRequestsPlayerOnMatchMsg.replaceAll("&", "§"); }



  
  public String getMatchRequestsReceivedMsg() { return this.matchRequestsReceivedMsg.replaceAll("&", "§"); }



  
  public String getHunterSkillMsg() { return this.hunterSkillMsg.replaceAll("&", "§"); }



  
  public String getHunterSkillMsg1() { return this.hunterSkillMsg1.replaceAll("&", "§"); }



  
  public String getHunterPoisonGasMsg() { return this.hunterPoisonGasMsg.replaceAll("&", "§"); }



  
  public String getHunterPoisonGasMsg1() { return this.hunterPoisonGasMsg1.replaceAll("&", "§"); }



  
  public String getHunterGetCopyMsg() { return this.hunterGetCopyMsg.replaceAll("&", "§"); }



  
  public String getHunterGetCopyMsg1() { return this.hunterGetCopyMsg1.replaceAll("&", "§"); }



  
  public String getHunterToTheHeadMsg() { return this.hunterToTheHeadMsg.replaceAll("&", "§"); }



  
  public String getHunterToTheHeadMsg1() { return this.hunterToTheHeadMsg1.replaceAll("&", "§"); }



  
  public String getHunterIPoisonMsg() { return this.hunterIPoisonMsg.replaceAll("&", "§"); }



  
  public String getHunterIPoisonMsg1() { return this.hunterIPoisonMsg1.replaceAll("&", "§"); }



  
  public String getHunterDivineBowMsg() { return this.hunterDivineBowMsg.replaceAll("&", "§"); }



  
  public String getHunterDivineBowMsg1() { return this.hunterDivineBowMsg1.replaceAll("&", "§"); }



  
  public String getHunterLBowMsg() { return this.hunterLBowMsg.replaceAll("&", "§"); }



  
  public String getHunterLBowMsg1() { return this.hunterLBowMsg1.replaceAll("&", "§"); }



  
  public String getHunterMortalTrapMsg() { return this.hunterMortalTrapMsg.replaceAll("&", "§"); }



  
  public String getHunterMortalTrapMsg1() { return this.hunterMortalTrapMsg1.replaceAll("&", "§"); }



  
  public String getHunterFirstAidMsg() { return this.hunterFirstAidMsg.replaceAll("&", "§"); }



  
  public String getHunterFirstAidMsg1() { return this.hunterFirstAidMsg1.replaceAll("&", "§"); }



  
  public String getHunterFinishHimMsg() { return this.hunterFinishHimMsg.replaceAll("&", "§"); }



  
  public String getHunterFinishHimMsg1() { return this.hunterFinishHimMsg1.replaceAll("&", "§"); }



  
  public String getHunterLealCompanionMsg() { return this.hunterLealCompanionMsg.replaceAll("&", "§"); }



  
  public String getHunterLealCompanionMsg1() { return this.hunterLealCompanionMsg1.replaceAll("&", "§"); }



  
  public String getMageSkillMsg() { return this.mageSkillMsg.replaceAll("&", "§"); }



  
  public String getMageSkillMsg1() { return this.mageSkillMsg1.replaceAll("&", "§"); }



  
  public String getMageBurnMsg() { return this.mageBurnMsg.replaceAll("&", "§"); }



  
  public String getMageBurnMsg1() { return this.mageBurnMsg1.replaceAll("&", "§"); }



  
  public String getMageArcaneIntellectMsg() { return this.mageArcaneIntellectMsg.replaceAll("&", "§"); }



  
  public String getMageArcaneIntellectMsg1() { return this.mageArcaneIntellectMsg1.replaceAll("&", "§"); }



  
  public String getMageFearOfHeightsMsg() { return this.mageFearOfHeightsMsg.replaceAll("&", "§"); }



  
  public String getMageFearOfHeightsMsg1() { return this.mageFearOfHeightsMsg1.replaceAll("&", "§"); }



  
  public String getMageFearOfHeightsMsg2() { return this.mageFearOfHeightsMsg2.replaceAll("&", "§"); }



  
  public String getMageDivineHealerMsg() { return this.mageDivineHealerMsg.replaceAll("&", "§"); }



  
  public String getMageDivineHealerMsg1() { return this.mageDivineHealerMsg1.replaceAll("&", "§"); }



  
  public String getMageNuclearBombMsg() { return this.mageNuclearBombMsg.replaceAll("&", "§"); }



  
  public String getMageNuclearBombMsg1() { return this.mageNuclearBombMsg1.replaceAll("&", "§"); }



  
  public String getMageNuclearBombMsg2() { return this.mageNuclearBombMsg2.replaceAll("&", "§"); }



  
  public String getMageTurningTheTablesMsg() { return this.mageTurningTheTablesMsg.replaceAll("&", "§"); }



  
  public String getMageTurningTheTablesMsg1() { return this.mageTurningTheTablesMsg1.replaceAll("&", "§"); }



  
  public String getMageTurningTheTablesMsg2() { return this.mageTurningTheTablesMsg2.replaceAll("&", "§"); }



  
  public String getMageNewLifeMsg() { return this.mageNewLifeMsg.replaceAll("&", "§"); }



  
  public String getMageNewLifeMsg1() { return this.mageNewLifeMsg1.replaceAll("&", "§"); }



  
  public String getMageManaSupplyMsg() { return this.mageManaSupplyMsg.replaceAll("&", "§"); }



  
  public String getMageManaSupplyMsg1() { return this.mageManaSupplyMsg1.replaceAll("&", "§"); }



  
  public String getMageLifeChangeMsg() { return this.mageLifeChangeMsg.replaceAll("&", "§"); }



  
  public String getMageLifeChangeMsg1() { return this.mageLifeChangeMsg1.replaceAll("&", "§"); }



  
  public String getMageWitchCatMsg() { return this.mageWitchCatMsg.replaceAll("&", "§"); }



  
  public String getMageWitchCatMsg1() { return this.mageWitchCatMsg1.replaceAll("&", "§"); }



  
  public String getWarriorSkillMsg() { return this.warriorSkillMsg.replaceAll("&", "§"); }



  
  public String getWarriorSkillMsg1() { return this.warriorSkillMsg1.replaceAll("&", "§"); }



  
  public String getWarriorBrutalHitMsg() { return this.warriorBrutalHitMsg.replaceAll("&", "§"); }



  
  public String getWarriorBrutalHitMsg1() { return this.warriorBrutalHitMsg1.replaceAll("&", "§"); }



  
  public String getWarriorBerserkerMsg() { return this.warriorBerserkerMsg.replaceAll("&", "§"); }



  
  public String getWarriorBerserkerMsg1() { return this.warriorBerserkerMsg1.replaceAll("&", "§"); }



  
  public String getWarriorBerserkerEffectMsg() { return this.warriorBerserkerEffectMsg.replaceAll("&", "§"); }



  
  public String getWarriorInsanityMsg() { return this.warriorInsanityMsg.replaceAll("&", "§"); }



  
  public String getWarriorInsanityMsg1() { return this.warriorInsanityMsg1.replaceAll("&", "§"); }



  
  public String getWarriorInsanityEffectYourselfMsg() { return this.warriorInsanityEffectYourselfMsg.replaceAll("&", "§"); }



  
  public String getWarriorInsanityEffectHisselfMsg() { return this.warriorInsanityEffectHisselfMsg.replaceAll("&", "§"); }



  
  public String getWarriorIronBallMsg() { return this.warriorIronBallMsg.replaceAll("&", "§"); }



  
  public String getWarriorIronBallMsg1() { return this.warriorIronBallMsg1.replaceAll("&", "§"); }



  
  public String getWarriorIronBallMsg2() { return this.warriorIronBallMsg2.replaceAll("&", "§"); }



  
  public String getWarriorDisarmMsg() { return this.warriorDisarmMsg.replaceAll("&", "§"); }



  
  public String getWarriorDisarmMsg1() { return this.warriorDisarmMsg1.replaceAll("&", "§"); }



  
  public String getWarriorSnatchAwayMsg() { return this.warriorSnatchAwayMsg.replaceAll("&", "§"); }



  
  public String getWarriorSnatchAwayMsg1() { return this.warriorSnatchAwayMsg1.replaceAll("&", "§"); }



  
  public String getWarriorLastTryMsg() { return this.warriorLastTryMsg.replaceAll("&", "§"); }



  
  public String getWarriorLastTryMsg1() { return this.warriorLastTryMsg1.replaceAll("&", "§"); }



  
  public String getWarriorLastTryMsg2() { return this.warriorLastTryMsg2.replaceAll("&", "§"); }



  
  public String getWarriorBeastMsg() { return this.warriorBeastMsg.replaceAll("&", "§"); }



  
  public String getWarriorBeastMsg1() { return this.warriorBeastMsg1.replaceAll("&", "§"); }



  
  public String getWarriorSlimeMsg() { return this.warriorSlimeMsg.replaceAll("&", "§"); }



  
  public String getWarriorSlimeMsg1() { return this.warriorSlimeMsg1.replaceAll("&", "§"); }



  
  public String getNormalFlameMsg() { return this.normalFlameMsg.replaceAll("&", "§"); }



  
  public String getNormalFlameMsg1() { return this.normalFlameMsg1.replaceAll("&", "§"); }



  
  public String getNormalWoodWeaponMsg() { return this.normalWoodWeaponMsg.replaceAll("&", "§"); }



  
  public String getNormalWoodWeaponMsg1() { return this.normalWoodWeaponMsg1.replaceAll("&", "§"); }



  
  public String getNormalGreatWeaponMsg() { return this.normalGreatWeaponMsg.replaceAll("&", "§"); }



  
  public String getNormalGreatWeaponMsg1() { return this.normalGreatWeaponMsg1.replaceAll("&", "§"); }



  
  public String getNormalInsectBiteMsg() { return this.normalInsectBiteMsg.replaceAll("&", "§"); }



  
  public String getNormalInsectBiteMsg1() { return this.normalInsectBiteMsg1.replaceAll("&", "§"); }



  
  public String getNormalTigerBiteMsg() { return this.normalTigerBiteMsg.replaceAll("&", "§"); }



  
  public String getNormalTigerBiteMsg1() { return this.normalTigerBiteMsg1.replaceAll("&", "§"); }



  
  public String getNormalBearScratchMsg() { return this.normalBearScratchMsg.replaceAll("&", "§"); }



  
  public String getNormalBearScratchMsg1() { return this.normalBearScratchMsg1.replaceAll("&", "§"); }



  
  public String getNormalElephantStompMsg() { return this.normalElephantStompMsg.replaceAll("&", "§"); }



  
  public String getNormalElephantStompMsg1() { return this.normalElephantStompMsg1.replaceAll("&", "§"); }



  
  public String getNormalManaSetMsg() { return this.normalManaSetMsg.replaceAll("&", "§"); }



  
  public String getNormalManaSetMsg1() { return this.normalManaSetMsg1.replaceAll("&", "§"); }



  
  public String getNormalMinorHealingMsg() { return this.normalMinorHealingMsg.replaceAll("&", "§"); }



  
  public String getNormalMinorHealingMsg1() { return this.normalMinorHealingMsg1.replaceAll("&", "§"); }



  
  public String getNormalMajorHealingMsg() { return this.normalMajorHealingMsg.replaceAll("&", "§"); }



  
  public String getNormalMajorHealingMsg1() { return this.normalMajorHealingMsg1.replaceAll("&", "§"); }



  
  public String getNormalEqualityMsg() { return this.normalEqualityMsg.replaceAll("&", "§"); }



  
  public String getNormalEqualityMsg1() { return this.normalEqualityMsg1.replaceAll("&", "§"); }



  
  public String getNormalShieldBearerMsg() { return this.normalShieldBearerMsg.replaceAll("&", "§"); }



  
  public String getNormalShieldBearerMsg1() { return this.normalShieldBearerMsg1.replaceAll("&", "§"); }



  
  public String getNormalShieldBearerMsg2() { return this.normalShieldBearerMsg2.replaceAll("&", "§"); }



  
  public String getNormalIncantationMsg() { return this.normalIncantationMsg.replaceAll("&", "§"); }



  
  public String getNormalIncantationMsg1() { return this.normalIncantationMsg1.replaceAll("&", "§"); }



  
  public String getNormalTakeARestMsg() { return this.normalTakeARestMsg.replaceAll("&", "§"); }



  
  public String getNormalTakeARestMsg1() { return this.normalTakeARestMsg1.replaceAll("&", "§"); }



  
  public String getNormalPresentForYouMsg() { return this.normalPresentForYouMsg.replaceAll("&", "§"); }



  
  public String getNormalPresentForYouMsg1() { return this.normalPresentForYouMsg1.replaceAll("&", "§"); }



  
  public String getNormalVampireMsg() { return this.normalVampireMsg.replaceAll("&", "§"); }



  
  public String getNormalVampireMsg1() { return this.normalVampireMsg1.replaceAll("&", "§"); }



  
  public String getNormalExiledZombieMsg() { return this.normalExiledZombieMsg.replaceAll("&", "§"); }



  
  public String getNormalExiledZombieMsg1() { return this.normalExiledZombieMsg1.replaceAll("&", "§"); }



  
  public String getMonsterVampireMsg() { return this.monsterVampireMsg.replaceAll("&", "§"); }



  
  public String getMonsterExiledZombieMsg() { return this.monsterExiledZombieMsg.replaceAll("&", "§"); }



  
  public String getMonsterBeastMsg() { return this.monsterBeastMsg.replaceAll("&", "§"); }



  
  public String getMonsterLealCompanionMsg() { return this.monsterLealCompanionMsg.replaceAll("&", "§"); }



  
  public String getMonsterSlimeMsg() { return this.monsterSlimeMsg.replaceAll("&", "§"); }



  
  public String getMonsterWitchCatMsg() { return this.monsterWitchCatMsg.replaceAll("&", "§"); }



  
  public String getGuiClassWarriorMsg() { return this.guiClassWarriorMsg.replaceAll("&", "§"); }



  
  public String getGuiClassMageMsg() { return this.guiClassMageMsg.replaceAll("&", "§"); }



  
  public String getGuiClassHunterMsg() { return this.guiClassHunterMsg.replaceAll("&", "§"); }



  
  public String getGuiCardShopMsg() { return this.guiCardShopMsg.replaceAll("&", "§"); }



  
  public String getGuiClearDeckMsg() { return this.guiClearDeckMsg.replaceAll("&", "§"); }



  
  public String getGuiReturnMsg() { return this.guiReturnMsg.replaceAll("&", "§"); }



  
  public String getGuiClassCardsMsg() { return this.guiClassCardsMsg.replaceAll("&", "§"); }



  
  public String getGuiNormalCardsMsg() { return this.guiNormalCardsMsg.replaceAll("&", "§"); }



  
  public String getGuiDeckDeletedMsg() { return this.guiDeckDeletedMsg.replaceAll("&", "§"); }



  
  public String getGuiReturnedMsg() { return this.guiReturnedMsg.replaceAll("&", "§"); }



  
  public String getGuiPaidFeeMsg() { return this.guiPaidFeeMsg.replaceAll("&", "§"); }



  
  public String getGuiNoMoneyFeeMsg() { return this.guiNoMoneyFeeMsg.replaceAll("&", "§"); }



  
  public String getGuiBothPlayingMsg() { return this.guiBothPlayingMsg.replaceAll("&", "§"); }



  
  public String getGuiOfflinePlayerMsg() { return this.guiOfflinePlayerMsg.replaceAll("&", "§"); }



  
  public String getGuiTwoCopiesMsg() { return this.guiTwoCopiesMsg.replaceAll("&", "§"); }



  
  public String getGuiNoCardInDeckMsg() { return this.guiNoCardInDeckMsg.replaceAll("&", "§"); }



  
  public String getGuiCardBuyMsg() { return this.guiCardBuyMsg.replaceAll("&", "§"); }



  
  public String getGuiCardNoMoneyMsg() { return this.guiCardNoMoneyMsg.replaceAll("&", "§"); }



  
  public String getAlreadyExistChallenge() { return this.alreadyExistChallenge.replaceAll("&", "§"); }



  
  public String getMonsterFiveMsg() { return this.monsterFiveMsg.replaceAll("&", "§"); }



  
  public String getNoEnoughMana() { return this.noEnoughMana.replaceAll("&", "§"); }



  
  public String getNoCommands() { return this.noCommands.replaceAll("&", "§"); }



  
  public String getMatchRequestsSentMsg() { return this.matchRequestsSentMsg.replaceAll("&", "§"); }




  
  public String getGuiLotteryMsg() { return this.guiLotteryMsg.replaceAll("&", "§"); }



  
  public String getMatchFoundWaitMsg() { return this.matchFoundWaitMsg.replaceAll("&", "§"); }



  
  public String getMatchFoundBeginMsg() { return this.matchFoundBeginMsg.replaceAll("&", "§"); }


  
  public String getGuiItemPostpageMsg() { return this.guiItemPostpageMsg.replaceAll("&", "§"); }


  
  public String getGuiItemPrepageMsg() { return this.guiItemPrepageMsg.replaceAll("&", "§"); }

  
  public void saveYamls() {
    try {
      this.fileConfig.save(this.file);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  public void loadYamls() {
    try {
      this.fileConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.file);
    }
    catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
