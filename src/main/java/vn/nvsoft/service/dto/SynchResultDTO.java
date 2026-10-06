package vn.nvsoft.service.dto;

import java.io.Serializable;

public class SynchResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer result;
    private String message;

    public SynchResultDTO() {
    }

    public SynchResultDTO(Integer result, String message) {
        this.result = result;
        this.message = message;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
