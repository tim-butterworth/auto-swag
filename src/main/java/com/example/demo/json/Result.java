package com.example.demo.json;

import java.util.Optional;

public interface Result<T> {
    Optional<T> getData();
    ErrorMessages getErrorMessages();
}
