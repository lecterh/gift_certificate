package com.epam.esm.converter.impl;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class CertificateConverterImpl implements CertificateConverter {

    @Override
    public CertificateDto toDTO(Certificate certificate) {

        CertificateDto certificateDto = new CertificateDto();

        certificateDto.setId(certificate.getId());
        certificateDto.setName(certificate.getName());
        certificateDto.setDescription(certificate.getDescription());
        certificateDto.setPrice(certificate.getPrice());
        certificateDto.setDuration(certificate.getDuration());
        certificateDto.setCreate(certificate.getCreate());
        certificateDto.setUpdate(certificate.getUpdate());
        certificateDto.setTags(certificate.getTags());

        return certificateDto;
    }

    @Override
    public Certificate toEntity(CertificateDto certificateDto) {

        Certificate certificate = new Certificate();

        certificate.setId(certificateDto.getId());
        certificate.setName(certificateDto.getName());
        certificate.setDescription(certificateDto.getDescription());
        certificate.setPrice(certificateDto.getPrice());
        certificate.setDuration(certificateDto.getDuration());
        certificate.setCreate(certificateDto.getCreate());
        certificate.setUpdate(certificateDto.getUpdate());
        certificate.setTags(certificateDto.getTags());

        return certificate;
    }

    @Override
    public List<TagDto> toDTOs(List<Tag> tags) {
        List<TagDto> tagsDto = new ArrayList<>(tags.size());

        for (Tag tmpTag : tags) {
            tagsDto.add(fromTagToDto(tmpTag));
        }

        return tagsDto;
    }

    @Override
    public List<Tag> toEntities(List<TagDto> tagsDto) {
        List<Tag> tags = new ArrayList<>(tagsDto.size());

        for (TagDto tmpTagsDto : tagsDto) {
            tags.add(fromDtoToTag(tmpTagsDto));
        }

        return tags;
    }

    public Tag fromDtoToTag(TagDto tagDto) {
        Tag tag = new Tag();

        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());

        return tag;
    }

    public TagDto fromTagToDto(Tag tag) {
        TagDto tagDto = new TagDto();

        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());

        return tagDto;
    }
}
