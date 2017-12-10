package dopenews.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

@Entity
public class Picture extends AbstractPersistable<Long> {
    private Article article;
    private String name;
    private String mediaType;
    private Long contentLength;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotEmpty
    @Size(max = 20000000) // 5 MB
    private byte[] data;

    public Article getArticle() {
        return article;
    }

    public String getName() {
        return name;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public byte[] getData() {
        return data;
    }

    @Transactional
    public void setArticle(Article article) {
        this.article = article;
    }

    @Transactional
    public void setName(String name) {
        this.name = name;
    }

    @Transactional
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Transactional
    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    @Transactional
    public void setData(byte[] data) {
        this.data = data;
    }
}