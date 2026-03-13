package ru.tictactoe.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MatrixJsonConverter implements AttributeConverter<int[][], String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(int[][] matrix) {
        if (matrix == null) return null;
        try {
            return mapper.writeValueAsString(matrix);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize matrix", e);
        }
    }

    @Override
    public int[][] convertToEntityAttribute(String json) {
        if (json == null) return null;
        try {
            return mapper.readValue(json, int[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize matrix", e);
        }
    }
}