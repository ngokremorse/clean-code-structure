package com.vpbs.bpm.bond.external.infrastructure.integration.esign.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SignatureType {
    PRIMARY,
    MARK;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    @JsonCreator
    public static SignatureType fromString(String key) {
        return key == null ? null : SignatureType.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String toLowerCase() {
        return toString();
    }
}
