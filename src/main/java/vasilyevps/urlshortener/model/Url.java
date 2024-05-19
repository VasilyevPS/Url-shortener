package vasilyevps.urlshortener.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;
import static jakarta.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "urls", indexes = @Index(columnList = "urlKey", name = "urlKeyIndex"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(unique = true)
    private String urlKey;

    @Column(unique = true)
    private String shortUrl;

    @Column(unique = true)
    @NotBlank
    private String longUrl;

    @CreationTimestamp
    @Temporal(TIMESTAMP)
    private Date createdAt;

    @Column(columnDefinition = "int default 0")
    private int visitsCount;
}
