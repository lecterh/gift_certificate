package com.epam.esm.repository.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.TagMapper;
import com.epam.esm.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper;

    private static final String CREATE = "insert into tag (name) values (?);";

    private static final String UPDATE = "update tag set name=? where id=?;";

    private static final String GET_BY_ID = "select * from tag where id=?;";

    private static final String GET_BY_NAME = "select * from tag where name=?;";

    private static final String GET_BY_CERTIFICATE_ID = "select * from tag " +
            "join certificate c on tag.id where c.id=?;";

    @Override
    public Tag add(Tag tag) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getName());
            return preparedStatement;
        }, keyHolder);
        return tag;
    }

    @Override
    public Tag update(Tag tag) {

        jdbcTemplate.update(UPDATE, tag.getName(), tag.getId());
        return tag;
    }

    @Override
    public Optional<Tag> getById(long id) {

        Optional<Tag> tag = jdbcTemplate.query(GET_BY_ID, tagMapper, id).stream().findAny();
        return tag;
    }

    @Override
    public List<Tag> getByName(String name) {

        return jdbcTemplate.queryForList(GET_BY_NAME, Tag.class);
    }

    @Override
    public List<Tag> getByCertificateId(long id) {

        return jdbcTemplate.queryForList(GET_BY_CERTIFICATE_ID, Tag.class);
    }
}
