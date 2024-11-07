package com.vpbs.bpm.bond.external.infrastructure.integration.esign.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SignPageType {
    ALL,
    LAST_PAGE,
    ODD_ONLY,
    EVEN_ONLY,
    CUSTOM;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    @JsonCreator
    public static SignPageType fromString(String key) {
        return key == null ? null : SignPageType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toLowerCase() {
        return toString();
    }
}
