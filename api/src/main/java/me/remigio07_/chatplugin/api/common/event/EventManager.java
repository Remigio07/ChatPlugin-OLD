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

package me.remigio07_.chatplugin.api.common.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import me.remigio07_.chatplugin.api.common.util.Utils;
import me.remigio07_.chatplugin.api.common.util.annotation.NotNull;
import me.remigio07_.chatplugin.api.common.util.manager.ChatPluginManager;
import me.remigio07_.chatplugin.api.common.util.manager.ChatPluginManagerException;

/**
 * Manager that handles {@link ChatPluginEvent}s and {@link EventSubscriber}s. See wiki for more info:
 * <br><a href="https://github.com/Remigio07/ChatPlugin/wiki/Developer-API#events">ChatPlugin wiki/Developer API/Events</a>
 */
public abstract class EventManager implements ChatPluginManager {
	
	protected static EventManager instance;
	protected boolean enabled;
	protected Map<Class<? extends ChatPluginEvent>, List<EventSubscriber<? extends ChatPluginEvent>>> subscribers = new HashMap<>();
	protected long loadTime;
	
	@Override
	public void unload() throws ChatPluginManagerException {
		enabled = false;
		
		subscribers.clear();
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public boolean isReloadable() {
		return false;
	}
	
	/**
	 * Gets an event's subscribers' list.
	 * 
	 * @param <E> Event's class
	 * @param event Event's class
	 * @return Event's subscribers' list
	 */
	@SuppressWarnings("unchecked")
	@NotNull
	public <E extends ChatPluginEvent> List<EventSubscriber<E>> getSubscribers(Class<E> event) {
		return subscribers.getOrDefault(event, Collections.emptyList()).stream().map(subscriber -> (EventSubscriber<E>) subscriber).collect(Collectors.toList());
	}
	
	/**
	 * Calls an event and passes it to its subscribers.
	 * 
	 * @param <E> Event's class
	 * @param event Event to call
	 */
	@SuppressWarnings("unchecked")
	public <E extends ChatPluginEvent> void call(E event) {
		List<?> subscribers = getSubscribers(event.getClass());
		
		if (subscribers != null && !subscribers.isEmpty())
			subscribers.forEach(subscriber -> ((EventSubscriber<E>) subscriber).getConsumer().accept(event));
	}
	
	/**
	 * Subscribes a new {@link EventSubscriber} and returns it.
	 * 
	 * @param <E> Event's class
	 * @param event Event's class
	 * @param consumer Consumer to execute
	 * @return New event subscriber
	 */
	public <E extends ChatPluginEvent> EventSubscriber<E> subscribe(Class<E> event, Consumer<E> consumer) {
		@SuppressWarnings("deprecation")
		EventSubscriber<E> subscriber = new EventSubscriber<>(event, (Consumer<E>) consumer);
		
		subscribers.put(event, Utils.addAndGet(subscribers.getOrDefault(event, new ArrayList<>()), Arrays.asList(subscriber)));
		return subscriber;
	}
	
	/**
	 * Unsubscribes an existing {@link EventSubscriber}
	 * obtained with {@link #subscribe(Class, Consumer)}.
	 * 
	 * @param <E> Event's class
	 * @param eventSubscriber Event subscriber to unsubscribe
	 */
	public <E extends ChatPluginEvent> void unsubscribe(EventSubscriber<E> eventSubscriber) {
		subscribers.put(eventSubscriber.getEvent(), Utils.removeAndGet(subscribers.getOrDefault(eventSubscriber.getEvent(), new ArrayList<>()), Arrays.asList(eventSubscriber)));
	}
	
	/**
	 * Gets this manager's instance.
	 * 
	 * @return Manager's instance
	 */
	public static EventManager getInstance() {
		return instance;
	}
	
}
