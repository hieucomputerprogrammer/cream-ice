package io.hieu.creamice.security.jwt;

import io.hieu.creamice.beans.User;
import io.hieu.creamice.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository IUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = IUserRepository.findByUsername(username);
        JwtUserDetails jwtUserDetails = new JwtUserDetails(1L, user.getUsername(),
                user.getPassword(), user.getRoles());
        return jwtUserDetails;
    }
}