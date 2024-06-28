package ru.otus.dataprocessor;

import jakarta.json.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Map;

public class FileSerializer implements Serializer {
    private final String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) {
        // формирует результирующий json и сохраняет его в файл
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        for (var item : data.entrySet()) {
            objectBuilder.add(item.getKey(), item.getValue());
        }

        JsonObject build = objectBuilder.build();

        try (var output = Json.createWriter(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            output.writeObject(build);
        } catch (Exception e) {
            throw new FileProcessException(e);
        }
    }
}
