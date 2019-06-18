package com.example.demo;

import com.example.demo.json.Result;
import com.example.demo.json.SafeAccessor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;

import static java.util.Arrays.asList;

public class SafeTraversJSON {
    public static void main(String[] args) {
        SafeAccessor safeAccessor = new SafeAccessor();

        Result<String> key = safeAccessor.getAttribute(new JSONObject("{ \"key\": [\"value\"]}"), asList("key", 0));
        showResult(key);
        showResult(safeAccessor.getAttribute(new JSONArray("[{ \"key\": [\"from the array\"]}]"), asList(0, "key", 0, "more", "more", "more")));
        showResult(safeAccessor.getAttribute("raw value", Collections.emptyList()));
    }

    private static <T> void showResult(Result<T> attribute) {
        System.out.println(attribute.getData());
        for (String message : attribute.getErrorMessages().getMessages()) {
            System.out.println(message);
        }
    }
}
