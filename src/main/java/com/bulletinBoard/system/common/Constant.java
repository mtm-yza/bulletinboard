package com.bulletinBoard.system.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class Constant {

    public static final int SUCCESS = 1;
    public static final int EMAIL_ALREADY_REGISTERED = 2;

    @Getter
    public static enum UserRole {

        GUEST(0, "Guest"), NORMAL(1, "Normal User"), ADMIN(2, "Admin");

        public static final Map<Integer, UserRole> BY_ID = new HashMap<>();
        public static final Map<String, UserRole> BY_NAME = new HashMap<>();

        private String name;
        private int id;

        static {
            // Attaching UserRoles by ID
            for (UserRole role : values()) {
                BY_ID.put(role.id, role);
            }
            // Attaching UserRoles by Name
            for (UserRole role : values()) {
                BY_NAME.put(role.name, role);
            }
        }
        
        UserRole(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public static UserRole valueOfId(int id) {
            return BY_ID.get(id);
        }

        public static UserRole valueOfName(String name) {
            return BY_NAME.get(name);
        }
    }
}
