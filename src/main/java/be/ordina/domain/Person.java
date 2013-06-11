package be.ordina.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RooJavaBean
@RooToString
@RooJpaEntity(table = "USERS")
public class Person implements UserDetails {

    private String username;

    private String password;

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Authorities> authorities = new ArrayList<Authorities>();


	@Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
}
