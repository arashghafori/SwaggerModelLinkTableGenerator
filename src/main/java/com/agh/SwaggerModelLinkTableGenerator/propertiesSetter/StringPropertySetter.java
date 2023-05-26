package com.agh.SwaggerModelLinkTableGenerator.propertiesSetter;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelParameterDTO;
import com.wordnik.swagger.models.properties.Property;
import com.wordnik.swagger.models.properties.StringProperty;

public class StringPropertySetter implements PrimitiveSetter {
    private static final String STRING_TYPE = "String";
    private static final String ENUM_TYPE = "Enum";
    private static final String COLON = ":";

    @Override
    public ModelParameterDTO set(String parameterName, Property property) {
        if (((StringProperty) property).getEnum() == null)
            return ModelParameterDTO.builder()
                    .isRef(false)
                    .parameterName(parameterName)
                    .parameterType(STRING_TYPE)
                    .build();
        else {
            StringBuilder enumType = new StringBuilder(ENUM_TYPE);
            return ModelParameterDTO.builder()
                    .isRef(false)
                    .parameterName(parameterName)
                    .parameterType(enumType.append(COLON).append(((StringProperty) property).getEnum().toString()).toString())
                    .build();
        }
    }
}
