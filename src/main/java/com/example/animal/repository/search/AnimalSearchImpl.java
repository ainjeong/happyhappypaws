package com.example.animal.repository.search;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Board;
import com.example.animal.domain.QAnimalInfo;
import com.example.animal.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnimalSearchImpl extends QuerydslRepositorySupport implements AnimalSearch{
    public AnimalSearchImpl(){
        super(AnimalInfo.class);
    }
    @Override
    public Page<AnimalInfo> search1(Pageable pageable) {
        QAnimalInfo animalInfo = QAnimalInfo.animalInfo;
        JPQLQuery<AnimalInfo> query = from(animalInfo);
        BooleanBuilder booleanBuilder = new BooleanBuilder();


        this.getQuerydsl().applyPagination(pageable, query);

        List<AnimalInfo> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<AnimalInfo> searchAll(String[] types, String keyword, Pageable pageable) {
        QAnimalInfo animalInfo = QAnimalInfo.animalInfo;
        JPQLQuery<AnimalInfo> query = from(animalInfo);
        if((types!=null && types.length >0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type: types){
                switch(type){
                    case "t":
                        booleanBuilder.or(animalInfo.kindCd.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(animalInfo.careAddr.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(animalInfo.sexCd.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if
        query.where(animalInfo.desertionNo.gt(""));
        //paging
        this.getQuerydsl().applyPagination(pageable, query);
        List<AnimalInfo> list = query.fetch();
        long count = query.fetchCount();
        return new PageImpl<>(list, pageable, count);
    }
    }

