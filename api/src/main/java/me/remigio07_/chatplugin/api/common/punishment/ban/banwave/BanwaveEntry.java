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

package me.remigio07_.chatplugin.api.common.punishment.ban.banwave;

import java.net.InetAddress;
import java.util.List;

import me.remigio07_.chatplugin.api.common.player.OfflinePlayer;
import me.remigio07_.chatplugin.api.common.punishment.ban.BanType;
import me.remigio07_.chatplugin.api.common.util.annotation.NotNull;
import me.remigio07_.chatplugin.api.common.util.annotation.Nullable;
import me.remigio07_.chatplugin.api.common.util.annotation.ServerImplementationOnly;
import me.remigio07_.chatplugin.api.common.util.text.ChatColor;
import me.remigio07_.chatplugin.api.server.language.Language;

/**
 * Represents a banwave entry handled by the {@link BanwaveManager}. See wiki for more info:
 * <br><a href="https://github.com/Remigio07/ChatPlugin/wiki/Ban-system#banwaves">ChatPlugin wiki/Ban system/Banwaves</a>
 */
public abstract class BanwaveEntry {
	
	/**
	 * Array containing all available placeholders that can
	 * be translated with a banwave entry's information. See wiki for more info:
	 * <br><a href="https://github.com/Remigio07/ChatPlugin/wiki/Ban-system#placeholders">ChatPlugin wiki/Ban system/Placeholders</a>
	 * 
	 * <p><strong>Content:</strong> ["player", "player_uuid", "ip_address", "staff_member", "who_removed", "reason", "server", "type", "date", "removal_date", "duration", "active", "global", "silent"]</p>
	 */
	public static final String[] PLACEHOLDERS = new String[] { "player", "player_uuid", "ip_address", "staff_member", "who_removed", "reason", "server", "type", "date", "removal_date", "duration", "active", "global", "silent" };
	protected OfflinePlayer player;
	protected InetAddress ipAddress;
	protected String staffMember, whoRemoved, reason, server;
	protected BanType type;
	protected long date, duration, removalDate = -1;
	protected boolean global, silent;
	
	protected BanwaveEntry(OfflinePlayer player, InetAddress ipAddress, String staffMember, String reason, String server, BanType type, long date, long duration, boolean global, boolean silent) {
		this.player = player;
		this.ipAddress = ipAddress;
		this.staffMember = staffMember;
		this.reason = reason == null ? null : ChatColor.translate(reason);
		this.server = server;
		this.type = type;
		this.date = date;
		this.duration = duration;
		this.global = global;
		this.silent = silent;
	}
	
	/**
	 * Gets this entry's player.
	 * Will return <code>null</code> if they have not been specified
	 * ({@link BanType#IP_ADDRESS} with no given player).
	 * 
	 * @return Entry's player
	 */
	@Nullable(why = "Player may not have been specified")
	public OfflinePlayer getPlayer() {
		return player;
	}
	
	/**
	 * Gets this entry's IP address.
	 * Will return <code>null</code> if the IP address is unknown.
	 * 
	 * @return Entry's IP address
	 */
	@Nullable(why = "IP address may be unknown")
	public InetAddress getIPAddress() {
		return ipAddress;
	}
	
	/**
	 * Gets who punished the player.
	 * 
	 * @return Entry's staff member
	 */
	@NotNull
	public String getStaffMember() {
		return staffMember;
	}
	
	/**
	 * Sets who punished the player.
	 * 
	 * @param staffMember Entry's staff member
	 */
	public void setStaffMember(@NotNull String staffMember) {
		this.staffMember = staffMember;
	}
	
	/**
	 * Gets who removed the entry.
	 * Will return <code>null</code> if the entry has not been removed.
	 * 
	 * @return Who removed the entry
	 */
	@Nullable(why = "Entry may not have been removed")
	public String getWhoRemoved() {
		return whoRemoved;
	}
	
	/**
	 * Sets who removed the entry.
	 * 
	 * @param whoRemoved Who removed the entry
	 */
	public void setWhoRemoved(@NotNull String whoRemoved) {
		this.whoRemoved = whoRemoved;
	}
	
