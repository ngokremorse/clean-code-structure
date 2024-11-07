package com.vpbs.bpm.qtrr01.external.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class XUserInfo {
    private String scope;
    private String email;
    @JsonProperty("email_verified")
    private boolean emailVerified;
    private String name;
    @JsonProperty("preferred_username")
    private String preferredUsername;
    @JsonProperty("resource_access")
    private ResourceAcess resourceAcess;
    @JsonProperty("realm_access")
    private RealmAcess realmAcess;
}
