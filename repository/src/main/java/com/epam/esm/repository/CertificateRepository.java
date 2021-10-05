package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository {

    Certificate add(Certificate certificate);

    Certificate update(Certificate certificate);

    Optional<Certificate> getById(long id);

    List<Certificate> getByName(String name);

    void delete(Certificate certificate);
}
