package com.epam.esm.repository;

import com.epam.esm.entity.Certificate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CertificateRepository {

    Certificate add(Certificate certificate);

    Certificate update(Certificate certificate);

    Optional<Certificate> getById(Long id);

    List<Certificate> getByName(String name);

    List<Certificate> getAll();

    void delete(Certificate certificate);
}

