package com.resume.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TextListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<String> items;

    public TextListSection() {
    }

    public TextListSection(String... items) {
        this(Arrays.asList(items));
    }

    public TextListSection(List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    public String getItem(int i) {
        return items.get(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextListSection that = (TextListSection) o;
        return items.equals(that.items);
    }

    @Override
    public String toHtml() {
        return String.join("\n", items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
        }
        return String.valueOf(sb);
    }
}
