package com.agh.SwaggerModelLinkTableGenerator.DTO;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ModelTableDTO {
    private String tableName;
    private List<ModelParameterDTO> parameters;
    private List<String> otherReferences;
}