	/**
	 * Gets this entry's reason.
	 * Will return <code>null</code> if no reason was specified.
	 * 
	 * @return Entry's reason
	 */
	@Nullable(why = "Reason may not have been specified")
	public String getReason() {
		return reason;
	}
	
	/**
	 * Sets this entry's reason.
	 * You can specify <code>null</code> to reset the reason.
	 * 
	 * @param reason Entry's reason
	 */
	public void setReason(@Nullable(why = "Reason may not have been specified") String reason) {
		this.reason = reason;
	}
	
	/**
	 * Gets this entry's origin server.
	 * 
	 * @return Entry's origin server
	 */
	@NotNull
	public String getServer() {
		return server;
	}
	
	/**
	 * Sets this entry's origin server.
	 * 
	 * @param server Entry's origin server
	 */
	public void setServer(@NotNull String server) {
		this.server = server;
	}
	
	/**
	 * Gets this entry's type.
	 * 
	 * @return Entry's type
	 */
	public BanType getType() {
		return type;
	}
	
	/**
	 * Gets this entry's creation or modification date, in milliseconds.
	 * 
	 * @return Entry's creation or modification date
	 */
	public long getDate() {
		return date;
	}
	
	/**
	 * Sets this entry's modification date.
	 * 
	 * @param date Entry's modification date
	 */
	public void setDate(long date) {
		this.date = date;
	}
	
	/**
	 * Gets this entry's removal's date, in milliseconds.
	 * Will return -1 if the entry has not been removed.
	 * 
	 * @return Entry's removal's date
	 */
	public long getRemovalDate() {
		return removalDate;
	}
	
	/**
	 * Sets this entry's removal's date, in milliseconds.
	 * 
	 * @param removalDate Entry's removal's date
	 */
	public void setRemovalDate(long removalDate) {
		this.removalDate = removalDate;
	}
	
	/**
	 * Gets this entry's duration, in milliseconds.
	 * Will return -1 if this entry is permanent.
	 * 
	 * @return Entry's duration
	 */
	public long getDuration() {
		return duration;
	}
	
	/**
	 * Sets this entry's duration, in milliseconds.
	 * You can specify -1 for a permanent entry.
	 * 
	 * @param duration Entry's duration
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	/**
	 * Checks if this entry affects all servers inside of the network
	 * (if using a multi instance setup with a proxy).
	 * 
	 * @return Whether this entry is global
	 */
	public boolean isGlobal() {
		return global;
	}
	
	/**
	 * Sets if this entry should affect all servers inside of the network
	 * (if using a multi instance setup with a proxy).
	 * 
	 * @param global Whether this entry is global
	 */
	public void setGlobal(boolean global) {
		this.global = global;
	}
	
	/**
	 * Checks if this entry is silent
	 * (only Staff members will receive the announcement).
	 * 
	 * @return Whether this entry is silent
	 */
	public boolean isSilent() {
		return silent;
	}
	
	/**
	 * Sets if this entry should be silent
	 * (only Staff members will receive the announcement).
	 * 
	 * @param silent Whether this entry is silent
	 */
	public void setSilent(boolean silent) {
		this.silent = silent;
	}
	
	/**
	 * Translates an input string with this entry's specific placeholders.
	 * Check {@link #PLACEHOLDERS} to know the available placeholders.
	 * 
	 * @param input Input containing placeholders
	 * @param language Language used to translate the placeholders
	 * @return Translated placeholders
	 */
	@ServerImplementationOnly(why = ServerImplementationOnly.NO_LANGUAGES)
	public abstract String formatPlaceholders(String input, Language language);
	
	/**
	 * Translates an input string list with this entry's specific placeholders.
	 * Check {@link #PLACEHOLDERS} to know the available placeholders.
	 * 
	 * @param input Input containing placeholders
	 * @param language Language used to translate the placeholders
	 * @return Translated placeholders
	 */
	@ServerImplementationOnly(why = ServerImplementationOnly.NO_LANGUAGES)
	public abstract List<String> formatPlaceholders(List<String> input, Language language);
	
}