package me.freshtea.utilmod.eventbus;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    private Map<Class<? extends Event>, List<Subscription>> handles = new ConcurrentHashMap<>();

    public void register(Object obj) {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();

        for (Method method : obj.getClass().getMethods()) {
            Listen listen = method.getAnnotation(Listen.class);

            if (listen != null) {
                Class<?>[] paramTypes = method.getParameterTypes();

                if (paramTypes.length != 1)
                    throw new IndexOutOfBoundsException("Listener method should have exactly one parameter.");

                if (!Event.class.isAssignableFrom(paramTypes[0]))
                    throw new RuntimeException("Listener parameter should be an event class.");

                try {
                    handles.computeIfAbsent((Class<? extends Event>) paramTypes[0], k -> new CopyOnWriteArrayList<>())
                            .add(new Subscription(listen.priority(), obj, lookup.unreflect(method)));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        for (List<Subscription> subList : handles.values())
            subList.sort((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority()));
    }

    public void setActive(Object obj, boolean state) {
        for (List<Subscription> subList : handles.values())
            for (Subscription sub : subList)
                if (sub.getObject().equals(obj))
                    sub.setActive(state);
    }

    public void post(Event event) {
        List<Subscription> subList = handles.get(event.getClass());

        if (subList != null) {
            for (Subscription sub : subList) {
                try {
                    sub.tryInvoke(event);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }

}
