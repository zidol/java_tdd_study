package com.example.chap09.autodebit;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.jupiter.api.Assertions.*;

/**
 * WireMockServer는 HTTP 서버를 흉내 낸다.
 * 일반적 사용법
 * - 테스트 실행 전에 WireMockServer를 시작한다. 실제 HTTP 서버가 뜬다.
 * - 테스트에서 WireMockServer의 동작을 기술한다.
 * - HTTP연동을 수행하는 테스트를 실행한다.
 * - 테스트 실행 후에 WireMockServer를 중지한다.
 *
 */
class CardNumberValidatorTest {

    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer(options().port(8089));
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void valid() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .withRequestBody(equalTo("1234567890"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("ok"))
        );

        CardNumberValidator validator =
                new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.VALID, validity);
    }

    @Test
    void timeout() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .willReturn(aResponse()
                        .withFixedDelay(5000))
        );

        CardNumberValidator validator =
                new CardNumberValidator("http://localhost:8089");
        CardValidity validity = validator.validate("1234567890");
        assertEquals(CardValidity.TIMEOUT, validity);
    }
}