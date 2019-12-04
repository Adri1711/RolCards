package com.adri1711.rolcards.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.adri1711.rolcards.RolCards;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;

public class UtilPlaceholder {
	public static void putPlaceholder(final RolCards plugin) {
		if (Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI") && plugin.isFeatherBoardEnabled())
			PlaceholderAPI.registerPlaceholder((Plugin) plugin, "rolcardsElo", new PlaceholderReplacer() {
				public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
					Player player = event.getPlayer();
					if (player != null) {
						return plugin.getElo1(player.getName()).toString();
					}
					return "Player needed";
				}
			});
	}
}
