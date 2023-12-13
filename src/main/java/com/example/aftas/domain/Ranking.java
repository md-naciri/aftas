package com.example.aftas.domain;
import com.example.aftas.domain.embeddable.RankId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
@Entity
public class Ranking {
    @EmbeddedId
    private RankId id;
    private Integer raank;
    private Integer score;
    @ManyToOne
    @MapsId("member_number")
    private Member member;
    @ManyToOne
    @MapsId("competition_code")
    private Competition competition;
}