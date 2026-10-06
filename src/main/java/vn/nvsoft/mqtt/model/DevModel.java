package vn.nvsoft.mqtt.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DevModel {

    private List<String> ids;
	private String name;
    private String mdl;
    private String sw;
    private String mf;
    public DevModel() {
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMdl() {
        return mdl;
    }

    public void setMdl(String mdl) {
        this.mdl = mdl;
    }

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getMf() {
        return mf;
    }

    public void setMf(String mf) {
        this.mf = mf;
    }
}
