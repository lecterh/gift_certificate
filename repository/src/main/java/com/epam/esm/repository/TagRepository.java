package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TagRepository {

    List<Tag> getAll();

    Tag add(Tag tag);

    void delete(Long id);

    void insertTag(Long certificateId, Long tagId);

    Tag update(Tag tag);

    Optional<Tag> getById(Long id);

    Optional<Tag> getByName(String name);

    List<Tag> getByCertificate(Long id);

}

