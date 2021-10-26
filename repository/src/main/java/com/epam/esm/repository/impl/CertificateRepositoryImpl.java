package com.epam.esm.repository.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.mapper.CertificateMapper;
import com.epam.esm.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CertificateRepositoryImpl implements CertificateRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CertificateMapper certificateMapper;

    private static final String CREATE =
            "insert into certificate (name, description, " +
                    "price, duration, create_date, last_update_date) " +
                    "values(?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "update certificate set name = ?, description = ?, " +
            "price = ?, duration = ?, last_update_date = ? where id = ?";

    private static final String DELETE = "delete from certificate where id=?";

    private static final String GET_ALL = "select id, name, description, price, " +
            "create_date, last_update_date from certificate";

    private static final String GET_BY_ID = "select id, name, description, price, duration," +
            "create_date, last_update_date from certificate where id=?";

    private static final String GET_BY_NAME = "select id, name, description, price, duration," +
            "create_date, last_update_date from certificate where name=?";



    @Override
    public Certificate add(Certificate certificate) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, certificate.getName());
            preparedStatement.setString(2, certificate.getDescription());
            preparedStatement.setBigDecimal(3, certificate.getPrice());
            preparedStatement.setInt(4, certificate.getDuration());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            return preparedStatement;
        }, keyHolder);

        certificate.setId(((Number) keyHolder.getKeys().get("id")).longValue());
        return certificate;
    }

    @Override
    public Certificate update(Certificate certificate) {

        jdbcTemplate.update(UPDATE, certificate.getName(), certificate.getDescription(), certificate.getPrice(),
                certificate.getDuration(), certificate.getUpdate(), certificate.getId());
        return certificate;

    }

    @Override
    public void delete(Certificate certificate) {

        jdbcTemplate.update(DELETE, certificate.getId());

    }

    @Override
    public Optional<Certificate> getById(Long id) {

        Optional<Certificate> certificate = jdbcTemplate.query(GET_BY_ID, certificateMapper, id).stream().findAny();
        return certificate;
    }

    @Override
    public List<Certificate> getByName(String name) {

        return jdbcTemplate.query(GET_BY_NAME, certificateMapper);
    }

    @Override
    public List<Certificate> getAll() {

        return jdbcTemplate.query(GET_ALL, certificateMapper);

    }
}

