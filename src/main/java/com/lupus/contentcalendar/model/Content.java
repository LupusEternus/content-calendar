package com.lupus.contentcalendar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


public record Content(
        @NotBlank
        Integer id,
        @NotBlank
        String title,
        String desc,
        @NotBlank
        Status status,
        @NotBlank
        Type contentType,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
        LocalDateTime dateCreated,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm", shape = JsonFormat.Shape.STRING)
        LocalDateTime dateUpdated,
        String url

){}


