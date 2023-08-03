package com.example.animal.repository;

import com.example.animal.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
