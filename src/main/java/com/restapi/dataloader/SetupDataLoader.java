package com.restapi.dataloader;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.model.VegOrNonVeg;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.repository.VegOrNonVegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private VegOrNonVegRepository vegOrNonVegRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

//        Create user roles
        Role userRole = createRoleIfNotFound(Role.USER);
        Role adminRole = createRoleIfNotFound(Role.ADMIN);
        Role restaurantRole = createRoleIfNotFound(Role.RESTAURANT);
//        Create user
        createUserIfNotFound("user", "user", userRole);
        createUserIfNotFound("admin", "admin", adminRole);
        createUserIfNotFound("restaurant", "restaurant", restaurantRole);
//        Create Veg or NonVeg
        createVegOrNonVegIfNotFound("Veg");
        createVegOrNonVegIfNotFound("Non Veg");
        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(final String username) {
        Role role = roleRepository.findByName(username);
        if (role == null) {
            role = new Role();
            role.setName(username);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private AppUser createUserIfNotFound(final String username, final String password,
                                         final Role role) {
        Optional<AppUser> optionalUser = userRepository.findByUsername(username);
        AppUser user = null;
        if (optionalUser.isEmpty()) {
            user = new AppUser();
            user.setUsername(username);
            user.setName(username);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setRoles(role);
            user = userRepository.save(user);
        }
        return user;
    }
    @Transactional
    private VegOrNonVeg createVegOrNonVegIfNotFound(final String title){
        VegOrNonVeg vegOrNonVeg=vegOrNonVegRepository.findByName(title);
        if(vegOrNonVeg==null){
            vegOrNonVeg=new VegOrNonVeg();
            vegOrNonVeg.setName(title);
            vegOrNonVeg=vegOrNonVegRepository.save(vegOrNonVeg);
        }
        return vegOrNonVeg;
    }
}
