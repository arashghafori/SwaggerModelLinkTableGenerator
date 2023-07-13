# Swagger Model Linked Table Generator
This is a Java library that converts Swagger(V2) models to linked tables. It can convert any swagger model to a liked table even if the model is generic.

## Getting Started
To use the library, first, you need to get the library code and build it with your build tool(Maven/Gradle). Here is the example for Maven:
```text
mvn clean install
```
After that, clone WSDL Parser and build it with your build tool(Maven/Gradle). Finally, you can add the dependency to your Pom file like this:
```xml
<dependency>
    <groupId>com.AGH</groupId>
    <artifactId>SwaggerModelLinkTableGenerator</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Examples

For example, I used [swagger pest store](https://petstore.swagger.io/) and wanted to convert Pet model. By using below code:

```java
private final static String SWAGGER_V2_PETSTORE_LOCATION = "https://petstore.swagger.io/v2/swagger.json";
Swagger swagger = new SwaggerParser().read(SWAGGER_V2_PETSTORE_LOCATION);
SwaggerParserUtils.getInstance().getModelTables(swagger.getDefinitions(), "Pet");
```

you'll get this JSON as a result:

```json
[
  {
    "tableName": "Pet",
    "parameters": [
      {
        "parameterName": "id",
        "parameterType": "Long",
        "ref": false
      },
      {
        "parameterName": "category",
        "parameterType": "Category",
        "ref": true
      },
      {
        "parameterName": "name",
        "parameterType": "String",
        "ref": false
      },
      {
        "parameterName": "photoUrls",
        "parameterType": "List<String>",
        "ref": false
      },
      {
        "parameterName": "tags",
        "parameterType": "List<Tag>",
        "ref": true
      },
      {
        "parameterName": "status",
        "parameterType": "Enum:[available, pending, sold]",
        "ref": false
      }
    ],
    "otherReferences": [
      "Category",
      "Tag"
    ]
  },
  {
    "tableName": "Category",
    "parameters": [
      {
        "parameterName": "id",
        "parameterType": "Long",
        "ref": false
      },
      {
        "parameterName": "name",
        "parameterType": "String",
        "ref": false
      }
    ],
    "otherReferences": []
  },
  {
    "tableName": "Tag",
    "parameters": [
      {
        "parameterName": "id",
        "parameterType": "Long",
        "ref": false
      },
      {
        "parameterName": "name",
        "parameterType": "String",
        "ref": false
      }
    ],
    "otherReferences": []
  }
]
```

and in the end you can simply change it to this table in UI:

|               | Pet                             |             |
|:--------------|:--------------------------------|:------------|
| ParameterName | ParameterType                   | IsReference |
| Id            | Long                            | FALSE       |
| category      | Category                        | TRUE        |
| name          | String                          | FALSE       |
| photoUrls     | List<String>                    | FALSE       |
| tags          | List<Tag>                       | TRUE        |
| status        | Enum:[available, pending, sold] | FALSE       |


|               | Category                        |             |
|:--------------|:--------------------------------|:------------|
| ParameterName | ParameterType                   | IsReference |
| Id            | Long                            | FALSE       |
| name          | String                          | FALSE       |


|               | Tag                             |             |
|:--------------|:--------------------------------|:------------|
| ParameterName | ParameterType                   | IsReference |
| Id            | Long                            | FALSE       |
| name          | String                          | FALSE       |
