package org.artfable.test.builder.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.artfable.test.builder.core.data.UserRepository;
import org.artfable.test.builder.core.model.AppUser;
import org.artfable.test.builder.core.model.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author artfable
 * 15.06.17
 */
@Service
public class UserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Log LOG = LogFactory.getLog(UserDetailsAuthenticationProvider.class);

    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("Username wasn't provided");
        }

        LOG.info("disclose: " + authentication.getCredentials());
        String password = passwordEncoder.encode((String) authentication.getCredentials());
        LOG.info("disclose: " + password);

        AppUser user = userRepository.findByLogin(username);

        if (user == null || !passwordEncoder.matches((String) authentication.getCredentials(), user.getPassword())) {
            throw new UsernameNotFoundException("User with name [" + username + "] not found, or password is invalid");
        }

        LOG.debug("Login as " + username);

//        List<UserRole> roles = roleRepository.findAll(user.getRoles());
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
//
//        return new UserDetailsDto(user.getLogin(), user.getId(), user.getPassword(), authorities);

        return new UserDetailsDto(user.getLogin(), user.getId(), user.getPassword(), authorities);
    }
}
