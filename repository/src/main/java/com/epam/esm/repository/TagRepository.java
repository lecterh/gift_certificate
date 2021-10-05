package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository {

    Tag add(Tag tag);

    Tag update(Tag tag);

    Optional<Tag> getById(long id);

    List<Tag> getByName(String name);

    List<Tag> getByCertificateId(long id);

}
