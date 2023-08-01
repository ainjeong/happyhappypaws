package com.example.animal.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
public class AnimalInfo {
    @Id
    private String desertionNo;
    private String filename;
    private String happenDt;
    private String happenPlace;
    private String kindCd;
    private String colorCd;
    private String age;
    private String weight;
    private String noticeNo;
    private String noticeSdt;
    private String noticeEdt;
    private String popfile;
    private String processState;
    private String sexCd;
    private String neuterYn;
    private String specialMark;
    private String careNm;
    private String careTel;
    private String careAddr;
    private String orgNm;
    private String chargeNm;
    private String officetel;

    public AnimalInfo(String desertionNo, String filename, String happenDt, String happenPlace, String kindCd, String colorCd, String age, String weight, String noticeNo, String noticeSdt, String noticeEdt, String popfile, String processState, String sexCd, String neuterYn, String specialMark, String careNm, String careTel, String careAddr, String orgNm, String chargeNm, String officetel) {
        this.desertionNo = desertionNo;
        this.filename = filename;
        this.happenDt = happenDt;
        this.happenPlace = happenPlace;
        this.kindCd = kindCd;
        this.colorCd = colorCd;
        this.age = age;
        this.weight = weight;
        this.noticeNo = noticeNo;
        this.noticeSdt = noticeSdt;
        this.noticeEdt = noticeEdt;
        this.popfile = popfile;
        this.processState = processState;
        this.sexCd = sexCd;
        this.neuterYn = neuterYn;
        this.specialMark = specialMark;
        this.careNm = careNm;
        this.careTel = careTel;
        this.careAddr = careAddr;
        this.orgNm = orgNm;
        this.chargeNm = chargeNm;
        this.officetel = officetel;
    }

    public AnimalInfo() {

    }
}
