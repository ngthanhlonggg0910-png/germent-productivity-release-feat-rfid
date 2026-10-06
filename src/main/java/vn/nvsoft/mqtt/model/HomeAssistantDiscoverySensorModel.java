package vn.nvsoft.mqtt.model;

public class HomeAssistantDiscoverySensorModel {

    private String name;
    private String stat_t;
    private String avty_t;
    private String pl_avail;
    private String pl_not_avail;
    private String json_attr_t;
    private String unit_of_meas;
    private String val_tpl;
    private String ic;
    private String uniq_id;
    private DevModel dev;

    public HomeAssistantDiscoverySensorModel() {
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

    public String getVal_tpl() {
        return val_tpl;
    }

    public void setVal_tpl(String val_tpl) {
        this.val_tpl = val_tpl;
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

    public String getJson_attr_t() {
        return json_attr_t;
    }

    public void setJson_attr_t(String json_attr_t) {
        this.json_attr_t = json_attr_t;
    }

    public String getUnit_of_meas() {
        return unit_of_meas;
    }

    public void setUnit_of_meas(String unit_of_meas) {
        this.unit_of_meas = unit_of_meas;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }
}
