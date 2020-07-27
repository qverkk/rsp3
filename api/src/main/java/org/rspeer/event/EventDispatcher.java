package org.rspeer.event;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import org.rspeer.event.listener.EventListener;

public class EventDispatcher {

    private final Map<Class<?>, Set<EventListener>> listeners = new ConcurrentHashMap<>();

    public void subscribe(EventListener el) {
        Set<Class<?>> interfaces = getListenerInterfaces(el.getClass());
        interfaces.forEach(group -> {
            if (!listeners.containsKey(group)) {
                listeners.put(group, new CopyOnWriteArraySet<>());
            }
            listeners.get(group).add(el);
        });
    }

    public void unsubscribe(EventListener el) {
        Set<Class<?>> interfaces = getListenerInterfaces(el.getClass());
        interfaces.forEach(group -> {
            if (listeners.containsKey(group)) {
                Set<EventListener> listenersGroup = listeners.get(group);
                listenersGroup.remove(el);

                if (listenersGroup.isEmpty()) {
                    listeners.remove(group);
                }
            }
        });
    }

    public void dispatch(Event<?, ?> e) {
        Class<?> group = e.getListenerClass();
        Set<EventListener> listenersGroup = listeners.getOrDefault(group, Collections.emptySet());
        listenersGroup.forEach(e::dispatch);
    }

    private Set<Class<?>> getListenerInterfaces(Class<?> clazz) {
        Set<Class<?>> interfaces = new HashSet<>();
        if (clazz != null && !clazz.equals(Object.class)) {
            // First we check the directly implemented interfaces
            Set<Class<?>> impl = Arrays.stream(clazz.getInterfaces())
                                       .filter(this::isEventListenerInterface)
                                       .collect(Collectors.toSet());
            interfaces.addAll(impl);

            // Then we check interfaces implemented by the SuperClass
            Class<?> superClass = clazz.getSuperclass();
            Set<Class<?>> superImpl = getListenerInterfaces(superClass);
            interfaces.addAll(superImpl);
        }
        return interfaces;
    }

    private boolean isEventListenerInterface(Class<?> clazz) {
        if (clazz.isInterface()) {
            // First we check if the class itself is an EventListener
            if (clazz.equals(EventListener.class)) {
                return true;
            }
            // Then we check if any of the SuperInterfaces is an EventListener
            return Arrays.stream(clazz.getInterfaces())
                         .anyMatch(this::isEventListenerInterface);
        }
        return false;
    }
}
