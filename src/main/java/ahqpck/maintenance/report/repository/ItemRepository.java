package ahqpck.maintenance.report.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ahqpck.maintenance.report.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
}