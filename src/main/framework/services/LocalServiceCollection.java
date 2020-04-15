package main.framework.services;

import java.util.*;

public class LocalServiceCollection implements Map<String, ILocalService> {
    private final HashMap<String, ILocalService> items;

    public LocalServiceCollection() {
        this.items = new HashMap<>();
    }

    @Override
    public int size() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.items.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.items.containsValue(value);
    }

    @Override
    public ILocalService get(Object key) {
        return this.items.get(key);
    }

    public <T extends ILocalService> T get(String key) {
        ILocalService localService = this.items.get(key);

        return (T)localService;
    }

    @Override
    public ILocalService put(String key, ILocalService localServiceInstance) {
        return this.items.put(key, localServiceInstance);
    }

    @Override
    public ILocalService remove(Object key) {
        this.items.get(key).close();

        return this.items.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends ILocalService> map) {
        this.items.putAll(map);
    }

    @Override
    public void clear() {
        this.items.values().forEach(ILocalService::close);

        this.items.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.items.keySet();
    }

    @Override
    public Collection<ILocalService> values() {
        return this.items.values();
    }

    @Override
    public Set<Entry<String, ILocalService>> entrySet() {
        return this.items.entrySet();
    }
}
