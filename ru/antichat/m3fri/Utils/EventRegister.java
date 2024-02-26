package ru.antichat.m3fri.Utils;

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

public class EventRegister {

	private final cn.nukkit.plugin.Plugin plugin;

	public EventRegister(ru.antichat.m3fri.Loader plugin) {
		this.plugin = plugin;
	}

	public void registerEvents() {
		plugin.getServer().getPluginManager().registerEvents(new ru.antichat.m3fri.Event.Event((ru.antichat.m3fri.Loader) plugin), plugin);
	}
}