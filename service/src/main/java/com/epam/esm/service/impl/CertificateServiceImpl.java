package com.epam.esm.service.impl;

import com.epam.esm.converter.CertificateConverter;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.NotFoundAnyEntityException;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.CertificateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final TagService tagService;
    private final CertificateConverter converter;
    private final CertificateValidator certificateValidator;

    @Override
    public List<CertificateDto> getAll() {

        if (certificateRepository.getAll().isEmpty()) {
            throw new NotFoundAnyEntityException("certificate-exception-0100401");
        }
        return certificateRepository.getAll().stream().map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    public CertificateDto findById(Long id) {

        certificateValidator.isIdValid(id);
        return converter.toDTO(certificateRepository.getById(id).orElseThrow(() ->
                new EntityNotFoundException("certificate-exception-0100402", id)));

    }

    @Override
    public CertificateDto create(CertificateDto certificateDto) {

        certificateValidator.isCertificateValid(converter.toEntity(certificateDto));
        return converter.toDTO(certificateRepository.add(converter.toEntity(certificateDto)));
    }

    @Override
    public void delete(Long id) {

        certificateValidator.isIdValid(id);
        certificateRepository.getById(id).ifPresentOrElse(
                certificateRepository::delete, () -> {
                    throw new EntityNotFoundException("certificate-exception-0100402", id);
                });
    }

    @Override
    public CertificateDto update(CertificateDto certificateDto) {

        certificateValidator.isCertificateValid(converter.toEntity(certificateDto));
        Certificate certificate = certificateRepository.getById(certificateDto.getId()).orElseThrow(() ->
                new EntityNotFoundException("certificate-exception-0100403", certificateDto.getId()));
        Certificate modCertificate = converter.toEntity(certificateDto);
        certificate.setName(modCertificate.getName());
        certificate.setDescription(modCertificate.getDescription());
        certificate.setPrice(modCertificate.getPrice());
        certificate.setDuration(modCertificate.getDuration());
        certificate.setUpdateDate(LocalDateTime.now());
        certificate.setTags(tagService.insertTags(modCertificate.getId(), modCertificate.getTags()));
        return converter.toDTO(certificateRepository.update(certificate));
    }
}
