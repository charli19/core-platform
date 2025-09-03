package com.inditex.core.price.infrastructure.config;

import com.inditex.core.AbstractApplicationTest;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenApiConfigTest extends AbstractApplicationTest {

    @Test
    void testCustomOpenAPIBean() {
        OpenApiConfig config = new OpenApiConfig();

        OpenAPI openAPI = config.customOpenAPI();

        assertNotNull(openAPI, "El bean OpenAPI no debe ser null");

        Info info = openAPI.getInfo();
        assertNotNull(info, "El objeto Info no debe ser null");

        assertEquals("API Core Platform", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertEquals("Core Platform API Documentation", info.getDescription());
    }
}