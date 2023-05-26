package com.agh.SwaggerModelLinkTableGenerator.propertiesSetter;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelParameterDTO;
import com.wordnik.swagger.models.properties.Property;

public class IntegerPropertySetter implements PrimitiveSetter {
    private static final String INTEGER_TYPE = "Integer";

    @Override
    public ModelParameterDTO set(String parameterName, Property property) {
        return ModelParameterDTO.builder()
                .isRef(false)
                .parameterName(parameterName)
                .parameterType(INTEGER_TYPE)
                .build();
    }
}
