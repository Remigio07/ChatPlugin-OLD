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

package me.remigio07_.chatplugin.api.server.integration.placeholder;

import me.remigio07_.chatplugin.api.common.integration.ChatPluginIntegration;
import me.remigio07_.chatplugin.api.common.integration.IntegrationType;
import me.remigio07_.chatplugin.api.server.player.ChatPluginServerPlayer;

/**
 * Represents a {@link ChatPluginIntegration} that translates placeholders for a {@link ChatPluginServerPlayer}.
 * 
 * <p><strong>Types:</strong> [{@link IntegrationType#PLACEHOLDERAPI}, {@link IntegrationType#MVDWPLACEHOLDERAPI}]</p>
 */
public interface PlaceholderIntegration extends ChatPluginIntegration {
	
	/**
	 * Translates an input string with placeholders,
	 * formatted for the specified player.
	 * 
	 * @param input Input containing placeholders
	 * @param player Player whose placeholders need to be translated
	 * @return Translated placeholders
	 */
	public String translatePlaceholders(String input, ChatPluginServerPlayer player);
	
}