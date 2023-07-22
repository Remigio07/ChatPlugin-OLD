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

package me.remigio07_.chatplugin.api.server.scoreboard.event;

import me.remigio07_.chatplugin.api.server.player.ChatPluginServerPlayer;
import me.remigio07_.chatplugin.api.server.scoreboard.Scoreboard;

/**
 * Represents a scoreboard triggered by a {@link ScoreboardEvent}.
 */
public interface EventScoreboard {
	
	/**
	 * Prepares an event for this scoreboard.
	 * 
	 * @param player Target player
	 * @param args Event's arguments ({@link EventArguments#types()})
	 */
	public default void prepareEvent(ChatPluginServerPlayer player, Object... args) {
		// some events do not need to be prepared
	}
	
	/**
	 * Translates an input string with this event scoreboard's specific placeholders.
	 * Check {@link ScoreboardEvent#getPlaceholders()} to know the available placeholders.
	 * 
	 * @param input Input containing placeholders
	 * @param player Target player
	 * @return Translated placeholders
	 */
	public default String formatPlaceholders(String input, ChatPluginServerPlayer player) {
		return input;
	}
	
	/**
	 * Gets the event that triggers this scoreboard.
	 * 
	 * @return Scoreboard's event
	 */
	public ScoreboardEvent getEvent();
	
	/**
	 * Gets the time this scoreboard should be displayed for
	 * before showing again the previous one, in milliseconds.
	 * 
	 * <p><strong>Found at:</strong> "settings.on-screen-time-ms" in {@link Scoreboard#getConfiguration()}</p>
	 * 
	 * @return On screen time
	 */
	public long getOnScreenTime();
	
}
