//package com.nhkhang.scoresystem.config;
//
//import com.nhkhang.scoresystem.model.Score;
//import com.nhkhang.scoresystem.repository.ScoreRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//@Component
//public class CsvSeeder implements CommandLineRunner {
//
//    @Autowired
//    private ScoreRepository scoreRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        try (BufferedReader reader = new BufferedReader(new FileReader("data/diem_thi_thpt_2024.csv"))) {
//            String line;
//            reader.readLine();
//
//            while ((line = reader.readLine()) != null) {
//                String[] data = line.split(",");
//
//                Score score = new Score();
//                score.setSbd(parseLongOrNull(getValueSafe(data, 0)));
//                score.setToan(parseFloatOrNull(getValueSafe(data, 1)));
//                score.setNguVan(parseFloatOrNull(getValueSafe(data, 2)));
//                score.setNgoaiNgu(parseFloatOrNull(getValueSafe(data, 3)));
//                score.setVatLi(parseFloatOrNull(getValueSafe(data, 4)));
//                score.setHoaHoc(parseFloatOrNull(getValueSafe(data, 5)));
//                score.setSinhHoc(parseFloatOrNull(getValueSafe(data, 6)));
//                score.setLichSu(parseFloatOrNull(getValueSafe(data, 7)));
//                score.setDiaLi(parseFloatOrNull(getValueSafe(data, 8)));
//                score.setGdcd(parseFloatOrNull(getValueSafe(data, 9)));
//                score.setMaNgoaiNgu(parseStringOrNull(getValueSafe(data, 10)));
//
//                scoreRepository.save(score);
//            }
//        }
//    }
//
//    private String getValueSafe(String[] data, int index) {
//        return index < data.length ? data[index] : null;
//    }
//
//    private Long parseLongOrNull(String input) {
//        if (input == null || input.trim().isEmpty()) {
//            return null;
//        }
//        return Long.parseLong(input.trim());
//    }
//
//    private Float parseFloatOrNull(String input) {
//        if (input == null || input.trim().isEmpty()) {
//            return null;
//        }
//        return Float.parseFloat(input.trim());
//    }
//
//    private String parseStringOrNull(String input) {
//        if (input == null || input.trim().isEmpty()) {
//            return null;
//        }
//        return input.trim();
//    }
//}
