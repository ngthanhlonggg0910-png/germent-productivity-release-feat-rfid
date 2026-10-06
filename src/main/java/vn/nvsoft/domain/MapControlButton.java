package vn.nvsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MapControlButton.
 */
@Entity
@Table(name = "map_control_button")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MapControlButton implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("mapControlButtons")
    private NVButton sourceButton;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("mapControlButtons")
    private NVButton targetButton;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NVButton getSourceButton() {
        return sourceButton;
    }

    public MapControlButton sourceButton(NVButton nVButton) {
        this.sourceButton = nVButton;
        return this;
    }

    public void setSourceButton(NVButton nVButton) {
        this.sourceButton = nVButton;
    }

    public NVButton getTargetButton() {
        return targetButton;
    }

    public MapControlButton targetButton(NVButton nVButton) {
        this.targetButton = nVButton;
        return this;
    }

    public void setTargetButton(NVButton nVButton) {
        this.targetButton = nVButton;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MapControlButton)) {
            return false;
        }
        return id != null && id.equals(((MapControlButton) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MapControlButton{" +
            "id=" + getId() +
            "}";
    }
}
