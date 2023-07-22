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

package me.remigio07_.chatplugin.common.ip_lookup;

import me.remigio07_.chatplugin.api.common.ip_lookup.IPLookupMethod;
import me.remigio07_.chatplugin.api.common.util.manager.ChatPluginManagerException;

public class IPLookupManagerImpl extends BaseIPLookupManager {
	
	@Override
	public void load() throws ChatPluginManagerException {
		if (load0()) {
			if (method == IPLookupMethod.LOCAL)
				throw new ChatPluginManagerException(this, "LOCAL cannot be selected as IP lookup method on the free version; only REMOTE is allowed.");
			enabled = true;
			loadTime = System.currentTimeMillis() - ms;
		}
	}
	
}
