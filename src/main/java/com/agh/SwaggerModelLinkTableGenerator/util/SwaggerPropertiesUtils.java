package com.agh.SwaggerModelLinkTableGenerator.util;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelParameterDTO;
import com.agh.SwaggerModelLinkTableGenerator.propertiesSetter.*;
import com.wordnik.swagger.models.properties.*;

public class SwaggerPropertiesUtils {

    private static class SwaggerPropertiesUtilsHelper{
        public static final SwaggerPropertiesUtils INSTANCE = new SwaggerPropertiesUtils();
    }

    public static SwaggerPropertiesUtils getInstance(){
        return SwaggerPropertiesUtilsHelper.INSTANCE;
    }

    public boolean isPrimitive(Property property){
        return property instanceof StringProperty ||
               property instanceof DateProperty ||
               property instanceof DateTimeProperty ||
               property instanceof DecimalProperty ||
               property instanceof FileProperty ||
               property instanceof IntegerProperty ||
               property instanceof FloatProperty ||
               property instanceof DoubleProperty ||
               property instanceof LongProperty ||
               property instanceof BooleanProperty ||
               property instanceof UUIDProperty ||
               property instanceof MapProperty ||
               property instanceof ObjectProperty;
    }

    public boolean isArray(Property property){
        return property instanceof ArrayProperty;
    }

    public boolean isRefProperty(Property property){
        return property instanceof RefProperty;
    }

    public ModelParameterDTO setPrimitive(String parameterName, Property property){
        if (property instanceof StringProperty) {
            return new StringPropertySetter().set(parameterName, property);
        } else if (property instanceof DateProperty) {
            return new DatePropertySetter().set(parameterName, property);
        } else if (property instanceof DateTimeProperty) {
            return new DateTimePropertySetter().set(parameterName, property);
        } else if (property instanceof DecimalProperty) {
            return new DecimalPropertySetter().set(parameterName, property);
        } else if (property instanceof FileProperty) {
            return new FilePropertySetter().set(parameterName, property);
        } else if (property instanceof IntegerProperty) {
            return new IntegerPropertySetter().set(parameterName, property);
        } else if (property instanceof FloatProperty) {
            return new FloatPropertySetter().set(parameterName, property);
        } else if (property instanceof DoubleProperty) {
            return new DoublePropertySetter().set(parameterName, property);
        } else if (property instanceof LongProperty) {
            return new LongPropertySetter().set(parameterName, property);
        } else if (property instanceof BooleanProperty) {
            return new BooleanPropertySetter().set(parameterName, property);
        } else if (property instanceof UUIDProperty) {
            return new UUIDPropertySetter().set(parameterName, property);
        } else if (property instanceof MapProperty) {
            return new MapPropertySetter().set(parameterName, property);
        }  else if (property instanceof ObjectProperty) {
            return new ObjectPropertySetter().set(parameterName, property);
        } else {
            return new OtherPropertySetter().set(parameterName, property);
        }
    }
}
