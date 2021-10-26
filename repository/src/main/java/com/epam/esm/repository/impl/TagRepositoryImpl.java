package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    private final JdbcTemplate jdbcTemplate;

    private final TagMapper tagMapper;

    private static final String GET_ALL = "select id, name from tag";

    private static final String CREATE = "insert into tag (name) values (?)";

    private static final String DELETE = "delete from tag where id=?";

    public static final String INSERT_TAG = "insert into certificate_tag (certificate_id, tag_id) " +
            "values(?, ?)";

    private static final String UPDATE = "update tag set name=? where id=?";

    private static final String GET_BY_ID = "select id, name from tag where id=?";

    private static final String GET_BY_NAME = "select id, name from tag where name=?";

    private static final String GET_BY_CERTIFICATE_ID = "select id, name from tag " +
            "join certificate c on tag.id where c.id=?";

    @Override
    public List<Tag> getAll() {
        //return Optional.of(jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(Tag.class)));
        //return jdbcTemplate.queryForList(GET_ALL, Tag.class);
        return jdbcTemplate.query(GET_ALL, tagMapper);

    }

    @Override
    public Tag add(Tag tag) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getName());
            return preparedStatement;
        }, keyHolder);
        tag.setId(((Number) keyHolder.getKeys().get("id")).longValue());
        return tag;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public void insertTag(Long certificateId, Long tagId) {
        jdbcTemplate.update(INSERT_TAG, certificateId, tagId);
    }

    @Override
    public Tag update(Tag tag) {

        jdbcTemplate.update(UPDATE, tag.getName(), tag.getId());
        return tag;
    }

    @Override
    public Optional<Tag> getById(Long id) {

        Optional<Tag> tag = jdbcTemplate.query(GET_BY_ID, tagMapper, id).stream().findAny();
        return tag;
    }

    @Override
    public List<Tag> getByName(String name) {

        return jdbcTemplate.queryForList(GET_BY_NAME, Tag.class);
    }

    @Override
    public List<Tag> getByCertificate(Long id) {

        return jdbcTemplate.queryForList(GET_BY_CERTIFICATE_ID, Tag.class);
    }
}

