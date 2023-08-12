package com.example.animal.service;

import com.example.animal.domain.AnimalInfo;
import com.example.animal.domain.Like;
import com.example.animal.domain.Member;
import com.example.animal.dto.LikeRequestDTO;
import com.example.animal.repository.AnimalInfoRepository;
import com.example.animal.repository.BoardRepository;
import com.example.animal.repository.LikeRepository;
import com.example.animal.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;
    private final AnimalInfoRepository animalInfoRepository;

    @Transactional
    public Long insert(LikeRequestDTO likeRequestDTO) throws Exception {
        Member member = memberRepository.findById(likeRequestDTO.getMemberId()).get();
        AnimalInfo animalInfo = animalInfoRepository.findById(likeRequestDTO.getAnimalInfoDesertionNo()).get();

       // if (likeRepository.findByMemberAndAnimalInfo(member, animalInfo).isPresent()) {
        //    boolean isLikeExists(member, animalInfo) = true;
        //}

        Like like = Like.builder()
                .animalInfo(animalInfo)
                .member(member)
                .build();

        return likeRepository.save(like).getId();
    }

    public boolean isLikeExists(Member member, AnimalInfo animalInfo) {
        return likeRepository.findByMemberAndAnimalInfo(member, animalInfo).isPresent();
    }


    @Transactional
    public void delete(LikeRequestDTO likeRequestDTO){
        Member member = memberRepository.findById(likeRequestDTO.getMemberId()).get();
        AnimalInfo animalInfo = animalInfoRepository.findById(likeRequestDTO.getAnimalInfoDesertionNo()).get();
        Like like = (Like) likeRepository.findByMemberAndAnimalInfo(member, animalInfo).get();
        likeRepository.delete(like);
    }

}
