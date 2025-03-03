package com.devsuperior.clientscrud.dto.errors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MessageList extends ErrorMessage {

    private final List<FieldMessage> errors = new ArrayList<>();

    public MessageList(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addMessage(String fieldName, String massage) {
        errors.add(new FieldMessage(fieldName, massage));
    }
}
