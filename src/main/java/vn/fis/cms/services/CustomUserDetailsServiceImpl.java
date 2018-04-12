package vn.fis.cms.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.fis.cms.domain.Permission;
import vn.fis.cms.domain.Role;
import vn.fis.cms.domain.User;
import vn.fis.cms.repositories.AccountRepository;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
		    User user = accountRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("Không đúng Email.");
        }

            return new org.springframework.security.core.userdetails.User(email, user.getPassword(), !user.getIslock(), true, true, true, getAuthorities(user.getRoles()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
	}



	private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));		
    }
	
	private final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> permission = new ArrayList<String>();
        final List<Permission> collection = new ArrayList<Permission>();
        for (final Role role : roles) {
            collection.addAll(role.getPermissions());
        }
        for (final Permission item : collection) {
        	permission.add(item.getCode());
        }
        
        return permission;
    }
	
	private final List<GrantedAuthority> getGrantedAuthorities(final List<String> permission) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final String privilege : permission) {
        	System.out.println(privilege);
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
	
}