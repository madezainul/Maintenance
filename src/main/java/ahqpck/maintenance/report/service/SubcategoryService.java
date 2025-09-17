package ahqpck.maintenance.report.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ahqpck.maintenance.report.dto.SubcategoryDTO;
import ahqpck.maintenance.report.entity.Category;
import ahqpck.maintenance.report.entity.Subcategory;
import ahqpck.maintenance.report.repository.CategoryRepository;
import ahqpck.maintenance.report.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepo;
    private final CategoryRepository categoryRepo;

    public List<Subcategory> getAll() {
        return subcategoryRepo.findAll();
    }

    public Subcategory getByCode(String code) {
        return subcategoryRepo.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Subcategory with code " + code + " not found"));
    }

    public Subcategory createSubcategory(SubcategoryDTO dto) {
        if (subcategoryRepo.existsByCodeIgnoreCaseAndNameNot(dto.getCode(), dto.getName()))
            throw new IllegalArgumentException("Duplicate subcategory code: " + dto.getCode());
        Category cat = categoryRepo.findByCode(dto.getCategory().getCode())
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + dto.getCategory().getCode()));
        Subcategory subcat = new Subcategory();
        subcat.setCode(dto.getCode().trim().toUpperCase());
        subcat.setName(dto.getName().trim());
        subcat.setCategory(cat);
        return subcategoryRepo.save(subcat);
    }
}
