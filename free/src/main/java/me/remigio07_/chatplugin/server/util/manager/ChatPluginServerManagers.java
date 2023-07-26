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

package me.remigio07_.chatplugin.server.util.manager;

import me.remigio07_.chatplugin.api.common.discord.DiscordIntegrationManager;
import me.remigio07_.chatplugin.api.common.event.EventManager;
import me.remigio07_.chatplugin.api.common.integration.IntegrationManager;
import me.remigio07_.chatplugin.api.common.ip_lookup.IPLookupManager;
import me.remigio07_.chatplugin.api.common.ip_lookup.LocalIPLookupManager;
import me.remigio07_.chatplugin.api.common.motd.MoTDManager;
import me.remigio07_.chatplugin.api.common.player.PlayerManager;
import me.remigio07_.chatplugin.api.common.punishment.ban.BanManager;
import me.remigio07_.chatplugin.api.common.punishment.ban.banwave.BanwaveManager;
import me.remigio07_.chatplugin.api.common.punishment.kick.KickManager;
import me.remigio07_.chatplugin.api.common.punishment.mute.MuteManager;
import me.remigio07_.chatplugin.api.common.punishment.warning.WarningManager;
import me.remigio07_.chatplugin.api.common.storage.StorageManager;
import me.remigio07_.chatplugin.api.common.telegram.TelegramIntegrationManager;
import me.remigio07_.chatplugin.api.common.util.manager.ChatPluginManagerException;
import me.remigio07_.chatplugin.api.common.util.manager.ChatPluginManagers;
import me.remigio07_.chatplugin.api.common.util.manager.TaskManager;
import me.remigio07_.chatplugin.api.server.actionbar.ActionbarManager;
import me.remigio07_.chatplugin.api.server.ad.AdManager;
import me.remigio07_.chatplugin.api.server.bossbar.BossbarManager;
import me.remigio07_.chatplugin.api.server.chat.ChatManager;
import me.remigio07_.chatplugin.api.server.chat.FormattedChatManager;
import me.remigio07_.chatplugin.api.server.chat.HoverInfoManager;
import me.remigio07_.chatplugin.api.server.chat.InstantEmojisManager;
import me.remigio07_.chatplugin.api.server.chat.PlayerPingManager;
import me.remigio07_.chatplugin.api.server.chat.StaffChatManager;
import me.remigio07_.chatplugin.api.server.chat.antispam.AntispamManager;
import me.remigio07_.chatplugin.api.server.chat.log.ChatLogManager;
import me.remigio07_.chatplugin.api.server.f3servername.F3ServerNameManager;
import me.remigio07_.chatplugin.api.server.gui.GUIManager;
import me.remigio07_.chatplugin.api.server.integration.anticheat.AnticheatManager;
import me.remigio07_.chatplugin.api.server.join_quit.JoinMessageManager;
import me.remigio07_.chatplugin.api.server.join_quit.JoinTitleManager;
import me.remigio07_.chatplugin.api.server.join_quit.MultiAccountCheckManager;
import me.remigio07_.chatplugin.api.server.join_quit.QuitMessageManager;
import me.remigio07_.chatplugin.api.server.join_quit.SuggestedVersionManager;
import me.remigio07_.chatplugin.api.server.join_quit.SwitchMessageManager;
import me.remigio07_.chatplugin.api.server.join_quit.WelcomeMessageManager;
import me.remigio07_.chatplugin.api.server.language.LanguageManager;
import me.remigio07_.chatplugin.api.server.rank.RankManager;
import me.remigio07_.chatplugin.api.server.scoreboard.ScoreboardManager;
import me.remigio07_.chatplugin.api.server.tablist.TablistManager;
import me.remigio07_.chatplugin.api.server.tablist.custom_suffix.CustomSuffixManager;
import me.remigio07_.chatplugin.api.server.util.manager.PingManager;
import me.remigio07_.chatplugin.api.server.util.manager.PlaceholderManager;
import me.remigio07_.chatplugin.api.server.util.manager.ProxyManager;
import me.remigio07_.chatplugin.api.server.util.manager.TPSManager;
import me.remigio07_.chatplugin.api.server.util.manager.VanishManager;
import me.remigio07_.chatplugin.bootstrap.Environment;
import me.remigio07_.chatplugin.common.discord.DummyDiscordIntegrationManager;
import me.remigio07_.chatplugin.common.ip_lookup.DummyLocalIPLookupManager;
import me.remigio07_.chatplugin.common.ip_lookup.IPLookupManagerImpl;
import me.remigio07_.chatplugin.common.punishment.ban.DummyBanManager;
import me.remigio07_.chatplugin.common.punishment.ban.banwave.DummyBanwaveManager;
import me.remigio07_.chatplugin.common.punishment.kick.DummyKickManager;
import me.remigio07_.chatplugin.common.punishment.mute.DummyMuteManager;
import me.remigio07_.chatplugin.common.punishment.warning.DummyWarningManager;
import me.remigio07_.chatplugin.common.storage.database.DatabaseManagerImpl;
import me.remigio07_.chatplugin.common.storage.flat_file.FlatFileManagerImpl;
import me.remigio07_.chatplugin.common.telegram.DummyTelegramIntegrationManager;
import me.remigio07_.chatplugin.common.util.manager.TaskManagerImpl;
import me.remigio07_.chatplugin.server.actionbar.ActionbarManagerImpl;
import me.remigio07_.chatplugin.server.bossbar.DummyBossbarManager;
import me.remigio07_.chatplugin.server.bukkit.integration.BukkitIntegrationManager;
import me.remigio07_.chatplugin.server.bukkit.manager.BukkitAdManager;
import me.remigio07_.chatplugin.server.bukkit.manager.BukkitEventManager;
import me.remigio07_.chatplugin.server.bukkit.manager.BukkitF3ServerNameManager;
import me.remigio07_.chatplugin.server.bukkit.manager.BukkitPlayerManager;
import me.remigio07_.chatplugin.server.chat.ChatManagerImpl;
import me.remigio07_.chatplugin.server.chat.DummyHoverInfoManager;
import me.remigio07_.chatplugin.server.chat.FormattedChatManagerImpl;
import me.remigio07_.chatplugin.server.chat.InstantEmojisManagerImpl;
import me.remigio07_.chatplugin.server.chat.PlayerPingManagerImpl;
import me.remigio07_.chatplugin.server.chat.StaffChatManagerImpl;
import me.remigio07_.chatplugin.server.chat.antispam.AntispamManagerImpl;
import me.remigio07_.chatplugin.server.chat.log.DummyChatLogManager;
import me.remigio07_.chatplugin.server.gui.DummyGUIManager;
import me.remigio07_.chatplugin.server.integration.anticheat.AnticheatManagerImpl;
import me.remigio07_.chatplugin.server.join_quit.DummyMultiAccountCheckManager;
import me.remigio07_.chatplugin.server.join_quit.DummySwitchMessageManager;
import me.remigio07_.chatplugin.server.join_quit.JoinMessageManagerImpl;
import me.remigio07_.chatplugin.server.join_quit.JoinTitleManagerImpl;
import me.remigio07_.chatplugin.server.join_quit.QuitMessageManagerImpl;
import me.remigio07_.chatplugin.server.join_quit.SuggestedVersionManagerImpl;
import me.remigio07_.chatplugin.server.join_quit.WelcomeMessageManagerImpl;
import me.remigio07_.chatplugin.server.language.LanguageManagerImpl;
import me.remigio07_.chatplugin.server.motd.DummyServerMoTDManager;
import me.remigio07_.chatplugin.server.rank.RankManagerImpl;
import me.remigio07_.chatplugin.server.scoreboard.DummyScoreboardManager;
import me.remigio07_.chatplugin.server.sponge.integration.SpongeIntegrationManager;
import me.remigio07_.chatplugin.server.sponge.manager.SpongeAdManager;
import me.remigio07_.chatplugin.server.sponge.manager.SpongeEventManager;
import me.remigio07_.chatplugin.server.sponge.manager.SpongeF3ServerNameManager;
import me.remigio07_.chatplugin.server.sponge.manager.SpongePlayerManager;
import me.remigio07_.chatplugin.server.tablist.TablistManagerImpl;
import me.remigio07_.chatplugin.server.tablist.custom_suffix.DummyCustomSuffixManager;

