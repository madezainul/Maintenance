package ahqpck.maintenance.report.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String id;

    @NotBlank(message = "Code is mandatory")
    private String code;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private SerialNumberDTO serialNumber;
    private SupplierDTO supplier;
    private SectionDTO section;

    @NotNull(message = "Stock is required")
    private Integer stock = 0;
}
