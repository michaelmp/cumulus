package mmp.cumulus.chronicling_america;

import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = "spring.main.web-application-type=reactive",
        classes = NewspaperAPIIntegrationTest.Config.class)
public class NewspaperAPIIntegrationTest {

    @EnableAutoConfiguration
    static class Config {
    }

    private static final NewspaperAPI API = new NewspaperAPI();

    @Test
    public void testIndex() {
        final int N_PAPERS = 10;
        List<NewspaperHeader> newspapers = API.index()
                .take(N_PAPERS)
                .collectList()
                .blockOptional()
                .orElse(Collections.emptyList());
        assertEquals(N_PAPERS, newspapers.size());
    }

    @Test
    public void testGetNewspaper() {
        NewspaperHeader header = new NewspaperHeader();
        header.setUrl("http://chroniclingamerica.loc.gov/lccn/sn85025905.json");
        assertNotNull(API.getNewspaper(header).block());
        header.setUrl("https://chroniclingamerica.loc.gov/lccn/sn85025905.json");
        assertNotNull(API.getNewspaper(header).block());
    }

    @Test
    public void testGetIssue() {
        IssueHeader header = new IssueHeader();
        header.setUrl("http://chroniclingamerica.loc.gov/lccn/sn85025905/1865-04-19/ed-1.json");
        assertNotNull(API.getIssue(header).block());
        header.setUrl("http://chroniclingamerica.loc.gov/lccn/sn85025905/1865-04-19/ed-1.json");
        assertNotNull(API.getIssue(header).block());
    }

    @Test
    public void testGetPage() {
        PageHeader header = new PageHeader();
        header.setUrl("http://chroniclingamerica.loc.gov/lccn/sn85025905/1865-04-19/ed-1/seq-1.json");
        assertNotNull(API.getPage(header).block());
        header.setUrl("http://chroniclingamerica.loc.gov/lccn/sn85025905/1865-04-19/ed-1/seq-1.json");
        assertNotNull(API.getPage(header).block());
    }

    @Test
    public void test_first_page_of_first_issue_of_first_10_newspapers_in_oregon_that_have_pdf_and_ocr() {
        List<Page> pages = API.index()
                .filter((header) -> {
                    return "Oregon".equals(header.getState());
                })
                .flatMap(API::getNewspaper)
                .sort((n1, n2) -> {
                    return n1.getStart_year().compareTo(n2.getStart_year());
                })
                .take(10)
                .concatMap((newspaper) -> {
                    return Flux.fromIterable(newspaper.getIssues()).take(1);
                })
                .flatMap(API::getIssue)
                .concatMap((issue) -> {
                    return Flux.fromIterable(issue.getPages()).take(1);
                })
                .flatMap(API::getPage)
                .filter((page) -> {
                    return page.getPdf() != null && page.getOcr() != null;
                })
                .collectList()
                .blockOptional()
                .orElse(Collections.emptyList());

        for (Page page : pages) {
            System.out.println(page.getPdf() + " : " + page.getOcr());
        }
    }

}
