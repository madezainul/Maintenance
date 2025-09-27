package ahqpck.maintenance.report.controller.rest;

import java.util.List;
import java.util.Locale.Category;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ahqpck.maintenance.report.dto.CategoryDTO;
import ahqpck.maintenance.report.dto.DTOMapper;
import ahqpck.maintenance.report.dto.SectionDTO;
import ahqpck.maintenance.report.dto.SupplierDTO;
import ahqpck.maintenance.report.service.CategoryService;
import ahqpck.maintenance.report.service.SectionService;
import ahqpck.maintenance.report.service.SupplierService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LiveSearchController {
    private final CategoryService categoryService;
    private final SupplierService supplierService;
    private final SectionService sectionService;
    private final DTOMapper dtoMapper;

    @GetMapping("/categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.getAll().stream()
                .map(dtoMapper::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/suppliers")
    public List<SupplierDTO> getSuppliers() {
        return supplierService.getAll().stream()
                .map(dtoMapper::mapToSupplierDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/sections")
    public List<SectionDTO> getSections() {
        return sectionService.getAll().stream()
                .map(dtoMapper::mapToSectionDTO)
                .collect(Collectors.toList());
    }
}
