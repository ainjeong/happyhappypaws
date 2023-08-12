package com.example.animal.dto;

import com.example.animal.domain.Like;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeRequestDTO {
    private String memberId;
    private String animalInfoDesertionNo;

    public LikeRequestDTO(String memberId, String animalInfoDesertionNo){
        this.memberId = memberId;
        this.animalInfoDesertionNo = animalInfoDesertionNo;
    }


}
