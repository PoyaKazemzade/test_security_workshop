package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class UsernameTest {
    @Test
    void correctUsername() {
        assertThat(Username.validate("bosse")).isTrue();
    }

    @Test
    void incorrectUsername() {
        assertThat(Username.validate("name with space")).isFalse();
    }

    @Test
    void nullInput() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> Username.validate(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"bo", "joe"})
    void shortUsername(String name) {
        assertThat(Username.validate(name)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"😀johan", "§", "----"})
    void invalidCharacters(String username) {
        assertThat(Username.validate(username)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Agent_007", "1990Anton"})
    void digitInputs(String username) {
        assertThat(Username.validate(username)).isTrue();
    }

    @ParameterizedTest
    @EmptySource
    void emptyStringUsername(String username) {
        assertThat(Username.validate(username)).isFalse();
    }

}
