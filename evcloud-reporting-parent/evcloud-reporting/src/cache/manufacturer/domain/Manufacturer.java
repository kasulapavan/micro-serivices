package com.evconnect.evcloud.reporting.cache.manufacturer.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static com.evconnect.evcloud.reporting.cache.manufacturer.domain.Manufacturer.FieldNames.MANUFACTURER_ID;
import static com.evconnect.evcloud.reporting.cache.manufacturer.domain.Manufacturer.FieldNames.NAME;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Document(collection = "REPORTING-Manufacturer")
public class Manufacturer {
    @Id
    @Field(MANUFACTURER_ID)
    private String manufacturerId;
    @Field(NAME)
    private String name;

    public static class FieldNames {
        public static final String MANUFACTURER_ID = "_id";
        public static final String NAME = "name";
    }
}