package vn.nvsoft.zalo.client.model;

import java.io.Serializable;

public class MessageDTO implements Serializable {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
