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

package me.remigio07_.chatplugin.api.common.util.manager;

import me.remigio07_.chatplugin.api.common.util.VersionUtils;
import me.remigio07_.chatplugin.api.common.util.annotation.ServerImplementationOnly;
import me.remigio07_.chatplugin.api.server.util.GameFeature;
import me.remigio07_.chatplugin.bootstrap.Environment;

/**
 * Interface that represents one of the managers used by ChatPlugin.
 */
public interface ChatPluginManager {
	
	/**
	 * Loads (or reloads) this manager.
	 * 
	 * @throws ChatPluginManagerException If something goes wrong
	 */
	public void load() throws ChatPluginManagerException;
	
	/**
	 * Unloads this manager.
	 * Will do nothing if this method is not overridden.
	 * 
	 * @throws ChatPluginManagerException If something goes wrong
	 */
	public default void unload() throws ChatPluginManagerException {
		
	}
	
	/**
	 * Reloads this manager.
	 * Will call {@link #unload()} and then
	 * {@link #load()} if not overridden.
	 * 
	 * @throws ChatPluginManagerException If something goes wrong
	 */
	public default void reload() throws ChatPluginManagerException {
		unload();
		load();
	}
	
	/**
	 * Checks if this manager is enabled.
	 * 
	 * @return Whether this manager is enabled
	 */
	public boolean isEnabled();
	
	/**
	 * Checks if this manager will be reloaded on a plugin reload.
	 * Will return <code>true</code> if this method is not overridden.
	 * 
	 * @return Whether this manager is reloadable
	 */
	public default boolean isReloadable() {
		return true;
	}
	
	/**
	 * Checks if this manager specifies a {@link GameFeature} annotation.
	 * In that case, if <code>{@link Environment#isProxy()} == false</code> ({@link GameFeature}s are not used on proxies),
	 * the feature's availability on the current environment is checked through three steps:
	 * 	<ol>
	 * 		<li>environment compatibility - whether the feature is available on Bukkit/Sponge</li>
	 * 		<li>Spigot requirement - whether the feature requires Spigot or a fork; Bukkit only</li>
	 * 		<li>minimum version - the minimum Vanilla version required to run the feature</li>
	 * 	</ol>
	 * 
	 * <p>See wiki for more info:
	 * <br><a href="https://github.com/Remigio07/ChatPlugin/wiki/Features-availability">ChatPlugin wiki/Features availability</a></p>
	 * 
	 * @param warnIfUnavailable Whether to send a message if the feature is unavailable
	 * @return Whether this manager's features may run on {@link Environment#getCurrent()}
	 */
	@ServerImplementationOnly(why = ServerImplementationOnly.GAME_FEATURE)
	public default boolean checkAvailability(boolean warnIfUnavailable) {
		GameFeature gameFeature = getClass().getAnnotation(GameFeature.class);
		
		if (Environment.isProxy() || gameFeature == null)
			return true;
		String str = null;
		
		if (Environment.isBukkit()) {
			if (!gameFeature.availableOnBukkit())
				str = "Bukkit cannot";
			else if (gameFeature.spigotRequired() && !VersionUtils.isSpigot())
				str = "Spigot is required to";
			else if (VersionUtils.getVersion().ordinal() < gameFeature.minimumBukkitVersion().ordinal())
				str = "At least Minecraft " + gameFeature.minimumBukkitVersion().toString() + " is required to";
		} else {
			if (!gameFeature.availableOnSponge())
				str = "Sponge cannot";
			else if (VersionUtils.getVersion().ordinal() < gameFeature.minimumSpongeVersion().ordinal())
				str = "At least Minecraft " + gameFeature.minimumSpongeVersion().toString() + " is required to";
		} if (str != null && warnIfUnavailable)
			LogManager.log(str + " run the " + gameFeature.name() + " module; disabling feature...", 1);
		return str == null;
	}
	
}