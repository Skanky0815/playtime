package org.playtime.application.commons.test

import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.mockserver.client.MockServerClient
import org.mockserver.logging.MockServerLogger
import org.mockserver.serialization.ExpectationSerializer
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

/**
 * Extensions for Integration Tests annotated with @MockServerTest
 *
 *
 * This extension loads all expectations (test data) from json files in the provided location and
 * configures the mock server with them. After each test case the server gets reset (by the mock
 * server test execution listener and not this extension) and this extension ensures that all test
 * data is deployed to the mock server again.
 *
 *
 * This extension can be registered in a @SpringBootTest annotated with @MockServerTest with the
 * following code:
 *
 * <pre>
 * @RegisterExtension
 * static MockServerExtension mockServerExtension =
 * MockServerExtension.builder().resourceLocation("classpath:mockdata/ *.json").build();
</pre> *
 *
 * Pay attention that the test also contains the following variable (this is a restriction by the
 * MockServer extension and not this one.
 *
 * <pre>
 * // This variable is needed that the mock server listener starts the mock server
 * @SuppressWarnings("unused")
 * private MockServerClient mockServerClient;
</pre> *
 */
class MockServerExtension(
    private val resourceLocation: String
) : BeforeAllCallback, BeforeEachCallback {

    private lateinit var arrayString: String

    // Loads all json files from the given resource location, converts them into a json array.
    //
    // Each json file must contain a json object according to the expectation definition of the
    // mock server (see https://www.mock-server.com/mock_server/creating_expectations.html).
    override fun beforeAll(context: ExtensionContext) {
        val json = PathMatchingResourcePatternResolver()
            .getResources(resourceLocation)
            .map { it.inputStream }
            .map { it.readAllBytes() }
            .map { String(it) }
            .map { JSONObject(it) }

        arrayString = JSONArray(json).toString()
    }

    // This file is passed to the mock server implementation and is
    // read at start up then.
    override fun beforeEach(context: ExtensionContext) {
        val expectationSerializer = ExpectationSerializer(MOCK_SERVER_LOGGER)
        val optionalTestClass = context.testClass

        if (optionalTestClass.isPresent) {
            val testClass = context.testClass.get()
            val mockServerClientField = testClass.getDeclaredField("mockServerClient")
            val mockServerClient = mockServerClientField.get(context.testInstance.get()) as MockServerClient

            mockServerClientField.isAccessible = true
            mockServerClient.upsert(*expectationSerializer.deserializeArray(arrayString, false))
            mockServerClientField.isAccessible = false
        }
    }

    companion object {
        private val MOCK_SERVER_LOGGER: MockServerLogger = MockServerLogger(MockServerExtension::class.java)
    }
}
