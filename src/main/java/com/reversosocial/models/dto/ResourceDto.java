package com.reversosocial.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {
    private Integer id;
    private String title;
    private String url;
    private String description;
    private String file;
    private String creatorEmail;
 
}
