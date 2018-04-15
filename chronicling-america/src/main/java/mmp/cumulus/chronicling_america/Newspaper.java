package mmp.cumulus.chronicling_america;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Year;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Newspaper {
    
    String name;
    String publisher;
    Year start_year;
    Year end_year;
    List<IssueHeader> issues;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Year getStart_year() {
        return start_year;
    }

    public void setStart_year(Year start_year) {
        this.start_year = start_year;
    }

    public Year getEnd_year() {
        return end_year;
    }

    public void setEnd_year(Year end_year) {
        this.end_year = end_year;
    }

    public List<IssueHeader> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueHeader> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "Newspaper{" + "name=" + name + ", publisher=" + publisher + ", start_year=" + start_year + ", end_year=" + end_year + ", issues=" + issues + '}';
    }
    
}
