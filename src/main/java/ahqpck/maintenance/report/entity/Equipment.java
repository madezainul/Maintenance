package ahqpck.maintenance.report.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import ahqpck.maintenance.report.util.Base62;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @Column(length = 22, updatable = false, nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    private String model;
    private String unit;

    private Integer qty = 0;

    private String manufacturer;
    private String serialNo;

    @Column(name = "manufactured_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufacturedDate;

    @Column(name = "commissioned_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate commissionedDate;

    private String capacity;
    private String remarks;

    private String image; // filename only

    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    private final Set<Complaint> complaints = new HashSet<>();

    @OneToMany(mappedBy = "equipment", fetch = FetchType.LAZY)
    private final Set<WorkReport> workReports = new HashSet<>();

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = Base62.encode(UUID.randomUUID());
        }
    }
}
