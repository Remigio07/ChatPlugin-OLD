/*
 * 	ChatPlugin - A complete yet lightweight plugin which handles just too many features!
 * 	Copyright 2023  Remigio07_
 * 	
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU Affero General Public License for more details.
 * 	
 * 	You should have received a copy of the GNU Affero General Public License
 * 	along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 	
 * 	<https://github.com/Remigio07/ChatPlugin>
 */

package me.remigio07_.chatplugin.bootstrap;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

/**
 * Represents the Sponge's bootstrapper.
 */
@Plugin(
		id = "chatplugin",
		name = "ChatPlugin",
		version = "${version}",
		url = "https://megaproserver.com/chatplugin",
		description = "A lightweight yet complete plugin which handles just too many features! Check the wiki for info: https://github.com/Remigio07/ChatPlugin",
		authors = "Remigio07_",
		dependencies = {
				@Dependency(
						id = "negativity",
						version = "[1.9.0,)",
						optional = true
						),
				@Dependency(
						id = "viaversion",
						version = "[4.0.0,)",
						optional = true
						)
		})
public class SpongeBootstrapper {
	
	private static SpongeBootstrapper instance;
	@Inject
	private Logger logger;
	@Inject
	@ConfigDir(sharedRoot = false)
	private Path dataFolder;
	
	/**
	 * Event automatically called on server startup.
	 * 
	 * @param event Startup event
	 */
	@SuppressWarnings("deprecation")
	@Listener
	public void onGameStartedServer(GameStartedServerEvent event) {
		instance = this;
		
		Environment.setCurrent(Environment.SPONGE);
		JARLibraryLoader.getInstance().initialize(logger, dataFolder);
	}
	
	/**
	 * Event automatically called on server shutdown.
	 * 
	 * @param event Shutdown event
	 */
	@Listener
	public void onGameStoppedServer(GameStoppedServerEvent event) {
		JARLibraryLoader.getInstance().disable();
	}
	
	/**
	 * Gets this Sponge plugin's instance.
	 * 
	 * @return Plugin's instance
	 */
	public static SpongeBootstrapper getInstance() {
		return instance;
	}
	
}
