package mmp.cumulus.cdl_dash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatasetPage {

    @JsonProperty("_embedded")
    private Embedded embedded;

    @JsonProperty("_links")
    private Links links;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "DatasetPage{" + "embedded=" + embedded + '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Embedded {

        @JsonProperty("stash:datasets")
        private List<Dataset> datasets;

        public List<Dataset> getDatasets() {
            return datasets;
        }

        public void setDatasets(List<Dataset> datasets) {
            this.datasets = datasets;
        }

        @Override
        public String toString() {
            return "Embedded{" + "datasets=" + datasets + '}';
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Links {

        private Link next;

        public Link getNext() {
            return next;
        }

        public void setNext(Link next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Links{" + "next=" + next + '}';
        }

    }
}
