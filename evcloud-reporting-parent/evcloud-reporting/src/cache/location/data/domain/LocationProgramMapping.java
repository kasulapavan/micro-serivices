package com.evconnect.evcloud.reporting.cache.location.data.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import static com.evconnect.evcloud.reporting.cache.location.data.domain.LocationProgramMapping.FieldNames.SITE_ID;
import static com.evconnect.evcloud.reporting.cache.location.data.domain.LocationProgramMapping.FieldNames.SITE_NAME;
import static com.evconnect.evcloud.reporting.cache.location.data.domain.LocationProgramMapping.FieldNames.UTILITY_ID;
import static com.evconnect.evcloud.reporting.cache.location.data.domain.LocationProgramMapping.FieldNames.VENDOR_ID;

@Getter
@Setter
@EqualsAndHashCode
public class LocationProgramMapping {
    @Field(UTILITY_ID)
    private String utilityId;

    @Field(SITE_ID)
    private String siteId;

    @Field(VENDOR_ID)
    private String vendorId;

    @Field(SITE_NAME)
    private String siteName;


    public static class FieldNames {
        public static final String UTILITY_ID = "utilityId";
        public static final String SITE_ID = "siteId";
        public static final String VENDOR_ID = "vendorId";
        public static final String SITE_NAME = "siteName";
    }
}