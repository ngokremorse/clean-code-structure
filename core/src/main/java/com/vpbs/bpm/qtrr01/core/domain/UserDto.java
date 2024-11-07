package com.vpbs.bpm.qtrr01.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;

    private String username;

    private String displayName;

    private String department;

    private String email;

    private Timestamp bpmLastModifiedDate;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;

    private Type type = Type.USER;

    public static enum Type {
        GROUP, USER
    }
}
