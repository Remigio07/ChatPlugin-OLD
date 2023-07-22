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

package me.remigio07_.chatplugin.server.sponge.integration.version;

import org.spongepowered.api.entity.living.player.Player;

import com.viaversion.viaversion.SpongePlugin;
import com.viaversion.viaversion.api.ViaAPI;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;

import me.remigio07_.chatplugin.api.common.integration.IntegrationType;
import me.remigio07_.chatplugin.api.common.integration.version.VersionIntegration;
import me.remigio07_.chatplugin.api.common.util.VersionUtils.Version;
import me.remigio07_.chatplugin.api.common.util.adapter.user.PlayerAdapter;
import me.remigio07_.chatplugin.api.server.player.ServerPlayerManager;
import me.remigio07_.chatplugin.server.sponge.integration.ChatPluginSpongeIntegration;

public class ViaVersionIntegration extends ChatPluginSpongeIntegration<VersionIntegration> implements VersionIntegration {
	
	public ViaVersionIntegration() {
		super(IntegrationType.VIAVERSION);
	}
	
	@Override
	protected void loadAPI() {
		api = ((SpongePlugin) plugin).getApi();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Version getVersion(PlayerAdapter player) {
		Version version = ServerPlayerManager.getInstance().getPlayerVersion(player.getUUID());
		return version == null ? Version.getVersion(ProtocolVersion.getProtocol(((ViaAPI<Player>) api).getPlayerVersion(player.spongeValue())).getName()) : version;
	}
	
}
