package com.mjc.school.service;

import com.mjc.school.service.dto.PieceOfNewsCreateDto;
import com.mjc.school.service.dto.PieceOfNewsResponseDto;
import com.mjc.school.service.dto.PieceOfNewsUpdateDto;

import java.util.List;

public interface NewsService {
    List<PieceOfNewsResponseDto> getAllNewsDto();
    PieceOfNewsResponseDto getNewsByIdDto(int id);
    boolean deleteNewsByIdDto(long id);
    PieceOfNewsResponseDto updatePieceOfNewsByIdDto(PieceOfNewsUpdateDto dto);
    PieceOfNewsResponseDto createPieceOfNewsDto(PieceOfNewsCreateDto dto);

}
