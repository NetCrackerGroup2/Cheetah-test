package com.ncedu.cheetahtest.user.entity;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String email;
    private String name;
    private String role;
    private String status;

    public UserDto(int id, String email, String name, String role, String status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public static UserDtoBuilder newBuilder() {
        return new UserDtoBuilder();
    }

    public static final class UserDtoBuilder {

        private int id;
        private String email;
        private String name;
        private String role;
        private String status;

        public UserDtoBuilder setId(int id) {
            return this;
        }

        public UserDtoBuilder setEmail(String email) {
            return this;
        }

        public UserDtoBuilder setName(String name) {
            return this;
        }

        public UserDtoBuilder setRole(String role) {
            return this;
        }

        public UserDtoBuilder setStatus(String status) {
            return this;
        }

        public UserDto build() {
            return new UserDto(id, email, name, role, status);
        }
    }
}
