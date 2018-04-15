package mmp.cumulus.chronicling_america;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Index {
    
    List<NewspaperHeader> newspapers;

    public List<NewspaperHeader> getNewspapers() {
        return newspapers;
    }

    public void setNewspapers(List<NewspaperHeader> newspapers) {
        this.newspapers = newspapers;
    }
    
}
