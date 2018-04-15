package mmp.cumulus.chronicling_america;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NewspaperAPI {

    private final WebClient client = WebClient.builder()
            .baseUrl("https://chroniclingamerica.loc.gov").build();

    public Flux<NewspaperHeader> index() {
        return client.get()
                .uri("/newspapers.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Index.class)
                .flatMapIterable((result) -> {
                    return result.getNewspapers();
                })
                .doOnError((t) -> {
                    System.err.println(t.getMessage());
                });
    }

    private <T> Mono<T> getGeneric(Class<T> klass, Header<T> header) {
        String url = header.getUrl().replaceFirst("http://", "https://");
        return WebClient.create(url)
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(klass)
                .doOnError((t) -> {
                    System.err.println(t.getMessage());
                });
    }

    public Mono<Newspaper> getNewspaper(NewspaperHeader header) {
        return getGeneric(Newspaper.class, header);
    }

    public Mono<Issue> getIssue(IssueHeader header) {
        return getGeneric(Issue.class, header);
    }
    
    public Mono<Page> getPage(PageHeader header) {
        return getGeneric(Page.class, header);
    }

}
