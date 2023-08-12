package com.example.animal.repository;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Like;
import com.example.animal.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Object> findByMemberAndAnimalInfo(Member member, AnimalInfo animalInfo);

    List<Like> findByMemberId(String memeberId);
}
