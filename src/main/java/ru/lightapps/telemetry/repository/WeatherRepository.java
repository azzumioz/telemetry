package ru.lightapps.telemetry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lightapps.telemetry.model.Weather;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface WeatherRepository extends JpaRepository <Weather, Integer> {

    Weather findTopByOrderByDateTimeDesc();

    @Transactional
    Weather save (Weather entity);

    @Query("SELECT w FROM Weather w WHERE w.dateTime BETWEEN :startDateTime AND :endDateTime ORDER BY w.dateTime DESC")
    Optional<List<Weather>> getBetween(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

}
