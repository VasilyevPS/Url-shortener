package vasilyevps.urlshortener.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vasilyevps.urlshortener.dto.UrlCreateDto;
import vasilyevps.urlshortener.dto.UrlDto;
import vasilyevps.urlshortener.model.Url;
import vasilyevps.urlshortener.repository.UrlRepository;
import vasilyevps.urlshortener.utils.UrlUtils;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlUtils urlUtils;

    public UrlDto create(UrlCreateDto urlCreateDto) {
        Url url = toEntity(urlCreateDto);
        Url existedUrl = urlRepository.findByUrlKey(url.getUrlKey()).orElse(null);
        if (existedUrl != null) {
            return toDto(existedUrl);
        }
        urlRepository.save(url);
        return toDto(url);
    }

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    public String getUrlByUrkKey(String urlKey) {
        Url url = urlRepository.findByUrlKey(urlKey).orElseThrow();
        url.setVisitsCount(url.getVisitsCount() + 1);
        return url.getLongUrl();
    }

    public void deleteUrl(String urlKey) {
        Url url = urlRepository.findByUrlKey(urlKey).orElseThrow();
        urlRepository.deleteById(url.getId());
    }

    private Url toEntity(UrlCreateDto urlCreateDto) {
        Url url = new Url();
        String longUrl = urlUtils.normalizeUrl(urlCreateDto.getUrl());
        url.setLongUrl(longUrl);
        String urlKey = urlUtils.generateUrlKey(longUrl);
        url.setUrlKey(urlKey);
        String shortUrl = urlUtils.createShortUrl(urlKey);
        url.setShortUrl(shortUrl);
        return url;
    }

    private UrlDto toDto(Url url) {
        UrlDto dto = new UrlDto();
        dto.setKey(url.getUrlKey());
        dto.setLongUrl(url.getLongUrl());
        dto.setShortUrl(url.getShortUrl());
        return dto;
    }
}
