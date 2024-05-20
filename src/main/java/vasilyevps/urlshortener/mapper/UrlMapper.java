package vasilyevps.urlshortener.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import vasilyevps.urlshortener.dto.UrlCreateDto;
import vasilyevps.urlshortener.dto.UrlDto;
import vasilyevps.urlshortener.model.Url;
import vasilyevps.urlshortener.utils.UrlUtils;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UrlMapper {

    @Autowired
    protected UrlUtils urlUtils;

    public Url create(UrlCreateDto createDto) {
        Url url = new Url();
        String longUrl = urlUtils.normalizeUrl(createDto.getUrl());
        String urlKey = urlUtils.generateUrlKey(longUrl);
        String shortUrl = urlUtils.createShortUrl(urlKey);
        url.setLongUrl(longUrl);
        url.setUrlKey(urlKey);
        url.setShortUrl(shortUrl);
        return url;
    }

    @Mapping(target = "key", source = "urlKey")
    public abstract UrlDto map(Url url);

}
