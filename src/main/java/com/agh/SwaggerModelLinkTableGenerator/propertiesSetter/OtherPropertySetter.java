package com.agh.SwaggerModelLinkTableGenerator.propertiesSetter;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelParameterDTO;
import com.wordnik.swagger.models.properties.Property;

public class OtherPropertySetter implements PrimitiveSetter {
    @Override
    public ModelParameterDTO set(String parameterName, Property property) {
        return ModelParameterDTO.builder()
                .isRef(false)
                .parameterName(parameterName)
                .parameterType(property.getType())
                .build();
    }
}
