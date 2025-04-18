package com.nhkhang.scoresystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScoreResponseDto {
    private Long sbd;
    private Float toan;
    private Float nguVan;
    private Float ngoaiNgu;
    private Float vatLi;
    private Float hoaHoc;
    private Float sinhHoc;
    private Float lichSu;
    private Float diaLi;
    private Float gdcd;
    private String maNgoaiNgu;
}
