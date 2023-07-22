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

package me.remigio07_.chatplugin.api.common.event.punishment.mute;

import me.remigio07_.chatplugin.api.common.player.OfflinePlayer;
import me.remigio07_.chatplugin.api.common.punishment.mute.Mute;
import me.remigio07_.chatplugin.api.common.punishment.mute.MuteManager;

/**
 * Represents the event called after a mute gets updated.
 * 
 * @see MuteManager#mute(OfflinePlayer, String, String, String, long, boolean, boolean)
 */
public class MuteUpdateEvent extends MuteEvent {
	
	private Mute oldMute;
	
	/**
	 * Constructs a new mute update event.
	 * 
	 * @param oldMute Old mute's copy
	 * @param mute Mute involved
	 */
	public MuteUpdateEvent(Mute oldMute, Mute mute) {
		super(mute);
		this.oldMute = oldMute;
	}
	
	/**
	 * Gets a copy of {@link #getMute()} with its old values.
	 * 
	 * @return Old mute's copy
	 */
	public Mute getOldMute() {
		return oldMute;
	}
	
}
