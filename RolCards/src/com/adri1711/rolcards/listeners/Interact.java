package com.adri1711.rolcards.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CreatedCard;
import com.adri1711.rolcards.cards.hunter.HunterCards;
import com.adri1711.rolcards.cards.mage.MageCards;
import com.adri1711.rolcards.cards.normal.NormalCards;
import com.adri1711.rolcards.cards.warrior.WarriorCards;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.utils.Skills;
import com.adri1711.rolcards.utils.Utils;
import com.adri1711.util.enums.AMaterials;

import net.md_5.bungee.api.ChatColor;

public class Interact implements Listener {
	private RolCards plugin;
	private MageCards mc;
	private HunterCards hc;
	private WarriorCards wc;
	private NormalCards nc;
	private List<Card> cartas;
	private LanguageMessages message;

	public Interact(RolCards plugin2) {
		this.plugin = plugin2;
		this.mc = plugin2.getMageCards();
		this.hc = plugin2.getHunterCards();
		this.wc = plugin2.getWarriorCards();
		this.cartas = plugin2.getCartas();
		this.nc = plugin2.getNormalCards();
	}

	@EventHandler
	public void onChalleng(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().getType() != EntityType.PLAYER)
			return;
		Player p = e.getPlayer();
		Jugador j = Utils.buscaJugador(p, this.plugin);
		if (j.isPlaying()) {
			if (this.message == null) {
				this.message = this.plugin.getMessages();
			}

			Player target = (Player) e.getRightClicked();
			if (p.getItemInHand().getType() == plugin.getApi().getMaterial(AMaterials.BLAZE_ROD)
					&& p.getItemInHand().hasItemMeta()) {
				if (!Utils.existePeticion(p, target)) {
					Utils.enviaPeticion(p, target, this.plugin);
				} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
					p.sendMessage(ChatColor.RED + "Este desafio ya existe, espera hasta que acepte");
				} else {
					p.sendMessage(ChatColor.RED + this.message.getAlreadyExistChallenge());
				}
			}
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent evt) {
		if (this.message == null) {
			this.message = this.plugin.getMessages();
		}
		if (evt.getAction() == Action.LEFT_CLICK_AIR) {
			Player p = evt.getPlayer();
			Jugador j = Utils.buscaJugador(p, this.plugin);
			if (j.isPlaying()) {
				if (evt.getItem() != null && evt.getItem().getType() != null) {
					if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.STICK)) {
						if (j.getPartida() != null) {
							if (j.isTurn()) {
								CardGUI.openMobsGUI(j);
							} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
								p.sendMessage(ChatColor.RED + "Espera tu turno");
							} else {
								p.sendMessage(ChatColor.RED + this.message.getEntityWaitMsg());
							}

						}
					} else if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.BLAZE_ROD)) {
						Utils.buscaPartida2(p, this.plugin);
					}

				}
			}
		} else if (evt.getAction() == Action.LEFT_CLICK_BLOCK) {
			Player p = evt.getPlayer();
			Jugador j = Utils.buscaJugador(p, this.plugin);
			if (j.isPlaying()) {
				if (evt.getItem() != null && evt.getItem().getType() != null) {
					if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.STICK)) {
						if (j.getPartida() != null) {
							if (j.isTurn()) {
								CardGUI.openMobsGUI(j);
							} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
								p.sendMessage(ChatColor.RED + "Espera tu turno");
							} else {
								p.sendMessage(ChatColor.RED + this.message.getEntityWaitMsg());
							}

						}
					} else if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.BLAZE_ROD)) {
						Utils.buscaPartida2(p, this.plugin);
					}
				}

				if (j.isTurn() && evt.getClickedBlock() != null
						&& evt.getClickedBlock().getType() == plugin.getApi().getMaterial(AMaterials.IRON_BLOCK)) {
					Utils.pasaTurno(j, this.plugin);

				}
			}

		} else if (evt.getAction() == Action.RIGHT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = evt.getPlayer();
			Jugador j = Utils.buscaJugador(p, this.plugin);
			if (j.isPlaying() && evt.getItem() != null) {
				if (evt.getItem().getType() != null) {
					if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.PAPER)) {
						if (evt.getItem().hasItemMeta()) {
							if (j.isTurn()) {
								Jugador enemy = j.getPartida().devuelveOtroJugador(j);
								ItemMeta carta = evt.getItem().getItemMeta();
								String nombreCarta = carta.getDisplayName();
								Card c = Utils.buscaCarta(nombreCarta, this.plugin);
								if (c != null) {
									nombreCarta = c.getCardName();
								}
								if (j.getMana() >= c.getCardCost().intValue()) {
									if (nombreCarta
											.equals(this.plugin.getMageCards().getArcaneIntellect().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeArcaneIntellectEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getMageCards().getBurn().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeBurnEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getMageCards().getLifeChange().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeLifeChangeEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getMageCards().getManaSupply().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeManaSupplyEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getMageCards().getNewLife().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeNewLifeEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getHunterCards().getHunterSkill().getCardName())) {
										if (!j.isUsedSkill()) {
											j.setMana(j.getMana() - c.getCardCost().intValue());
											j.setUsedSkill(true);
											enviaMensajes(nombreCarta, c, p, enemy);
											Skills.makeHunterSkillEffect(plugin, j, enemy);
										} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
											p.sendMessage(ChatColor.RED + "Ya usaste tu habilidad de clase");
										} else {
											p.sendMessage(ChatColor.RED + "You have already used your skill");
										}

										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getHunterCards().getDivineBow().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeDivineBowEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getHunterCards().getGetACopy().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeGetACopyEffect(j, enemy, this.plugin);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getHunterCards().getInstantPoison().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeInstantPoisonEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getHunterCards().getLegendaryBow().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeLegendaryBowEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getHunterCards().getPoisonGas().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makePoisonGasEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getWarriorCards().getDisarm().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeDisarmEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getWarriorCards().getSnatchAway().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeSnatchAwayEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getWarriorCards().getIronBall().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeIronBallEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getNormalCards().getEquality().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeEqualityEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getNormalCards().getFlame().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeFlameEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getNormalCards().getGreatWeapon().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeGreatWeaponEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getNormalCards().getManaSet().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeManaSetEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getNormalCards().getShieldBearer().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeShieldBearerEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getNormalCards().getTakeARest().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeTakeARestEffect(j, enemy, this.plugin);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getNormalCards().getWoodWeapon().getCardName())) {
										p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
										p.updateInventory();
										j.setMana(j.getMana() - c.getCardCost().intValue());
										enviaMensajes(nombreCarta, c, p, enemy);
										Skills.makeWoodWeaponEffect(plugin, j, enemy);
										Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										return;
									}
									if (nombreCarta.equals(this.plugin.getNormalCards().getVampire().getCardName())) {
										if (j.getMonstruos().size() != 5) {
											p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
											p.updateInventory();
											j.setMana(j.getMana() - c.getCardCost().intValue());
											enviaMensajes(nombreCarta, c, p, enemy);
											Skills.invokesVampire(j, this.plugin);
											Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);

										} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
											p.sendMessage(ChatColor.GREEN + p.getName() + " ya tienes 5 monstruos ");
										} else {

											p.sendMessage(
													ChatColor.GREEN + p.getName() + this.message.getMonsterFiveMsg());
										}

										return;
									}
									if (nombreCarta
											.equals(this.plugin.getHunterCards().getLealCompanion().getCardName())) {
										if (j.getMonstruos().size() != 5) {
											p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
											p.updateInventory();
											j.setMana(j.getMana() - c.getCardCost().intValue());
											enviaMensajes(nombreCarta, c, p, enemy);
											Skills.invokesLealCompanion(j, this.plugin);
											Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);

										} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
											p.sendMessage(ChatColor.GREEN + p.getName() + " ya tienes 5 monstruos ");
										} else {

											p.sendMessage(
													ChatColor.GREEN + p.getName() + this.message.getMonsterFiveMsg());
										}

										return;
									}
									if (nombreCarta.equals(this.plugin.getWarriorCards().getBeast().getCardName())) {
										if (j.getMonstruos().size() != 5) {
											p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
											p.updateInventory();
											j.setMana(j.getMana() - c.getCardCost().intValue());
											enviaMensajes(nombreCarta, c, p, enemy);
											Skills.invokesBeast(j, this.plugin);
											Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);

										} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
											p.sendMessage(ChatColor.GREEN + p.getName() + " ya tienes 5 monstruos ");
										} else {

											p.sendMessage(
													ChatColor.GREEN + p.getName() + this.message.getMonsterFiveMsg());
										}

										return;
									}
									if (nombreCarta
											.equals(this.plugin.getNormalCards().getExiledZombie().getCardName())) {
										if (j.getMonstruos().size() != 5) {
											p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
											p.updateInventory();
											j.setMana(j.getMana() - c.getCardCost().intValue());
											enviaMensajes(nombreCarta, c, p, enemy);
											Skills.invokesExiledZombie(j, this.plugin);
											Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
											p.sendMessage(ChatColor.GREEN + p.getName() + " ya tienes 5 monstruos ");
										} else {

											p.sendMessage(
													ChatColor.GREEN + p.getName() + this.message.getMonsterFiveMsg());
										}

										return;
									}
									if (nombreCarta.equals(this.plugin.getWarriorCards().getLimo().getCardName())) {
										if (j.getMonstruos().size() != 5) {
											p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
											p.updateInventory();
											j.setMana(j.getMana() - c.getCardCost().intValue());
											enviaMensajes(nombreCarta, c, p, enemy);
											Skills.invokesSlime(j, this.plugin);
											Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
											p.sendMessage(ChatColor.GREEN + p.getName() + " ya tienes 5 monstruos ");
										} else {

											p.sendMessage(
													ChatColor.GREEN + p.getName() + this.message.getMonsterFiveMsg());
										}

										return;
									}
									if (nombreCarta.equals(this.plugin.getMageCards().getWitchCat().getCardName())) {
										if (j.getMonstruos().size() != 5) {
											p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
											p.updateInventory();
											j.setMana(j.getMana() - c.getCardCost().intValue());
											enviaMensajes(nombreCarta, c, p, enemy);
											Skills.invokesCat(j, this.plugin);
											Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
										} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
											p.sendMessage(ChatColor.GREEN + p.getName() + " ya tienes 5 monstruos ");
										} else {

											p.sendMessage(
													ChatColor.GREEN + p.getName() + this.message.getMonsterFiveMsg());
										}

										return;
									}
									if (nombreCarta.equals(this.plugin.getMageCards().getMageSkill().getCardName())) {
										CardGUI.openFriendlyTargetGUI(j, enemy, c);
										return;
									}
									if (nombreCarta.equals(this.plugin.getHunterCards().getFirstAid().getCardName())) {
										CardGUI.openFriendlyTargetGUI(j, enemy, c);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getNormalCards().getMinorHealing().getCardName())) {
										CardGUI.openFriendlyTargetGUI(j, enemy, c);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getNormalCards().getMajorHealing().getCardName())) {
										CardGUI.openFriendlyTargetGUI(j, enemy, c);
										return;
									}
									if (nombreCarta
											.equals(this.plugin.getMageCards().getDivineHealer().getCardName())) {
										CardGUI.openFriendlyTargetGUI(j, enemy, c);
										return;
									}
									if (c instanceof CreatedCard) {
										CreatedCard cc = (CreatedCard) c;
										switch (cc.getCardEffect()) {
										case DAMAGE:
											CardGUI.openTargetGUI(j, enemy, (Card) cc);
											break;
										case HEAL:
											CardGUI.openFriendlyTargetGUI(j, enemy, (Card) cc);
											break;
										case DRAW:
											p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
											p.updateInventory();
											j.setMana(j.getMana() - c.getCardCost().intValue());
											enviaMensajes(nombreCarta, c, p, enemy);
											Skills.makeDrawEffect(plugin, j, enemy, cc);
											Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
											break;

										case SPAWN:
											if (j.getMonstruos().size() != 5) {
												p.getInventory().removeItem(new ItemStack[] { p.getItemInHand() });
												p.updateInventory();
												j.setMana(j.getMana() - c.getCardCost().intValue());
												enviaMensajes(nombreCarta, c, p, enemy);
												Skills.makeSpawnEffect(plugin, j, enemy, cc);
												Utils.updateScoreboard(j.getP(), j, j.getP().getName(), this.plugin);
												break;
											}
											if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
												p.sendMessage(
														ChatColor.GREEN + p.getName() + " ya tienes 5 monstruos ");
												break;
											}
											p.sendMessage(
													ChatColor.GREEN + p.getName() + this.message.getMonsterFiveMsg());
											break;
										}

										return;
									}
									CardGUI.openTargetGUI(j, enemy, c);

									return;
								}

								if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
									p.sendMessage(ChatColor.RED + "No tienes suficiente mana para esto (Tienes "
											+ j.getMana() + ")");
								} else {
									p.sendMessage(ChatColor.RED + this.message.getNoEnoughMana() + j.getMana() + ")");
								}

							} else if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
								p.sendMessage(ChatColor.RED + "Espera tu turno");
							} else {
								p.sendMessage(ChatColor.RED + this.message.getEntityWaitMsg());
							}

						}
					} else if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.EMERALD)) {
						if (evt.getItem().hasItemMeta())
							CardGUI.openGUI(evt.getPlayer());
					} else if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.DIAMOND)) {
						if (evt.getItem().hasItemMeta())
							CardGUI.openChallengGUI(evt.getPlayer());
					} else if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.BLAZE_POWDER)) {
						if (evt.getItem().hasItemMeta())
							this.plugin.getTop10(p);
					} else if (evt.getItem().getType() == plugin.getApi().getMaterial(AMaterials.STICK)
							&& evt.getItem().hasItemMeta() && j.getPartida() != null) {
						Jugador j2 = j.getPartida().devuelveOtroJugador(j);
						Utils.daVidaEnemigo(j.getP(), j2.getP(), this.plugin);
					}
				}
			}
		}
	}

	private void enviaMensajes(String nombreCarta, Card c, Player p, Jugador enemy) {
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
		if (this.plugin.getLanguage().equalsIgnoreCase("es")) {
			Utils.mandaDescCarta(p, enemy, nombreCarta, descripcion, plugin);

		} else {
			Utils.mandaDescCarta2(p, enemy, nombreCarta, descripcion, this.message, plugin);
		}
	}

	public RolCards getPlugin() {
		return this.plugin;
	}

	public void setPlugin(RolCards plugin) {
		this.plugin = plugin;
	}

	public MageCards getMc() {
		return this.mc;
	}

	public void setMc(MageCards mc) {
		this.mc = mc;
	}

	public HunterCards getHc() {
		return this.hc;
	}

	public void setHc(HunterCards hc) {
		this.hc = hc;
	}

	public WarriorCards getWc() {
		return this.wc;
	}

	public void setWc(WarriorCards wc) {
		this.wc = wc;
	}

	public List<Card> getCartas() {
		return this.cartas;
	}

	public void setCartas(List<Card> cartas) {
		this.cartas = cartas;
	}

	public NormalCards getNc() {
		return this.nc;
	}

	public void setNc(NormalCards nc) {
		this.nc = nc;
	}
}
