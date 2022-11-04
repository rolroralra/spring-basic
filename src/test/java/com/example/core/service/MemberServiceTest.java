package com.example.core.service;

import com.example.core.config.AppConfig;
import com.example.core.model.Grade;
import com.example.core.model.Member;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberServiceTest {
    private MemberService memberService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Order(1)
    @DisplayName("Test join")
    @ParameterizedTest
    @CsvSource(value = {"1:memberA:VIP", "2:memberB:BASIC"}, delimiterString = ":")
    void join(Long memberId, String name, Grade grade) {
        // Given
        Member member = new Member(memberId, name, grade);

        // When
        memberService.join(member);
        Member findMember = memberService.findMember(memberId);

        // Then
        assertThat(findMember).isEqualTo(member);
    }

    @Order(2)
    @DisplayName("Test find")
    @ParameterizedTest
    @MethodSource("provideVipMember")
    void findMember(Member member) {
        // Given
        assertThat(member).isNotNull()
                .hasFieldOrPropertyWithValue("grade", Grade.VIP);

        memberService.join(member);

        // When
        Member findMember = memberService.findMember(1L);

        // Then
        assertThat(findMember).isNotNull();
    }

    public static List<Arguments> provideVipMember() {
        return Stream.of(new Member(1L, "memberA", Grade.VIP))
                .map(Arguments::of)
                .collect(Collectors.toList());
    }

    public static List<Arguments> provideBasicMember() {
        return Stream.of(new Member(1L, "memberA", Grade.BASIC))
                .map(Arguments::of)
                .collect(Collectors.toList());
    }
}
