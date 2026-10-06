package vn.nvsoft.zalo.client.model;

import java.io.Serializable;

public class ZaloMessage implements Serializable {
    private Recipient recipient;
    private MessageDTO message;

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public MessageDTO getMessage() {
        return message;
    }

    public void setMessage(MessageDTO message) {
        this.message = message;
    }
}
