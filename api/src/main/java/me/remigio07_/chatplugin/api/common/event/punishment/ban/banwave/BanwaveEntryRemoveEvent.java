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

package me.remigio07_.chatplugin.api.common.event.punishment.ban.banwave;

import me.remigio07_.chatplugin.api.common.punishment.ban.banwave.BanwaveEntry;

/**
 * Represents the event called after a banwave entry gets removed from the entries' list.
 */
public class BanwaveEntryRemoveEvent extends BanwaveEntryAddEvent {
	
	/**
	 * Constructs a new banwave entry remove event.
	 * 
	 * @param entry Entry involved
	 */
	public BanwaveEntryRemoveEvent(BanwaveEntry entry) {
		super(entry);
	}
	
}
