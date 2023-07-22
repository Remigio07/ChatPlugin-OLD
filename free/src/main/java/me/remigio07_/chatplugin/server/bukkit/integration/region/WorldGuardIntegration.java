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

package me.remigio07_.chatplugin.server.bukkit.integration.region;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.EventExecutor;

import com.google.common.collect.Iterables;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import me.remigio07_.chatplugin.api.common.event.EventManager;
import me.remigio07_.chatplugin.api.common.integration.IntegrationType;
import me.remigio07_.chatplugin.api.common.player.PlayerManager;
import me.remigio07_.chatplugin.api.server.integration.region.RegionIntegration;
import me.remigio07_.chatplugin.api.server.player.ChatPluginServerPlayer;
import me.remigio07_.chatplugin.api.server.player.ServerPlayerManager;
import me.remigio07_.chatplugin.api.server.scoreboard.event.ScoreboardEvent;
import me.remigio07_.chatplugin.bootstrap.BukkitBootstrapper;
import me.remigio07_.chatplugin.server.bukkit.integration.ChatPluginBukkitIntegration;
import me.remigio07_.chatplugin.server.bukkit.manager.BukkitEventManager;

public class WorldGuardIntegration extends ChatPluginBukkitIntegration<RegionIntegration> implements RegionIntegration {
	
	public WorldGuardIntegration() {
		super(IntegrationType.WORLDGUARD);
	}
	
	@Override
	protected void loadAPI() {
		api = WorldGuard.getInstance().getPlatform().getRegionContainer();
		
		Bukkit.getPluginManager().registerEvent(PlayerMoveEvent.class, ((BukkitEventManager) EventManager.getInstance()).getListener(), EventPriority.MONITOR, new EventExecutor() {
			
			@Override
			public void execute(Listener listener, Event event) throws EventException {
				if (((ServerPlayerManager) PlayerManager.getInstance()).isWorldEnabled(((PlayerMoveEvent) event).getTo().getWorld().getName())) {
					Location from = ((PlayerMoveEvent) event).getFrom();
					Location to = ((PlayerMoveEvent) event).getTo();
					
					if (!from.getWorld().equals(to.getWorld()) || from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
						String oldRegion = getRegionID(from);
						String newRegion = getRegionID(to);
						
						if (oldRegion == null) {
							if (newRegion != null) {
								// 1
								((BukkitEventManager) EventManager.getInstance()).applyScoreboard(ScoreboardEvent.REGION_ENTER, ((PlayerMoveEvent) event).getPlayer(), newRegion);
							} // else 3 - do nothing
						} else if (newRegion == null) {
							// 2
							((BukkitEventManager) EventManager.getInstance()).applyScoreboard(ScoreboardEvent.REGION_LEAVE, ((PlayerMoveEvent) event).getPlayer(), oldRegion);
						} else if (!oldRegion.equals(newRegion)) {
							// 4
							((BukkitEventManager) EventManager.getInstance()).applyScoreboard(ScoreboardEvent.REGION_ENTER, ((PlayerMoveEvent) event).getPlayer(), newRegion);
						} // else 5 - do nothing
					}
				}
			}
		}, BukkitBootstrapper.getInstance());
	}
	
	@Override
	public String getRegionID(ChatPluginServerPlayer player) {
		return getRegionID(player.toAdapter().bukkitValue().getLocation());
	}
	
	private String getRegionID(Location location) {
		ProtectedRegion region = Iterables.getFirst(((RegionContainer) api).get(BukkitAdapter.adapt(location.getWorld())).getApplicableRegions(BlockVector3.at(location.getBlockX(), location.getBlockY(), location.getBlockZ())).getRegions(), null);
		
		return region == null ? null : region.getId();
	}
	
}
