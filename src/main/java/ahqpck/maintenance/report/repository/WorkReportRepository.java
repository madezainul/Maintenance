package ahqpck.maintenance.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ahqpck.maintenance.report.entity.Equipment;
import ahqpck.maintenance.report.entity.User;
import ahqpck.maintenance.report.entity.WorkReport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkReportRepository extends JpaRepository<WorkReport, String>, JpaSpecificationExecutor<WorkReport> {

    /**
     * Find all work reports by status
     */
    List<WorkReport> findByStatus(WorkReport.Status status);

    @Query("SELECT COUNT(w) > 0 FROM WorkReport w " +
            "WHERE w.equipment.code = :equipmentCode " +
            "  AND w.problem = :problem " +
            "  AND (:solution IS NULL AND w.solution IS NULL OR w.solution = :solution) " +
            "  AND w.startTime = :startTime")
    boolean hasSimilarReportOnDate(
            @Param("equipmentCode") String equipmentCode,
            @Param("problem") String problem,
            @Param("solution") String solution,
            @Param("startTime") LocalDateTime startTime);

    /**
     * Find by equipment (useful for equipment history)
     */
    List<WorkReport> findByEquipment(Equipment equipment);

    /**
     * Optional: find by technician
     */
    List<WorkReport> findByTechnicians(User technician);

    @Query("""
        SELECT EXISTS (
            SELECT 1 FROM WorkReport wr
            WHERE wr.equipment.code = :equipmentCode
              AND wr.category = 'BREAKDOWN'
              AND wr.startTime < :stopTime
              AND wr.stopTime > :startTime
        )
    """)
    boolean hasOverlappingBreakdownReport(
        @Param("equipmentCode") String equipmentCode,
        @Param("startTime") LocalDateTime startTime,
        @Param("stopTime") LocalDateTime stopTime);

    /**
     * Optional: find by supervisor
     */
    List<WorkReport> findBySupervisor(User supervisor);

    @Query("SELECT COUNT(w) > 0 FROM WorkReport w " +
            "WHERE w.equipment.code = :equipmentCode " +
            "AND w.problem = :problem " +
            "AND w.reportDate BETWEEN :start AND :end")
    boolean existsByEquipmentCodeAndProblemAndReportDateBetween(
            @Param("equipmentCode") String equipmentCode,
            @Param("problem") String problem,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);

    /**
     * Optional: check if a code exists (for uniqueness)
     */
    // boolean existsByCodeIgnoreCase(String code);
}