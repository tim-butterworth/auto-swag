package com.example.demo.json;

import java.util.Optional;

public class ResultWithData<T> implements Result<T> {

    private final ErrorMessages errorMessages;
    private final Optional<T> data;

    public ResultWithData(ErrorMessages errorMessages, T data) {
        this.errorMessages = errorMessages;
        this.data = Optional.ofNullable(data);
    }

    @Override
    public Optional<T> getData() {
        return data;
    }

    @Override
    public ErrorMessages getErrorMessages() {
        return errorMessages;
    }
}
