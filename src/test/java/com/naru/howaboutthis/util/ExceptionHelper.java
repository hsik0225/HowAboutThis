package com.naru.howaboutthis.util;

import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionHelper {
    public static <T extends Throwable> void exceptionTest(Class<T> expectedType, Executable executable, String expectedMessage) {
        T exception = assertThrows(expectedType, executable);

        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
    }
}
