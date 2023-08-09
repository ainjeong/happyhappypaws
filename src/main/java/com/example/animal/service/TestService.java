package com.example.animal.service;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.repository.AnimalInfoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    AnimalInfoRepository animalInfoRepository;

    public void init(String jsonData) {
        try {
            JSONObject jobj;
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonOBJ = (JSONObject) jsonParser.parse(jsonData);

            JSONObject parseResponse = (JSONObject) jsonOBJ.get("response");

            JSONObject parseBody = (JSONObject) parseResponse.get("body");

            JSONObject parseItems = (JSONObject) parseBody.get("items");
            JSONArray array = (JSONArray) parseItems.get("item");

            for (int i = 0; i < array.size(); i++) {
                jobj = (JSONObject) array.get(i);

                AnimalInfo animalInfo = AnimalInfo.builder()
                        .desertionNo((String) jobj.get("desertionNo"))
                        .filename((String) jobj.get("Filename"))
                        .happenDt((String) jobj.get("happenDt"))
                        .happenPlace((String) jobj.get("happenPlace"))
                        .kindCd((String) jobj.get("kindCd"))
                        .colorCd((String)jobj.get("colorCd"))
                        .age((String)jobj.get("age"))
                        .weight((String)jobj.get("weight"))
                        .noticeNo((String)jobj.get("noticeNo"))
                        .noticeSdt((String)jobj.get("noticeSdt"))
                        .noticeEdt((String)jobj.get("noticeEdt"))
                        .popfile((String)jobj.get("popfile"))
                        .processState((String)jobj.get("processState"))
                        .sexCd((String)jobj.get("sexCd"))
                        .neuterYn((String)jobj.get("neuterYn"))
                        .specialMark((String)jobj.get("specialMark"))
                        .careNm((String)jobj.get("careNm"))
                        .careTel((String)jobj.get("careTel"))
                        .careAddr((String)jobj.get("careAddr"))
                        .orgNm((String)jobj.get("orgNm"))
                        .chargeNm((String)jobj.get("chargeNm"))
                        .officetel((String)jobj.get("officetel"))
                                .build();

                animalInfoRepository.save(animalInfo);
            }


        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    public List<AnimalInfo> getRandomFiveEntities() {
        try {
            String jpql = "SELECT e FROM AnimalInfo e ORDER BY RAND()";
            Query query = entityManager.createQuery(jpql);
            query.setMaxResults(3);
            List<AnimalInfo> randomEntities = query.getResultList();

            return randomEntities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
