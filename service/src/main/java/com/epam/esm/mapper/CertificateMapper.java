package com.epam.esm.mapper;

import com.epam.esm.entity.Certificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class CertificateMapper implements RowMapper<Certificate> {

    @Override
    public Certificate mapRow(ResultSet rs, int rowNum) throws SQLException {

        Certificate certificate = new Certificate();
        certificate.setId(rs.getLong("id"));
        certificate.setName(rs.getString("name"));
        certificate.setDescription(rs.getString("description"));
        certificate.setPrice(rs.getBigDecimal("price"));
        certificate.setDuration(rs.getInt("duration"));
        certificate.setCreate(ZonedDateTime.ofInstant(rs.getTimestamp("create_date")
            .toInstant(), ZoneOffset.UTC).toLocalDateTime());
        certificate.setUpdate(ZonedDateTime.ofInstant(rs.getTimestamp("last_update_date")
            .toInstant(), ZoneOffset.UTC).toLocalDateTime());

        return certificate;
    }
}
