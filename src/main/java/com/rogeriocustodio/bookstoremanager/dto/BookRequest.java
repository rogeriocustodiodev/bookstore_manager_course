package com.rogeriocustodio.bookstoremanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRequest {

    private String name;

    private Integer pages;

    private Integer chapters;

    private String isbn;

    @JsonProperty("publisher_name")
    private String publisherName;

    private AuthorRequest author;
}
