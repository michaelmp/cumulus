package mmp.cumulus.chronicling_america;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IssueHeader implements Header<Issue> {

    String url;
    LocalDate date_issued;

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getDate_issued() {
        return date_issued;
    }

    public void setDate_issued(LocalDate date_issued) {
        this.date_issued = date_issued;
    }

    @Override
    public String toString() {
        return "IssueHeader{" + "url=" + url + ", date_issued=" + date_issued + '}';
    }

}
