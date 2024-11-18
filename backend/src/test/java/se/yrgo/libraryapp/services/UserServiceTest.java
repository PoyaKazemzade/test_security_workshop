package se.yrgo.libraryapp.services;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import se.yrgo.libraryapp.dao.UserDao;
import se.yrgo.libraryapp.entities.*;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Test
    @SuppressWarnings("deprecation")
    void correctLogin() {
        final String userId = "1";
        final UserId id = UserId.of(userId);
        final String username = "testuser";
        final String password = "password";
        final String passwordHash = "password";
        final LoginInfo info = new LoginInfo(id, passwordHash);
        final PasswordEncoder encoder =
                org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        when(userDao.getLoginInfo(username)).thenReturn(Optional.of(info));
        UserService userService = new UserService(userDao, encoder);
        assertThat(userService.validate(username,
                password)).isEqualTo(Optional.of(id));
    }

    @Test
    @SuppressWarnings("deprecation")
    void registerUser_Success() {
        final String username = "newuser";
        final String realname = "my name";
        final String password = "securePassword";
        final String passwordHash = "securePassword";
        final PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

        when(userDao.isNameAvailable(username)).thenReturn(true);
        when(userDao.register(username, realname, passwordHash)).thenReturn(true);

        UserService userService = new UserService(userDao, encoder);

        assertThat(userService.register(username, realname, password)).isTrue();
    }

    @Test
    @SuppressWarnings("deprecation")
    void registerUsernameNotAvailable() {
        final String username = "existingUser";
        final String realname = "my name";
        final String password = "password";
        final PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

        when(userDao.isNameAvailable(username)).thenReturn(false);

        UserService userService = new UserService(userDao, encoder);

        assertThat(userService.register(username, realname, password)).isFalse();
    }

    @Test
    @SuppressWarnings("deprecation")
    void registerInvalidInputThrowsException() {
        final PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();

        UserService userService = new UserService(userDao, encoder);

        assertThatThrownBy(() -> userService.register("", "my name", "password"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Username and password must not be empty");

        assertThatThrownBy(() -> userService.register("user", "my name", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Username and password must not be empty");
    }
}
