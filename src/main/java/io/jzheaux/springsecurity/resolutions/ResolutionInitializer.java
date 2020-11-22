package io.jzheaux.springsecurity.resolutions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

@Component
public class ResolutionInitializer implements SmartInitializingSingleton {
    private final ResolutionRepository resolutions;
    private final UserRepository users;

    public ResolutionInitializer(ResolutionRepository resolutions, UserRepository users) {
        this.resolutions = resolutions;
        this.users = users;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.resolutions.save(new Resolution("Read War and Peace", "user"));
        this.resolutions.save(new Resolution("Free Solo the Eiffel Tower", "user"));
        this.resolutions.save(new Resolution("Hang Christmas Lights", "user"));
        User user = new User("user",
                "{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
        user.grantAuthority("resolution:read");
        user.setFullName("User Userson");
        user.grantAuthority("resolution:write");
        user.grantAuthority("user:read");
        this.users.save(user);
        User hasRead = new User();
        hasRead.setFullName("Has Read");
        hasRead.setUsername("hasRead");
        hasRead.setPassword("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
        hasRead.grantAuthority("resolution:read");
        System.out.println(hasRead);
        this.users.save(hasRead);
        User admin = new User("admin", "{bcrypt}$2a$10$bTu5ilpT4YILX8dOWM/05efJnoSlX4ElNnjhNopL9aPoRyUgvXAYa");
        admin.grantAuthority("ROLE_ADMIN");
        admin.setFullName("Admin Adminson");
        this.users.save(admin);

        User hasWrite = new User();
        hasWrite.setFullName("Has Write");

        hasWrite.setUsername("hasWrite");
        hasWrite.setPassword("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
        hasWrite.grantAuthority("resolution:write");
        hasWrite.addFriend(hasRead);
        hasWrite.setSubscription("premium");
        this.users.save(hasWrite);
    }
}
