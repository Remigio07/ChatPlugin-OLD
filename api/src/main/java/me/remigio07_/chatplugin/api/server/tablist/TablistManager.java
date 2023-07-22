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

package me.remigio07_.chatplugin.api.server.tablist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.regex.Pattern;

import me.remigio07_.chatplugin.api.common.storage.configuration.ConfigurationType;
import me.remigio07_.chatplugin.api.common.util.VersionUtils.Version;
import me.remigio07_.chatplugin.api.common.util.annotation.Nullable;
import me.remigio07_.chatplugin.api.common.util.manager.ChatPluginManager;
import me.remigio07_.chatplugin.api.common.util.manager.TaskManager;
import me.remigio07_.chatplugin.api.server.event.tablist.TablistSendEvent;
import me.remigio07_.chatplugin.api.server.player.ChatPluginServerPlayer;
import me.remigio07_.chatplugin.api.server.tablist.custom_suffix.CustomSuffixManager;
import me.remigio07_.chatplugin.api.server.util.GameFeature;
import me.remigio07_.chatplugin.api.server.util.PlaceholderType;

/**
 * Manager that handles {@link Tablist}s. See wiki for more info:
 * <br><a href="https://github.com/Remigio07/ChatPlugin/wiki/Tablists">ChatPlugin wiki/Tablists</a>
 * 
 * @see CustomSuffixManager
 */
@GameFeature(
		name = "tablist",
		availableOnBukkit = true,
		availableOnSponge = true,
		spigotRequired = false,
		minimumBukkitVersion = Version.V1_8,
		minimumSpongeVersion = Version.V1_8
		)
public abstract class TablistManager extends TimerTask implements ChatPluginManager {
	
	/**
	 * Pattern representing the allowed tablist IDs.
	 * 
	 * <p><strong>Regex:</strong> "^[a-zA-Z0-9-_]{2,36}$"</p>
	 * 
	 * @see #isValidTablistID(String)
	 */
	public static final Pattern TABLIST_ID_PATTERN = Pattern.compile("^[a-zA-Z0-9-_]{2,36}$");
	protected static TablistManager instance;
	protected boolean enabled, randomOrder;
	protected long sendingTimeout, timerTaskID = -1;
	protected List<PlaceholderType> placeholderTypes = Collections.emptyList();
	protected List<Tablist> tablists = new ArrayList<>();
	protected int timerIndex = -1;
	protected long loadTime;
	
	/**
	 * Checks if this manager is enabled.
	 * 
	 * <p><strong>Found at:</strong> "tablists.settings.enabled" in {@link ConfigurationType#TABLISTS}</p>
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Checks if the tablists should be sent in a random order.
	 * 
	 * <p><strong>Found at:</strong> "tablists.settings.random-order" in {@link ConfigurationType#TABLISTS}</p>
	 * 
	 * @return Whether to use a random order
	 */
	public boolean isRandomOrder() {
		return randomOrder;
	}
	
	/**
	 * Gets the timeout between sendings, in milliseconds.
	 * 
	 * <p><strong>Found at:</strong> "tablists.settings.sending-timeout-ms" in {@link ConfigurationType#TABLISTS}</p>
	 * 
	 * @return Time between sendings
	 */
	public long getSendingTimeout() {
		return sendingTimeout;
	}
	
	/**
	 * Gets the list of placeholder types used
	 * to translate {@link Tablist#getTexts()}.
	 * 
	 * <p><strong>Found at:</strong> "tablists.settings.placeholder-types" in {@link ConfigurationType#TABLISTS}</p>
	 * 
	 * @return Placeholders used to translate texts
	 */
	public List<PlaceholderType> getPlaceholderTypes() {
		return placeholderTypes;
	}
	
	/**
	 * Gets the list of loaded tablists.
	 * You may modify the returned list.
	 * 
	 * @return Loaded tablists' list
	 */
	public List<Tablist> getTablists() {
		return tablists;
	}
	
	/**
	 * Gets a tablist from {@link #getTablists()} by its ID.
	 * Will return <code>null</code> if the tablist is not loaded.
	 * 
	 * @param id Tablist's ID
	 * @return Loaded tablist
	 */
	@Nullable(why = "Specified tablist may not be loaded")
	public Tablist getTablist(String id) {
		return tablists.stream().filter(tablist -> tablist.getID().equals(id)).findAny().orElse(null);
	}
	
	/**
	 * Gets the {@link #run()}'s timer's task's ID.
	 * You can interact with it using {@link TaskManager}'s methods.
	 * 
	 * @return Sending task's ID
	 */
	public long getTimerTaskID() {
		return timerTaskID;
	}
	
	/**
	 * Gets the {@link #run()}'s timer's index of {@link #getTablists()}.
	 * 
	 * @return Timer's index
	 */
	public int getTimerIndex() {
		return timerIndex;
	}
	
	/**
	 * Checks if the specified String is a valid tablist ID.
	 * 
	 * @param tablistID Tablist ID to check
	 * @return Whether the specified tablist ID is valid
	 * @see #TABLIST_ID_PATTERN
	 */
	public boolean isValidTablistID(String tablistID) {
		return TABLIST_ID_PATTERN.matcher(tablistID).matches();
	}
	
	/**
	 * Gets this manager's instance.
	 * 
	 * @return Manager's instance
	 */
	public static TablistManager getInstance() {
		return instance;
	}
	
	/**
	 * Automatic tablist sender, called once every {@link #getSendingTimeout()} ms.
	 */
	@Override
	public abstract void run();
	
	/**
	 * Sends a tablist to a loaded player.
	 * 
	 * @param tablist Tablist to send
	 * @param player Player to send the tablist to
	 * @see TablistSendEvent
	 */
	public abstract void sendTablist(Tablist tablist, ChatPluginServerPlayer player);
	
}
