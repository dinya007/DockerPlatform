package ru.tisov.denis.platform.domain;

import java.util.List;

public class Image {

    private String name;
    private List<String> tags;

    public Image() {
    }

    public Image(String name, List<String> tags) {
        this.name = name;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tag) {
        this.tags = tag;
    }
}
