package main.framework.configuration.model.selenium;

import com.fasterxml.jackson.annotation.JsonInclude;
import main.framework.test.mappings.WebElementMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class WebElementMappingCollection implements Map<String, WebElementMapping> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HashMap<String, WebElementMapping> items;

    public WebElementMappingCollection() {
        this.items = new HashMap<>();

        this.items.put("SearchBox", new WebElementMapping("/html/body/div[1]/header/div/div[2]/div[2]/div/div/div/form/label/input[1]", "claudiocidade"));
        this.items.put("ConfirmSearch", new WebElementMapping("//*[@id=\"jump-to-suggestion-search-global\"]/a/div[3]/span[2]", null));
        this.items.put("ResultMessage", new WebElementMapping("//*[@id=\"js-pjax-container\"]/div/div[3]/div/div/h3", "We couldn’t find any repositories matching 'claudiocidade'"));
    }

    public void refreshKeys() {
        this.items.keySet()
                .iterator()
                .forEachRemaining(item -> this.items.get(item).setKey(item));
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
    public boolean containsKey(Object o) {
        return this.items.containsKey(o);
    }

    @Override
    public boolean containsValue(Object o) {
        return this.items.containsValue(o);
    }

    @Override
    public WebElementMapping get(Object o) {
        WebElementMapping result = this.items.get(o);
        result.setKey(String.valueOf(o));
        return result;
    }

    @Override
    public WebElementMapping put(String s, WebElementMapping webElementMapping) {
        return this.items.put(s, webElementMapping);
    }

    @Override
    public WebElementMapping remove(Object o) {
        return this.items.remove(o);
    }

    @Override
    public void putAll(Map<? extends String, ? extends WebElementMapping> map) {
        this.items.putAll(map);
    }

    @Override
    public void clear() {
        this.items.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.items.keySet();
    }

    @Override
    public Collection<WebElementMapping> values() {
        return this.items.values();
    }

    @Override
    public Set<Entry<String, WebElementMapping>> entrySet() {
        return this.items.entrySet();
    }
}
