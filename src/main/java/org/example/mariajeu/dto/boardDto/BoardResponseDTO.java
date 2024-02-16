package org.example.mariajeu.dto.boardDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BoardResponseDTO {

    private String userNickname;
    private String foodName;
    private String foodKind;

    private String ateDate;
    private String ateLocation;
    private String withPerson;
    private String wineName;
    private String winery;
    private String createdYear;
    private String kind;
    private String country;
    private String createdLocation;
    private String content;
}
