package mmp.cumulus.chronicling_america;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

    LocalDate date_issued;
    List<PageHeader> pages;

    public LocalDate getDate_issued() {
        return date_issued;
    }

    public void setDate_issued(LocalDate date_issued) {
        this.date_issued = date_issued;
    }

    public List<PageHeader> getPages() {
        return pages;
    }

    public void setPages(List<PageHeader> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Issue{" + "date_issued=" + date_issued + ", pages=" + pages + '}';
    }

}
