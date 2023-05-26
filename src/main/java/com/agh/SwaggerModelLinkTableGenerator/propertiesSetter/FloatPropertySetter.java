package com.agh.SwaggerModelLinkTableGenerator.propertiesSetter;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelParameterDTO;
import com.wordnik.swagger.models.properties.Property;

public class FloatPropertySetter implements PrimitiveSetter {
    private static final String FLOAT_TYPE = "Float";

    @Override
    public ModelParameterDTO set(String parameterName, Property property) {
        return ModelParameterDTO.builder()
                .isRef(false)
                .parameterName(parameterName)
                .parameterType(FLOAT_TYPE)
                .build();
    }
}
