package vasilyevps.urlshortener.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public static final String ROOT_ADDRESS = "";
    public static final String API_ROOT_ADDRESS = ROOT_ADDRESS + "/api";
    public static final String STATS_ADDRESS = ROOT_ADDRESS + "/stats";
    public static final String DEFAULT_URL = "https://www.google.ru/";
    public static final String DEFAULT_URL_KEY = "3853400aca";
    public static final String DEFAULT_URL_SHORT = "http://localhost:8080/3853400aca";
    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    private final UrlCreateDto urlCreateDto = new UrlCreateDto(DEFAULT_URL);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlRepository urlRepository;

    public void clearDB() {
        urlRepository.deleteAll();
    }

    public ResultActions addDefaultUrl(String address) throws Exception {
        return addUrl(DEFAULT_URL, address);
    }

    public ResultActions addUrl(String url, String address) throws Exception {
        final var request = post(address);
        if (address.equals(API_ROOT_ADDRESS)) {
            request.content(asJson(url))
                    .contentType(APPLICATION_JSON);
        } else {
            request.param("url", url)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED);
        }

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
