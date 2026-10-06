package vn.nvsoft.zalo.client.model;

import java.util.List;
import java.util.Objects;

public class LoginDO extends BasicDO {
    private String name;
    private String jid;
    private String server;
    private boolean search;
    private String token;

    public LoginDO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDO loginDO = (LoginDO) o;
        return getError() == loginDO.getError() &&
                search == loginDO.search &&
                Objects.equals(getMessage(), loginDO.getMessage()) &&
                Objects.equals(name, loginDO.name) &&
                Objects.equals(jid, loginDO.jid) &&
                Objects.equals(server, loginDO.server) &&
                Objects.equals(token, loginDO.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getError(), getMessage(), name, jid, server, search, token);
    }

    @Override
    public String toString() {
        return "LoginDO{" +
                "errorCode=" + getError() +
                ", error='" + getMessage() + '\'' +
                ", name='" + name + '\'' +
                ", jid='" + jid + '\'' +
                ", server='" + server + '\'' +
                ", search=" + search +
                ", token='" + token + '\'' +
                '}';
    }
}