public class ChatPluginServerManagers extends ChatPluginManagers {
	
	public ChatPluginServerManagers() {
		instance = this;
	}
	
	@Override
	public void loadManagers() throws ChatPluginManagerException {
		addManager(TaskManager.class, new TaskManagerImpl());
		addManager(StorageManager.class, getStorageMethod().isDatabase() ? new DatabaseManagerImpl() : new FlatFileManagerImpl());
		addManager(ProxyManager.class, new ProxyManagerImpl());
		addManager(IPLookupManager.class, new IPLookupManagerImpl());
		addManager(LocalIPLookupManager.class, new DummyLocalIPLookupManager());
		addManager(LanguageManager.class, new LanguageManagerImpl());
		addManager(RankManager.class, new RankManagerImpl());
		addManager(EventManager.class, Environment.isBukkit() ? new BukkitEventManager() : new SpongeEventManager());
		addManager(IntegrationManager.class, Environment.isBukkit() ? new BukkitIntegrationManager() : new SpongeIntegrationManager());
		addManager(AnticheatManager.class, new AnticheatManagerImpl());
		addManager(VanishManager.class, new VanishManagerImpl());
		addManager(JoinMessageManager.class, new JoinMessageManagerImpl());
		addManager(QuitMessageManager.class, new QuitMessageManagerImpl());
		addManager(SwitchMessageManager.class, new DummySwitchMessageManager());
		addManager(JoinTitleManager.class, new JoinTitleManagerImpl());
		addManager(WelcomeMessageManager.class, new WelcomeMessageManagerImpl());
		addManager(MultiAccountCheckManager.class, new DummyMultiAccountCheckManager());
		addManager(PlayerManager.class, Environment.isBukkit() ? new BukkitPlayerManager() : new SpongePlayerManager());
		addManager(BanManager.class, new DummyBanManager());
		addManager(BanwaveManager.class, new DummyBanwaveManager());
		addManager(WarningManager.class, new DummyWarningManager());
		addManager(KickManager.class, new DummyKickManager());
		addManager(MuteManager.class, new DummyMuteManager());
		addManager(PlaceholderManager.class, new PlaceholderManagerImpl());
		addManager(SuggestedVersionManager.class, new SuggestedVersionManagerImpl());
		addManager(TPSManager.class, new TPSManagerImpl());
		addManager(PingManager.class, new PingManagerImpl());
		addManager(ChatManager.class, new ChatManagerImpl());
		addManager(ChatLogManager.class, new DummyChatLogManager());
		addManager(StaffChatManager.class, new StaffChatManagerImpl());
		addManager(HoverInfoManager.class, new DummyHoverInfoManager());
		addManager(AntispamManager.class, new AntispamManagerImpl());
		addManager(FormattedChatManager.class, new FormattedChatManagerImpl());
		addManager(InstantEmojisManager.class, new InstantEmojisManagerImpl());
		addManager(PlayerPingManager.class, new PlayerPingManagerImpl());
		addManager(ScoreboardManager.class, new DummyScoreboardManager());
		addManager(TablistManager.class, new TablistManagerImpl());
		addManager(CustomSuffixManager.class, new DummyCustomSuffixManager());
		addManager(BossbarManager.class, new DummyBossbarManager());
		addManager(ActionbarManager.class, new ActionbarManagerImpl());
		addManager(AdManager.class, Environment.isBukkit() ? new BukkitAdManager() : new SpongeAdManager());
		addManager(F3ServerNameManager.class, Environment.isBukkit() ? new BukkitF3ServerNameManager() : new SpongeF3ServerNameManager());
		addManager(GUIManager.class, new DummyGUIManager());
		addManager(MoTDManager.class, new DummyServerMoTDManager());
		addManager(DiscordIntegrationManager.class, new DummyDiscordIntegrationManager());
		addManager(TelegramIntegrationManager.class, new DummyTelegramIntegrationManager());
		
		PlayerManager.getInstance().loadOnlinePlayers();
	}
	
}
