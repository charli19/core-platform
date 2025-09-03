package com.inditex.core.e2e;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class E2EApplicationTests {

    @LocalServerPort
    private int port;

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:e2e/features")
                .systemProperty("baseUrl", "http://localhost:" + port)
                .relativeTo(getClass());
    }

}