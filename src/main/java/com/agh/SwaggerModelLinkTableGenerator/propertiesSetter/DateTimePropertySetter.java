package com.agh.SwaggerModelLinkTableGenerator.propertiesSetter;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelParameterDTO;
import com.wordnik.swagger.models.properties.Property;

public class DateTimePropertySetter implements PrimitiveSetter {
    private static final String DATETIME_TYPE = "DateTime";

    @Override
    public ModelParameterDTO set(String parameterName, Property property) {
        return ModelParameterDTO.builder()
                .isRef(false)
                .parameterName(parameterName)
                .parameterType(DATETIME_TYPE)
                .build();
    }
}
