package vasilyevps.urlshortener.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vasilyevps.urlshortener.dto.UrlCreateDto;
import vasilyevps.urlshortener.dto.UrlDto;
import vasilyevps.urlshortener.mapper.UrlMapper;
import vasilyevps.urlshortener.model.Url;
import vasilyevps.urlshortener.repository.UrlRepository;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;

    public UrlDto create(UrlCreateDto urlCreateDto) {
        Url url = urlMapper.create(urlCreateDto);
        Url existedUrl = urlRepository.findByUrlKey(url.getUrlKey()).orElse(null);
        if (existedUrl != null) {
            return urlMapper.map(existedUrl);
        }
        urlRepository.save(url);
        return urlMapper.map(url);
    }

    public List<UrlDto> getAllUrls() {
        List<Url> urls = urlRepository.findAll();
        return urls.stream().map(urlMapper::map).toList();
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

}
