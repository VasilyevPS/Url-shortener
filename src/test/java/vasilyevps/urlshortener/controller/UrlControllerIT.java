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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vasilyevps.urlshortener.utils.TestUtils.API_ROOT_ADDRESS;
import static vasilyevps.urlshortener.utils.TestUtils.DEFAULT_URL_KEY;
import static vasilyevps.urlshortener.utils.TestUtils.ROOT_ADDRESS;

@AutoConfigureMockMvc
@SpringBootTest
public class UrlControllerIT {

    @Autowired
    TestUtils testUtils;

    @Autowired
    private UrlRepository urlRepository;

    @AfterEach
    public void clear() {
        testUtils.clearDB();
    }

    @Test
    public void testWelcome() throws Exception {
        testUtils.perform(get(ROOT_ADDRESS))
                .andExpect(status().isOk());
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


}
