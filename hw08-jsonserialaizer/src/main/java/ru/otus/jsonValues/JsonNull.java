package ru.otus.jsonValues;

import ru.otus.JsonValue;

public class JsonNull implements JsonValue {
    public JsonNull() {}

    @Override
    public String toString() {
        return "null";
    }
}
