package com.example.GestionDeLivraison.Model;

public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    LIVREUR_READ("livreur:read"),
    LIVREUR_UPDATE("livreur:update"),
    LIVREUR_CREATE("livreur:create"),
    LIVREUR_DELETE("livreur:delete"),
    COMMERCANT_READ("commercant:read"),
    COMMERCANT_UPDATE("commercant:update"),
    COMMERCANT_CREATE("commercant:create"),
    COMMERCANT_DELETE("commercant:delete"),
    CLIENT_READ("client:read"),
    CLIENT_UPDATE("client:update"),
    CLIENT_CREATE("client:create"),
    CLIENT_DELETE("client:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}