/*
 * 	ChatPlugin - A complete yet lightweight plugin which handles just too many features!
 * 	Copyright 2023  Remigio07
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

package me.remigio07.chatplugin.api.server.util.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.remigio07.chatplugin.api.common.util.annotation.NotNull;
import me.remigio07.chatplugin.api.common.util.manager.ChatPluginManager;
import me.remigio07.chatplugin.api.server.player.ChatPluginServerPlayer;

/**
 * Manager that handles vanished {@link ChatPluginServerPlayer}s and fake players counters.
 * 
 * @see #getOnlineWorld(String)
 * @see #getOnlineServer()
 */
public abstract class VanishManager implements ChatPluginManager {
	
	protected static VanishManager instance;
	protected boolean enabled, invisibility;
	protected Map<String, List<ChatPluginServerPlayer>> vanished = new HashMap<>();
	protected long loadTime;
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Checks if invisibility should be applied to vanished players.
	 * Even if false, other players will not be able to see them: will
	 * only affect other Staff members who will be able to see them.
	 * 
	 * @return Whether vanished players should be invisible
	 */
	public boolean isInvisibility() {
		return invisibility;
	}
	
	/**
	 * Gets the vanished players' map. Every entry is composed of a {@link String}
	 * which represents a world and a {@link List} of {@link ChatPluginServerPlayer}.
	 * 
	 * @return Vanished players' map
	 */
	public Map<String, List<ChatPluginServerPlayer>> getVanishedMap() {
		return vanished;
	}
	
	/**
	 * Gets the vanished players' list in the specified world.
	 * Will return {@link Collections#emptyList()} if {@link #getVanishedMap()}
	 * does not contain the specified <code>world</code>.
	 * 
	 * @param world World to check
	 * @return Vanished players' list
	 */
	@NotNull
	public List<ChatPluginServerPlayer> getVanishedList(String world) {
		return vanished.getOrDefault(world, Collections.emptyList());
	}
	
	/**
	 * Gets the vanished players' amount in the current server.
	 * 
	 * @return Total vanished players
	 */
	public int getVanishedAmount() {
		return getVanishedList().size();
	}
	
	/**
	 * Gets this manager's instance.
	 * 
	 * @return Manager's instance
	 */
	public static VanishManager getInstance() {
		return instance;
	}
	
	/**
	 * Hides a player from the ones who do not have
	 * the permission "chatplugin.commands.vanish".
	 * 
	 * @param player Player to hide
	 */
	public abstract void hide(ChatPluginServerPlayer player);
	
	/**
	 * Shows a player (who was previously vanished) to all online players.
	 * 
	 * @param player Player to show
	 */
	public abstract void show(ChatPluginServerPlayer player);
	
	/**
	 * Updates a player's visibility.
	 * 
	 * @param player Player to update
	 * @param justJoined Whether the player just joined the server
	 */
	public abstract void update(ChatPluginServerPlayer player, boolean justJoined);
	
	/**
	 * Gets a list containing the vanished players.
	 * 
	 * @return Vanished players
	 */
	public abstract List<ChatPluginServerPlayer> getVanishedList();
	
	/**
	 * Gets a list containing the vanished players' names.
	 * 
	 * @return Vanished players' names
	 */
	public abstract List<String> getVanishedNames();
	
	/**
	 * Checks if a player is vanished.
	 * 
	 * @param player Player to check
	 * @return Whether the player is vanished
	 */
	public abstract boolean isVanished(ChatPluginServerPlayer player);
	
	/**
	 * Gets the adjusted amount of players in a certain
	 * world, subtracting the vanished players' amount.
	 * 
	 * @param world World to check
	 * @return Adjusted players' amount
	 */
	public abstract int getOnlineWorld(String world);
	
	/**
	 * Gets the adjusted amount of players in this server,
	 * subtracting the vanished players' amount.
	 * 
	 * @return Adjusted players' amount
	 * @see ProxyManager#getOnlinePlayers(String, boolean)
	 */
	public abstract int getOnlineServer();
	
}
