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

package me.remigio07_.chatplugin.server.motd;

import java.net.InetAddress;

import me.remigio07_.chatplugin.api.common.motd.MoTD;
import me.remigio07_.chatplugin.api.common.util.VersionUtils.Version;
import me.remigio07_.chatplugin.api.common.util.manager.ChatPluginManagerException;
import me.remigio07_.chatplugin.api.server.language.Language;
import me.remigio07_.chatplugin.api.server.motd.ServerMoTDManager;

public class DummyServerMoTDManager extends ServerMoTDManager {
	
	@Override
	public void load() throws ChatPluginManagerException {
		instance = this;
	}
	
	@Override
	public MoTD getMoTD(InetAddress ipAddress, Version version) {
		return null;
	}
	
	@Override
	public MoTD getMoTD(InetAddress ipAddress, Version version, Language language) {
		return null;
	}
	
}