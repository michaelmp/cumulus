package mmp.cumulus.cdl_dash;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class Dash {

    private final WebClient client = WebClient.builder()
            .baseUrl("https://dash.ucop.edu").build();

    public Mono<Index> index() {
        return client.get()
                .uri("/api")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Index.class);
    }

    public Flux<Dataset> datasets() {
        return index()
                .map(Index::getLinks)
                .map(Index.Links::getDatasetsLink)
                .map(Link::getHref)
                .flatMap((href) -> {
                    return client.get()
                            .uri(href)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(DatasetPage.class);
                })
                .flux()
                .flatMap(this::recurseYieldPage)
                .map(DatasetPage::getEmbedded)
                .flatMapIterable(DatasetPage.Embedded::getDatasets);
    }

    private Flux<DatasetPage> recurseYieldPage(DatasetPage page) {
        if (page.getLinks() == null || page.getLinks().getNext() == null) {
            return Flux.just(page);
        } else {
            return Flux.concat(Flux.just(page), recurseYieldPage(getNextPage(page)));
        }
    }

    private Flux<DatasetPage> recurseYieldPage(Flux<DatasetPage> page) {
        return page.flatMap(this::recurseYieldPage);
    }

    private Flux<DatasetPage> getNextPage(DatasetPage page) {
        return client.get()
                .uri(page.getLinks().getNext().getHref())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(DatasetPage.class)
                .doOnEach((p) -> {
                    System.err.println("Loaded additional page: " + p);
                });
    }

}
