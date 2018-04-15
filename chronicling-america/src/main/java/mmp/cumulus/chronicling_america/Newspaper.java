package mmp.cumulus.chronicling_america;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Newspaper {
    
    String name;
    String publisher;
    String start_year;
    String end_year;
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

    public String getStart_year() {
        return start_year;
    }

    public void setStart_year(String start_year) {
        this.start_year = start_year;
    }

    public String getEnd_year() {
        return end_year;
    }

    public void setEnd_year(String end_year) {
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
