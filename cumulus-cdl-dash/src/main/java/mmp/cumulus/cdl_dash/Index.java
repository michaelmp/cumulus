package mmp.cumulus.cdl_dash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Index {

    @JsonProperty("_links")
    private Index.Links links;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Index{" + "links=" + links + '}';
    }

    public static class Links {

        @JsonProperty("stash:datasets")
        private Link datasetsLink;

        public Link getDatasetsLink() {
            return datasetsLink;
        }

        public void setDatasetsLink(Link datasetsLink) {
            this.datasetsLink = datasetsLink;
        }

        @Override
        public String toString() {
            return "Links{" + "datasetsLink=" + datasetsLink + '}';
        }

    }
}
