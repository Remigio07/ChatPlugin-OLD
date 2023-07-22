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

package me.remigio07_.chatplugin.server.command.user;

import java.util.Arrays;
import java.util.List;

import me.remigio07_.chatplugin.api.common.util.adapter.user.PlayerAdapter;
import me.remigio07_.chatplugin.api.server.language.Language;
import me.remigio07_.chatplugin.api.server.player.ChatPluginServerPlayer;
import me.remigio07_.chatplugin.api.server.player.ServerPlayerManager;
import me.remigio07_.chatplugin.api.server.util.PlaceholderType;
import me.remigio07_.chatplugin.api.server.util.adapter.user.CommandSenderAdapter;
import me.remigio07_.chatplugin.api.server.util.manager.PlaceholderManager;
import me.remigio07_.chatplugin.server.command.BaseCommand;

public class RankInfoCommand extends BaseCommand {
	
	private static final List<PlaceholderType> PLACEHOLDERS = Arrays.asList(PlaceholderType.PLAYER);
	
	public RankInfoCommand() {
		super("/rankinfo <player>");
		tabCompletionArgs.put(0, players);
	}
	
	@Override
	public List<String> getMainArgs() {
		return Arrays.asList("rankinfo", "rinfo", "rank");
	}
	
	@Override
	public void execute(CommandSenderAdapter sender, Language language, String[] args) {
		if (args.length == 1) {
			if (PlayerAdapter.getPlayer(args[0], false) != null) {
				@SuppressWarnings("deprecation")
				ChatPluginServerPlayer player = ServerPlayerManager.getInstance().getPlayer(args[0], false, true);
				
				if (player != null) {
					sender.sendMessage(PlaceholderManager.getInstance().translatePlaceholders(language.getMessage("commands.rankinfo"), player, language, PLACEHOLDERS));
				} else sender.sendMessage(language.getMessage("misc.disabled-world"));
			} else sender.sendMessage(language.getMessage("misc.player-not-found", args[0]));
		} else sendUsage(sender, language);
	}
	
}
