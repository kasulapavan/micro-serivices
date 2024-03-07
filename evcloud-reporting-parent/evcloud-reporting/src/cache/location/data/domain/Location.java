package com.evconnect.evcloud.reporting.cache.location.data.domain;

import com.evconnect.evcloud.common.condition.ExcludeFromJacocoGeneratedReport;
import com.evconnect.evcloud.common.db.annotation.CustomCompoundIndex;
import com.evconnect.evcloud.common.db.annotation.PartialExpression;
import com.evconnect.evcloud.reporting.domain.LatitudeLongitude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.evconnect.evcloud.reporting.cache.location.data.domain.LocationUtilityRate.FieldNames.TARIFF_ID;

@Document(collection = "Reporting-Location")
@Getter
@Setter
@ToString
@Accessors(chain = true)
@CustomCompoundIndex(
        name = "utilityRateTariffId",
        def = "{'" + Location.FieldNames.UTILITY_RATE + "." + TARIFF_ID + "': 1}",
        partialExpression = @PartialExpression(
                value = Location.FieldNames.UTILITY_RATE + "." + TARIFF_ID
        ))
@ExcludeFromJacocoGeneratedReport
public class Location {

    @Id
    @Field(FieldNames.EXTERNAL_ID)
    private String externalId;
    @Field(FieldNames.LAST_UPDATED)
    private Date lastUpdated;
    @Indexed
    @Field(FieldNames.NETWORK_ID)
    private String networkId;
    @Field(FieldNames.ORGANIZATION_ID)
    private String organizationId;
    @Field(FieldNames.LOCATION_NAME)
    private String name;
    @Field(FieldNames.COORDINATES)
    private LatitudeLongitude latitudeLongitude;
    @Field(FieldNames.BUSINESS_TYPE)
    private String businessType;
    @Field(FieldNames.SITE_TYPE)
    private String siteType;
    @Field(FieldNames.UTILITY_PROGRAM)
    private LocationProgramMapping utilityProgram;
    @Field(FieldNames.ADDRESS)
    private Address address;
    @Field(FieldNames.ALT_ID)
    private String altId;
    @Field(FieldNames.TIME_STAMP)
    private Long timeStamp;
    @Field(FieldNames.ZONE_ID)
    private ZoneId zoneId;
    @Field(FieldNames.HIDE_ON_MAP)
    private boolean hideOnMap;
    @Field(FieldNames.HIDE_FROM_OPERATORS)
    private List<String> hideFromOperators;
    @Field(FieldNames.IS_RFID_REQUIRED)
    private boolean isRfidRequired;
    @Field(FieldNames.PRIMARY_SNITCH_EMAIL)
    private String primarySnitchEmail;// for snitching
    @Field(FieldNames.CC_EMAILS)
    private String[] ccEmails; //for snitching
    @Field(FieldNames.DEFAULT_LOCATION_TARIFF_ID)
    private String defaultLocationTariffId;
    @Field(FieldNames.UTILITY_RATE)
    private LocationUtilityRate utilityRate;

    public static class FieldNames {
        public static final String TIME_STAMP = "timeStamp";
        public static final String LAST_UPDATED = "lastUpdated";
        public static final String ZONE_ID = "zoneId";
        public static final String EXTERNAL_ID = "_id";
        public static final String NETWORK_ID = "networkId";
        public static final String ORGANIZATION_ID = "organizationId";
        public static final String LOCATION_NAME = "name";
        public static final String COORDINATES = "latitudeLongitude";
        public static final String BUSINESS_TYPE = "businessType";
        public static final String SITE_TYPE = "siteType";
        public static final String UTILITY_PROGRAM = "utilityProgram";
        public static final String ADDRESS = "address";
        public static final String ALT_ID = "altId";
        public static final String HIDE_ON_MAP = "hideOnMap";
        public static final String HIDE_FROM_OPERATORS = "hideFromOperators";
        public static final String IS_RFID_REQUIRED = "isRfidRequired";
        public static final String PRIMARY_SNITCH_EMAIL = "primarySnitchEmail";
        public static final String CC_EMAILS = "ccEmails";
        public static final String DEFAULT_LOCATION_TARIFF_ID = "defaultLocationTariffId";
        public static final String UTILITY_RATE = "utilityRate";
    }
}