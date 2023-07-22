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

package me.remigio07_.chatplugin.api.server.event.gui;

import me.remigio07_.chatplugin.api.common.event.ChatPluginEvent;
import me.remigio07_.chatplugin.api.server.gui.GUI;

/**
 * Represents a {@link GUI}-related event.
 */
public abstract class GUIEvent implements ChatPluginEvent {
	
	protected GUI gui;
	
	protected GUIEvent(GUI gui) {
		this.gui = gui;
	}
	
	/**
	 * Gets the GUI involved with this event.
	 * 
	 * @return GUI involved
	 */
	public GUI getGUI() {
		return gui;
	}
	
}
