package vn.nvsoft.zalo.client.model;

import java.util.Objects;

public class LogoutDO extends BasicDO {

    private boolean search;

    public LogoutDO() {
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogoutDO logoutDO = (LogoutDO) o;
        return getError() == logoutDO.getError() &&
                search == logoutDO.search &&
                Objects.equals(getMessage(), logoutDO.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getError(), getMessage(), search);
    }

    @Override
    public String toString() {
        return "LogoutDO{" +
                "errorCode=" + getError() +
                ", error='" + getMessage() + '\'' +
                ", search=" + search +
                '}';
    }
}
