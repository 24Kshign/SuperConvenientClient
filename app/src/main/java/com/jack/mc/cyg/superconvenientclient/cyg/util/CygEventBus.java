package com.jack.mc.cyg.superconvenientclient.cyg.util;


import de.greenrobot.event.EventBus;
import de.greenrobot.event.EventBusBuilder;

/**
 * Delegate了EventBus
 * 调试的时候可以在RzEventBus打断点，调试方便点
 */
public final class CygEventBus {

    private static final class SingletonHolder {
        private static final CygEventBus INSTANCE = new CygEventBus();
    }

    public static CygEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CygEventBus() {
    }

    private final EventBus mEventBus = EventBus.getDefault();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 这个接口没有任何方法
     * 以下类的子类 implements Subscriber 会自动注册和反注册EventBus
     * 1. RzActivity
     * 2. RzFragmentActivity
     * 3. CygFragment
     * 4. BaseModel
     * 5. BasePresenter
     */
    public interface Subscriber {
    }

    public void registerSubscriber(Object subscriber) {
        if (subscriber instanceof Subscriber) {
            register(subscriber);
        }
    }

    public void unregisterSubscriber(Object subscriber) {
        if (subscriber instanceof Subscriber) {
            unregister(subscriber);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void register(Object subscriber) {
        try {
            mEventBus.register(subscriber);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public void register(Object subscriber, int priority) {
        try {
            mEventBus.register(subscriber, priority);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public void registerSticky(Object subscriber) {
        try {
            mEventBus.registerSticky(subscriber);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public void registerSticky(Object subscriber, int priority) {
        try {
            mEventBus.registerSticky(subscriber, priority);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public void unregister(Object subscriber) {
        try {
            mEventBus.unregister(subscriber);
        } catch (Exception e) {
            CygLog.error(e);
        }
    }

    public boolean isRegistered(Object subscriber) {
        try {
            return mEventBus.isRegistered(subscriber);
        } catch (Exception e) {
            CygLog.error(e);
        }
        return false;
    }

    public void post(Object event) {
        mEventBus.post(event);
    }

    public void postSticky(Object event) {
        mEventBus.postSticky(event);
    }

    public <T> T getStickyEvent(Class<T> eventType) {
        return mEventBus.getStickyEvent(eventType);
    }

    public <T> T removeStickyEvent(Class<T> eventType) {
        return mEventBus.removeStickyEvent(eventType);
    }

    public boolean removeStickyEvent(Object event) {
        return mEventBus.removeStickyEvent(event);
    }

    public void removeAllStickyEvents() {
        mEventBus.removeAllStickyEvents();
    }

    public boolean hasSubscriberForEvent(Class<?> eventClass) {
        return mEventBus.hasSubscriberForEvent(eventClass);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static EventBusBuilder builder() {
        return EventBus.builder();
    }

    public static void clearCaches() {
        EventBus.clearCaches();
    }

    public void cancelEventDelivery(Object event) {
        mEventBus.cancelEventDelivery(event);
    }
}
