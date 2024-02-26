package ru.antichat.m3fri.Event;

/**
 *
 *     _               _      _        _         _
 *   _/\\___ _____  __/\\  __/\\___  _/\\___   _/\\_
 *  (_      v    ))/    \\(_  ____))(_   _  ))(____))
 *  /  :   <\   \\\_/':// / ||__    /  |))//  /  \\
 *  /:. |   //   /// \.:\\/:. ._))  /:.    \\ /:.  \\
 * \___|  //\  // \__  //\  _))    \___|  // \__  //
 * \//  \//     \//  \//           \//     \//
 *
 * @developer m3fri
 * @link https://vk.com/m3fri .
 *
 */

public class Event implements cn.nukkit.event.Listener {
	private final ru.antichat.m3fri.Loader plugin;
	private final java.util.List<String> blockedWords;
	private final java.util.regex.Pattern domainPattern;

	public Event(ru.antichat.m3fri.Loader plugin) {
		this.plugin = plugin;
		this.blockedWords = plugin.getConfig().getStringList("blockedWords");
		this.domainPattern = java.util.regex.Pattern.compile("(?<=\\s|^)(?:(?:https?|ftp):\\/\\/)?[\\w-]+(?:\\.[\\w-]+)+\\.?(:\\d+)?(?:\\/\\S*)?(?=\\s|$)");
	}

	@cn.nukkit.event.EventHandler
	public void onPlayerChat(cn.nukkit.event.player.PlayerChatEvent event) {
		String message = event.getMessage().toLowerCase();
		for (String word : blockedWords) {
			if (message.contains(word.toLowerCase())) {
				event.setCancelled(true);
				String blockedWordsMessage = plugin.getConfig().getString("blockedWordsMessage", "§fИгрок §e{player} §fнаписал запрещенное слово: §b{word}§r");
				blockedWordsMessage = blockedWordsMessage.replace("{player}", event.getPlayer().getName()).replace("{word}", word);
				event.getPlayer().sendMessage(blockedWordsMessage);
				plugin.getLogger().info(blockedWordsMessage);
				return;
			}
		}
		java.util.regex.Matcher matcher = domainPattern.matcher(message);
		if (matcher.find()) {
			event.setCancelled(true);
			String blockedDomainsMessage = plugin.getConfig().getString("blockedDomainsMessage", "§fИгрок §e{player} §fнаписал сообщение, в котором содержится доменное имя: §b{domain}§r");
			blockedDomainsMessage = blockedDomainsMessage.replace("{player}", event.getPlayer().getName()).replace("{domain}", matcher.group());
			event.getPlayer().sendMessage(blockedDomainsMessage);
			plugin.getLogger().info(blockedDomainsMessage);
			return;
		}
		if (!isValidMessage(message)) {
			event.setCancelled(true);
		}
	}

	private boolean isValidMessage(String message) {
		return message.matches("[\\p{L}\\p{IsCyrillic}\\d\\s]+");
	}
}
