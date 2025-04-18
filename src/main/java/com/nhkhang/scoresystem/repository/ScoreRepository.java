package com.nhkhang.scoresystem.repository;

import com.nhkhang.scoresystem.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query("SELECT s FROM Score s WHERE s.sbd = :sbd")
    Optional<Score> findBySbd(Long sbd);

    @Query("SELECT s FROM Score s WHERE s.toan IS NOT NULL AND s.vatLi IS NOT NULL AND s.hoaHoc IS NOT NULL " +
            "ORDER BY (s.toan + s.vatLi + s.hoaHoc) DESC LIMIT 10")
    List<Score> findTop10GroupA();

    @Query(value = """
    SELECT 
        SUM(CASE WHEN :column >= 8 THEN 1 ELSE 0 END) AS greaterThan8,
        SUM(CASE WHEN :column >= 6 AND :column < 8 THEN 1 ELSE 0 END) AS between6And8,
        SUM(CASE WHEN :column >= 4 AND :column < 6 THEN 1 ELSE 0 END) AS between4And6,
        SUM(CASE WHEN :column < 4 THEN 1 ELSE 0 END) AS lessThan4
    FROM score
    """, nativeQuery = true)
    List<Object[]> getStatsForSubject(@Param("column") String column);

    @Query(value = """
        SELECT
        FROM Score s
        WHERE s.toan IS NOT NULL AND s.vat_li IS NOT NULL AND s.hoa_hoc IS NOT NULL
        ORDER BY (s.toan + s.vat_li + s.hoa_hoc) DESC
        LIMIT 10
    """, nativeQuery = true)
    List<Score> getTop10StudentsByGroupA();
}