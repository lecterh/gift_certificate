package com.epam.esm.converter;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;

public interface TagConverter {

    TagDto toDTO(Tag tag);
    Tag toEntity(TagDto tagDto);

}
