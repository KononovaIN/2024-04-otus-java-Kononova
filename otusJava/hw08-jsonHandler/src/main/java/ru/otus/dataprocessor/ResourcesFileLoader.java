package ru.otus.dataprocessor;

import jakarta.json.*;
import ru.otus.model.Measurement;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    private final String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        List<Measurement> measurements = new ArrayList<>();
        // читает файл, парсит и возвращает результат
        try (var jsonReader = Json.createReader(new BufferedInputStream(new FileInputStream(fileName)))) {
            JsonStructure jsonStructure = jsonReader.read();

            if (jsonStructure.getValueType().equals(JsonValue.ValueType.ARRAY)) {
                JsonArray jsonArray = jsonStructure.asJsonArray();

                for (JsonValue next : jsonArray) {
                    JsonObject jsonObject = next.asJsonObject();

                    measurements.add(buildMeasurement(jsonObject));
                }
            } else if (jsonStructure.getValueType().equals(JsonValue.ValueType.OBJECT)) {
                JsonObject jsonObject = jsonStructure.asJsonObject();

                measurements.add(buildMeasurement(jsonObject));
            } else {
                throw new FileProcessException("Invalid structure of JSON");
            }

            return measurements;
        } catch (Exception e) {
            throw new FileProcessException(e);
        }
    }

    private Measurement buildMeasurement(JsonObject jsonObject) {
        JsonValue name = jsonObject.get("name");
        JsonValue value = jsonObject.get("value");

        return new Measurement(name.toString().replaceAll("\"", ""), Double.parseDouble(value.toString()));
    }
}
