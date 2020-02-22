package com.masterpiece.stockmarketsimulator.entities;


import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Role extends AbstractEntity {


    @Column(length = 255, nullable = false, unique = true)
    private String code;


    @Column(length = 50, nullable = false)
    private boolean defaultRole = false;


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
