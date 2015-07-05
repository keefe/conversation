package us.categorize.conversation.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import us.categorize.conversation.dao.UserDao;
import us.categorize.conversation.model.UserProfile;

public class AccountConnectionSignupService implements ConnectionSignUp {

    private static final Logger LOG = LoggerFactory.getLogger(AccountConnectionSignupService.class);

    private final UserDao userDao;

    public AccountConnectionSignupService(UserDao usersDao) {
        this.userDao = usersDao;
    }

    public String execute(Connection<?> connection) {
        org.springframework.social.connect.UserProfile profile = connection.fetchUserProfile();
        String userId = UUID.randomUUID().toString();
        // TODO: Or simply use: r = new Random(); r.nextInt(); ???
        LOG.debug("Created user-id: " + userId);
        userDao.createUser(userId, new UserProfile(userId, profile));
        return userId;
    }
}
