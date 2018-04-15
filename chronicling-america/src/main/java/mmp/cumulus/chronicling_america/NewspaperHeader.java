package mmp.cumulus.chronicling_america;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewspaperHeader implements Header<Newspaper> {
    
    String url;
    String title;
    String state;

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Header{" + "url=" + url + ", title=" + title + ", state=" + state + '}';
    }
    
}
