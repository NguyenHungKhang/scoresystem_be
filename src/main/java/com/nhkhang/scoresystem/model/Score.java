package com.nhkhang.scoresystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "score")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Score {
    @Id
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
