package ru.gb.mall.inventory.enums;

public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MODERATOR("ROLE_MODERATOR"),
    ROLE_USER("ROLE_USER");

    private final String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}