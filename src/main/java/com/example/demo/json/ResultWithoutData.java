package com.example.demo.json;

import java.util.Optional;

public class ResultWithoutData<T> implements Result<T> {

    private final ErrorMessages errorMessages;

    public ResultWithoutData(ErrorMessages errorMessages) {
        this.errorMessages = errorMessages;
    }

    @Override
    public Optional<T> getData() {
        return Optional.empty();
    }

    @Override
    public ErrorMessages getErrorMessages() {
        return errorMessages;
    }
}
