package com.miewone.DeuNotice.Service;

import com.miewone.DeuNotice.Domain.DeuPostRepository;
import com.miewone.DeuNotice.Dto.DeuPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final DeuPostRepository deuPostRepository;

    @Transactional
    public void save(DeuPostDto dto)
    {
        deuPostRepository.save(dto.toEntity());
    }
}
