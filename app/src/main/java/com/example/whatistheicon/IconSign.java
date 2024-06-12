package com.example.whatistheicon;

public class IconSign {
    private String description;
    private String icon;

    public IconSign() {
    }

    public IconSign(String description, String icon) {
        this.description = description;
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "IconSign{" +
                "description='" + description + '\'' +
                ", Icon='" + icon + '\'' +
                '}';
    }
}
