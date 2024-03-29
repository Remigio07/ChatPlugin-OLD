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

package me.remigio07.chatplugin.api.common.integration;

import me.remigio07.chatplugin.api.common.util.annotation.Nullable;

/**
 * Represents an integration handled by the {@link IntegrationManager}. See wiki for more info:
 * <br><a href="https://github.com/Remigio07/ChatPlugin/wiki/Plugin-integrations">ChatPlugin wiki/Plugin integrations</a>
 * 
 */
public interface ChatPluginIntegration {
	
	/**
	 * Checks if this integration is enabled.
	 * 
	 * @return Whether this integration is enabled
	 */
	public boolean isEnabled();
	
	/**
	 * Gets this integration's type.
	 * 
	 * @return Integration's type
	 */
	public IntegrationType<?> getType();
	
	/**
	 * Gets this integration's plugin object.
	 * 
	 * @return Integrations' plugin object
	 */
	public Object getPlugin();
	
	/**
	 * Gets this integration's API object, if present.
	 * Will return <code>null</code> if not used.
	 * 
	 * @return Integration's API object
	 */
	@Nullable(why = "Not all integrations use an API object")
	public Object getAPI();
	
	/**
	 * Loads this integration and enable it if present in the server.
	 */
	public void load();
	
}
