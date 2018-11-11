package com.wero.gec.githubRepositories;

import java.io.Serializable;

public class Repository implements Serializable {
    private String name;
    private String url;
    private String description;

    public Repository(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public Repository(String name) {
        this.name = name;
        this.url = null;
        this.description = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
