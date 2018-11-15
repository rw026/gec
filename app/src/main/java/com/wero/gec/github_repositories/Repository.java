package com.wero.gec.github_repositories;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Repository implements Serializable, Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<Repository> CREATOR = new Parcelable.Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel source) {
            return new Repository(source.readString(), source.readString(), source.readString());
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}
