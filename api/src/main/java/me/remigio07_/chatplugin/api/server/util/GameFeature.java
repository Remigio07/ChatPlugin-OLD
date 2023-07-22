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

package me.remigio07_.chatplugin.api.server.util;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import me.remigio07_.chatplugin.api.common.util.VersionUtils.Version;
import me.remigio07_.chatplugin.api.common.util.annotation.NotNull;
import me.remigio07_.chatplugin.api.common.util.annotation.ServerImplementationOnly;
import me.remigio07_.chatplugin.api.common.util.manager.ChatPluginManager;

/**
 * Annotation used to indicate that a Minecraft feature handled by a {@link ChatPluginManager} is not available under certain circumstances.
 * Should be applied to managers' class declarations and checked through {@link ChatPluginManager#checkAvailability(boolean)}. See wiki for more info:
 * <br><a href="https://github.com/Remigio07/ChatPlugin/wiki/Features-availability">ChatPlugin wiki/Features availability</a>
 * 
 * <p><strong>Note:</strong> the check is processed on the plugin's server implementations only (Bukkit and Sponge).</p>
 */
@Target(TYPE)
@Retention(RUNTIME)
@ServerImplementationOnly(why = ServerImplementationOnly.GAME_FEATURE)
public @interface GameFeature {
	
	/**
	 * Gets this feature's name.
	 * 
	 * <p><strong>Example:</strong> "actionbar", "bossbar"...</p>
	 * 
	 * @return Feature's name
	 */
	public String name();
	
	/**
	 * Checks if this feature is available on Bukkit environments.
	 * 
	 * @return Whether this feature is available on Bukkit
	 */
	public boolean availableOnBukkit();
	
	/**
	 * Checks if this feature is available on Sponge environments.
	 * 
	 * @return Whether this feature is available on Sponge
	 */
	public boolean availableOnSponge();
	
	/**
	 * Checks if Spigot is required to run this feature.
	 * Applies to Bukkit environments only.
	 * 
	 * @return Whether Spigot is required to run this feature
	 */
	public boolean spigotRequired();
	
	/**
	 * Gets the minimum Vanilla version required to run this feature on a Bukkit environment.
	 * 
	 * @return Minimum Vanilla version required to run on Bukkit
	 */
	@NotNull
	public Version minimumBukkitVersion();
	
	/**
	 * Gets the minimum Vanilla version required to run this feature on a Sponge environment.
	 * 
	 * @return Minimum Vanilla version required to run on Sponge
	 */
	@NotNull
	public Version minimumSpongeVersion();
	
}
