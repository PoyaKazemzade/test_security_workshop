package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class RealNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"Sarah Conner", "Jeremy Clarkson"})
    void validNames(String name) {
        assertThat(RealName.validate(name)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sarah bloodyhell", "Jeremy badword Clarkson"})
    void invalidNames(String name) {
        assertThat(RealName.validate(name)).isFalse();
    }

    @Test
    void validNameWithLeetSpeak() {
        assertThat(RealName.validate("John B4dword Doe")).isFalse();
    }


    @Test
    void nullName() {
        assertThatThrownBy(() -> RealName.validate(null))
                .isInstanceOf(NullPointerException.class);
    }
}
