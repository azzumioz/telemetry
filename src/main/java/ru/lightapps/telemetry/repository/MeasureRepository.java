package ru.lightapps.telemetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lightapps.telemetry.model.Measure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface MeasureRepository extends JpaRepository<Measure, Integer> {

    Measure findById(int id);

    List<Measure> findAllByOrderByDateTimeDesc();

    List<Measure> findAllByOrderByDateTime();

    Measure findTopByOrderByDateTimeDesc();

    @Query("SELECT m FROM Measure m WHERE m.id=(SELECT MAX (m.id) FROM m) ORDER BY m.dateTime DESC")
    Optional<Measure> getLast();

    List<Measure> findTop720ByOrderByDateTimeDesc();

    @Query("SELECT m FROM Measure m WHERE m.dateTime BETWEEN :startDateTime AND :endDateTime ORDER BY m.dateTime DESC")
    Optional<List<Measure>> getBetween(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    @Transactional
    Measure save(Measure entity);


}
