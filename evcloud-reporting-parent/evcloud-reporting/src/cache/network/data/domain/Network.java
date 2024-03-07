package com.evconnect.evcloud.reporting.cache.network.data.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.COUNTRY_CODE;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.COUNTRY_CODES;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.CURRENCIES;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.CURRENCY;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.CUSTOMER_SUPPORT_EMAIL;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.KEY_FOB_COST;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.NAME;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.NETWORK_ID;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.PARTY_ID;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.PREFERRED_LANGUAGE;
import static com.evconnect.evcloud.reporting.cache.network.data.domain.Network.FieldNames.WEBSITE;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Document(collection = "Reporting-Network")
public class Network {
    @Id
    @Field(NETWORK_ID)
    private String networkId;
    @Field(NAME)
    private String name;
    @Field(CUSTOMER_SUPPORT_EMAIL)
    private String customerSupportEmail;
    @Field(KEY_FOB_COST)
    private Double keyFobCost;
    /**
     * @deprecated (remove when users can create multi - country network and all checks are done)
     */
    @Deprecated(forRemoval = true)
    @Field(COUNTRY_CODE)
    private String countryCode;
    @Field(COUNTRY_CODES)
    private Set<String> countryCodes;
    @Field(PARTY_ID)
    private String partyId;
    /**
     * @deprecated (remove when users can create multi - currency network and all checks are done)
     */
    @Deprecated(forRemoval = true)
    @Field(CURRENCY)
    private String currency;
    @Field(CURRENCIES)
    private Set<String> currencies;
    @Field(WEBSITE)
    private String website;
    @Field(PREFERRED_LANGUAGE)
    private String preferredLanguage;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FieldNames {
        public static final String NETWORK_ID = "_id";
        public static final String NAME = "name";
        public static final String CUSTOMER_SUPPORT_EMAIL = "customerSupportEmail";
        public static final String KEY_FOB_COST = "keyFobCost";
        public static final String COUNTRY_CODE = "countryCode";
        public static final String COUNTRY_CODES = "countryCodes";
        public static final String PARTY_ID = "partyId";
        public static final String CURRENCY = "currency";
        public static final String CURRENCIES = "currencies";
        public static final String WEBSITE = "website";
        public static final String PREFERRED_LANGUAGE = "preferredLanguage";
    }

}