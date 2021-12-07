package com.epam.esm.service;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {

    List<TagDto> getAll();

    TagDto getById(Long id);

    List<Tag> getByCertificate(Long id);

    TagDto create(TagDto tagDto);

    List<Tag> insertTags(Long certificateId, List<Tag> tag);

    void delete(Long id);

}

