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

package me.remigio07_.chatplugin.api.common.event.punishment.ban;

import me.remigio07_.chatplugin.api.common.player.OfflinePlayer;
import me.remigio07_.chatplugin.api.common.punishment.ban.Ban;
import me.remigio07_.chatplugin.api.common.punishment.ban.BanManager;

/**
 * Represents the event called after a ban gets updated.
 * 
 * @see BanManager#ban(OfflinePlayer, String, String, String, long, boolean, boolean)
 * @see BanManager#banIP(OfflinePlayer, String, String, String, long, boolean, boolean)
 * @see BanManager#banIP(String, String, String, String, long, boolean, boolean)
 */
public class BanUpdateEvent extends BanEvent {
	
	private Ban oldBan;
	
	/**
	 * Constructs a new ban update event.
	 * 
	 * @param oldBan Old ban's copy
	 * @param ban Ban involved
	 */
	public BanUpdateEvent(Ban oldBan, Ban ban) {
		super(ban);
		this.oldBan = oldBan;
	}
	
	/**
	 * Gets a copy of {@link #getBan()} with its old values.
	 * 
	 * @return Old ban's copy
	 */
	public Ban getOldBan() {
		return oldBan;
	}
	
}