package com.evconnect.evcloud.reporting.cache.location.data.domain;

import com.evconnect.evcloud.common.condition.ExcludeFromJacocoGeneratedReport;
import com.evconnect.evcloud.genability.api.domain.EvCloudGenabilityChargeType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ExcludeFromJacocoGeneratedReport
public class LocationUtilityRate {
    private Long lseId;
    private String lseName;
    @Field(FieldNames.TARIFF_ID)
    private Long tariffId;
    private String tariffName;
    private String tariffCode;
    private boolean isFlatCost;
    private Double pricePerKwh;
    private Set<EvCloudGenabilityChargeType> costReportComponents = new HashSet<>();

    public static class FieldNames {
        public static final String TARIFF_ID = "tariffId";
    }
}