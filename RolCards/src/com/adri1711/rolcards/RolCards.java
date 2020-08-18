package com.adri1711.rolcards;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.adri1711.api.API1711;
import com.adri1711.rolcards.arenas.ArenaRC;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.cards.CardEffect;
import com.adri1711.rolcards.cards.CreatedCard;
import com.adri1711.rolcards.cards.hunter.HunterCards;
import com.adri1711.rolcards.cards.mage.MageCards;
import com.adri1711.rolcards.cards.normal.NormalCards;
import com.adri1711.rolcards.cards.warrior.WarriorCards;
import com.adri1711.rolcards.jugador.Fase;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.listeners.Ataque;
import com.adri1711.rolcards.listeners.CardGUI;
import com.adri1711.rolcards.listeners.Death;
import com.adri1711.rolcards.listeners.Interact;
import com.adri1711.rolcards.listeners.Join;
import com.adri1711.rolcards.listeners.MatchChat;
import com.adri1711.rolcards.listeners.Projectile;
import com.adri1711.rolcards.listeners.Quit;
import com.adri1711.rolcards.listeners.Restricciones;
import com.adri1711.rolcards.listeners.Teleport;
import com.adri1711.rolcards.match.Partida;
import com.adri1711.rolcards.peticiones.Peticion;
import com.adri1711.rolcards.utils.UtilPlaceholder;
import com.adri1711.rolcards.utils.Utils;
import com.adri1711.util.enums.AMaterials;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class RolCards extends JavaPlugin {
	private Connection con;
	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	private String sVersion;
	private boolean featherBoardEnabled;
	private boolean resourcePackEnabled;
	private boolean scoreboardEnabled;
	private PreparedStatement addElo;
	private PreparedStatement updateElo;
	private PreparedStatement getElo;
	private PreparedStatement addStats;
	private PreparedStatement updateKills;
	private PreparedStatement getKillsPlayer;
	private PreparedStatement updateDeaths;
	private PreparedStatement getDeathsPlayer;
	private PreparedStatement getTop10;
	private String resourceNormal;
	private Location spawnLocation;
	private List<ArenaRC> arenas;
	private List<Partida> partidas;
	private List<Jugador> jugadores;
	private static List<Peticion> peticiones;
	private ItemStack menuCartas;
	private ItemStack desafiador;
	private ItemStack requests;
	private ItemStack itemTop;
	private String scoreboardName;
	private List<Card> cartas;
	private MageCards mageCards;
	private HunterCards hunterCards;
	private WarriorCards warriorCards;
	private NormalCards normalCards;
	private static Economy economy = null;
	private static Permission permission = null;
	private Integer turnTime;
	private List<Object> configArenas;
	private int prizeAmount;
	private String language;
	private File cardsFile;
	private FileConfiguration cards;
	private File eloFile;
	private FileConfiguration eloConfig;
	private LanguageMessages messages;
	private boolean mySQL;
	private boolean directJoin;
	private Integer joinMatchFee;
	private Double lotMoney;
	private List<CreatedCard> listaCreatedCard;
	private Integer deckSize;
	private String rewardCommand;

	private API1711 api;

	public void onEnable() {
		this.cardsFile = new File(getDataFolder(), "cards.yml");
		this.eloFile = new File(getDataFolder(), "elo.yml");
		loadYamls();
		saveYamls();
		loadConfig();
		getServer().getPluginManager().registerEvents((Listener) new MatchChat(this), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new Death(this), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new Ataque(this), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new Teleport(this), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new Join(this), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new Quit(this), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new Interact(this), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new CardGUI(this), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new Projectile(), (Plugin) this);
		getServer().getPluginManager().registerEvents((Listener) new Restricciones(this), (Plugin) this);
		getLogger().info(" Author adri1711- activado");
		setupEconomy();
		setupPermissions();
		inicializaVariables();
	}

	public void onDisable() {
		saveYamls();
		getServer().getScheduler().cancelTasks((Plugin) this);
		getLogger().info(" Author adri1711 - desactivado");
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager()
				.getRegistration(Permission.class);
		if (permissionProvider != null) {
			permission = (Permission) permissionProvider.getProvider();
		}
		return (permission != null);
	}

	public void addPerm(String perm, Player player) {
		permission.playerAdd(player, perm);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(Economy.class);
		if (economyProvider != null) {
			economy = (Economy) economyProvider.getProvider();
		}

		return (economy != null);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (label.equalsIgnoreCase("rolcards")) {
				if (args.length == 0) {
					if (getLanguage().equalsIgnoreCase("es")) {
						p.sendMessage("§6-----------------§e§lRolCards§6---------------------\n" + ChatColor.YELLOW
								+ "   /rolcards:\n" + "          " + ChatColor.BLUE + "->" + ChatColor.GREEN
								+ "Muestra este menu de ayuda\n");
						if (p.hasPermission("rolcards.play")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards play:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Comando para jugar RolCards\n" + ChatColor.YELLOW
									+ "   /rolcards join:\n" + "          " + ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Comando para unirte a una partida\n");
						}
						if (p.hasPermission("rolcards.leave")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards leave:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Comando para dejar de jugar RolCards\n");
						}
						if (p.hasPermission("rolcards.spawn.set")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards spawn set:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN
									+ "Comando para setear el spawn al que teletransporta al acabar la partida\n");
						}
						if (p.hasPermission("rolcards.elo")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards elo:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Comando para ver tu elo\n");
						}
						if (p.hasPermission("rolcards.stats")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards stats:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Comando para ver tus stats\n");
						}
						if (p.hasPermission("rolcards.stats.other")) {
							p.sendMessage(
									ChatColor.YELLOW + "   /rolcards stats [player]:\n" + "          " + ChatColor.BLUE
											+ "->" + ChatColor.GREEN + "Comando para ver las stats de otro jugador\n");
						}
						if (p.hasPermission("rolcards.top")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards top:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Comando para ver el top 10\n");
						}
						if (p.hasPermission("rolcards.elo.other")) {
							p.sendMessage(
									ChatColor.YELLOW + "   /rolcards elo [player]:\n" + "          " + ChatColor.BLUE
											+ "->" + ChatColor.GREEN + "Comando para ver el elo de un jugador\n");
						}

						if (p.hasPermission("rolcards.elo.give")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards elo give [player] [amount]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN + "Comando para dar elo a un jugador\n");
						}
						if (p.hasPermission("rolcards.elo.set")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards elo set [player] [amount]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Comando para setear el elo a un jugador\n");
						}
						if (p.hasPermission("rolcards.card.create")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards card create:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN + "Comando para crear una carta\n");
						}
						if (p.hasPermission("rolcards.arena.create")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena create [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN + "Comando para crear una arena\n");
						}
						if (p.hasPermission("rolcards.arena.spawnset")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setspawn1 [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Comando para poner el 1 spawn de la arena\n");

							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setspawn2 [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Comando para poner el 2 spawn de la arena\n");
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setdeathmatch1 [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Comando para poner el 1 spawn de la deathmatch de la arena\n");
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setdeathmatch2 [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Comando para poner el 2 spawn de la deathmatch de la arena\n");
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setmobspawn1 [ID] [number]:\n"
									+ "          " + ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Comando para setear el MobSpawn numero [number] del primer jugador de una arena\nEl [number] debe ser entre 1 y 5");
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setmobspawn2 [ID] [number]:\n"
									+ "          " + ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Comando para setear el MobSpawn numero [number] del primer jugador de una arena\nEl [number] debe ser entre 1 y 5");
						}
						if (p.hasPermission("rolcards.reload")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards reload:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Comando para recargar el plugin\n");
						}
						if (p.hasPermission("rolcards.test")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards test:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Comando para testear cartas\n");
						}
						p.sendMessage("§6----------------------------------------------");
					} else {
						p.sendMessage("§6-----------------§e§lRolCards§6---------------------\n" + ChatColor.YELLOW
								+ "   /rolcards:\n" + "          " + ChatColor.BLUE + "->" + ChatColor.GREEN
								+ "Shows this help menu\n");
						if (p.hasPermission("rolcards.play")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards play:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Command to play RolCards\n" + ChatColor.YELLOW
									+ "   /rolcards join:\n" + "          " + ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Command to join a match\n");
						}
						if (p.hasPermission("rolcards.leave")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards leave:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Command to leave RolCards\n");
						}
						if (p.hasPermission("rolcards.spawn.set")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards spawn set:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN
									+ "Command to set the spawn where you teleport when a match is finished\n");
						}
						if (p.hasPermission("rolcards.elo")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards elo:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Command to see your elo\n");
						}
						if (p.hasPermission("rolcards.stats")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards stats:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Command to see your stats\n");
						}
						if (p.hasPermission("rolcards.stats.other")) {
							p.sendMessage(
									ChatColor.YELLOW + "   /rolcards stats [player]:\n" + "          " + ChatColor.BLUE
											+ "->" + ChatColor.GREEN + "Command to see the stats of another player\n");
						}
						if (p.hasPermission("rolcards.top")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards top:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Command to see the top 10\n");
						}
						if (p.hasPermission("rolcards.elo.other")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards elo [player]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN + "Command to see the elo of a player\n");
						}

						if (p.hasPermission("rolcards.elo.give")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards elo give [player] [amount]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN + "Command to give elo to a player\n");
						}
						if (p.hasPermission("rolcards.elo.set")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards elo set [player] [amount]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN + "Command to set the elo of a player\n");
						}
						if (p.hasPermission("rolcards.card.create")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards card create:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN + "Command to create a Card\n");
						}
						if (p.hasPermission("rolcards.arena.create")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena create [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN + "Command to create an Arena\n");
						}
						if (p.hasPermission("rolcards.arena.spawnset")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setspawn1 [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Command to set the 1st spawn of an Arena\n");
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setspawn2 [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Command to set the 2nd spawn of an Arena\n");

							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setdeathmatch1 [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Command to set the 1st deathmatch spawn of an Arena\n");
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setdeathmatch2 [ID]:\n" + "          "
									+ ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Command to set the 2nd deathmatch spawn of an Arena\n");
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setmobspawn1 [ID] [number]:\n"
									+ "          " + ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Command to set the [number] MobSpawn of the first player of an Arena\nThe number must be between 1 and 5");
							p.sendMessage(ChatColor.YELLOW + "   /rolcards arena setmobspawn2 [ID] [number]:\n"
									+ "          " + ChatColor.BLUE + "->" + ChatColor.GREEN
									+ "Command to set the [number] MobSpawn of the second player of an Arena\nThe number must be between 1 and 5");
						}

						if (p.hasPermission("rolcards.reload")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards reload:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Command to reload the plugin\n");
						}
						if (p.hasPermission("rolcards.test")) {
							p.sendMessage(ChatColor.YELLOW + "   /rolcards test:\n" + "          " + ChatColor.BLUE
									+ "->" + ChatColor.GREEN + "Command to test cards\n");
						}
						p.sendMessage("§6----------------------------------------------");
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("elo")) {
						if (p.hasPermission("rolcards.elo")) {
							p.sendMessage("§9" + getElo1(p.getName()).intValue() + "§2 Elo");
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("test")) {
						if (p.hasPermission("rolcards.test")) {
							Utils.buscaPartida(p, p, this);
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("join")) {
						if (p.hasPermission("rolcards.play")) {
							Utils.buscaPartida2(p, this);
						} else {
							p.sendMessage("§cYou dont have permission to do this command! (rolcards.play) ");
						}
					} else if (args[0].equalsIgnoreCase("top")) {
						if (p.hasPermission("rolcards.top")) {
							getTop10(p);
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("stats")) {
						if (p.hasPermission("rolcards.stats")) {
							if (this.mySQL) {
								if (getLanguage().equalsIgnoreCase("es")) {
									p.sendMessage("§b======== Estadisticas de " + p.getName() + " §b========");
									p.sendMessage("§b    ->Elo: §f" + getElo1(p.getName()));
									p.sendMessage("§b    ->Ganadas: §f" + getKills1(p.getName()).intValue());
									p.sendMessage("§b    ->Perdidas: §f" + getDeaths1(p.getName()).intValue());
									p.sendMessage("§b    ->KDR: §f" + (getKills1(p.getName()).doubleValue()
											/ getDeaths1(p.getName()).doubleValue()));
								} else {
									p.sendMessage("§b======== Stats of " + p.getName() + " §b========");
									p.sendMessage("§b    ->Elo: §f" + getElo1(p.getName()));
									p.sendMessage("§b    ->Wins: §f" + getKills1(p.getName()).intValue());
									p.sendMessage("§b    ->Loses: §f" + getDeaths1(p.getName()).intValue());
									p.sendMessage("§b    ->KDR: §f" + (getKills1(p.getName()).doubleValue()
											/ getDeaths1(p.getName()).doubleValue()));
								}
							} else {
								p.sendMessage("§cYou have to activate MySQL to see this!");
							}
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("play")) {
						if (p.hasPermission("rolcards.play")) {
							if (Utils.checkInventoryVacio(p)) {

								Jugador j = Utils.buscaJugador(p, this);
								if (this.scoreboardEnabled) {
									Utils.updateScoreboard(p, j, null, this);
								}
								j.setPlaying(true);
								if (isResourcePackEnabled()) {
									if (getsVersion().contains("v1_15")) {
										p.setResourcePack(
												"https://www.dropbox.com/s/gn40ymxroq2t8by/RolCards1.15.zip?dl=1");

									} else {
										p.setResourcePack(
												"https://www.dropbox.com/s/atehu8letooruvh/Rolcards.zip?dl=1");
									}
								}
								Utils.setInventarioDefault(p, this);
							} else if (this.language.equalsIgnoreCase("es")) {
								p.sendMessage("§cVacia tu inventario antes de unirte a RolCards!");
							} else {
								p.sendMessage("§cClear your inventory before joining RolCards!");
							}
						} else {

							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("leave")) {
						if (p.hasPermission("rolcards.leave")) {

							Jugador j = Utils.buscaJugador(p, this);
							if (j.getPartida() == null) {

								p.setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
								j.setPlaying(false);
								if (isResourcePackEnabled())
									p.setResourcePack(this.resourceNormal);
								Utils.deleteInventarioDefault(p, this);
							} else {
								p.sendMessage("§cYou cant leave in a match!");
							}
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("reload")) {
						if (p.hasPermission("rolcards.reload")) {
							inicializaVariables();
							p.sendMessage("§9[RolCards] §7Reloaded plugin");
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("info")) {
						if (p.hasPermission("rolcards.info")) {
							Jugador j = Utils.buscaJugador(p, this);
							Partida partida = j.getPartida();
							if (partida != null) {
								Jugador j2 = partida.devuelveOtroJugador(j);
								Utils.daVidaEnemigo(j.getP(), j2.getP(), this);
							} else {
								p.sendMessage("§cYou must be on a match to do this command!");
							}
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else {
						p.sendMessage("§cYou cannot perform this command!");
					}
				} else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("elo")) {
						if (p.hasPermission("rolcards.elo.other")) {
							if (getElo1(args[1]) != null) {
								p.sendMessage("§2" + args[1] + " has §9" + getElo1(args[1]) + "§2 Elo");
							} else {
								p.sendMessage("§cUser " + args[1] + " not found. Did you write it correctly?");
							}
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("card")) {
						if (p.hasPermission("rolcards.card.create")) {
							Jugador j = Utils.buscaJugador(p, this);
							j.setFase(Fase.CARDCLASS);
							p.sendMessage("§8[RolCards] §2Choose a class for the card.(Write the number)");
							p.sendMessage("     §a0 §e-> §aNormal");
							p.sendMessage("     §a1 §e-> §aHunter");
							p.sendMessage("     §a2 §e-> §aMage");
							p.sendMessage("     §a3 §e-> §aWarrior");
						}
					} else if (args[0].equalsIgnoreCase("stats")) {
						if (p.hasPermission("rolcards.stats.other")) {
							if (this.mySQL) {
								if (getKills1(args[1]) != null) {
									if (getLanguage().equalsIgnoreCase("es")) {
										p.sendMessage("§b======== Estadisticas de " + args[1] + " §b========");
										p.sendMessage("§b    ->Elo: §f" + getElo1(args[1]).intValue());
										p.sendMessage("§b    ->Ganadas: §f" + getKills1(args[1]).intValue());
										p.sendMessage("§b    ->Perdidas: §f" + getDeaths1(args[1]).intValue());
										p.sendMessage("§b    ->KDR: §f" + (getKills1(args[1]).doubleValue()
												/ getDeaths1(args[1]).doubleValue()));
									} else {
										p.sendMessage("§b======== Stats of " + args[1] + " §b========");
										p.sendMessage("§b    ->Elo: §f" + getElo1(args[1]).intValue());
										p.sendMessage("§b    ->Wins: §f" + getKills1(args[1]).intValue());
										p.sendMessage("§b    ->Loses: §f" + getDeaths1(args[1]).intValue());
										p.sendMessage("§b    ->KDR: §f" + (getKills1(args[1]).doubleValue()
												/ getDeaths1(args[1]).doubleValue()));
									}
								} else {

									p.sendMessage("§cUser " + args[1] + " not found. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou have to activate MySQL to see this!");
							}
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					} else if (args[0].equalsIgnoreCase("spawn")) {
						if (p.hasPermission("rolcards.spawn.set")) {
							if (args[1].equalsIgnoreCase("set")) {
								this.spawnLocation = p.getLocation();
								getConfig().set("spawn", p.getLocation());
								saveConfig();
								p.sendMessage("§2You have set the spawn succesfully");
							}
						} else {
							p.sendMessage("§cYou dont have permission to do this command!");
						}
					}
				} else if (args.length == 3) {
					if (args[0].equalsIgnoreCase("arena")) {
						if (args[1].equalsIgnoreCase("create")) {
							if (p.hasPermission("rolcards.arena.create")) {
								Integer idArena = Integer.valueOf(args[2]);
								if (idArena != null) {
									if (!this.configArenas.contains(idArena)) {
										List<Location> l1 = new ArrayList<>();
										List<Location> l2 = new ArrayList<>();
										for (int i = 0; i < 5; i++) {
											l1.add(p.getLocation());
											l2.add(p.getLocation());
										}
										ArenaRC arena = new ArenaRC(idArena, p.getLocation(), p.getLocation(),
												p.getLocation(), p.getLocation(), l1, l2);
										this.arenas.add(arena);
										this.configArenas.add(idArena);
										this.configArenas.add(p.getLocation());
										this.configArenas.add(p.getLocation());
										this.configArenas.add(p.getLocation());
										this.configArenas.add(p.getLocation());
										for (Location l : l1) {
											this.configArenas.add(l);
										}
										for (Location l : l2) {
											this.configArenas.add(l);
										}
										getConfig().set("arenas", this.configArenas);
										p.sendMessage("§2Arena has been created, change the spawn locations.");
										saveConfig();
									} else {
										p.sendMessage("§cThis Arena already exists.");
									}
								} else {
									p.sendMessage("§cID must be a number. Did you write it correctly?");
								}
							} else {

								p.sendMessage("§cYou dont have permission to do this command!");
							}
						} else if (args[1].equalsIgnoreCase("setspawn1")) {
							if (p.hasPermission("rolcards.arena.spawnset")) {
								Integer idArena = Integer.valueOf(args[2]);
								if (idArena != null) {
									ArenaRC aren = Utils.buscaArena(idArena, this);
									if (aren != null) {
										aren.setSpawn1(p.getLocation());
										Utils.cambiaArena(idArena, aren, this);
										int aux = this.configArenas.indexOf(idArena);
										this.configArenas.set(aux + 1, p.getLocation());
										getConfig().set("arenas", this.configArenas);
										p.sendMessage("§2Arena spawn1 set.");
										saveConfig();
									} else {
										p.sendMessage("§cArena " + idArena + " doesnt exist!");
									}
								} else {
									p.sendMessage("§cID must be a number. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou dont have permission to do this command!");
							}
						} else if (args[1].equalsIgnoreCase("setspawn2")) {
							if (p.hasPermission("rolcards.arena.spawnset")) {
								Integer idArena = Integer.valueOf(args[2]);
								if (idArena != null) {
									ArenaRC aren = Utils.buscaArena(idArena, this);
									if (aren != null) {
										aren.setSpawn2(p.getLocation());
										Utils.cambiaArena(idArena, aren, this);
										int aux = this.configArenas.indexOf(idArena);
										this.configArenas.set(aux + 2, p.getLocation());
										getConfig().set("arenas", this.configArenas);
										p.sendMessage("§2Arena spawn2 set.");
										saveConfig();
									} else {
										p.sendMessage("§cArena " + idArena + " doesnt exist!");
									}
								} else {
									p.sendMessage("§cID must be a number. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou dont have permission to do this command!");
							}
						} else if (args[1].equalsIgnoreCase("setdeathmatch1")) {
							if (p.hasPermission("rolcards.arena.spawnset")) {
								Integer idArena = Integer.valueOf(args[2]);
								if (idArena != null) {
									ArenaRC aren = Utils.buscaArena(idArena, this);
									if (aren != null) {
										aren.setSpawndeathmatch1(p.getLocation());
										Utils.cambiaArena(idArena, aren, this);
										int aux = this.configArenas.indexOf(idArena);
										this.configArenas.set(aux + 3, p.getLocation());
										getConfig().set("arenas", this.configArenas);
										p.sendMessage("§2Arena deathmatch1 set.");
										saveConfig();
									} else {
										p.sendMessage("§cArena " + idArena + " doesnt exist!");
									}
								} else {
									p.sendMessage("§cID must be a number. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou dont have permission to do this command!");
							}
						} else if (args[1].equalsIgnoreCase("setdeathmatch2")) {
							if (p.hasPermission("rolcards.arena.spawnset")) {
								Integer idArena = Integer.valueOf(args[2]);
								if (idArena != null) {
									ArenaRC aren = Utils.buscaArena(idArena, this);
									if (aren != null) {
										aren.setSpawndeathmatch2(p.getLocation());
										Utils.cambiaArena(idArena, aren, this);
										int aux = this.configArenas.indexOf(idArena);
										this.configArenas.set(aux + 4, p.getLocation());
										getConfig().set("arenas", this.configArenas);
										p.sendMessage("§2Arena deathmatch2 set.");
										saveConfig();
									} else {
										p.sendMessage("§cArena " + idArena + " doesnt exist!");
									}
								} else {
									p.sendMessage("§cID must be a number. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou dont have permission to do this command!");
							}
						}
					}
				} else if (args.length == 4) {
					if (args[0].equalsIgnoreCase("elo")) {
						if (args[1].equalsIgnoreCase("give")) {
							if (p.hasPermission("rolcards.elo.give")) {
								if (getElo1(args[2]) != null) {
									Double eloADar = Double.valueOf(args[3]);
									if (eloADar != null) {
										addElo(args[2], eloADar);
										p.sendMessage("§2" + args[3] + " Elo given to §9" + args[2]);
									} else {
										p.sendMessage("§cAmount wrong. Did you write it correctly?");
									}
								} else {
									p.sendMessage("§cUser " + args[2] + " not found. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou dont have permission to do this command!");
							}

						} else if (args[1].equalsIgnoreCase("set")) {
							if (p.hasPermission("rolcards.elo.set")) {
								if (getElo1(args[2]) != null) {
									Double eloADar = Double.valueOf(args[3]);
									if (eloADar != null) {
										updateElo(args[2], eloADar);
										p.sendMessage("§2" + args[2] + " Elo set to §9" + eloADar);
									} else {
										p.sendMessage("§cAmount wrong. Did you write it correctly?");
									}
								} else {
									p.sendMessage("§cUser " + args[2] + " not found. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou dont have permission to do this command!");
							}

						}
					} else if (args[0].equalsIgnoreCase("arena")) {
						if (args[1].equalsIgnoreCase("setmobspawn1")) {
							if (p.hasPermission("rolcards.arena.spawnset")) {
								Integer idArena = Integer.valueOf(args[2]);
								Integer numeroSpawn = Integer.valueOf(args[3]);
								if (idArena != null) {
									if (numeroSpawn != null) {
										if (numeroSpawn.intValue() > 0 && numeroSpawn.intValue() < 6) {
											ArenaRC aren = Utils.buscaArena(idArena, this);
											if (aren != null) {
												aren.getMobSpawn1().set(numeroSpawn.intValue() - 1, p.getLocation());
												Utils.cambiaArena(idArena, aren, this);
												int aux = this.configArenas.indexOf(idArena);
												this.configArenas.set(aux + 4 + numeroSpawn.intValue(),
														p.getLocation());
												getConfig().set("arenas", this.configArenas);
												p.sendMessage("§2Arena MobSpawn1 " + numeroSpawn + " set.");
												saveConfig();
											} else {
												p.sendMessage("§cArena " + idArena + " doesnt exist!");
											}
										} else {
											p.sendMessage(
													"§cThe number of the spawn must be between 1 and 5. Did you write it correctly?");
										}
									} else {
										p.sendMessage("§cID must be a number. Did you write it correctly?");
									}
								} else {

									p.sendMessage("§cID must be a number. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou dont have permission to do this command!");
							}
						} else if (args[1].equalsIgnoreCase("setmobspawn2")) {
							if (p.hasPermission("rolcards.arena.spawnset")) {
								Integer idArena = Integer.valueOf(args[2]);
								Integer numeroSpawn = Integer.valueOf(args[3]);
								if (idArena != null) {
									if (numeroSpawn != null) {
										if (numeroSpawn.intValue() > 0 && numeroSpawn.intValue() < 6) {
											ArenaRC aren = Utils.buscaArena(idArena, this);
											if (aren != null) {
												aren.getMobSpawn2().set(numeroSpawn.intValue() - 1, p.getLocation());
												Utils.cambiaArena(idArena, aren, this);
												int aux = this.configArenas.indexOf(idArena);
												this.configArenas.set(aux + 9 + numeroSpawn.intValue(),
														p.getLocation());
												getConfig().set("arenas", this.configArenas);
												p.sendMessage("§2Arena MobSpawn2 " + numeroSpawn + " set.");
												saveConfig();
											} else {
												p.sendMessage("§cArena " + idArena + " doesnt exist!");
											}
										} else {
											p.sendMessage(
													"§cThe number of the spawn must be between 1 and 5. Did you write it correctly?");
										}
									} else {
										p.sendMessage("§cID must be a number. Did you write it correctly?");
									}
								} else {

									p.sendMessage("§cID must be a number. Did you write it correctly?");
								}
							} else {
								p.sendMessage("§cYou dont have permission to do this command!");
							}
						}
					}
				}
			}
		} else {
			sender.sendMessage("You cannot perform this command!");
		}
		return true;
	}

	public void loadConfiguration() {
		this.getConfig().options().copyDefaults(true);
		this.saveDefaultConfig();
	}

	public void defaultConfiguration() {
		this.getConfig().addDefault("enableMySQL", (Object) false);
		this.getConfig().addDefault("mysql.host", (Object) "");
		this.getConfig().addDefault("mysql.port", (Object) "");
		this.getConfig().addDefault("mysql.database", (Object) "");
		this.getConfig().addDefault("mysql.user", (Object) "");
		this.getConfig().addDefault("mysql.password", (Object) "");
		this.getConfig().addDefault("spawn", (Object) new Location(Bukkit.getWorld("world"), 0.0, 60.0, 0.0));
		this.getConfig().addDefault("turnTime", (Object) 25);
		this.getConfig().addDefault("prizeAmount", (Object) 10);
		this.getConfig().addDefault("language", (Object) "en");
		this.getConfig().addDefault("directJoin", (Object) false);
		this.getConfig().addDefault("featherBoardEnabled", (Object) false);
		this.getConfig().addDefault("scoreboardEnabled", (Object) true);
		this.getConfig().addDefault("joinMatchFee", (Object) 0);
		this.getConfig().addDefault("lotMoneyCost", (Object) 4500.0);
		this.getConfig().addDefault("arenas", (Object) new ArrayList());
		this.getConfig().addDefault("createdCards", (Object) new ArrayList());
		this.getConfig().addDefault("resourceNormal", (Object) "http://www.faithful32x32.com/dl/faithful32pack.zip");
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		this.saveYamls();
	}

	public void inicializaVariables() {
		api = new API1711("%%__USER__%%", "RolCards");

		this.language = this.getConfig().getString("language");
		this.featherBoardEnabled = this.getConfig().getBoolean("featherBoardEnabled");
		this.scoreboardEnabled = this.getConfig().getBoolean("scoreboardEnabled");
		this.arenas = new ArrayList<ArenaRC>();
		this.partidas = new ArrayList<Partida>();
		this.resourcePackEnabled = this.getConfig().getBoolean("resourcePackEnabled");
		this.rewardCommand = this.getConfig().getString("rewardCommand");
		this.resourceNormal = this.getConfig().getString("resourceNormal");
		this.lotMoney = this.getConfig().getDouble("lotMoneyCost");
		try {
			this.sVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		} catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
			this.sVersion = "";
		}
		this.getLogger().info("[RolCards] Spigot version using: " + this.sVersion);
		if (this.jugadores == null) {
			this.jugadores = new ArrayList<Jugador>();
		}
		RolCards.peticiones = new ArrayList<Peticion>();
		this.prizeAmount = this.getConfig().getInt("prizeAmount");
		this.directJoin = this.getConfig().getBoolean("directJoin");
		this.scoreboardName = this.getConfig().getString("scoreboardName");
		this.joinMatchFee = this.getConfig().getInt("joinMatchFee");
		this.deckSize = this.getConfig().getInt("deckSize");
		this.messages = new LanguageMessages(this);
		this.listaCreatedCard = new ArrayList<CreatedCard>();
		int numerosuma = 12;
		for (int num = 0; num < this.getConfig().getStringList("createdCards").size();) {
			numerosuma = 12;
			CardClass cc = CardClass.NORMAL;
			final String s;
			switch (s = this.getConfig().getStringList("createdCards").get(num)) {
			case "NORMAL": {
				cc = CardClass.NORMAL;
				break;
			}
			case "MAGE": {
				cc = CardClass.MAGE;
				break;
			}
			case "WARRIOR": {
				cc = CardClass.WARRIOR;
				break;
			}
			case "HUNTER": {
				cc = CardClass.HUNTER;
				break;
			}
			default:
				break;
			}
			final String name = this.getConfig().getStringList("createdCards").get(num + 1);
			final Integer price = new Integer(this.getConfig().getStringList("createdCards").get(num + 2));
			final Integer cost = new Integer(this.getConfig().getStringList("createdCards").get(num + 3));
			final String perm = this.getConfig().getStringList("createdCards").get(num + 4);
			final String desc1 = this.getConfig().getStringList("createdCards").get(num + 5);
			final String desc2 = this.getConfig().getStringList("createdCards").get(num + 6);
			final String desc3 = this.getConfig().getStringList("createdCards").get(num + 7);
			final String desc4 = this.getConfig().getStringList("createdCards").get(num + 8);
			final String desc5 = this.getConfig().getStringList("createdCards").get(num + 9);
			CardEffect ce = CardEffect.DAMAGE;
			final String s2;
			switch (s2 = this.getConfig().getStringList("createdCards").get(num + 10)) {
			case "DRAW": {
				ce = CardEffect.DRAW;
				break;
			}
			case "HEAL": {
				ce = CardEffect.HEAL;
				break;
			}
			case "SPAWN": {
				ce = CardEffect.SPAWN;
				break;
			}
			case "DAMAGE": {
				ce = CardEffect.DAMAGE;
				break;
			}
			default:
				break;
			}
			final Integer value = new Integer(this.getConfig().getStringList("createdCards").get(num + 11));
			if (ce == CardEffect.SPAWN) {
				numerosuma = 15;
				final String mobname = this.getConfig().getStringList("createdCards").get(num + 12);
				final Double damage = new Double(this.getConfig().getStringList("createdCards").get(num + 13));
				final Double health = new Double(this.getConfig().getStringList("createdCards").get(num + 14));
				if (desc5 == "") {
					if (desc4 == "") {
						if (desc3 == "") {
							if (desc2 == "") {
								new CreatedCard(cc, name, price, cost, perm, desc1, ce, value, mobname, damage, health,
										this);
							} else {
								new CreatedCard(cc, name, price, cost, perm, desc1, desc2, ce, value, mobname, damage,
										health, this);
							}
						} else {
							new CreatedCard(cc, name, price, cost, perm, desc1, desc2, desc3, ce, value, mobname,
									damage, health, this);
						}
					} else {
						new CreatedCard(cc, name, price, cost, perm, desc1, desc2, desc3, desc4, ce, value, mobname,
								damage, health, this);
					}
				} else {
					new CreatedCard(cc, name, price, cost, perm, desc1, desc2, desc3, desc4, desc5, ce, value, mobname,
							damage, health, this);
				}
			} else if (desc5 == "") {
				if (desc4 == "") {
					if (desc3 == "") {
						if (desc2 == "") {
							new CreatedCard(cc, name, price, cost, perm, desc1, ce, value, this);
						} else {
							new CreatedCard(cc, name, price, cost, perm, desc1, desc2, ce, value, this);
						}
					} else {
						new CreatedCard(cc, name, price, cost, perm, desc1, desc2, desc3, ce, value, this);
					}
				} else {
					new CreatedCard(cc, name, price, cost, perm, desc1, desc2, desc3, desc4, ce, value, this);
				}
			} else {
				new CreatedCard(cc, name, price, cost, perm, desc1, desc2, desc3, desc4, desc5, ce, value, this);
			}
			num += numerosuma;
		}
		if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI") && this.featherBoardEnabled) {
			UtilPlaceholder.putPlaceholder(this);
		}
		this.warriorCards = new WarriorCards(this);
		this.mageCards = new MageCards(this);
		this.hunterCards = new HunterCards(this);
		this.normalCards = new NormalCards(this);
		for (final CreatedCard crca : this.listaCreatedCard) {
			switch (crca.getClase()) {
			default: {
				continue;
			}
			case NORMAL: {
				this.normalCards.getNormalCards().add(crca);
				continue;
			}
			case HUNTER: {
				this.hunterCards.getHunterCards().add(crca);
				continue;
			}
			case MAGE: {
				this.mageCards.getMageCards().add(crca);
				continue;
			}
			case WARRIOR: {
				this.warriorCards.getWarriorCards().add(crca);
				continue;
			}
			}
		}
		(this.cartas = new ArrayList<Card>()).addAll(this.warriorCards.getWarriorCards());
		this.cartas.addAll(this.mageCards.getMageCards());
		this.cartas.addAll(this.hunterCards.getHunterCards());
		this.cartas.addAll(this.normalCards.getNormalCards());
		this.turnTime = this.getConfig().getInt("turnTime");
		final ItemStack esmeralda = new ItemStack(getApi().getMaterial(AMaterials.EMERALD));
		final ItemMeta esmeraldaMeta = esmeralda.getItemMeta();
		if (this.language.equalsIgnoreCase("es")) {
			esmeraldaMeta.setDisplayName("§2§lMenu de Cartas");
		} else {
			esmeraldaMeta.setDisplayName(this.messages.getCardMenuItemName());
		}
		esmeralda.setItemMeta(esmeraldaMeta);
		final ItemStack topItem = new ItemStack(getApi().getMaterial(AMaterials.BLAZE_POWDER));
		final ItemMeta topItemMeta = topItem.getItemMeta();
		topItemMeta.setDisplayName("§2§lTop 10");
		topItem.setItemMeta(topItemMeta);
		this.itemTop = topItem;
		this.menuCartas = esmeralda;
		final ItemStack diamond = new ItemStack(getApi().getMaterial(AMaterials.DIAMOND));
		final ItemMeta diamondMeta = diamond.getItemMeta();
		if (this.language.equalsIgnoreCase("es")) {
			diamondMeta.setDisplayName("§2§lPeticiones");
		} else {
			diamondMeta.setDisplayName(this.messages.getRequestItemName());
		}
		diamond.setItemMeta(diamondMeta);
		this.requests = diamond;
		final ItemStack vara = new ItemStack(getApi().getMaterial(AMaterials.BLAZE_ROD));
		final ItemMeta varaMeta = vara.getItemMeta();
		if (this.language.equalsIgnoreCase("es")) {
			varaMeta.setDisplayName("§c§lDesafiador");
		} else {
			varaMeta.setDisplayName(this.messages.getChallengItemName());
		}
		List<String> lista = new ArrayList<String>();
		if (this.language.equalsIgnoreCase("es")) {
			final String line = "§7Click derecho a alguien para retarlo";
			lista.add(line);
		} else {
			lista = this.messages.getChallengItemLore();
		}
		varaMeta.setLore((List) lista);
		vara.setItemMeta(varaMeta);
		this.desafiador = vara;
		this.mySQL = this.getConfig().getBoolean("enableMySQL");
		this.host = this.getConfig().getString("mysql.host");
		this.port = this.getConfig().getString("mysql.port");
		this.database = this.getConfig().getString("mysql.database");
		this.user = this.getConfig().getString("mysql.user");
		this.password = this.getConfig().getString("mysql.password");
		if (this.mySQL) {
			try {
				this.con = DriverManager.getConnection("jdbc:mysql://" + this.getHost() + ":"
						+ Integer.valueOf(this.getPort()) + "/" + this.getDatabase1(), this.getUser(),
						this.getPassword());
				final Statement statement = this.con.createStatement();
				statement.execute(
						"CREATE TABLE IF NOT EXISTS elo(name VARCHAR(36) PRIMARY KEY NOT NULL, balance DECIMAL(65, 2))");
				statement.execute(
						"CREATE TABLE IF NOT EXISTS stats(name VARCHAR(36) PRIMARY KEY NOT NULL, kills DECIMAL(65, 2), deaths DECIMAL(65, 2))");
				this.getElo = this.con.prepareStatement("SELECT balance FROM elo WHERE name=?");
				this.getTop10 = this.con.prepareStatement("SELECT * FROM elo ORDER BY balance DESC LIMIT 10");
				this.updateElo = this.con.prepareStatement("UPDATE elo SET balance=? WHERE name=?");
				this.addElo = this.con.prepareStatement("INSERT INTO elo(name,balance) VALUES(?,?)");
				this.getKillsPlayer = this.con.prepareStatement("SELECT kills FROM stats WHERE name=?");
				this.updateKills = this.con.prepareStatement("UPDATE stats SET kills=? WHERE name=?");
				this.addStats = this.con.prepareStatement("INSERT INTO stats(name,kills,deaths) VALUES(?,?,?)");
				this.getDeathsPlayer = this.con.prepareStatement("SELECT deaths FROM stats WHERE name=?");
				this.updateDeaths = this.con.prepareStatement("UPDATE stats SET deaths=? WHERE name=?");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.spawnLocation = (Location) this.getConfig().get("spawn");
		this.arenas = new ArrayList<ArenaRC>();
		this.configArenas = (List<Object>) this.getConfig().getList("arenas");
		for (int i = 0; i < this.configArenas.size(); i += 15) {
			final Integer id = (Integer) this.configArenas.get(i);
			final Location spawn1 = (Location) this.configArenas.get(i + 1);
			final Location spawn2 = (Location) this.configArenas.get(i + 2);
			final Location spawndt1 = (Location) this.configArenas.get(i + 3);
			final Location spawndt2 = (Location) this.configArenas.get(i + 4);
			final List<Location> mspawn1 = new ArrayList<Location>();
			final List<Location> mspawn2 = new ArrayList<Location>();
			mspawn1.add((Location) this.configArenas.get(i + 5));
			mspawn1.add((Location) this.configArenas.get(i + 6));
			mspawn1.add((Location) this.configArenas.get(i + 7));
			mspawn1.add((Location) this.configArenas.get(i + 8));
			mspawn1.add((Location) this.configArenas.get(i + 9));
			mspawn2.add((Location) this.configArenas.get(i + 10));
			mspawn2.add((Location) this.configArenas.get(i + 11));
			mspawn2.add((Location) this.configArenas.get(i + 12));
			mspawn2.add((Location) this.configArenas.get(i + 13));
			mspawn2.add((Location) this.configArenas.get(i + 14));
			final ArenaRC arena = new ArenaRC(id, spawn1, spawn2, spawndt1, spawndt2, mspawn1, mspawn2);
			this.arenas.add(arena);
			final Partida partida = new Partida(arena, this);
			this.partidas.add(partida);
		}
	}

	public boolean loadConfig() {
		if (!new File(this.getDataFolder() + File.separator + "config.yml").exists()) {
			this.saveDefaultConfig();
		}
		try {
			new YamlConfiguration().load(new File(this.getDataFolder() + File.separator + "config.yml"));
		} catch (Exception e) {
			System.out.println("--- --- RolCards --- ---");
			System.out.println("There was an error loading your configuration.");
			System.out.println("A detailed description of your error is shown below.");
			System.out.println("--- --- --- ---");
			e.printStackTrace();
			Bukkit.getPluginManager().disablePlugin((Plugin) this);
			return false;
		}
		try {
			this.reloadConfig();
		} catch (Exception e) {
			System.out.println("[RolCards] Reloading worlds");
		}
		this.loadConfiguration();
		return true;
	}

	public Connection getCon() {
		return this.con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabase1() {
		return this.database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PreparedStatement getAddElo() {
		return this.addElo;
	}

	public void setAddElo(PreparedStatement addElo) {
		this.addElo = addElo;
	}

	public PreparedStatement getUpdateElo() {
		return this.updateElo;
	}

	public void setUpdateElo(PreparedStatement updateElo) {
		this.updateElo = updateElo;
	}

	public PreparedStatement getGetElo() {
		return this.getElo;
	}

	public void setGetElo(PreparedStatement getElo) {
		this.getElo = getElo;
	}

	public void getTop10(Player p) {
		if (this.mySQL) {
			try {
				ResultSet resultSet = this.getTop10.executeQuery();
				String[] listaTop = new String[10];
				int counter = 0;
				while (resultSet.next()) {
					Double d = Double.valueOf(resultSet.getDouble("balance"));
					listaTop[counter] = ChatColor.RED + "     " + (counter + 1) + "-> " + ChatColor.GREEN
							+ resultSet.getString("name") + ChatColor.YELLOW + " --- " + ChatColor.GREEN + d.intValue();
					counter++;
				}
				p.sendMessage("§6-----------------§e§lRolCards§6---------------------\n");
				for (int i = 0; i < 10; i++) {
					p.sendMessage(listaTop[i]);
				}
				p.sendMessage("§6----------------------------------------------");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			List<String> listaPlayers = this.eloConfig.getStringList("players");
			List<Double> listaElo = this.eloConfig.getDoubleList("elo");

			for (int i = 0; i < listaElo.size(); i++) {
				for (int e = 0; e < listaElo.size(); e++) {
					if (((Double) listaElo.get(e)).doubleValue() >= ((Double) listaElo.get(i)).doubleValue()) {
						Double daux = listaElo.get(i);
						String saux = listaPlayers.get(i);
						listaElo.set(i, listaElo.get(e));
						listaPlayers.set(i, listaPlayers.get(e));
						listaElo.set(e, daux);
						listaPlayers.set(e, saux);
					}
				}
			}
			p.sendMessage("§6-----------------§e§lRolCards§6---------------------\n");
			for (int i = 9; i >= 0; i--) {
				if (i < listaPlayers.size() && i < listaElo.size())
					p.sendMessage(
							"§a" + (String) listaPlayers.get(i) + " :§c " + ((Double) listaElo.get(i)).intValue());
			}
			p.sendMessage("§6----------------------------------------------");
		}
	}

	public Double getElo1(String name) {
		if (this.mySQL)
			try {
				getGetElo().setString(1, name);
				ResultSet resultSet = this.getElo.executeQuery();
				if (resultSet.next()) {
					return Double.valueOf(resultSet.getDouble("balance"));
				}
				return null;
			} catch (SQLException e) {
				e.printStackTrace();

				return null;
			}
		if (this.eloConfig.getDoubleList("elo") == null)
			return null;
		if (this.eloConfig.getStringList("players").indexOf(name) == -1)
			return null;
		return this.eloConfig.getDoubleList("elo").get(this.eloConfig.getStringList("players").indexOf(name));
	}

	public void updateElo(String name, Double balance) {
		if (this.mySQL) {
			try {
				this.updateElo.setDouble(1, balance.doubleValue());
				this.updateElo.setString(2, name);
				this.updateElo.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			if (!this.eloConfig.getStringList("players").contains(name)) {
				List<String> slista = this.eloConfig.getStringList("players");
				slista.add(name);
				this.eloConfig.set("players", slista);
				List<Double> dlista = this.eloConfig.getDoubleList("elo");
				dlista.add(balance);
				this.eloConfig.set("elo", dlista);
				saveYamls();
			}
			Integer e = Integer.valueOf(this.eloConfig.getStringList("players").indexOf(name));
			List<Double> dlaux = this.eloConfig.getDoubleList("elo");
			dlaux.set(e.intValue(), balance);
			this.eloConfig.set("elo", dlaux);
			saveYamls();
		}
	}

	public void addElo(String name, Double aSumar) {
		if (this.mySQL) {
			try {
				Double previousBalance = getElo1(name);
				if (previousBalance == null) {
					this.addElo.setString(1, name);
					this.addElo.setDouble(2, 1500.0D + aSumar.doubleValue());
					this.addElo.execute();
				} else {
					updateElo(name, Double.valueOf(previousBalance.doubleValue() + aSumar.doubleValue()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			if (this.eloConfig.getStringList("players") == null) {
				this.eloConfig.addDefault("players", new ArrayList());
				this.eloConfig.addDefault("elo", new ArrayList());
				saveYamls();
			}
			if (!this.eloConfig.getStringList("players").contains(name)) {
				List<String> slista = this.eloConfig.getStringList("players");
				slista.add(name);
				this.eloConfig.set("players", slista);
				List<Double> dlista = this.eloConfig.getDoubleList("elo");
				dlista.add(Double.valueOf(1500.0D));
				this.eloConfig.set("elo", dlista);
				saveYamls();
			}
			Integer e = Integer.valueOf(this.eloConfig.getStringList("players").indexOf(name));
			List<Double> dlaux = this.eloConfig.getDoubleList("elo");
			dlaux.set(e.intValue(),
					Double.valueOf(((Double) this.eloConfig.getDoubleList("elo").get(e.intValue())).doubleValue()
							+ aSumar.doubleValue()));
			this.eloConfig.set("elo", dlaux);
			saveYamls();
		}
	}

	public Double getKills1(String name) {
		if (this.mySQL) {
			try {
				getGetKillsPlayer().setString(1, name);
				ResultSet resultSet = this.getKillsPlayer.executeQuery();
				if (resultSet.next()) {
					return Double.valueOf(resultSet.getDouble("kills"));
				}
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void updateKills(String name, Double kills) {
		if (this.mySQL) {
			try {
				this.updateKills.setDouble(1, kills.doubleValue());
				this.updateKills.setString(2, name);
				this.updateKills.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void addStats1(String name, Double killsASumar, Double deathsASumar) {
		if (this.mySQL) {
			try {
				Double previousBalanceKills = getKills1(name);
				Double previousBalanceDeaths = getDeaths1(name);
				if (previousBalanceKills == null) {
					this.addStats.setString(1, name);
					this.addStats.setDouble(2, 0.0D + killsASumar.doubleValue());
					this.addStats.setDouble(3, 0.0D + deathsASumar.doubleValue());
					this.addStats.execute();
				} else {
					updateKills(name, Double.valueOf(previousBalanceKills.doubleValue() + killsASumar.doubleValue()));
					updateDeaths(name,
							Double.valueOf(previousBalanceDeaths.doubleValue() + deathsASumar.doubleValue()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Double getDeaths1(String name) {
		if (this.mySQL) {
			try {
				getGetDeathsPlayer().setString(1, name);
				ResultSet resultSet = this.getDeathsPlayer.executeQuery();
				if (resultSet.next()) {
					return Double.valueOf(resultSet.getDouble("deaths"));
				}
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void updateDeaths(String name, Double deaths) {
		if (this.mySQL) {
			try {
				this.updateDeaths.setDouble(1, deaths.doubleValue());
				this.updateDeaths.setString(2, name);
				this.updateDeaths.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<ArenaRC> getArenas() {
		return this.arenas;
	}

	public void setArenas(List<ArenaRC> arenas) {
		this.arenas = arenas;
	}

	public List<Partida> getPartidas() {
		return this.partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public List<Jugador> getJugadores() {
		return this.jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Location getSpawnLocation() {
		return this.spawnLocation;
	}

	public void setSpawnLocation(Location spawnLocation) {
		this.spawnLocation = spawnLocation;
	}

	public ItemStack getMenuCartas() {
		return this.menuCartas;
	}

	public void setMenuCartas(ItemStack menuCartas) {
		this.menuCartas = menuCartas;
	}

	public ItemStack getDesafiador() {
		return this.desafiador;
	}

	public void setDesafiador(ItemStack desafiador) {
		this.desafiador = desafiador;
	}

	public static List<Peticion> getPeticiones() {
		return peticiones;
	}

	public static void setPeticiones(List<Peticion> peticiones) {
		RolCards.peticiones = peticiones;
	}

	public ItemStack getRequests() {
		return this.requests;
	}

	public void setRequests(ItemStack requests) {
		this.requests = requests;
	}

	public List<Card> getCartas() {
		return this.cartas;
	}

	public void setCartas(List<Card> cartas) {
		this.cartas = cartas;
	}

	public MageCards getMageCards() {
		return this.mageCards;
	}

	public void setMageCards(MageCards mageCards) {
		this.mageCards = mageCards;
	}

	public HunterCards getHunterCards() {
		return this.hunterCards;
	}

	public void setHunterCards(HunterCards hunterCards) {
		this.hunterCards = hunterCards;
	}

	public WarriorCards getWarriorCards() {
		return this.warriorCards;
	}

	public void setWarriorCards(WarriorCards warriorCards) {
		this.warriorCards = warriorCards;
	}

	public NormalCards getNormalCards() {
		return this.normalCards;
	}

	public void setNormalCards(NormalCards normalCards) {
		this.normalCards = normalCards;
	}

	public static Economy getEconomy() {
		return economy;
	}

	public static void setEconomy(Economy economy) {
		RolCards.economy = economy;
	}

	public Integer getTurnTime() {
		return this.turnTime;
	}

	public void setTurnTime(Integer turnTime) {
		this.turnTime = turnTime;
	}

	public int getPrizeAmount() {
		return this.prizeAmount;
	}

	public void setPrizeAmount(int prizeAmount) {
		this.prizeAmount = prizeAmount;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public File getCardsFile() {
		return this.cardsFile;
	}

	public void setCardsFile(File cardsFile) {
		this.cardsFile = cardsFile;
	}

	public FileConfiguration getCards() {
		return this.cards;
	}

	public void setCards(FileConfiguration cards) {
		this.cards = cards;
	}

	public void loadYamls() {
		try {
			this.cards = (FileConfiguration) YamlConfiguration.loadConfiguration(this.cardsFile);
			this.eloConfig = (FileConfiguration) YamlConfiguration.loadConfiguration(this.eloFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveYamls() {
		try {
			this.cards.save(this.cardsFile);
			this.eloConfig.save(this.eloFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ItemStack getItemTop() {
		return this.itemTop;
	}

	public void setItemTop(ItemStack itemTop) {
		this.itemTop = itemTop;
	}

	public File getEloFile() {
		return this.eloFile;
	}

	public void setEloFile(File eloFile) {
		this.eloFile = eloFile;
	}

	public FileConfiguration getEloConfig() {
		return this.eloConfig;
	}

	public void setEloConfig(FileConfiguration eloConfig) {
		this.eloConfig = eloConfig;
	}

	public boolean isMySQL() {
		return this.mySQL;
	}

	public void setMySQL(boolean mySQL) {
		this.mySQL = mySQL;
	}

	public boolean isDirectJoin() {
		return this.directJoin;
	}

	public void setDirectJoin(boolean directJoin) {
		this.directJoin = directJoin;
	}

	public PreparedStatement getAddStats() {
		return this.addStats;
	}

	public void setAddStats(PreparedStatement addStats) {
		this.addStats = addStats;
	}

	public PreparedStatement getUpdateKills() {
		return this.updateKills;
	}

	public void setUpdateKills(PreparedStatement updateKills) {
		this.updateKills = updateKills;
	}

	public PreparedStatement getGetKillsPlayer() {
		return this.getKillsPlayer;
	}

	public void setGetKillsPlayer(PreparedStatement getKillsPlayer) {
		this.getKillsPlayer = getKillsPlayer;
	}

	public PreparedStatement getUpdateDeaths() {
		return this.updateDeaths;
	}

	public void setUpdateDeaths(PreparedStatement updateDeaths) {
		this.updateDeaths = updateDeaths;
	}

	public PreparedStatement getGetDeathsPlayer() {
		return this.getDeathsPlayer;
	}

	public void setGetDeathsPlayer(PreparedStatement getDeathsPlayer) {
		this.getDeathsPlayer = getDeathsPlayer;
	}

	public String getScoreboardName() {
		return this.scoreboardName;
	}

	public void setScoreboardName(String scoreboardName) {
		this.scoreboardName = scoreboardName;
	}

	public Integer getJoinMatchFee() {
		return this.joinMatchFee;
	}

	public void setJoinMatchFee(Integer joinMatchFee) {
		this.joinMatchFee = joinMatchFee;
	}

	public LanguageMessages getMessages() {
		return this.messages;
	}

	public void setMessages(LanguageMessages messages) {
		this.messages = messages;
	}

	public Double getLotMoney() {
		return this.lotMoney;
	}

	public void setLotMoney(Double lotMoney) {
		this.lotMoney = lotMoney;
	}

	public List<CreatedCard> getListaCreatedCard() {
		return this.listaCreatedCard;
	}

	public void setListaCreatedCard(List<CreatedCard> listaCreatedCard) {
		this.listaCreatedCard = listaCreatedCard;
	}

	public String getResourceNormal() {
		return this.resourceNormal;
	}

	public void setResourceNormal(String resourceNormal) {
		this.resourceNormal = resourceNormal;
	}

	public boolean isFeatherBoardEnabled() {
		return this.featherBoardEnabled;
	}

	public void setFeatherBoardEnabled(boolean featherBoardEnabled) {
		this.featherBoardEnabled = featherBoardEnabled;
	}

	public boolean isScoreboardEnabled() {
		return this.scoreboardEnabled;
	}

	public void setScoreboardEnabled(boolean scoreboardEnabled) {
		this.scoreboardEnabled = scoreboardEnabled;
	}

	public Integer getDeckSize() {
		return this.deckSize;
	}

	public void setDeckSize(Integer deckSize) {
		this.deckSize = deckSize;
	}

	public String getsVersion() {
		return this.sVersion;
	}

	public void setsVersion(String sVersion) {
		this.sVersion = sVersion;
	}

	public boolean isResourcePackEnabled() {
		return this.resourcePackEnabled;
	}

	public void setResourcePackEnabled(boolean resourcePackEnabled) {
		this.resourcePackEnabled = resourcePackEnabled;
	}

	public String getRewardCommand() {
		return this.rewardCommand;
	}

	public void setRewardCommand(String rewardCommand) {
		this.rewardCommand = rewardCommand;
	}

	public API1711 getApi() {
		return api;
	}

	public void setApi(API1711 api) {
		this.api = api;
	}

}
