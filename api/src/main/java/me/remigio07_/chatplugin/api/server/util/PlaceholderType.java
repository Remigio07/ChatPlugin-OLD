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

package me.remigio07_.chatplugin.api.server.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import me.remigio07_.chatplugin.api.common.util.annotation.NotNull;
import me.remigio07_.chatplugin.api.common.util.annotation.Nullable;

/**
 * Represents all ChatPlugin's integrated placeholders plus PlaceholderAPI and MVdWPlaceholderAPI extensions.
 * Read the wiki for more info:
 * <br><a href="https://github.com/Remigio07/ChatPlugin/wiki/Placeholders">ChatPlugin wiki/Placeholders</a>
 */
public enum PlaceholderType {
	
	/**
	 * Will translate only the {player} placeholder.
	 */
	JUST_NAME(new String[] { "player" }),
	
	/**
	 * Represents player-related placeholders.
	 */
	PLAYER(new String[] {
			"player", "uuid", "ip_address", "health", "max_health", "food", "level", "xp", "gamemode",
			"ping", "ping_format", "language_id", "language_display_name", "locale", "version", "version_protocol", "last_login", "time_played",
			"world", "online_world", "vanished_world",
			"player_id", "player_bans", "player_warnings", "player_kicks", "player_mutes", "messages_sent", "player_anticheat_bans", "player_anticheat_warnings", "player_anticheat_kicks", "player_anticheat_mutes",
			"x", "y", "z", "yaw", "pitch",
			"rank", "prefix", "suffix", "tag_prefix", "tag_suffix", "tag_color", "chat_color", "rank_description",
			"isp", "continent", "country", "subdivisions", "city", "country_code", "postal_code", "latitude", "longitude", "accuracy_radius_km", "accuracy_radius_mi", "accuracy_radius_nm"
			}),
	
	/**
	 * Represents server-side placeholders.
	 */
	SERVER(new String[] {
			"online", "online_total", "max_players", "vanished",
			"date_full", "date_day", "date_hour",
			"enabled_worlds", "enabled_players", "enabled_managers", "startup_time", "last_reload_time", "uptime",
			"plugin_version", "server_version", "server_version_protocol", "server_nms_version", "server_java_version",
			"server_id", "server_display_name", "main_language_id", "main_language_display_name",
			"total_storage", "used_storage", "free_storage", "server_os_name", "server_os_arch", "server_os_version", "active_threads",
			"total_players", "total_bans", "total_warnings", "total_kicks", "total_mutes", "total_staff_bans", "total_staff_warnings", "total_staff_kicks", "total_staff_mutes", "total_anticheat_bans", "total_anticheat_warnings", "total_anticheat_kicks", "total_anticheat_mutes",
			"tps_1_min", "tps_5_min", "tps_15_min", "tps_1_min_format", "tps_5_min_format", "tps_15_min_format", "max_memory", "total_memory", "used_memory", "free_memory", "cpu_cores",
			"discord_punishments_channel_id", "discord_staff_notifications_channel_id"
			}),
	
	/**
	 * Represents integrations' placeholders (includes also PlaceholderAPI's and MVdWPlaceholderAPI's).
	 */
	INTEGRATIONS(new String[] { "balance" });
	
	private String[] placeholders;
	
	private PlaceholderType(String[] placeholders) {
		this.placeholders = placeholders;
	}
	
	/**
	 * Gets the list of available placeholders for this placeholder type. Note that if
	 * <code>this == {@link #INTEGRATIONS}</code> an array with just some elements will be returned,
	 * even if this includes all the placeholders from PlaceholderAPI and MVdWPlaceholderAPI.
	 * 
	 * @return Array of available placeholders
	 */
	public String[] getPlaceholders() {
		return placeholders;
	}
	
	/**
	 * Gets a placeholder type from an input String. The input should
	 * be equals (ignoring case) to one of this enum's constants.
	 * If not, <code>null</code> will be returned.
	 * 
	 * @param input {@link #name()}, ignoring case
	 * @return Requested placeholder type or <code>null</code> if the input is invalid
	 */
	@Nullable(why = "Input may not correspond to any placeholder type")
	public static PlaceholderType getPlaceholder(String input) {
		for (PlaceholderType placeholders : values())
			if (placeholders.name().equalsIgnoreCase(input))
				return placeholders;
		return null;
	}
	
	/**
	 * Gets a placeholder types list from an input String list.
	 * 
	 * @param input Input list containing {@link #name()}s, ignoring case
	 * @return Requested placeholder types
	 * @see #getPlaceholder(String)
	 */
	@NotNull
	public static List<PlaceholderType> getPlaceholders(List<String> input) {
		return input.stream().map(PlaceholderType::getPlaceholder).filter(Objects::nonNull).collect(Collectors.toCollection(ArrayList::new));
	}
	
	/**
	 * Gets the placeholder type that contains the given identifier.
	 * Will return <code>null</code> if no placeholder types
	 * contain the specified identifier.
	 * 
	 * @param identifierWithoutBrackets Identifier to check
	 * @return Requested placeholder type
	 */
	@Nullable(why = "Identifier may not belong to any placeholder type")
	public static PlaceholderType getPlaceholderType(String identifierWithoutBrackets) {
		for (PlaceholderType type : values())
			for (String identifier : type.getPlaceholders())
				if (identifierWithoutBrackets.equalsIgnoreCase(identifier))
					return type;
		return null;
	}
	
	/**
	 * Counts the supported placeholders.
	 * 
	 * @return Placeholders' count
	 */
	public static int count() {
		return PLAYER.getPlaceholders().length + SERVER.getPlaceholders().length + INTEGRATIONS.getPlaceholders().length;
	}
	
}
