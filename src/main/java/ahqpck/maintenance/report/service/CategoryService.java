package ahqpck.maintenance.report.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ahqpck.maintenance.report.dto.CategoryDTO;
import ahqpck.maintenance.report.entity.Category;
import ahqpck.maintenance.report.entity.MachineType;
import ahqpck.maintenance.report.repository.CategoryRepository;
import ahqpck.maintenance.report.repository.MachineTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepo;
    private final MachineTypeRepository machineTypeRepo;

    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    public Category getByCode(String code) {
        return categoryRepo.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + code));
    }

    public Category createCategory(CategoryDTO dto) {
        if (categoryRepo.existsByCodeIgnoreCaseAndNameNot(dto.getCode(), dto.getName()))
            throw new IllegalArgumentException("Duplicate category code: " + dto.getCode());
        MachineType mt = machineTypeRepo.findByCode(dto.getMachineType().getCode())
                .orElseThrow(() -> new IllegalArgumentException("Machine Type not found: " + dto.getMachineType().getCode()));
            dto.getMachineType().getCode();
        Category cat = new Category();
        cat.setCode(dto.getCode().trim().toUpperCase());
        cat.setName(dto.getName().trim());
        cat.setMachineType(mt);
        return categoryRepo.save(cat);
    }
}
