package com.epam.esm.controller;

import com.epam.esm.dto.TagDto;
import com.epam.esm.service.TagService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/tags",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class TagController {

    private final TagService tagService;

    @GetMapping
    @ResponseStatus(OK)
    public List<TagDto> getAll() {

        return tagService.getAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public TagDto create(@RequestBody TagDto tagDto) {

        return tagService.create(tagDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public TagDto getById(@PathVariable("id") Long id) {

        return tagService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable("id") Long id) {

        tagService.delete(id);
    }

}

