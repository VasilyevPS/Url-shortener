package vasilyevps.urlshortener.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import vasilyevps.urlshortener.repository.UrlRepository;
import vasilyevps.urlshortener.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vasilyevps.urlshortener.utils.TestUtils.API_ROOT_ADDRESS;
import static vasilyevps.urlshortener.utils.TestUtils.DEFAULT_API_URL_SHORT;
import static vasilyevps.urlshortener.utils.TestUtils.DEFAULT_URL_KEY;

@AutoConfigureMockMvc
@SpringBootTest
public class ApiControllerIT {

    @Autowired
    TestUtils testUtils;

    @Autowired
    private UrlRepository urlRepository;

    @AfterEach
    public void clear() {
        testUtils.clearDB();
    }


    @Test
    public void testCreateAPI() throws Exception {
        assertEquals(0, urlRepository.count());
        testUtils.addDefaultUrl(API_ROOT_ADDRESS).andExpect(status().isCreated());
        assertEquals(1, urlRepository.count());
        var expected = urlRepository.findByUrlKey(DEFAULT_URL_KEY).orElse(null);
        assertNotNull(expected);
    }

    @Test
    public void testCreateInfoMissingAPI() throws Exception {
        assertEquals(0, urlRepository.count());
        testUtils.addUrl("", API_ROOT_ADDRESS).andExpect(status().isUnprocessableEntity());
        assertEquals(0, urlRepository.count());
    }

    @Test
    public void testCreateTwiceAPI() throws Exception {
        assertEquals(0, urlRepository.count());
        testUtils.addDefaultUrl(API_ROOT_ADDRESS).andExpect(status().isCreated());
        testUtils.addDefaultUrl(API_ROOT_ADDRESS).andExpect(status().isCreated());
        assertEquals(1, urlRepository.count());
    }

    @Test
    public void testGetLongUrlAPI() throws Exception {
        testUtils.addDefaultUrl(API_ROOT_ADDRESS).andExpect(status().isCreated());
        final var request = get(DEFAULT_API_URL_SHORT);
        testUtils.perform(request).andExpect(status().isFound());
    }

    @Test
    public void testGetLongUrlNotExistedAPI() throws Exception {
        final var request = get(DEFAULT_API_URL_SHORT);
        testUtils.perform(request).andExpect(status().isNotFound());
    }


    @Test
    public void testDeleteAPI() throws Exception {
        testUtils.addDefaultUrl(API_ROOT_ADDRESS);
        assertEquals(1, urlRepository.count());
        final var request = delete(DEFAULT_API_URL_SHORT);
        testUtils.perform(request).andExpect((status().isOk()));
        assertEquals(0, urlRepository.count());
    }

    @Test
    public void testDeleteNotExistedUrlAPI() throws Exception {
        final var request = delete(DEFAULT_API_URL_SHORT);
        testUtils.perform(request).andExpect((status().isNotFound()));
    }
}
