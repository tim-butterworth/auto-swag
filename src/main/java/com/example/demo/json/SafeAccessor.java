package com.example.demo.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class SafeAccessor {
    public <T> Result<T> getAttribute(Object jsonObject, List<Object> path) {
        ErrorMessages errorMessages = new ErrorMessages();
        if (path.isEmpty()) {
            errorMessages.addMessage("The path was empty");

            return new ResultWithData<>(errorMessages, (T) jsonObject);
        }

        Iterator<Object> iterator = path.iterator();
        Optional<Object> currentValue = Optional.ofNullable(jsonObject);

        while (iterator.hasNext()) {
            Object rawKey = iterator.next();
            if (rawKey instanceof String) {
                currentValue = tryAccessingJsonObject(currentValue, (String) rawKey, errorMessages);
            } else if (rawKey instanceof Integer) {
                currentValue = tryAccessingJsonArray(currentValue, (Integer) rawKey, errorMessages);
            } else {
                errorMessages.addMessage(rawKey.getClass().getSimpleName() + " is not an acceptable key");

                return new ResultWithData<>(errorMessages, null);
            }
        }

        return currentValue
                .<Result<T>>map(value -> new ResultWithData<>(errorMessages, (T) value))
                .orElseGet(() -> new ResultWithoutData<T>(errorMessages));
    }

    private Optional<Object> tryAccessingJsonArray(Optional<Object> currentValue, Integer rawKey, ErrorMessages errorMessages) {
        return currentValue
                .flatMap((value) -> {
                    if (value instanceof JSONArray) {
                        return Optional.of((JSONArray) value);
                    } else {
                        errorMessages.addMessage("Tried to Integer access the non array " + value);
                        return Optional.empty();
                    }
                })
                .flatMap((array) -> {
                    if (array.length() > rawKey) {
                        return Optional.of(array.get(rawKey));
                    } else {
                        errorMessages.addMessage(rawKey + " was out of bounds for " + array.toString());
                        return Optional.empty();
                    }
                });
    }

    private Optional<Object> tryAccessingJsonObject(Optional<Object> currentValue, String rawKey, ErrorMessages errorMessages) {
        return currentValue
                .flatMap((value) -> {
                    if (value instanceof JSONObject) {
                        return Optional.of((JSONObject) value);
                    } else {
                        errorMessages.addMessage("Could not use String access for " + value);
                        return Optional.empty();
                    }
                })
                .flatMap((jsonObject) -> {
                    if (jsonObject.has(rawKey)) {
                        return Optional.of(jsonObject.get(rawKey));
                    } else {
                        errorMessages.addMessage(rawKey + " was not present on " + jsonObject.toString());
                        return Optional.empty();
                    }
                });
    }
}
