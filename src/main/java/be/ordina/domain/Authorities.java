package be.ordina.domain;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;

@RooJavaBean
@RooToString
@RooJpaEntity(table = "AUTHORITIES")
public class Authorities implements GrantedAuthority {

    private String username;

    private String authority;
}
