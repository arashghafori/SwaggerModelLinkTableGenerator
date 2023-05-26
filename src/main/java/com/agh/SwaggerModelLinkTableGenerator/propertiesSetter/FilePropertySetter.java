package com.agh.SwaggerModelLinkTableGenerator.propertiesSetter;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelParameterDTO;
import com.wordnik.swagger.models.properties.Property;

public class FilePropertySetter implements PrimitiveSetter {
    private static final String FILE_TYPE = "File";

    @Override
    public ModelParameterDTO set(String parameterName, Property property) {
        return ModelParameterDTO.builder()
                .isRef(false)
                .parameterName(parameterName)
                .parameterType(FILE_TYPE)
                .build();
    }
}
