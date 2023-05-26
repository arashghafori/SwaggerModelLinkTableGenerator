package com.agh.SwaggerModelLinkTableGenerator.util;

import com.agh.SwaggerModelLinkTableGenerator.DTO.ModelTableDTO;
import com.wordnik.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class SwaggerParserUtilsTest {
    private final static String SWAGGER_V2_PETSTORE_LOCATION = "https://petstore.swagger.io/v2/swagger.json";
    private SwaggerParserUtils swaggerParserUtils;
    private Swagger swagger;

    @BeforeEach
    void setUp() {
        swaggerParserUtils = SwaggerParserUtils.getInstance();
        swagger = new SwaggerParser().read(SWAGGER_V2_PETSTORE_LOCATION);
    }

    @Test
    @DisplayName("get_model_table_without_any_references")
    void getUserModelTables() {
        List<ModelTableDTO> userModelTables = swaggerParserUtils.getModelTables(swagger.getDefinitions(), "User");
        assertNotNull(userModelTables);
    }

    @Test
    @DisplayName("get_model_table_with_references")
    void getPetModelTables() {
        List<ModelTableDTO> petModelTables = swaggerParserUtils.getModelTables(swagger.getDefinitions(), "Pet");
        assertNotNull(petModelTables);
        boolean hasReference = petModelTables.stream().anyMatch(item -> !item.getOtherReferences().isEmpty());
        assertTrue(hasReference);
    }
}