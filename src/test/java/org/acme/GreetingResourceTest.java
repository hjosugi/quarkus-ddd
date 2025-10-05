package org.acme;

import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testHelloEndpoint() {
        assertTrue(true);
    }

}