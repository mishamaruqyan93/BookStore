package am.itspace.bookstore.sequrity;

import am.itspace.bookstore.entity.User;
import am.itspace.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = repository.findByEmail(username);
        if (!byEmail.isPresent()) {
            throw new UsernameNotFoundException("User does not exists");
        }
        return new CurrentUser(byEmail.get());
    }
}
