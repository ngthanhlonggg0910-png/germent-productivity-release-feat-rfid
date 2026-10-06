package vn.nvsoft.domain;

import java.util.List;

public class NotificationMessage {
    private List<String> users;
    private String title;
    private String message;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationMessage{" +
            "users=" + users +
            ", title='" + title + '\'' +
            ", message='" + message + '\'' +
            '}';
    }
}
