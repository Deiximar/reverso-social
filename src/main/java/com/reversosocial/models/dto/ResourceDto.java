package com.reversosocial.models.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotNull(message = "El campo título es requerido.")
    @NotEmpty(message = "El campo título es requerido.")
    @NotBlank(message = "El campo título es requerido.")
    private String title;

    @Pattern(regexp = "^(https?:\\/\\/)?(www\\.)?[a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$", message = "El campo enlace debe ser una URL válida.")
    private String url;

    @NotNull(message = "El campo descripción es requerido.")
    @NotEmpty(message = "El campo descripción es requerido.")
    @NotBlank(message = "El campo descripción es requerido.")
    private String description;

    private MultipartFile file;
    private String fileUrl;
    private String fileName;
    private Boolean shouldModifyFile;

    private String creatorEmail;

}
