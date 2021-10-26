package com.epam.esm.converter.impl;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
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
        certificateDto.setTags(toDTOs(certificate.getTags()));

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
        certificate.setTags(toEntities(certificateDto.getTags()));

        return certificate;
    }

    @Override
    public List<Tag> toEntities(List<TagDto> tagsDto) {
        if (tagsDto == null) {
            return null;
        }

        List<Tag> list = new ArrayList<>(tagsDto.size());
        for (TagDto tagDto : tagsDto) {
            list.add(tagDtoToTag(tagDto));
        }

        return list;
    }

    @Override
    public List<TagDto> toDTOs(List<Tag> tags) {
        if (tags == null) {
            return null;
        }

        List<TagDto> list = new ArrayList<>(tags.size());
        for (Tag tag : tags) {
            list.add(tagToTagDto(tag));
        }

        return list;
    }

    private Tag tagDtoToTag(TagDto tagDto) {
        if (tagDto == null) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());

        return tag;
    }

    private TagDto tagToTagDto(Tag tag) {
        if (tag == null) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());

        return tagDto;
    }

}

