package com.example.animal.dto;

import com.example.animal.domain.Board;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;
    @NotEmpty
    @Size(min = 3, max = 100)
    private String title;
    @NotEmpty
    private String content;

    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;


}
