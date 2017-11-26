package dopenews.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Picture extends AbstractPersistable<Long> {
    private Article article;
    private String name;
    private String mediaType;
    private Long contentLength;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
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


    public void setArticle(Article article) {
        this.article = article;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}