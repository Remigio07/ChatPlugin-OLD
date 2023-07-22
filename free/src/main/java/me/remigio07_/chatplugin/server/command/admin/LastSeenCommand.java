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

package me.remigio07_.chatplugin.server.command.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import me.remigio07_.chatplugin.api.common.player.OfflinePlayer;
import me.remigio07_.chatplugin.api.common.storage.PlayersDataType;
import me.remigio07_.chatplugin.api.common.storage.StorageConnector;
import me.remigio07_.chatplugin.api.common.util.adapter.user.PlayerAdapter;
import me.remigio07_.chatplugin.api.common.util.manager.TaskManager;
import me.remigio07_.chatplugin.api.server.language.Language;
import me.remigio07_.chatplugin.api.server.player.ChatPluginServerPlayer;
import me.remigio07_.chatplugin.api.server.util.Utils;
import me.remigio07_.chatplugin.api.server.util.adapter.user.CommandSenderAdapter;
import me.remigio07_.chatplugin.server.command.BaseCommand;

public class LastSeenCommand extends BaseCommand {
	
	public LastSeenCommand() {
		super("/lastseen <player>");
		tabCompletionArgs.put(0, players);
	}
	
	@Override
	public List<String> getMainArgs() {
		return Arrays.asList("lastseen", "seen", "last", "lastlogout", "lastlogin");
	}
	
	@Override
	public void execute(CommandSenderAdapter sender, Language language, String[] args) {
		if (args.length == 1) {
			PlayerAdapter player = PlayerAdapter.getPlayer(args[0], false);
			
			if (player == null) {
				TaskManager.runAsync(() -> {
					try {
						OfflinePlayer offlinePlayer = new OfflinePlayer(args[0]);
						
						if (offlinePlayer.getUUID().equals(Utils.NIL_UUID)) {
							sender.sendMessage(language.getMessage("misc.inexistent-player", args[0]));
							return;
						} if (StorageConnector.getInstance().isPlayerStored(offlinePlayer))
							sender.sendMessage(language.getMessage("commands.lastseen.offline", offlinePlayer.getName(), Utils.formatTime(System.currentTimeMillis() - StorageConnector.getInstance().getPlayerData(PlayersDataType.LAST_LOGOUT, offlinePlayer).longValue(), language, false, false), StorageConnector.getInstance().getPlayerData(PlayersDataType.PLAYER_IP, offlinePlayer)));
						else sender.sendMessage(language.getMessage("misc.player-not-stored", args[0]));
					} catch (IllegalArgumentException e) {
						sender.sendMessage(language.getMessage("misc.invalid-player-name"));
					} catch (IOException e) {
						sender.sendMessage(language.getMessage("misc.cannot-fetch", args[0], e.getMessage()));
					} catch (SQLException e) {
						sender.sendMessage(language.getMessage("misc.database-error", e.getMessage()));
					}
				}, 0L);
			} else if (player.isLoaded()) {
				sender.sendMessage(language.getMessage("commands.lastseen.online", player.getName(), Utils.formatTime(System.currentTimeMillis() - ((ChatPluginServerPlayer) player.chatPluginValue()).getLoginTime(), language, false, false)));
			} else sender.sendMessage(language.getMessage("misc.disabled-world"));
		} else sendUsage(sender, language);
	}
	
}
