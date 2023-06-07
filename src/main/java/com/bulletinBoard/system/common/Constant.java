package com.bulletinBoard.system.common;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class Constant {

    /**
     * <h2>SUCCESS</h2>
     * <p>
     * SUCCESS
     * </p>
     */
    public static final int SUCCESS = 1;

    /**
     * <h2>EMAIL_ALREADY_REGISTERED</h2>
     * <p>
     * EMAIL_ALREADY_REGISTERED
     * </p>
     */
    public static final int EMAIL_ALREADY_REGISTERED = 2;

    /**
     * <h2>INVALID_CREDENTIAL</h2>
     * <p>
     * INVALID_CREDENTIAL
     * </p>
     */
    public static final int INVALID_CREDENTIAL = 3;

    /**
     * <h2>UserRole Class</h2>
     * <p>
     * Process for Displaying UserRole
     * </p>
     * 
     * @author YeZawAung
     *
     */
    @Getter
    public static enum UserRole {

        GUEST(0, "ROLE_GUEST"), NORMAL(1, "ROLE_USER"), ADMIN(2, "ROLE_ADMIN");

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

        /**
         * <h2>Constructor for UserRole</h2>
         * <p>
         * Constructor for UserRole
         * </p>
         * 
         * @param id
         * @param name
         */
        UserRole(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * <h2>valueOfId</h2>
         * <p>
         * Get UserRole by ID
         * </p>
         *
         * @param id int
         *
         * @return UserRole
         */
        public static UserRole valueOfId(int id) {
            return BY_ID.get(id);
        }

        /**
         * <h2>valueOfName</h2>
         * <p>
         * Get UserRole by Name
         * </p>
         *
         * @param name String
         *
         * @return UserRole
         */
        public static UserRole valueOfName(String name) {
            return BY_NAME.get(name);
        }
    }
}
