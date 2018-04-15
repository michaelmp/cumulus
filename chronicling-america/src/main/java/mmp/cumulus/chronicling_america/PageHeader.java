package mmp.cumulus.chronicling_america;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageHeader implements Header<Page> {

    Long sequence;
    String url;

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PageHeader{" + "sequence=" + sequence + ", url=" + url + '}';
    }

}
