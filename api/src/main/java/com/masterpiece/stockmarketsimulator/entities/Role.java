package com.masterpiece.stockmarketsimulator.entities;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;


@Entity
public class Role extends AbstractEntity {


    @Column(length = 256, nullable = false, unique = true)
    private String code;


    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    private boolean defaultRole = false;

    protected Role(){}

    public Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(boolean defaultRole) {
        this.defaultRole = defaultRole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "code='" + code + '\'' +
                ", defaultRole=" + defaultRole +
                '}';
    }
}
