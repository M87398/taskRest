package org.taskrest.remotedataprovider;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RemoteUserDataTest {

    private static Stream<Arguments> remoteUserData() {
        return Stream.of(
                Arguments.of(RemoteUserData.builder().followers(4).publicRepos(10).build(), 18.0f),
                Arguments.of(RemoteUserData.builder().followers(0).publicRepos(10).build(), null),
                Arguments.of(RemoteUserData.builder().followers(7).publicRepos(10).build(), 10.285714f)
        );
    }

    @ParameterizedTest
    @MethodSource("remoteUserData")
    void shouldGetCalculations(RemoteUserData data, Float expected) {
        //given

        //when
        Float result = data.getCalculations();

        //then
        assertThat(result).isEqualTo(expected);
    }
}