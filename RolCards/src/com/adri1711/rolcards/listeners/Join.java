package com.adri1711.rolcards.listeners;

import com.adri1711.rolcards.RolCards;
import com.adri1711.rolcards.cards.Card;
import com.adri1711.rolcards.cards.CardClass;
import com.adri1711.rolcards.jugador.Jugador;
import com.adri1711.rolcards.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
	private RolCards plugin;

	public Join(RolCards plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent evt) {
		Player p = evt.getPlayer();
		Jugador j = new Jugador(p, this.plugin);
		this.plugin.getJugadores().add(j);
		if (this.plugin.getElo1(p.getName()) == null || this.plugin.getElo1(p.getName()).doubleValue() == 0.0D) {
			this.plugin.addElo(p.getName(), Double.valueOf(0.0D));
		}
		if (this.plugin.getKills1(p.getName()) == null || this.plugin.getKills1(p.getName()).doubleValue() == 0.0D) {
			this.plugin.addStats1(p.getName(), Double.valueOf(0.0D), Double.valueOf(0.0D));
		}
		if (this.plugin.getCards().get(p.getName()) != null) {
			cargaDatos(p, j);
		}
		if (this.plugin.isDirectJoin()) {
			j.setPlaying(true);
			if (plugin.getsVersion().contains("v1_15")) {
				p.setResourcePack("https://www.dropbox.com/s/gn40ymxroq2t8by/RolCards1.15.zip?dl=1");
			} else {
				p.setResourcePack("https://www.dropbox.com/s/atehu8letooruvh/Rolcards.zip?dl=1");
			}
			Utils.setInventarioDefault(p, this.plugin);
			Utils.updateScoreboard(p, j, null, this.plugin);
		}
	}

	public RolCards getPlugin() {
		return this.plugin;
	}

	public void setPlugin(RolCards plugin) {
		this.plugin = plugin;
	}

	private void cargaDatos(Player p, Jugador j) {
		CardClass clase;
		List<Card> listaCartas = new ArrayList<Card>();
		List<String> listaNombreCartas = plugin.getCards().getStringList(p.getName() + ".cards");
		if (!listaNombreCartas.isEmpty()) {
			for (String s : listaNombreCartas) {
				if (s != null) {

					listaCartas.add(Utils.buscaCarta(s, plugin));
				}
			}
		}
		String s = plugin.getCards().getString(p.getName() + ".class");
		switch (s) {
		case "normal":
			clase = CardClass.NORMAL;
			break;
		case "hunter":
			clase = CardClass.HUNTER;
			break;
		case "mage":
			clase = CardClass.MAGE;
			break;
		case "warrior":
			clase = CardClass.WARRIOR;
			break;
		default:
			clase = CardClass.NORMAL;
			break;
		}

		for (Card c : listaCartas) {
			j.addCarta(c);
		}
		j.setClase(clase);

	}
}
