package com.github.floushee.playground

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals


@ExtendWith(MockKExtension::class)
class ApplicationTest {

    @Test
    fun `health check returns http 200`() = withTestApplication({
        moduleWithDependencies()
    }) {
        with(handleRequest(HttpMethod.Get, "/api/helloworld")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello, World!", response.content!!)
        }
    }
}