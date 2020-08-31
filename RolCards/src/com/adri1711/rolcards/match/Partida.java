package com.adri1711.rolcards.match;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.arenas.ArenaRC;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.language.LanguageMessages;
import com.adri1711.rolcards.monsters.Monster;
import com.adri1711.rolcards.utils.Utils;
import java.util.Random;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class Partida {
	private ArenaRC arena;
	private Jugador jugador1;
	private Jugador jugador2;
	private RolCards plugin;
	private BukkitTask sched;
	private boolean played;
	private int mana;
	private int numTurnosPasados;
	private LanguageMessages message;

	public Partida(ArenaRC arena, RolCards plugin) {
		this.arena = arena;
		this.jugador1 = null;
		this.jugador2 = null;
		this.plugin = plugin;
		this.played = false;
		this.mana = 0;
		this.message = plugin.getMessages();
	}

	public void comienzaPartida() {
		this.jugador1.setPartida(this);
		this.jugador2.setPartida(this);
		Player p1 = this.jugador1.getP();
		Player p2 = this.jugador2.getP();
		p1.setGameMode(GameMode.SURVIVAL);
		p2.setGameMode(GameMode.SURVIVAL);
		p1.setHealth(20.0D);
		p2.setHealth(20.0D);
		p1.getEquipment().clear();
		p1.getEquipment().setChestplate(null);
		p2.getEquipment().setChestplate(null);
		p2.getEquipment().clear();
		p1.getInventory().clear();
		p2.getInventory().clear();
		p1.updateInventory();
		p2.updateInventory();
		p1.teleport(this.arena.getSpawn1());
		p2.teleport(this.arena.getSpawn2());
		setPlayed(true);
		Utils.giveClassSkill(this.jugador1, this.plugin);
		Utils.giveClassSkill(this.jugador2, this.plugin);
		daCartas();
	}

	public void daCartas() {
		Random r = new Random();
		for (int i = 0; i < 3; i++) {
			int carta1 = r.nextInt(this.jugador1.getCartas().size());
			ItemStack carta = Utils.transformaCarta(this.jugador1.getCartas().get(carta1), this.plugin);
			this.jugador1.getCartas().remove(carta1);
			this.jugador1.getP().getInventory().setItem(i + 2, carta);
		}
		for (int i = 0; i < 3; i++) {
			int carta1 = r.nextInt(this.jugador2.getCartas().size());
			ItemStack carta = Utils.transformaCarta(this.jugador2.getCartas().get(carta1), this.plugin);
			this.jugador2.getCartas().remove(carta1);
			this.jugador2.getP().getInventory().setItem(i + 2, carta);
		}
		turnoJugador1();
	}

	public void turnoJugador1() {
		this.jugador1.setUsedSkill(false);
		if (this.mana != 10) {
			this.mana++;
		}
		ponMobsReady(this.jugador1);
		this.jugador1.setMana(this.mana);
		this.jugador1.getP().playSound(this.jugador1.getP().getLocation(), Utils.buscaSonido("ARROW", "SHOOT"), 10.0F,
				1.0F);
		this.jugador1.setTurn(true);
		this.jugador2.setTurn(false);
		this.jugador1.drawACard();
		if (this.plugin.getLanguage().equals("es")) {
			Utils.usaTitle(this.jugador1.getP(), ChatColor.GREEN + "Turno de",
					ChatColor.YELLOW + this.jugador1.getP().getName(), this.plugin);
			Utils.usaTitle(this.jugador2.getP(), ChatColor.GREEN + "Turno de",
					ChatColor.YELLOW + this.jugador1.getP().getName(), this.plugin);
		} else {
			Utils.usaTitle(this.jugador1.getP(), ChatColor.GREEN + this.message.getPlayerTurnOfMsg(),
					ChatColor.YELLOW + this.jugador1.getP().getName(), this.plugin);
			Utils.usaTitle(this.jugador2.getP(), ChatColor.GREEN + this.message.getPlayerTurnOfMsg(),
					ChatColor.YELLOW + this.jugador1.getP().getName(), this.plugin);
		}
		if (this.plugin.isScoreboardEnabled()) {
			Utils.updateScoreboard(this.jugador1.getP(), this.jugador1, this.jugador1.getP().getName(), this.plugin);
			Utils.updateScoreboard(this.jugador2.getP(), this.jugador2, this.jugador1.getP().getName(), this.plugin);
		}
		this.sched = Bukkit.getServer().getScheduler().runTaskLater((Plugin) this.plugin, new Runnable() {
			public void run() {
				if (Partida.this.getNumTurnosPasados() == 0) {
					if (Partida.this.jugador1 != null && Partida.this.jugador1.getPartida() != null) {
						Partida.this.turnoJugador2();
					}
				} else {

					Partida.this.numTurnosPasados = Partida.this.numTurnosPasados - 1;
				}
			}
		}, (20 * this.plugin.getTurnTime().intValue()));
	}

	public void turnoJugador2() {
		if (this.jugador1.getCartas().size() == 0 && this.jugador2.getCartas().size() == 0) {
			deathMatch();
		} else {
			ponMobsReady(this.jugador2);
			this.jugador2.getP().playSound(this.jugador2.getP().getLocation(), Utils.buscaSonido("ARROW", "SHOOT"),
					10.0F, 1.0F);
			this.jugador2.setUsedSkill(false);
			this.jugador2.setMana(this.mana);
			this.jugador1.setTurn(false);
			this.jugador2.setTurn(true);
			this.jugador2.drawACard();
			if (this.plugin.getLanguage().equals("es")) {
				Utils.usaTitle(this.jugador1.getP(), ChatColor.GREEN + "Turno de",
						ChatColor.YELLOW + this.jugador2.getP().getName(), this.plugin);
				Utils.usaTitle(this.jugador2.getP(), ChatColor.GREEN + "Turno de",
						ChatColor.YELLOW + this.jugador2.getP().getName(), this.plugin);
			} else {
				Utils.usaTitle(this.jugador1.getP(), ChatColor.GREEN + this.message.getPlayerTurnOfMsg(),
						ChatColor.YELLOW + this.jugador2.getP().getName(), this.plugin);
				Utils.usaTitle(this.jugador2.getP(), ChatColor.GREEN + this.message.getPlayerTurnOfMsg(),
						ChatColor.YELLOW + this.jugador2.getP().getName(), this.plugin);
			}
			if (this.plugin.isScoreboardEnabled()) {
				Utils.updateScoreboard(this.jugador1.getP(), this.jugador1, this.jugador2.getP().getName(),
						this.plugin);
				Utils.updateScoreboard(this.jugador2.getP(), this.jugador2, this.jugador2.getP().getName(),
						this.plugin);
			}
			this.sched = Bukkit.getServer().getScheduler().runTaskLater((Plugin) this.plugin, new Runnable() {
				public void run() {
					if (Partida.this.getNumTurnosPasados() == 0) {
						if (Partida.this.jugador2 != null && Partida.this.jugador2.getPartida() != null) {
							Partida.this.turnoJugador1();
						}
					} else {

						Partida.this.numTurnosPasados = Partida.this.numTurnosPasados - 1;
					}
				}
			}, (20 * this.plugin.getTurnTime().intValue()));
		}
	}

	private void deathMatch() {
		this.jugador1.setTurn(true);
		this.jugador2.setTurn(true);
		Player p1 = this.jugador1.getP();
		Player p2 = this.jugador2.getP();
		p1.teleport(this.arena.getSpawndeathmatch1());
		p2.teleport(this.arena.getSpawndeathmatch2());
		Utils.usaTitle(this.jugador1.getP(), ChatColor.RED + this.message.getMatchDeathmatchMsg(), "", this.plugin);
		Utils.usaTitle(this.jugador2.getP(), ChatColor.RED + this.message.getMatchDeathmatchMsg(), "", this.plugin);
		p1.playSound(p1.getLocation(), Utils.buscaSonido2("EXPLODE", "ENTITY_GENERIC_EXPLODE"), 10.0F, 1.0F);
		p2.playSound(p2.getLocation(), Utils.buscaSonido2("EXPLODE", "ENTITY_GENERIC_EXPLODE"), 10.0F, 1.0F);
	}

	public ArenaRC getArena() {
		return this.arena;
	}

	public void setArena(ArenaRC arena) {
		this.arena = arena;
	}

	public Jugador getJugador1() {
		return this.jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2() {
		return this.jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}

	public RolCards getPlugin() {
		return this.plugin;
	}

	public void setPlugin(RolCards plugin) {
		this.plugin = plugin;
	}

	public BukkitTask getSched() {
		return this.sched;
	}

	public void setSched(BukkitTask sched) {
		this.sched = sched;
	}

	public boolean isPlayed() {
		return this.played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	public int getMana() {
		return this.mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getNumTurnosPasados() {
		return this.numTurnosPasados;
	}

	public void setNumTurnosPasados(int numTurnosPasados) {
		this.numTurnosPasados = numTurnosPasados;
	}

	public void setAllDefault() {
		this.jugador1.ponTodoDefault();
		this.jugador2.ponTodoDefault();
		Utils.setInventarioDefault(this.jugador1.getP(), this.plugin);
		Utils.setInventarioDefault(this.jugador2.getP(), this.plugin);
		this.jugador1 = null;
		this.jugador2 = null;
		this.played = false;
		this.mana = 0;
	}

	public void meteJugadores(Jugador aux, Jugador player) {
		this.jugador1 = aux;
		this.jugador2 = player;
		comienzaPartida();
	}

	public Jugador devuelveOtroJugador(Jugador j) {
		Jugador res = null;
		if (j.getP().getName().equals(this.jugador1.getP().getName())) {
			res = this.jugador2;
		} else if (j.getP().getName().equals(this.jugador2.getP().getName())) {
			res = this.jugador1;
		}
		return res;
	}

	private void ponMobsReady(Jugador j) {
		for (Monster mon : j.getMonstruos())
			mon.setReady(true);
	}

	public void meteJugador1(Jugador j1) {
		this.jugador1 = j1;
		j1.setPartida(this);
	}

	public void meteJugador2(Jugador j1) {
		this.jugador2 = j1;
		j1.setPartida(this);
		comienzaPartida();
	}
}
