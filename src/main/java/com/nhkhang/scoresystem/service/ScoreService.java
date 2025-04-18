package com.nhkhang.scoresystem.service;

import com.nhkhang.scoresystem.exception.ResourceNotFoundException;
import com.nhkhang.scoresystem.model.Score;
import com.nhkhang.scoresystem.repository.ScoreRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private EntityManager entityManager;

    public Score findOneByRegistrationNumber(Long registrationNumber) {
        Score score = scoreRepository.findBySbd(registrationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "registration number", registrationNumber));
        return score;
    }

    public List<Score> findTop10GroupA() {
        List<Score> scores = scoreRepository.findTop10GroupA();
        return scores;
    }

    public Map<String, Object> getStatsBySubject(String column) {
        Set<String> validColumns = Set.of(
                "toan", "ngu_van", "ngoai_ngu", "vat_li", "hoa_hoc", "sinh_hoc",
                "lich_su", "dia_li", "gdcd"
        );

        if (!validColumns.contains(column)) {
            throw new ResourceNotFoundException("Subject", "name", column);
        }

        // Tạo câu truy vấn SQL với dynamic column
        String sql = """
    SELECT 
        SUM(CASE WHEN %1$s >= 8 THEN 1 ELSE 0 END) AS greaterThan8,
        SUM(CASE WHEN %1$s >= 6 AND %1$s < 8 THEN 1 ELSE 0 END) AS between6And8,
        SUM(CASE WHEN %1$s >= 4 AND %1$s < 6 THEN 1 ELSE 0 END) AS between4And6,
        SUM(CASE WHEN %1$s < 4 THEN 1 ELSE 0 END) AS lessThan4
    FROM score
    """.formatted(column);

        // Thực thi câu truy vấn
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> result = query.getResultList();

        // Tạo một Map để lưu trữ kết quả
        Map<String, Object> stats = new HashMap<>();
        if (!result.isEmpty()) {
            Object[] row = result.get(0); // Vì chỉ có một dòng kết quả trả về

            stats.put("subject", column);
            stats.put("greaterThan8", ((Number) row[0]).intValue());
            stats.put("between6And8", ((Number) row[1]).intValue());
            stats.put("between4And6", ((Number) row[2]).intValue());
            stats.put("lessThan4", ((Number) row[3]).intValue());
        }

        return stats;
    }

}
