package com.epam.esm.converter;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.List;

public interface CertificateConverter {

    CertificateDto toDTO(Certificate certificate);

    Certificate toEntity(CertificateDto certificateDto);

    List<Tag> toEntities(List<TagDto> tagsDto);

    List<TagDto> toDTOs(List<Tag> tags);

}
