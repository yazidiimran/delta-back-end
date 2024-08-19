package net.yazidi.delta.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatistiqueDTO {
    private Long CA;
    private Long CAP;
    private List<CADTO> CAS;
    private List<CMDDTO> CMDS;
    private List<CATDTO> CACAT;
    private List<CABYCLIENT> CABYCLIENT;
    private List<CABYCOMMERCIAL> CABYCOMMERCIAL;
    private int clients;
    private int fournsisseurs;
    private int commerciaux;
    private int commandes;
}
