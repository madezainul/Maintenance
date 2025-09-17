package ahqpck.maintenance.report.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ahqpck.maintenance.report.dto.MachineTypeDTO;
import ahqpck.maintenance.report.entity.MachineType;
import ahqpck.maintenance.report.repository.MachineTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MachineTypeService {
    
    private final MachineTypeRepository machineTyperepo;

    public MachineType createMachineType(MachineTypeDTO dto) {
        String code = dto.getCode().trim().toUpperCase();
        String name = dto.getName().trim();
        if (machineTyperepo.findByCode(code).isPresent())
            throw new IllegalArgumentException("Duplicate code.");
        MachineType mt = new MachineType();
        mt.setCode(code);
        mt.setName(name.trim());
        return machineTyperepo.save(mt);
    }

    public MachineType getMachineTypeByCode(String code) {
        return machineTyperepo.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Machine Type not found: " + code));
    }

    public List<MachineType> getAll() {
        return machineTyperepo.findAll();
    }

    public Optional<MachineType> searchByName(String term) {
        if (term == null || term.trim().isEmpty())
            return Optional.empty();
        term = "%" + term.trim().toLowerCase() + "%";
        return machineTyperepo.findByName(term);
    }
}