package com.sample.page;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Objects;

public class ContactBean{

    @NonNull
    private String content = "";

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactBean)) return false;
        ContactBean that = (ContactBean) o;
        return getContent().equals(that.getContent());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }

    @Override
    public String toString() {
        return "ContactBean{" +
                "content='" + content + '\'' +
                '}';
    }
}