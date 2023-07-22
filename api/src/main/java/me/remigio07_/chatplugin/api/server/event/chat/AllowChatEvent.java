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

package me.remigio07_.chatplugin.api.server.event.chat;

import me.remigio07_.chatplugin.api.common.event.CancellableEvent;
import me.remigio07_.chatplugin.api.server.chat.ChatManager;
import me.remigio07_.chatplugin.api.server.player.ChatPluginServerPlayer;

/**
 * Represents an event called before a player's message is sent.
 * 
 * @see ChatManager#handleChatEvent(ChatPluginServerPlayer, String)
 */
public class AllowChatEvent extends ChatEvent implements CancellableEvent {
	
	private boolean cancelled;
	
	/**
	 * Constructs a new allow chat event.
	 * 
	 * @param player Player involved
	 * @param message Message involved
	 */
	public AllowChatEvent(ChatPluginServerPlayer player, String message) {
		super(player, message);
	}
	
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
}
