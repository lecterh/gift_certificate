package com.epam.esm.service.impl;

import com.epam.esm.converter.TagConverter;
import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ErrorCode;
import com.epam.esm.exception.EntityDuplicateException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.NotFoundAnyEntityException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagValidator;
import lombok.AllArgsConstructor;
import org.postgresql.shaded.com.ongres.scram.common.message.ServerFinalMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagConverter converter;
    private final TagValidator tagValidator;

    @Override
    public List<TagDto> getAll() {

        if (tagRepository.getAll().isEmpty()) {
            throw new NotFoundAnyEntityException("tag-exception-0100503");
        }
        return tagRepository.getAll().stream().map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public TagDto getById(Long id) {

        tagValidator.getIdValid(id);
        return converter.toDTO(tagRepository.getById(id).orElseThrow(() ->
                new EntityNotFoundException(ErrorCode.TAG_NOT_FOUND.getCode(), id)));

    }

    @Override
    public List<Tag> getByCertificate(Long id) {

        return tagRepository.getByCertificate(id);
    }

    @Override
    public TagDto create(TagDto tagDto) {

        tagValidator.getNameValid(converter.toEntity(tagDto).getName());
        if (tagRepository.getByName(tagDto.getName()).isPresent()) {
            throw new EntityDuplicateException(ErrorCode.TAG_UNIQ_NAME.getCode(), tagDto.getName());
        }
        return converter.toDTO(tagRepository.add(converter.toEntity(tagDto)));
    }

    @Override
    public List<Tag> insertTags(Long certificateId, List<Tag> tag) {
        if (tag == null) {
            return tag;
        }
        tag.forEach(t -> tagRepository.insertTag(certificateId, t.getId()));
        return tag;
    }

    @Override
    public void delete(Long id) {
        tagValidator.getIdValid(id);
        tagRepository.getById(id).ifPresentOrElse(t -> tagRepository.delete(id), () -> {
            throw new EntityNotFoundException(ErrorCode.TAG_NOT_FOUND.getCode(), id);
        });
    }
}

