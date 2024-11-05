package se.yrgo.libraryapp.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class UtilsTest {

    //testing onlyLettersAndWhitespace() method in Utils class

    @ParameterizedTest
    @ValueSource(strings = {"hello world", "Hello World", "hello, world!"})
    void normalSentence(String string){
        assertThat(Utils.onlyLettersAndWhitespace(string)).isEqualTo("hello world");
    }

    @Test
    void mixedCharacters(){
        assertThat(Utils.onlyLettersAndWhitespace("😀Hello /world€")).isEqualTo("hello world");
    }

    @Test
    void nullInput(){
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> Utils.onlyLettersAndWhitespace(null));
    }
    @Test
    void onlyWhitespace(){
        assertThat(Utils.onlyLettersAndWhitespace("   ")).isEqualTo("   ");
    }

    @Test
    void numeralInput(){
        assertThat(Utils.onlyLettersAndWhitespace("1234")).isEqualTo("");
    }



    // testing cleanAndUnleet() method in Utils class

    @Test
    void leetCharacters() {
        assertThat(Utils.cleanAndUnLeet("H3ll0 W0rld")).isEqualTo("hello world");
    }



    @Test
    void specialCharacters() {
        assertThat(Utils.cleanAndUnLeet("H3ll0 W@rld!"))
                .isEqualTo("hello wrld");
    }


    @ParameterizedTest
    @EmptySource
    void emptyString(String string) {
        assertThat(Utils.cleanAndUnLeet(string)).isEqualTo("");
    }

    @Test
    void testCleanAndUnLeetNullInput() {
        assertThatThrownBy(() -> Utils.cleanAndUnLeet(null))
                .isInstanceOf(NullPointerException.class);
    }
}
