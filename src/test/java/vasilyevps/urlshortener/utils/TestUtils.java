package vasilyevps.urlshortener.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import vasilyevps.urlshortener.dto.UrlCreateDto;
import vasilyevps.urlshortener.repository.UrlRepository;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class TestUtils {
    public static final String ROOT_URL = "";
    public static final String DEFAULT_URL = "https://www.google.ru";
    public static final String DEFAULT_URL_KEY = "3853400aca";
    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    private final UrlCreateDto urlCreateDto = new UrlCreateDto(DEFAULT_URL);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlRepository urlRepository;

    public void clearDB() {
        urlRepository.deleteAll();
    }

    public ResultActions addDefaultUrl() throws Exception {
        return addUrl(DEFAULT_URL);
    }

    public ResultActions addUrl(String url) throws Exception {
        final var request = post(ROOT_URL)
                .content(asJson(url))
                .contentType(APPLICATION_JSON);

        return perform(request);
    }

    public ResultActions perform(final MockHttpServletRequestBuilder request) throws Exception {
        return mockMvc.perform(request);
    }

    public static String asJson(final Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }

    public static <T> T fromJson(final String json, final TypeReference<T> to) throws JsonProcessingException {
        return MAPPER.readValue(json, to);
    }
}
