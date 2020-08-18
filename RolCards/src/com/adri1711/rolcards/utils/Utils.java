package com.adri1711.rolcards.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.arenas.ArenaRC;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.cards.CreatedCard;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.match.Partida;
import com.adri1711.rolcards.monsters.Monster;
import com.adri1711.rolcards.peticiones.Peticion;
import com.adri1711.util.enums.AMaterials;

import net.md_5.bungee.api.ChatColor;

public class Utils {
	public static Jugador buscaJugador(Player p, RolCards plugin) {
		Jugador res = null;
		for (Jugador j : plugin.getJugadores()) {
			if (j.getP().getName() == p.getName()) {
				res = j;
				break;
			}
		}
		return res;
	}

	public static void pasaTurno(Jugador j, RolCards plugin) {
		Partida part = j.getPartida();
		if (part != null) {
			if (part.getJugador1().equals(j)) {
				part.setNumTurnosPasados(part.getNumTurnosPasados() + 1);
				part.turnoJugador2();
			} else if (part.getJugador2().equals(j)) {
				part.setNumTurnosPasados(part.getNumTurnosPasados() + 1);
				part.turnoJugador1();
			}
		}
	}

	public static void comunicaPerdedor(Jugador p, Partida part, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			Bukkit.getServer().broadcastMessage("§8[§6RolCards§8] §6" + part.devuelveOtroJugador(p).getP().getName()
					+ message.getAnnouncement() + p.getP().getName());
		} else {
			Bukkit.getServer().broadcastMessage("§8[§6RolCards§8] §6" + part.devuelveOtroJugador(p).getP().getName()
					+ message.getAnnouncement() + p.getP().getName());
		}
		if (part.getJugador1().equals(p)) {
			part.getJugador1().getP().teleport(plugin.getSpawnLocation());
			part.getJugador2().getP().teleport(plugin.getSpawnLocation());
			if (plugin.getLanguage().equalsIgnoreCase("es")) {
				part.getJugador1().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.BLUE
						+ part.getJugador2().getP().getName() + ChatColor.GREEN + " gano");
				part.getJugador2().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.BLUE
						+ part.getJugador2().getP().getName() + ChatColor.GREEN + " gano");
			} else {
				part.getJugador1().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.BLUE
						+ part.getJugador2().getP().getName() + ChatColor.GREEN + message.getMatchMatchMsg());
				part.getJugador2().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.BLUE
						+ part.getJugador2().getP().getName() + ChatColor.GREEN + message.getMatchMatchMsg());
			}
			Double eloGanador = plugin.getElo1(part.getJugador2().getP().getName());
			Double eloPerdedor = plugin.getElo1(part.getJugador1().getP().getName());
			Double aSumar = Double.valueOf(eloPerdedor.doubleValue() / eloGanador.doubleValue() * 2.0D);
			if (part.getJugador2().getP().hasPermission("rolcards.vip")) {
				plugin.addElo(part.getJugador2().getP().getName(), Double.valueOf(aSumar.doubleValue() * 2.0D));
			} else {
				plugin.addElo(part.getJugador2().getP().getName(), aSumar);
			}
			plugin.addElo(part.getJugador1().getP().getName(), Double.valueOf(aSumar.doubleValue() * -1.0D));
			if (plugin.isMySQL()) {
				plugin.addStats1(part.getJugador1().getP().getName(), Double.valueOf(0.0D), Double.valueOf(1.0D));
				plugin.addStats1(part.getJugador2().getP().getName(), Double.valueOf(1.0D), Double.valueOf(0.0D));
			}

			if (plugin.getLanguage().equalsIgnoreCase("es")) {
				part.getJugador1().getP().sendMessage("§8[§6RolCards§8] §4Perdiste " + aSumar + " Elo");
				part.getJugador2().getP().sendMessage("§8[§6RolCards§8] §2Ganaste " + aSumar + " Elo");
			} else {
				part.getJugador1().getP()
						.sendMessage("§8[§6RolCards§8] " + ChatColor.RED + message.getMatchLoseMsg() + aSumar + " Elo");
				part.getJugador2().getP().sendMessage(
						"§8[§6RolCards§8] " + ChatColor.GREEN + message.getMatchEloMsg() + aSumar + " Elo");
			}
			Player player = part.getJugador2().getP();
			Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
					plugin.getRewardCommand().replaceAll("%player%", part.getJugador2().getP().getName()));
			part.getJugador2().getP().setHealth(20.0D);
			if (part.getJugador2().getP().hasPermission("rolcards.vip")) {
				RolCards.getEconomy().depositPlayer((OfflinePlayer) player, (plugin.getPrizeAmount() * 2));
				if (plugin.getLanguage().equalsIgnoreCase("es")) {
					part.getJugador2().getP().sendMessage(
							"§8[§6RolCards§8] " + ChatColor.GREEN + "Ganaste " + (plugin.getPrizeAmount() * 2) + "$");
				} else {
					part.getJugador2().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.GREEN
							+ message.getMatchPrizeMsg() + (plugin.getPrizeAmount() * 2) + "$");
				}
			} else {
				RolCards.getEconomy().depositPlayer((OfflinePlayer) player, plugin.getPrizeAmount());
				if (plugin.getLanguage().equalsIgnoreCase("es")) {
					part.getJugador2().getP().sendMessage(
							"§8[§6RolCards§8] " + ChatColor.GREEN + "Ganaste " + plugin.getPrizeAmount() + "$");
				} else {
					part.getJugador2().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.GREEN
							+ message.getMatchPrizeMsg() + plugin.getPrizeAmount() + "$");
				}
			}

			part.getJugador1().getP().setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
			part.getJugador2().getP().setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
			updateScoreboard(part.getJugador1().getP(), part.getJugador1(), null, plugin);
			updateScoreboard(part.getJugador2().getP(), part.getJugador2(), null, plugin);
			part.setAllDefault();
		} else {
			part.getJugador1().getP().teleport(plugin.getSpawnLocation());
			part.getJugador2().getP().teleport(plugin.getSpawnLocation());
			if (plugin.getLanguage().equalsIgnoreCase("es")) {
				part.getJugador1().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.BLUE
						+ part.getJugador1().getP().getName() + ChatColor.GREEN + " gano");
				part.getJugador2().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.BLUE
						+ part.getJugador1().getP().getName() + ChatColor.GREEN + " gano");
			} else {
				part.getJugador1().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.BLUE
						+ part.getJugador1().getP().getName() + ChatColor.GREEN + message.getMatchMatchMsg());
				part.getJugador2().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.BLUE
						+ part.getJugador1().getP().getName() + ChatColor.GREEN + message.getMatchMatchMsg());
			}
			Double eloGanador = plugin.getElo1(part.getJugador1().getP().getName());
			Double eloPerdedor = plugin.getElo1(part.getJugador2().getP().getName());
			Double aSumar = Double.valueOf(eloPerdedor.doubleValue() / eloGanador.doubleValue() * 2.0D);
			if (part.getJugador1().getP().hasPermission("rolcards.vip")) {
				plugin.addElo(part.getJugador1().getP().getName(), Double.valueOf(aSumar.doubleValue() * 2.0D));
			} else {
				plugin.addElo(part.getJugador1().getP().getName(), aSumar);
			}
			plugin.addElo(part.getJugador2().getP().getName(), Double.valueOf(aSumar.doubleValue() * -1.0D));
			if (plugin.isMySQL()) {
				plugin.addStats1(part.getJugador2().getP().getName(), Double.valueOf(0.0D), Double.valueOf(1.0D));
				plugin.addStats1(part.getJugador1().getP().getName(), Double.valueOf(1.0D), Double.valueOf(0.0D));
			}

			Player player = part.getJugador1().getP();
			Bukkit.getServer().dispatchCommand((CommandSender) Bukkit.getConsoleSender(),
					plugin.getRewardCommand().replaceAll("%player%", part.getJugador1().getP().getName()));

			part.getJugador1().getP().setHealth(20.0D);
			if (part.getJugador1().getP().hasPermission("rolcards.vip")) {
				RolCards.getEconomy().depositPlayer((OfflinePlayer) player, (plugin.getPrizeAmount() * 2));
				if (plugin.getLanguage().equalsIgnoreCase("es")) {
					part.getJugador2().getP()
							.sendMessage("§8[§6RolCards§8] " + ChatColor.RED + "Perdiste " + aSumar + " Elo");
					part.getJugador1().getP()
							.sendMessage("§8[§6RolCards§8] " + ChatColor.GREEN + "Ganaste " + aSumar + " Elo");
					part.getJugador1().getP().sendMessage(
							"§8[§6RolCards§8] " + ChatColor.GREEN + "Ganaste " + (plugin.getPrizeAmount() * 2) + "$");
				} else {
					part.getJugador2().getP().sendMessage(
							"§8[§6RolCards§8] " + ChatColor.RED + message.getMatchLoseMsg() + aSumar + " Elo");
					part.getJugador1().getP().sendMessage(
							"§8[§6RolCards§8] " + ChatColor.GREEN + message.getMatchEloMsg() + aSumar + " Elo");
					part.getJugador1().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.GREEN
							+ message.getMatchPrizeMsg() + (plugin.getPrizeAmount() * 2) + "$");
				}
			} else {
				RolCards.getEconomy().depositPlayer((OfflinePlayer) player, plugin.getPrizeAmount());
				if (plugin.getLanguage().equalsIgnoreCase("es")) {
					part.getJugador2().getP()
							.sendMessage("§8[§6RolCards§8] " + ChatColor.RED + "Perdiste " + aSumar + " Elo");
					part.getJugador1().getP()
							.sendMessage("§8[§6RolCards§8] " + ChatColor.GREEN + "Ganaste " + aSumar + " Elo");
					part.getJugador1().getP().sendMessage(
							"§8[§6RolCards§8] " + ChatColor.GREEN + "Ganaste " + plugin.getPrizeAmount() + "$");
				} else {
					part.getJugador2().getP().sendMessage(
							"§8[§6RolCards§8] " + ChatColor.RED + message.getMatchLoseMsg() + aSumar + " Elo");
					part.getJugador1().getP().sendMessage(
							"§8[§6RolCards§8] " + ChatColor.GREEN + message.getMatchEloMsg() + aSumar + " Elo");
					part.getJugador1().getP().sendMessage("§8[§6RolCards§8] " + ChatColor.GREEN
							+ message.getMatchPrizeMsg() + plugin.getPrizeAmount() + "$");
				}
			}
			part.getJugador1().getP().setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
			part.getJugador2().getP().setScoreboard(Bukkit.getServer().getScoreboardManager().getNewScoreboard());
			updateScoreboard(part.getJugador1().getP(), part.getJugador1(), null, plugin);
			updateScoreboard(part.getJugador2().getP(), part.getJugador2(), null, plugin);
			part.setAllDefault();
		}
	}

	public static void setInventarioDefault(Player p, RolCards plugin) {
		p.getInventory().clear();
		p.getEquipment().clear();
		p.updateInventory();
		p.getInventory().setItem(0, plugin.getDesafiador());
		p.getInventory().setItem(4, plugin.getItemTop());
		p.getInventory().setItem(7, plugin.getRequests());
		p.getInventory().setItem(8, plugin.getMenuCartas());
		p.updateInventory();
	}

	public static void deleteInventarioDefault(Player p, RolCards plugin) {
		p.getInventory().removeItem(new ItemStack[] { plugin.getDesafiador() });
		p.getInventory().removeItem(new ItemStack[] { plugin.getItemTop() });
		p.getInventory().removeItem(new ItemStack[] { plugin.getRequests() });
		p.getInventory().removeItem(new ItemStack[] { plugin.getMenuCartas() });
		p.updateInventory();
	}

	public static ItemStack transformaCarta(Card c, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		ItemStack carta = new ItemStack(plugin.getApi().getMaterial(AMaterials.PAPER));
		ItemMeta cartaMeta = carta.getItemMeta();
		cartaMeta.setDisplayName(String.valueOf(c.getCardName().replaceAll("&", "§")) + " §1" + message.getManaCostMsg()
				+ ": " + c.getCardCost());
		List<String> lore = new ArrayList<>();
		if (c.getCardDescriptionLine1() != null && c.getCardDescriptionLine1() != "") {
			lore.add(c.getCardDescriptionLine1().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine2() != null && c.getCardDescriptionLine2() != "") {
			lore.add(c.getCardDescriptionLine2().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine3() != null && c.getCardDescriptionLine3() != "") {
			lore.add(c.getCardDescriptionLine3().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine4() != null && c.getCardDescriptionLine4() != "") {
			lore.add(c.getCardDescriptionLine4().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine5() != null && c.getCardDescriptionLine5() != "") {
			lore.add(c.getCardDescriptionLine5().replaceAll("&", "§"));
		}

		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			lore.add(ChatColor.BLUE + "Coste de mana: " + c.getCardCost());
		} else {
			lore.add(ChatColor.BLUE + message.getManaCostMsg() + c.getCardCost());
		}
		List<String> lore2 = new ArrayList<>();
		for (String s : lore) {
			if (!s.equalsIgnoreCase("") && !s.equalsIgnoreCase(" ")) {
				lore2.add(s);
			}
		}
		cartaMeta.setLore(lore2);
		carta.setItemMeta(cartaMeta);
		return carta;
	}

	public static ItemStack transformaCartaGUI(Card c, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		ItemStack carta = new ItemStack(plugin.getApi().getMaterial(AMaterials.BOOK));
		ItemMeta cartaMeta = carta.getItemMeta();
		cartaMeta.setDisplayName(c.getCardName().replaceAll("&", "§"));
		List<String> lore = new ArrayList<>();
		if (c.getCardDescriptionLine1() != null && c.getCardDescriptionLine1() != "") {
			lore.add(c.getCardDescriptionLine1().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine2() != null && c.getCardDescriptionLine2() != "") {
			lore.add(c.getCardDescriptionLine2().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine3() != null && c.getCardDescriptionLine3() != "") {
			lore.add(c.getCardDescriptionLine3().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine4() != null && c.getCardDescriptionLine4() != "") {
			lore.add(c.getCardDescriptionLine4().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine5() != null && c.getCardDescriptionLine5() != "") {
			lore.add(c.getCardDescriptionLine5().replaceAll("&", "§"));
		}

		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			lore.add(ChatColor.BLUE + "Coste de mana: " + c.getCardCost());
		} else {
			lore.add(ChatColor.BLUE + message.getManaCostMsg() + c.getCardCost());
		}
		List<String> lore2 = new ArrayList<>();
		for (String s : lore) {
			if (!s.equalsIgnoreCase("") && !s.equalsIgnoreCase(" ")) {
				lore2.add(s);
			}
		}
		cartaMeta.setLore(lore2);
		carta.setItemMeta(cartaMeta);
		return carta;
	}

	public static ItemStack transformaCartaShopGUI(Card c, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		ItemStack carta = new ItemStack(plugin.getApi().getMaterial(AMaterials.BOOK));
		ItemMeta cartaMeta = carta.getItemMeta();
		cartaMeta.setDisplayName(c.getCardName().replaceAll("&", "§"));
		List<String> lore = new ArrayList<>();
		if (c.getCardDescriptionLine1() != null && c.getCardDescriptionLine1() != "") {
			lore.add(c.getCardDescriptionLine1().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine2() != null && c.getCardDescriptionLine2() != "") {
			lore.add(c.getCardDescriptionLine2().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine3() != null && c.getCardDescriptionLine3() != "") {
			lore.add(c.getCardDescriptionLine3().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine4() != null && c.getCardDescriptionLine4() != "") {
			lore.add(c.getCardDescriptionLine4().replaceAll("&", "§"));
		}

		if (c.getCardDescriptionLine5() != null && c.getCardDescriptionLine5() != "") {
			lore.add(c.getCardDescriptionLine5().replaceAll("&", "§"));
		}

		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			lore.add(ChatColor.BLUE + "Coste de mana: " + c.getCardCost());
		} else {
			lore.add(String.valueOf(message.getManaCostMsg()) + c.getCardCost());
		}
		lore.add(ChatColor.BLUE + "" + c.getCardPrice() + "$");
		List<String> lore2 = new ArrayList<>();
		for (String s : lore) {
			if (!s.equalsIgnoreCase("") && !s.equalsIgnoreCase(" ")) {
				lore2.add(s);
			}
		}
		cartaMeta.setLore(lore2);
		carta.setItemMeta(cartaMeta);
		return carta;
	}

	public static void buscaPartida(Player aux, Player player, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		Jugador j1 = buscaJugador(aux, plugin);
		Jugador j2 = buscaJugador(player, plugin);
		String language = plugin.getLanguage();
		if (j1 != null && j2 != null) {
			if (j1.getPartida() == null && j2.getPartida() == null) {
				if (j1.getClase() != CardClass.NORMAL && j2.getClase() != CardClass.NORMAL) {
					if (j1.getCartas().size() == plugin.getDeckSize().intValue()
							&& j2.getCartas().size() == plugin.getDeckSize().intValue()) {
						Partida partida = null;
						for (Partida part : plugin.getPartidas()) {
							if (!part.isPlayed()) {
								part.meteJugadores(j1, j2);
								partida = part;
								break;
							}
						}
						if (partida == null) {
							if (language.equalsIgnoreCase("es")) {
								aux.sendMessage("§4Todas las partidas estan ocupadas espera a que termine una");
								player.sendMessage("§4Todas las partidas estan ocupadas espera a que termine una");
							} else {
								aux.sendMessage(message.getMatchRequestsNoMatchMsg());
								player.sendMessage(message.getMatchRequestsNoMatchMsg());
							}

						}
					} else if (language.equalsIgnoreCase("es")) {
						aux.sendMessage("§4Ambos jugadores necesitan tener " + plugin.getDeckSize() + " cartas");
						player.sendMessage("§4Ambos jugadores necesitan tener " + plugin.getDeckSize() + " cartas");
					} else {
						aux.sendMessage(message.getMatchRequestsNoCardsMsg());
						player.sendMessage(message.getMatchRequestsNoCardsMsg());
					}

				} else if (language.equalsIgnoreCase("es")) {
					aux.sendMessage("§4Ambos jugadores deben haber escogido su clase");
					player.sendMessage("§4Ambos jugadores deben haber escogido su clase");
				} else {
					aux.sendMessage(message.getMatchRequestsNoClassMsg());
					player.sendMessage(message.getMatchRequestsNoClassMsg());
				}

			} else if (language.equalsIgnoreCase("es")) {
				player.sendMessage("§4El otro jugador esta ya en una partida");
			} else {
				player.sendMessage(message.getMatchRequestsPlayerOnMatchMsg());
			}
		}
	}

	public static void buscaPartida2(Player player, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		Jugador j1 = buscaJugador(player, plugin);
		String language = plugin.getLanguage();
		if (j1 != null) {
			if (j1.getPartida() == null) {
				if (j1.getClase() != CardClass.NORMAL) {
					if (j1.getCartas().size() == plugin.getDeckSize().intValue()) {
						Partida partida = null;
						boolean segundo = false;
						for (Partida part : plugin.getPartidas()) {
							if (!part.isPlayed() && part.getJugador1() != null && part.getJugador2() == null) {
								part.meteJugador2(j1);
								player.sendMessage(message.getMatchFoundBeginMsg());
								segundo = true;
								partida = part;

								break;
							}
						}
						if (!segundo) {
							for (Partida part : plugin.getPartidas()) {
								if (!part.isPlayed()) {
									if (part.getJugador1() == null)
										part.meteJugador1(j1);
									player.sendMessage(message.getMatchFoundWaitMsg());
									partida = part;
									break;
								}
							}
						}
						if (partida == null) {
							if (language.equalsIgnoreCase("es")) {
								player.sendMessage("§4Todas las partidas estan ocupadas espera a que termine una");
							} else {
								player.sendMessage(message.getMatchRequestsNoMatchMsg());
							}

						}
					} else if (language.equalsIgnoreCase("es")) {
						player.sendMessage("§4Ambos jugadores necesitan tener" + plugin.getDeckSize() + " cartas");
					} else {
						player.sendMessage(message.getMatchRequestsNoCardsMsg());
					}

				} else if (language.equalsIgnoreCase("es")) {
					player.sendMessage("§4Ambos jugadores deben haber escogido su clase");
				} else {
					player.sendMessage(message.getMatchRequestsNoClassMsg());
				}

			} else if (language.equalsIgnoreCase("es")) {
				player.sendMessage("§4El otro jugador esta ya en una partida");
			} else {
				player.sendMessage(message.getMatchRequestsPlayerOnMatchMsg());
			}
		}
	}

	public static int getNumCartas(Jugador j, Card c) {
		int res = 0;
		for (Card car : j.getCartas()) {
			if (car.equals(c)) {
				res++;
			}
		}
		return res;
	}

	public static Card buscaCarta(String displayName, RolCards plugin) {
		Card c = null;
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			int index = displayName.indexOf("Mana");
			if (index != -1)
				displayName = displayName.substring(0, index);
		} else {
			int index = displayName.indexOf(plugin.getMessages().getManaCostMsg());
			if (index != -1)
				displayName = displayName.substring(0, index);
		}
		for (Card carta : plugin.getCartas()) {
			if (displayName.contains(carta.getCardName())) {
				c = carta;
				break;
			}
		}
		if (displayName.contains(plugin.getMageCards().getMageSkill().getCardName())) {
			c = plugin.getMageCards().getMageSkill();
		} else if (displayName.contains(plugin.getHunterCards().getHunterSkill().getCardName())) {
			c = plugin.getHunterCards().getHunterSkill();
		} else if (displayName.contains(plugin.getWarriorCards().getWarriorSkill().getCardName())) {
			c = plugin.getWarriorCards().getWarriorSkill();
		}
		return c;
	}

	public static boolean existePeticion(Player p, Player s) {
		Peticion pe = null;
		for (Peticion pet : RolCards.getPeticiones()) {
			if (pet.getFrom().equals(p) && pet.getTo().equals(s)) {
				pe = pet;
			}
		}
		return (pe != null);
	}

	public static void enviaPeticion(Player p, Player target, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		Peticion pet = new Peticion(p, target);
		RolCards.getPeticiones().add(pet);
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			p.sendMessage("§2Desafio enviado a " + target.getName());
			target.sendMessage("§2Desafio recibido de " + p.getName());
		} else {
			p.sendMessage(String.valueOf(message.getMatchRequestsSentMsg()) + target.getName());
			target.sendMessage(String.valueOf(message.getMatchRequestsReceivedMsg()) + p.getName());
		}
	}

	public static void giveClassSkill(Jugador jugador1, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		ItemStack info = new ItemStack(plugin.getApi().getMaterial(AMaterials.STICK));
		ItemMeta infoMeta = info.getItemMeta();
		List<String> loreaux = new ArrayList<>();
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			infoMeta.setDisplayName("§6Info del enemigo / Menu Mobs");
			loreaux.add("§7Click derecho para la info del enemigo");
			loreaux.add("§7Click izquierdo para abrir el menu de mobs");
			infoMeta.setLore(loreaux);
		} else {
			infoMeta.setDisplayName(message.getInfoItemName());
			infoMeta.setLore(message.getInfoItemLore());
		}
		info.setItemMeta(infoMeta);
		switch (jugador1.getClase()) {
		case WARRIOR:
			jugador1.getP().getInventory().setItem(8,
					transformaCarta(plugin.getWarriorCards().getWarriorSkill(), plugin));
			break;
		case MAGE:
			jugador1.getP().getInventory().setItem(8, transformaCarta(plugin.getMageCards().getMageSkill(), plugin));
			break;
		case HUNTER:
			jugador1.getP().getInventory().setItem(8,
					transformaCarta(plugin.getHunterCards().getHunterSkill(), plugin));
			break;
		}

		jugador1.getP().getInventory().setItem(7, info);
	}

	public static ArenaRC buscaArena(Integer idArena, RolCards plugin) {
		ArenaRC arena = null;
		for (ArenaRC a : plugin.getArenas()) {
			if (a.getNumArena() == idArena) {
				arena = a;

				break;
			}
		}
		return arena;
	}

	public static void cambiaArena(Integer idArena, ArenaRC aren, RolCards plugin) {
		for (ArenaRC a : plugin.getArenas()) {
			if (a.getNumArena() == idArena) {
				int i = plugin.getArenas().indexOf(a);
				plugin.getArenas().set(i, aren);
				break;
			}
		}
	}

	public static void daVidaEnemigo(Player p, Player p2, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			p.sendMessage("§2Tu enemigo tiene " + p2.getHealth() + "/20");
		} else {
			p.sendMessage(String.valueOf(message.getEnemyHealthInfo()) + p2.getHealth() + "/20");
		}
	}

	public static boolean checkInventoryVacio(Player p) {
		boolean res = true;
		int limit = p.getInventory().getSize();
		for (int i = 0; i < limit; i++) {
			if (p.getInventory().getItem(i) != null) {
				res = false;
				break;
			}
		}
		return res;
	}

	public static void updateScoreboard(Player p, Jugador j, String nombre, RolCards plugin) {
		if (plugin.isScoreboardEnabled()) {
			LanguageMessages message = plugin.getMessages();
			Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
			Objective obj = scoreboard.registerNewObjective("aaa", "bbb");
			Player player = p;

			obj.setDisplayName(
					String.valueOf(ChatColor.YELLOW.toString()) + ChatColor.BOLD + plugin.getScoreboardName());

			obj.setDisplaySlot(DisplaySlot.SIDEBAR);
			if (plugin.isMySQL()) {
				Score once = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Elo:");
				Score diez = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + plugin.getElo1(p.getName()));

				Score nueve = obj.getScore("");
				Score ocho = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + message.getScoreboardWinMsg());
				if (plugin.getLanguage().equalsIgnoreCase("es")) {
					ocho = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Ganadas:");
				}

				Score siete = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + plugin.getKills1(p.getName()));
				if (plugin.getKills1(p.getName()) == null) {
					siete = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + "0");
				} else if (plugin.getKills1(p.getName()).doubleValue() == 0.0D) {
					siete = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + "0");
				}
				Score seis = obj.getScore("    ");

				Score cinco = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + message.getScoreboardLoseMsg());
				if (plugin.getLanguage().equalsIgnoreCase("es")) {
					cinco = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Perdidas:");
				}
				Score cuatro = obj
						.getScore(String.valueOf(ChatColor.GREEN.toString()) + plugin.getDeaths1(p.getName()));
				if (plugin.getDeaths1(p.getName()) == null) {
					cuatro = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + "0");
				} else if (plugin.getDeaths1(p.getName()).doubleValue() == 0.0D) {
					cuatro = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + "0");
				}
				Score tres = obj.getScore("                ");
				Score dos = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + message.getScoreboardMoneyMsg());

				if (plugin.getLanguage().equalsIgnoreCase("es")) {
					dos = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Dinero:");
				}
				Score uno = obj.getScore(String.valueOf(ChatColor.GREEN.toString())
						+ RolCards.getEconomy().getBalance((OfflinePlayer) player));
				if (j.getPartida() != null) {
					ocho = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Mana:");
					siete = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + j.getMana());
					cinco = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + message.getScoreboardTurnOfMsg());
					if (plugin.getLanguage().equalsIgnoreCase("es")) {
						cinco = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Turno de:");
					}
					if (nombre != null) {
						cuatro = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + nombre);
					}
				}
				once.setScore(11);
				diez.setScore(10);
				nueve.setScore(9);
				ocho.setScore(8);
				siete.setScore(7);
				seis.setScore(6);
				cinco.setScore(5);
				cuatro.setScore(4);
				tres.setScore(3);
				dos.setScore(2);
				uno.setScore(1);
			} else {
				Score once = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Elo:");
				Score diez = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + plugin.getElo1(p.getName()));
				Score tres = obj.getScore("                ");
				Score dos = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + message.getScoreboardMoneyMsg());
				if (plugin.getLanguage().equalsIgnoreCase("es")) {
					dos = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Dinero:");
				}
				Score uno =null;
				
					if(RolCards.getEconomy()!=null && player!=null){
						uno=obj.getScore(String.valueOf(ChatColor.GREEN.toString())
						+ RolCards.getEconomy().getBalance((OfflinePlayer) player));
					}else{
						uno=obj.getScore(String.valueOf(ChatColor.GREEN.toString())
								+ "0");
					}
					
				if (j.getPartida() != null) {
					Score ocho = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Mana:");
					Score siete = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + j.getMana());
					Score cinco = obj
							.getScore(String.valueOf(ChatColor.WHITE.toString()) + message.getScoreboardTurnOfMsg());
					if (plugin.getLanguage().equalsIgnoreCase("es")) {
						cinco = obj.getScore(String.valueOf(ChatColor.WHITE.toString()) + "Turno de:");
					}
					if (nombre != null) {
						Score cuatro = obj.getScore(String.valueOf(ChatColor.GREEN.toString()) + nombre);
						cuatro.setScore(6);
					}
					ocho.setScore(9);
					siete.setScore(8);
					cinco.setScore(7);
				}

				once.setScore(5);
				diez.setScore(4);
				tres.setScore(3);
				dos.setScore(2);
				uno.setScore(1);
			}
			p.setScoreboard(scoreboard);
		}
	}

	public static void doSkillToPlayer(Jugador j, Jugador enemy, Card c, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		Player p = j.getP();
		String nombreCarta = c.getCardName();
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			int index = nombreCarta.indexOf("Mana");
			if (index != -1)
				nombreCarta = nombreCarta.substring(0, index);
		} else {
			int index = nombreCarta.indexOf(plugin.getMessages().getManaCostMsg());
			if (index != -1)
				nombreCarta = nombreCarta.substring(0, index);
		}
		if (nombreCarta.contains(plugin.getMageCards().getMageSkill().getCardName())) {
			if (!j.isUsedSkill()) {
				j.setMana(j.getMana() - c.getCardCost().intValue());
				j.setUsedSkill(true);
				Skills.makeMageSkillEffect(plugin,j, enemy);
				enviaMensajes(plugin, c, p, enemy, nombreCarta, message);

			} else if (plugin.getLanguage().equalsIgnoreCase("es")) {
				p.sendMessage(ChatColor.RED + "Ya usaste tu habilidad de clase");
			} else {
				p.sendMessage(ChatColor.RED + message.getAlreadyUsedSkillMsg());
			}

			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getArcaneIntellect().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeArcaneIntellectEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getBurn().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeBurnEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getDivineHealer().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeDivineHealerEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getFearOfHeights().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeFearOfHeightsEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getLifeChange().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeLifeChangeEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getManaSupply().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeManaSupplyEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getNewLife().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeNewLifeEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getNuclearBomb().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeNuclearBombEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getTurningTheTables().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeTurningTheTablesEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled()) {
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			}
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getHunterSkill().getCardName())) {
			if (!j.isUsedSkill()) {
				j.setMana(j.getMana() - c.getCardCost().intValue());
				j.setUsedSkill(true);
				enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
				Skills.makeHunterSkillEffect(plugin,j, enemy);
			} else if (plugin.getLanguage().equalsIgnoreCase("es")) {
				p.sendMessage(ChatColor.RED + "Ya usaste tu habilidad de clase");
			} else {
				p.sendMessage(ChatColor.RED + message.getAlreadyUsedSkillMsg());
			}

			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getDivineBow().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeDivineBowEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getFinishHim().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeFinishHimEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getFirstAid().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeFirstAidEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getGetACopy().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeGetACopyEffect(j, enemy, plugin);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getInstantPoison().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeInstantPoisonEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getLegendaryBow().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeLegendaryBowEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getMortalTrap().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeMortalTrapEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getPoisonGas().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makePoisonGasEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getToTheHead().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeToTheHeadEffect(plugin,j, enemy);
			if (plugin.isScoreboardEnabled()) {
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			}
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getWarriorSkill().getCardName())) {
			if (!j.isUsedSkill()) {
				j.setMana(j.getMana() - c.getCardCost().intValue());
				j.setUsedSkill(true);
				enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
				Skills.makeWarriorSkillEffect(plugin,j, enemy);
			} else if (plugin.getLanguage().equalsIgnoreCase("es")) {
				p.sendMessage(ChatColor.RED + "Ya usaste tu habilidad de clase");
			} else {
				p.sendMessage(ChatColor.RED + message.getAlreadyUsedSkillMsg());
			}

			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getBerserker().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeBerserkerEffect(j, enemy, plugin);
			if (plugin.isScoreboardEnabled())
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getBrutalHit().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeBrutalHitEffect(plugin,j, enemy);

			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getInsanity().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeInsanityEffect(j, enemy, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getIronBall().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeIronBallEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getDisarm().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeDisarmEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getSnatchAway().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeSnatchAwayEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getLastTry().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeLastTryEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getBearScratch().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeBearScratchEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getElephantStomp().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeElephantStompEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getEquality().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeEqualityEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getFlame().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeFlameEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getGreatWeapon().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeGreatWeaponEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getIncantation().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeIncantationEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getInsectBite().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeInsectBiteEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getMajorHealing().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeMajorHealingEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getManaSet().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeManaSetEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getMinorHealing().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeMinorHealingEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getPresentForYou().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makePresentForYouEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getShieldBearer().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeShieldBearerEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getTakeARest().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeTakeARestEffect(j, enemy, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getTigerBite().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeTigerBiteEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getWoodWeapon().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
			Skills.makeWoodWeaponEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (c instanceof CreatedCard) {
			CreatedCard cc = (CreatedCard) c;
			switch (cc.getCardEffect()) {
			case DAMAGE:
				p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
				p.updateInventory();
				j.setMana(j.getMana() - c.getCardCost().intValue());
				enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
				Skills.makeDamageEffect(plugin,j, enemy, cc);
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
				break;
			case HEAL:
				p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
				p.updateInventory();
				j.setMana(j.getMana() - c.getCardCost().intValue());
				enviaMensajes(plugin, c, p, enemy, nombreCarta, message);
				Skills.makeHealEffect(plugin,j, enemy, cc);
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
				break;
			}
			return;
		}
	}

	private static void enviaMensajes(RolCards plugin, Card c, Player p, Jugador enemy, String nombreCarta,
			LanguageMessages message) {
		String[] descripcion;
		if (!c.getCardDescriptionLine2().equals("")) {
			if (!c.getCardDescriptionLine3().equals("")) {
				if (!c.getCardDescriptionLine4().equals("")) {
					if (!c.getCardDescriptionLine5().equals("")) {
						descripcion = new String[] { c.getCardDescriptionLine1(), c.getCardDescriptionLine2(),
								c.getCardDescriptionLine3(), c.getCardDescriptionLine4(), c.getCardDescriptionLine5() };
					} else {
						descripcion = new String[] { c.getCardDescriptionLine1(), c.getCardDescriptionLine2(),
								c.getCardDescriptionLine3(), c.getCardDescriptionLine4() };
					}
				} else {
					descripcion = new String[] { c.getCardDescriptionLine1(), c.getCardDescriptionLine2(),
							c.getCardDescriptionLine3() };
				}
			} else {
				descripcion = new String[] { c.getCardDescriptionLine1(), c.getCardDescriptionLine2() };
			}
		} else {
			descripcion = new String[] { c.getCardDescriptionLine1() };
		}
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			Utils.mandaDescCarta(p, enemy, nombreCarta, descripcion, plugin);

		} else {
			Utils.mandaDescCarta2(p, enemy, nombreCarta, descripcion, message, plugin);
		}
	}

	public static void doSkillToEntity(Jugador j, Jugador enemy, Monster mon, Card c, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		Player p = j.getP();
		String nombreCarta = c.getCardName();
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			int index = nombreCarta.indexOf("Mana");
			if (index != -1)
				nombreCarta = nombreCarta.substring(0, index);
		} else {
			int index = nombreCarta.indexOf(plugin.getMessages().getManaCostMsg());
			if (index != -1)
				nombreCarta = nombreCarta.substring(0, index);
		}
		if (nombreCarta.contains(plugin.getMageCards().getMageSkill().getCardName())) {
			if (!j.isUsedSkill()) {
				j.setMana(j.getMana() - c.getCardCost().intValue());
				j.setUsedSkill(true);
				Skills.makeMageSkillEffect(plugin,j, enemy, mon);
				enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);

			} else if (plugin.getLanguage().equalsIgnoreCase("es")) {
				p.sendMessage(ChatColor.RED + "Ya usaste tu habilidad de clase");
			} else {
				p.sendMessage(ChatColor.RED + message.getAlreadyUsedSkillMsg());
			}

			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getArcaneIntellect().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeArcaneIntellectEffect(plugin,j, enemy);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getBurn().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeBurnEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getDivineHealer().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeDivineHealerEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getFearOfHeights().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeFearOfHeightsEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getLifeChange().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeLifeChangeEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getManaSupply().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeManaSupplyEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getNewLife().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeNewLifeEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getNuclearBomb().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeNuclearBombEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getMageCards().getTurningTheTables().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeTurningTheTablesEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getHunterSkill().getCardName())) {
			if (!j.isUsedSkill()) {
				j.setMana(j.getMana() - c.getCardCost().intValue());
				j.setUsedSkill(true);
				enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
				Skills.makeHunterSkillEffect(plugin,j, enemy);
			} else if (plugin.getLanguage().equalsIgnoreCase("es")) {
				p.sendMessage(ChatColor.RED + "Ya usaste tu habilidad de clase");
			} else {
				p.sendMessage(ChatColor.RED + message.getAlreadyUsedSkillMsg());
			}

			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getDivineBow().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeDivineBowEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getFinishHim().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeFinishHimEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getFirstAid().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeFirstAidEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getGetACopy().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeGetACopyEffect(j, enemy, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getInstantPoison().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeInstantPoisonEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getLegendaryBow().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeLegendaryBowEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getMortalTrap().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeMortalTrapEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getPoisonGas().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makePoisonGasEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getHunterCards().getToTheHead().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeToTheHeadEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getWarriorSkill().getCardName())) {
			if (!j.isUsedSkill()) {
				j.setMana(j.getMana() - c.getCardCost().intValue());
				j.setUsedSkill(true);
				enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
				Skills.makeWarriorSkillEffect(j, enemy, mon, plugin);
			} else if (plugin.getLanguage().equalsIgnoreCase("es")) {
				p.sendMessage(ChatColor.RED + "Ya usaste tu habilidad de clase");
			} else {
				p.sendMessage(ChatColor.RED + message.getAlreadyUsedSkillMsg());
			}

			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getBerserker().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeBerserkerEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getBrutalHit().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeBrutalHitEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getInsanity().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeInsanityEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getIronBall().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeIronBallEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getDisarm().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeDisarmEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getSnatchAway().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeSnatchAwayEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getWarriorCards().getLastTry().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeLastTryEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getBearScratch().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeBearScratchEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getElephantStomp().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeElephantStompEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getEquality().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeEqualityEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getFlame().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeFlameEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getGreatWeapon().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeGreatWeaponEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getIncantation().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeIncantationEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getInsectBite().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeInsectBiteEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getMajorHealing().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeMajorHealingEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getManaSet().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeManaSetEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getMinorHealing().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeMinorHealingEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getPresentForYou().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makePresentForYouEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getShieldBearer().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeShieldBearerEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getTakeARest().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeTakeARestEffect(j, enemy, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getTigerBite().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeTigerBiteEffect(j, enemy, mon, plugin);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (nombreCarta.contains(plugin.getNormalCards().getWoodWeapon().getCardName())) {
			p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
			p.updateInventory();
			j.setMana(j.getMana() - c.getCardCost().intValue());
			enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
			Skills.makeWoodWeaponEffect(plugin,j, enemy, mon);
			updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
			return;
		}
		if (c instanceof CreatedCard) {
			CreatedCard cc = (CreatedCard) c;
			switch (cc.getCardEffect()) {
			case DAMAGE:
				p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
				p.updateInventory();
				j.setMana(j.getMana() - c.getCardCost().intValue());
				enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
				Skills.makeDamageEffect(j, enemy, cc, mon, plugin);
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
				break;
			case HEAL:
				p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
				p.updateInventory();
				j.setMana(j.getMana() - c.getCardCost().intValue());
				enviaMensajes2(plugin, p, enemy, mon, nombreCarta, c, message);
				Skills.makeHealEffect(j, enemy, cc, mon, plugin);
				updateScoreboard(j.getP(), j, j.getP().getName(), plugin);
				break;
			}
			return;
		}
	}

	private static void enviaMensajes2(RolCards plugin, Player p, Jugador enemy, Monster mon, String nombreCarta,
			Card c, LanguageMessages message) {
		String[] descripcion;
		if (!c.getCardDescriptionLine2().equals("")) {
			if (!c.getCardDescriptionLine3().equals("")) {
				if (!c.getCardDescriptionLine4().equals("")) {
					if (!c.getCardDescriptionLine5().equals("")) {
						descripcion = new String[] { c.getCardDescriptionLine1(), c.getCardDescriptionLine2(),
								c.getCardDescriptionLine3(), c.getCardDescriptionLine4(), c.getCardDescriptionLine5() };
					} else {
						descripcion = new String[] { c.getCardDescriptionLine1(), c.getCardDescriptionLine2(),
								c.getCardDescriptionLine3(), c.getCardDescriptionLine4() };
					}
				} else {
					descripcion = new String[] { c.getCardDescriptionLine1(), c.getCardDescriptionLine2(),
							c.getCardDescriptionLine3() };
				}
			} else {
				descripcion = new String[] { c.getCardDescriptionLine1(), c.getCardDescriptionLine2() };
			}
		} else {
			descripcion = new String[] { c.getCardDescriptionLine1() };
		}
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			Utils.mandaDescMonster(p, enemy, mon, nombreCarta, descripcion, plugin);

		} else {
			Utils.mandaDescMonster2(p, enemy, mon, nombreCarta, descripcion, message, plugin);
		}
	}

	public static void actualizaSalud(Jugador j, Jugador enemy, Monster mon, Double saludRes) {
		mon.setSalud(saludRes);
		mon.getMonsterType()
				.setCustomName(String.valueOf(mon.getName()) + " " + mon.getDanio() + "☢  " + mon.getSalud() + "♥");
	}

	public static void mataMonstruo(Jugador j, Jugador enemy, Monster mon, RolCards plugin) {
		LanguageMessages message = plugin.getMessages();
		enemy.getMonstruos().remove(enemy.getMonstruos().indexOf(mon));
		mon.getMonsterType().remove();
		if (plugin.getLanguage().equalsIgnoreCase("es")) {
			j.getP().sendMessage(ChatColor.RED + mon.getName() + " murio");
			enemy.getP().sendMessage(ChatColor.RED + mon.getName() + " murio");
		} else {
			j.getP().sendMessage(ChatColor.RED + mon.getName() + message.getEntityDeathMsg());
			enemy.getP().sendMessage(ChatColor.RED + mon.getName() + message.getEntityDeathMsg());
		}
	}

	public static Monster buscaMonstruo(Jugador j, String s) {
		Monster mon = null;
		for (Monster m : j.getMonstruos()) {
			if (m.getMonsterType().getUniqueId().toString().equalsIgnoreCase(s)) {
				mon = m;
				break;
			}
		}
		return mon;
	}

	public static Integer buscaLugarLibre(Jugador j) {
		int lugar = -1;
		for (int i = 0; i < 5; i++) {
			boolean res = false;
			for (Monster m : j.getMonstruos()) {
				if (m.getLugar() == i) {
					res = true;
				}
			}
			if (!res) {
				lugar = i;
				break;
			}
		}
		return Integer.valueOf(lugar);
	}

	public static Sound buscaSonido(String s, String s2) {
		Sound sound = null;
		for (Sound sou : Sound.values()) {
			if (sou.toString().contains(s) && sou.toString().contains(s2)) {
				sound = sou;
				break;
			}
		}
		if (sound == null) {
			sound = Sound.values()[0];
		}
		return sound;
	}

	public static Effect buscaEffect(String s, String s2) {
		Effect sound = null;
		for (Effect sou : Effect.values()) {
			if (sou.toString().contains(s) && sou.toString().contains(s2)) {
				sound = sou;
				break;
			}
		}
		if (sound == null) {
			sound = Effect.values()[0];
		}
		return sound;
	}

	

	public static EntityType buscaEntityType(String s, String s2) {
		EntityType sound = null;
		for (EntityType sou : EntityType.values()) {
			if (sou.toString().contains(s) && sou.toString().contains(s2)) {
				sound = sou;
				break;
			}
		}
		return sound;
	}

	public static Sound buscaSonido2(String s, String s2) {
		Sound sound = null;
		for (Sound sou : Sound.values()) {
			if (sou.toString().contains(s) || sou.toString().contains(s2)) {
				sound = sou;
				break;
			}
		}
		return sound;
	}

	public static void mandaDescCarta(Player p, Jugador enemy, String nombreCarta, String[] descripcion,
			RolCards plugin) {

		plugin.getApi().send((CommandSender) p, ChatColor.GREEN + p.getName() + " uso ", nombreCarta,
				Arrays.asList(descripcion), null, "");
		plugin.getApi().send((CommandSender) enemy.getP(), ChatColor.GREEN + p.getName() + " uso ", nombreCarta,
				Arrays.asList(descripcion), null, "");
	}

	public static void mandaDescCarta2(Player p, Jugador enemy, String nombreCarta, String[] descripcion,
			LanguageMessages message, RolCards plugin) {

		plugin.getApi().send((CommandSender) p, ChatColor.GREEN + p.getName() + message.getUsingCardMsg() + " ",
				nombreCarta, Arrays.asList(descripcion), null, "");
		plugin.getApi().send((CommandSender) enemy.getP(),
				ChatColor.GREEN + p.getName() + message.getUsingCardMsg() + " ", nombreCarta,
				Arrays.asList(descripcion), null, "");
	}

	public static void usaTitle(Player p, String title, String subtitle, RolCards plugin) {
		plugin.getApi().usaTitle(p, title, subtitle);
	}

	public static void mandaDescMonster(Player p, Jugador enemy, Monster mon, String nombreCarta, String[] descripcion,
			RolCards plugin) {

		plugin.getApi().send((CommandSender) p, ChatColor.GREEN + p.getName() + " uso ", nombreCarta,
				Arrays.asList(descripcion), null, ChatColor.GREEN + " en " + mon.getName());
		plugin.getApi().send((CommandSender) enemy.getP(), ChatColor.GREEN + p.getName() + " uso ", nombreCarta,
				Arrays.asList(descripcion), null, ChatColor.GREEN + " en " + mon.getName());

	}

	public static void mandaDescMonster2(Player p, Jugador enemy, Monster mon, String nombreCarta, String[] descripcion,
			LanguageMessages message, RolCards plugin) {

		plugin.getApi().send((CommandSender) p, ChatColor.GREEN + p.getName() + message.getUsingCardMsg(), nombreCarta,
				Arrays.asList(descripcion), null, ChatColor.GREEN + " en " + mon.getName());
		plugin.getApi().send((CommandSender) enemy.getP(), ChatColor.GREEN + p.getName() + " uso ", nombreCarta,
				Arrays.asList(descripcion), null, ChatColor.GREEN + message.getUsingCardTargetMsg() + mon.getName());

	}

}
