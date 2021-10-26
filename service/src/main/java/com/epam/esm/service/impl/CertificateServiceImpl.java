package com.epam.esm.service.impl;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final TagService tagService;
    private final CertificateConverter converter;

    @Override
    public List<CertificateDto> getAll() {

        List<Certificate> certificates = certificateRepository.getAll();
        certificates.forEach(c -> c.setTags(tagService.getByCertificate(c.getId())));
        certificates.forEach(c -> c.setCreate(c.getCreate()));
        certificates.forEach(c -> c.setUpdate(c.getUpdate()));
        return certificates.stream().map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public CertificateDto findById(Long id) {
        //isValidId(id);
        Certificate certificate = certificateRepository.getById(id).orElse(null);
        certificate.setTags(tagService.getByCertificate(id));
        return converter.toDTO(certificate);
    }

    @Override
    public CertificateDto create(CertificateDto certificateDto) {

        Certificate certificate = converter.toEntity(certificateDto);
        certificate.setId(null);
        certificate.setCreate(LocalDateTime.now());
        certificate.setUpdate(LocalDateTime.now());
        return converter.toDTO(certificate);
    }

    @Override
    public void delete(Long id) {
        certificateRepository.getById(id).ifPresentOrElse(
                certificateRepository::delete, () -> {});
    }

    @Override
    public CertificateDto update(CertificateDto certificateDto) {

        Certificate certificate = certificateRepository.getById(certificateDto.getId()).orElse(null);
        Certificate modCertificate = converter.toEntity(certificateDto);
        certificate.setName(modCertificate.getName());
        certificate.setDescription(modCertificate.getDescription());
        certificate.setPrice(modCertificate.getPrice());
        certificate.setDuration(modCertificate.getDuration());
        certificate.setUpdate(LocalDateTime.now());
        certificate.setTags(tagService.insertTags(modCertificate.getId(), modCertificate.getTags()));
        return converter.toDTO(certificate);
    }
}
