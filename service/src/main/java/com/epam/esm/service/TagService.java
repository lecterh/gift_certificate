package com.epam.esm.service;

import com.epam.esm.dto.TagDto;

import java.util.List;

public interface TagService {

    List<TagDto> getAll();

    TagDto findById(long id);

    TagDto findByName(String name);

    TagDto add(TagDto tagDto);

    void delete(long id);

    void delete(String name);

}
