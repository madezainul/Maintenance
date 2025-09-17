package ahqpck.maintenance.report.entity;

import java.util.List;
import java.util.UUID;

import ahqpck.maintenance.report.util.Base62;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "machine_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineType {

    @Id
    @Column(length = 22, updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, length = 2, unique = true)
    @Size(max = 2)
    private String code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "machineType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = Base62.encode(UUID.randomUUID());
        }
    }
}