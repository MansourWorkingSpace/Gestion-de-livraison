package com.example.GestionDeLivraison.Model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.GestionDeLivraison.Model.Permission.*;

public enum RoleUser {
    ADMIN(
            Set.of(
                    ADMIN_CREATE, ADMIN_UPDATE, ADMIN_DELETE, ADMIN_READ,
                    LIVREUR_CREATE, LIVREUR_UPDATE, LIVREUR_DELETE, LIVREUR_READ,
                    COMMERCANT_CREATE, COMMERCANT_UPDATE, COMMERCANT_DELETE, COMMERCANT_READ,
                    CLIENT_CREATE, CLIENT_UPDATE, CLIENT_DELETE, CLIENT_READ
            )
    ),
    LIVREUR(
            Set.of(
                    LIVREUR_CREATE, LIVREUR_UPDATE, LIVREUR_DELETE, LIVREUR_READ
            )
    ),
    COMMERCANT(
            Set.of(
                    COMMERCANT_CREATE, COMMERCANT_UPDATE, COMMERCANT_DELETE, COMMERCANT_READ
            )
    ),
    CLIENT(
            Set.of(
                    CLIENT_CREATE, CLIENT_UPDATE, CLIENT_DELETE, CLIENT_READ
            )
    );

    private final Set<Permission> permissions;

    // ✅ Ajoute ce constructeur manuellement
    RoleUser(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return authorities;
    }
}
