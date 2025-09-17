package ahqpck.maintenance.report.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import ahqpck.maintenance.report.entity.Complaint;
import ahqpck.maintenance.report.entity.WorkReport;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkReportDTO {

    private String id;

    private String code;

    private WorkReport.Shift shift;

    private LocalDate reportDate;

    private LocalDateTime updatedAt;

    private AreaDTO area;

    // @NotNull(message = "Equipment is mandatory")
    private EquipmentDTO equipment;

    @NotNull(message = "Category is mandatory")
    private WorkReport.Category category;

    private String problem;

    private String solution;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime stopTime;

    private Set<String> technicianEmpIds = new HashSet<>();
    private Set<UserDTO> technicians = new HashSet<>();

    private UserDTO supervisor;

    @NotNull(message = "Status is mandatory")
    private WorkReport.Status status;

    private WorkReport.Scope scope;

    private String workType;

    private String remark;

    @Valid
    private List<WorkReportPartDTO> partsUsed = new ArrayList<>();

    private Integer totalResolutionTimeMinutes;

    private String resolutionTimeDisplay;

//     public Set<String> getTechnicianEmpIds() {
//     if (technicians == null) return new HashSet<>();
//     return technicians.stream()
//             .map(UserDTO::getEmployeeId)
//             .filter(id -> id != null && !id.trim().isEmpty())
//             .map(String::trim)
//             .collect(Collectors.toSet());
// }
}