package ahqpck.maintenance.report.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ahqpck.maintenance.report.dto.SerialNumberDTO;
import ahqpck.maintenance.report.entity.SerialNumber;
import ahqpck.maintenance.report.entity.Subcategory;
import ahqpck.maintenance.report.repository.SerialNumberRepository;
import ahqpck.maintenance.report.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SerialNumberService {
    private final SerialNumberRepository serialNumberRepo;
    private final SubcategoryRepository subcategoryRepo;

    public List<SerialNumber> getAll() {
        return serialNumberRepo.findAll();
    }

    public SerialNumber getByCode(String code) {
        return serialNumberRepo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Serial number not found with code: " + code));
    }

    public SerialNumber createSerialNumber(SerialNumberDTO dto) {
        if (serialNumberRepo.existsByCodeIgnoreCaseAndNameNot(dto.getCode(), dto.getName()))
            throw new IllegalArgumentException("Duplicate serial number code: " + dto.getCode());
        Subcategory subcat = subcategoryRepo.findByCode(dto.getSubcategory().getCode())
                .orElseThrow(() -> new IllegalArgumentException("Subcategory not found: " + dto.getSubcategory().getCode()));
        SerialNumber sn = new SerialNumber();
        sn.setCode(dto.getCode().trim().toUpperCase());
        sn.setName(dto.getName().trim());
        sn.setSubcategory(subcat);
        return serialNumberRepo.save(sn);
    }
}
