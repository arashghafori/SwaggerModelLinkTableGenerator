package com.agh.SwaggerModelLinkTableGenerator.util;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelParameterDTO;
import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelTableDTO;
import com.wordnik.swagger.models.Model;
import com.wordnik.swagger.models.properties.ArrayProperty;
import com.wordnik.swagger.models.properties.Property;
import com.wordnik.swagger.models.properties.RefProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwaggerParserUtils {
    final static String LIST_VARIABLE = "List";
    final static String GENERIC_LIST_VARIABLE = "List<";
    final static String END_OF_GENERIC_SIGN_VARIABLE = ">";
    private static class SwaggerParserUtilsHelper{
        public static final SwaggerParserUtils INSTANCE = new SwaggerParserUtils();
    }

    public static SwaggerParserUtils getInstance(){
        return SwaggerParserUtilsHelper.INSTANCE;
    }

    public Map.Entry<String, Model> getModel(Map<String, Model> definitions, String modelName){
        return definitions.entrySet().stream().parallel().filter(model -> model.getKey().equals(modelName)).findFirst().orElse(null);
    }

    public ModelTableDTO createModelTable(Map.Entry<String, Model> inputModel){
        String classname = inputModel.getKey();
        Model model = inputModel.getValue();

        List<ModelParameterDTO> parameters = new ArrayList<>();
        List<String> otherReferences = new ArrayList<>();

        Map<String, Property> properties = model.getProperties();
        if (properties != null){
            properties.forEach((key, value) -> {
                Property property = properties.get(key);
                if (SwaggerPropertiesUtils.getInstance().isPrimitive(property))
                    parameters.add(SwaggerPropertiesUtils.getInstance().setPrimitive(key, property));
                else if (SwaggerPropertiesUtils.getInstance().isArray(property)) {
                    if (SwaggerPropertiesUtils.getInstance().isPrimitive(((ArrayProperty) property).getItems())) {
                        ModelParameterDTO modelParameterDTO = SwaggerPropertiesUtils.getInstance().setPrimitive(key, ((ArrayProperty) property).getItems());
                        StringBuilder listVariable = new StringBuilder(GENERIC_LIST_VARIABLE);
                        modelParameterDTO.setParameterType(listVariable.append(modelParameterDTO.getParameterType()).append(END_OF_GENERIC_SIGN_VARIABLE).toString());
                        parameters.add(modelParameterDTO);
                    } else if (SwaggerPropertiesUtils.getInstance().isRefProperty(((ArrayProperty) property).getItems())) {
                        RefProperty refProperty = (RefProperty) ((ArrayProperty) property).getItems();
                        StringBuilder listVariable = new StringBuilder(GENERIC_LIST_VARIABLE);
                        parameters.add(ModelParameterDTO.builder()
                                .isRef(true)
                                .parameterName(key)
                                .parameterType(listVariable.append(refProperty.getSimpleRef()).append(END_OF_GENERIC_SIGN_VARIABLE).toString())
                                .build());
                        if (!otherReferences.contains(refProperty.getSimpleRef()))
                            otherReferences.add(refProperty.getSimpleRef());
                    } else {
                        parameters.add(ModelParameterDTO.builder()
                                .isRef(false)
                                .parameterName(key)
                                .parameterType(LIST_VARIABLE)
                                .build());
                    }
                } else if (SwaggerPropertiesUtils.getInstance().isRefProperty(property)) {
                    RefProperty refProperty = (RefProperty) property;
                    parameters.add(ModelParameterDTO.builder()
                            .isRef(true)
                            .parameterName(key)
                            .parameterType(refProperty.getSimpleRef())
                            .build());
                    if (!otherReferences.contains(refProperty.getSimpleRef()))
                        otherReferences.add(refProperty.getSimpleRef());
                }
            });
        }

        return ModelTableDTO.builder()
                .tableName(classname)
                .parameters(parameters)
                .otherReferences(otherReferences)
                .build();

    }

    public List<ModelTableDTO> getModelTables(Map<String, Model> definitions, String modelName){
        List<ModelTableDTO> result = new ArrayList<>();
        Map.Entry<String, Model> model = getModel(definitions, modelName);
        if (model == null)
            return new ArrayList<>();
        ModelTableDTO firstModelTable = SwaggerParserUtils.getInstance().createModelTable(model);
        result.add(firstModelTable);

        List<String> allReferences = new ArrayList<>();
        List<String> otherReferences = new ArrayList<>();
        if (!firstModelTable.getOtherReferences().isEmpty()) {
            firstModelTable.getOtherReferences().forEach(firstModelOtherReferences -> {
                if (!otherReferences.contains(firstModelOtherReferences)){
                    otherReferences.add(firstModelOtherReferences);
                    allReferences.add(firstModelOtherReferences);
                }
            });


            while (!otherReferences.isEmpty()){
                String refItem = otherReferences.get(0);
                Map.Entry<String, Model> refModel = getModel(definitions, refItem);
                ModelTableDTO modelTable = SwaggerParserUtils.getInstance().createModelTable(refModel);
                result.add(modelTable);
                if (!modelTable.getOtherReferences().isEmpty()) {
                    modelTable.getOtherReferences().forEach(innerModelOtherReferences -> {
                        if (!allReferences.contains(innerModelOtherReferences)){
                            otherReferences.add(innerModelOtherReferences);
                            allReferences.add(innerModelOtherReferences);
                        }
                    });
                }
                otherReferences.remove(refItem);
            }
        }
        return result;
    }
}
