package com.creseliana.generator.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
public abstract class Model {
    @MongoId(FieldType.STRING)
    protected String id;
}
