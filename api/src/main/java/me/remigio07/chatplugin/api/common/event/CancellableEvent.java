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

package me.remigio07.chatplugin.api.common.event;

/**
 * Represents a cancellable event.
 */
public interface CancellableEvent extends ChatPluginEvent {
	
	/**
	 * Checks if this event has been cancelled and will not be executed.
	 * 
	 * @return Whether this event is cancelled
	 */
	public boolean isCancelled();
	
	/**
	 * Sets the cancellation state of this event. It will be passed through other
	 * registered listeners even if it is cancelled, but it will not be executed.
	 * 
	 * @param cancelled Whether this event should be cancelled
	 */
	public void setCancelled(boolean cancelled);
	
}
