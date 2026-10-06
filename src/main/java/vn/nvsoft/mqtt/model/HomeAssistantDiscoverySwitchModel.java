package vn.nvsoft.mqtt.model;

public class HomeAssistantDiscoverySwitchModel {

    private String name;
    private String stat_t;
    private String avty_t;
    private String pl_avail;
    private String pl_not_avail;
    private String cmd_t;
    private String val_tpl;
    private String pl_off;
    private String pl_on;
    private String uniq_id;
    private DevModel dev;

    public HomeAssistantDiscoverySwitchModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStat_t() {
        return stat_t;
    }

    public void setStat_t(String stat_t) {
        this.stat_t = stat_t;
    }

    public String getAvty_t() {
        return avty_t;
    }

    public void setAvty_t(String avty_t) {
        this.avty_t = avty_t;
    }

    public String getPl_avail() {
        return pl_avail;
    }

    public void setPl_avail(String pl_avail) {
        this.pl_avail = pl_avail;
    }

    public String getPl_not_avail() {
        return pl_not_avail;
    }

    public void setPl_not_avail(String pl_not_avail) {
        this.pl_not_avail = pl_not_avail;
    }

    public String getCmd_t() {
        return cmd_t;
    }

    public void setCmd_t(String cmd_t) {
        this.cmd_t = cmd_t;
    }

    public String getVal_tpl() {
        return val_tpl;
    }

    public void setVal_tpl(String val_tpl) {
        this.val_tpl = val_tpl;
    }

    public String getPl_off() {
        return pl_off;
    }

    public void setPl_off(String pl_off) {
        this.pl_off = pl_off;
    }

    public String getPl_on() {
        return pl_on;
    }

    public void setPl_on(String pl_on) {
        this.pl_on = pl_on;
    }

    public String getUniq_id() {
        return uniq_id;
    }

    public void setUniq_id(String uniq_id) {
        this.uniq_id = uniq_id;
    }

    public DevModel getDev() {
        return dev;
    }

    public void setDev(DevModel dev) {
        this.dev = dev;
    }
}
