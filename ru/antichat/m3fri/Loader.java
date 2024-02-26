package ru.antichat.m3fri;

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

public class Loader extends cn.nukkit.plugin.PluginBase implements cn.nukkit.event.Listener {
	private cn.nukkit.utils.Config config;

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		if (!this.getDataFolder().exists()) {
			this.getDataFolder().mkdir();
		}

		java.io.File configFile = new java.io.File(this.getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			this.saveResource("config.yml", false);
		}

		this.config = new cn.nukkit.utils.Config(configFile, cn.nukkit.utils.Config.YAML);

		String author = this.getDescription().getAuthors().get(0);
		if (!author.equalsIgnoreCase("m3fri")) {
			this.getLogger().warning("§c§lИмя автора плагина в §eplugin.yml §fбыло изменено! Верните §bm3fri, §fчтобы плагин заработал");
			this.getServer().getPluginManager().disablePlugin(this);
			return;
		}

		new ru.antichat.m3fri.Utils.EventRegister(this).registerEvents();
	}

	public cn.nukkit.utils.Config getConfig() {
		return config;
	}
}
