package com.reversosocial.models.dto;

import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile file;
    private String fileUrl;
    
    private String creatorEmail;
 
}
