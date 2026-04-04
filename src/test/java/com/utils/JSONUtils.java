package com.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class JSONUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JSONUtils(){}

    /* This method is used to read the message from file */
    public static JsonNode readJsonFromFile(String filepath){
        try {
            return mapper.readTree(Paths.get(filepath).toFile());
        } catch (IOException e) {
            throw new RuntimeException("Unable to find the json file :"+filepath+" "+e.getMessage());
        }
    }

    /* This method is used to read the json from string */
    public static JsonNode readJson(String jsonstring){
        try {
            return mapper.readTree(jsonstring);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid json string provided :"+jsonstring);
        }
    }

    /* This method is used to extract the value if user provides valid path */
    public static <T> T readValueFromJsonPath(JsonNode node, String jsonpath){
        return JsonPath.read(node.toString(),jsonpath);
    }

    public static String readJsonString(JsonNode node, String jsonpath){
        return readValueFromJsonPath(node,jsonpath).toString();
    }

    public static Integer readJsonInt(JsonNode node, String jsonpath){
        return readValueFromJsonPath(node,jsonpath);
    }

    public static Boolean readJsonBoolean(JsonNode node, String jsonpath){
        return readValueFromJsonPath(node,jsonpath);
    }

    public static Double readJsonDouble(JsonNode node, String jsonpath){
        return readValueFromJsonPath(node,jsonpath);
    }

    /* Method to set the value of particular key */
    public static JsonNode setJsonValue(JsonNode node, String jsonpath, Object value){
        DocumentContext context = JsonPath.parse(node.toString());
        context.set(jsonpath,value);
        return readJson(context.jsonString());
    }

    /* Covert JSON to Pojo Classes */
    public static <T> T jsonToPojo(JsonNode node, Class<T> clazz){
        try {
            return mapper.treeToValue(node,clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> jsonToListPojo(JsonNode node, Class<T> clazz){
        try {
            return mapper.readerForListOf(clazz).readValue(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Convert pojo classes to JSON */
    public static JsonNode pojoToJson(Object object){
        return mapper.valueToTree(object);
    }

    /* Compare JSON nodes */
    public static boolean compareJsonNode(JsonNode expected_node,JsonNode actual_node){
        return expected_node.equals(actual_node);
    }

    /* Save json to file */
    public static void saveJsonToFile(JsonNode node, String filepath){
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filepath),node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Pretty print json */
    public static String jsonPrettyPrint(JsonNode node){
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }




}
