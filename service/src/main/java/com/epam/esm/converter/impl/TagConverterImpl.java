package com.epam.esm.converter.impl;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;

public class TagConverterImpl implements TagConverter {

    @Override
    public TagDto toDTO(Tag tag) {

        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());

        return tagDto;
    }

    @Override
    public Tag toEntity(TagDto tagDto) {

        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());

        return tag;
    }
}
