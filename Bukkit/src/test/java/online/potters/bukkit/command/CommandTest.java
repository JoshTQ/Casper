package online.potters.bukkit.command;

import online.potters.api.command.CasperCommand;
import online.potters.impl.bukkit.command.BukkitCasperCommand;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.HashSet;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Server.class, Bukkit.class})
public class CommandTest {

	@Before
	public void setupServer() {
		PowerMockito.mockStatic(Bukkit.class);
		PowerMockito.when(Bukkit.getServer()).thenReturn(PowerMockito.mock(Server.class));

		PluginManager pluginManager = PowerMockito.mock(PluginManager.class);
		PowerMockito.when(Bukkit.getPluginManager()).thenReturn(pluginManager);
	}

	@Test
	public void commandTest() {
		JavaPlugin javaPlugin = PowerMockito.mock(JavaPlugin.class);

		CasperCommand bukkitCasperCommand = BukkitCasperCommand.commandBuilder()
				.name("testing")
				.handler((sender, args) -> {
					sender.sendMessage("Hello");

					if (args.length == 1) {
						sender.sendMessage("Nice argument!");
					}
				})
				.consoleCommand(true)
				.permission("command.run")
				.aliases("test", "tested")
				.minimumArgs(2)
				.build(javaPlugin);
	}
}