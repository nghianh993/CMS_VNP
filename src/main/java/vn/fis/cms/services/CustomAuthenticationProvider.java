package vn.fis.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fis.cms.domain.User;
import vn.fis.cms.repositories.AccountRepository;

import java.util.ArrayList;

@Service
@Transactional
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = accountRepository.findByEmail(email);

        if (user == null) {
            System.out.println("Không đúng Email.");
            return null;
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);

        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
