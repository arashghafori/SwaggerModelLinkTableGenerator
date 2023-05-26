package com.agh.SwaggerModelLinkTableGenerator.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ModelParameterDTO {
    private String parameterName;
    private String parameterType;
    private boolean isRef;
}
