package com.inditex.core.e2e;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class E2EApplicationTests {

    @LocalServerPort
    private int port;

    @Karate.Test
    Karate testAll() throws IOException {
        Results results = Runner.path("classpath:e2e/features")
                .systemProperty("baseUrl", "http://localhost:" + port)
                .outputCucumberJson(true)
                .parallel(1);

        copyReports(results.getReportDir());

        return Karate.run("classpath:e2e/features")
                .systemProperty("baseUrl", "http://localhost:" + port)
                .relativeTo(getClass());
    }

    private void copyReports(String sourceDirPath) throws IOException {
        Path sourceDir = Paths.get(sourceDirPath);
        Path targetDir = Paths.get("src/main/resources/static/karate/reports");
        Files.createDirectories(targetDir);

        try (Stream<Path> paths = Files.walk(sourceDir)) {
            paths.filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            Files.copy(file, targetDir.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        System.out.println("Reporte disponible en: https://core-platform.up.railway.app/karate/reports/karate-summary.html");
    }


}