package com.vamshimaddur.mongodb.task;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public record Task(
        @MongoId
        String id,
        @NotEmpty String title,
        @NotEmpty String description,
        Status status
) {
}
