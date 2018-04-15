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
    public void testGetNewspaper() {
        NewspaperHeader header = new NewspaperHeader();
        header.setUrl("http://chroniclingamerica.loc.gov/lccn/sn85025905.json");
        assertNotNull(API.getNewspaper(header).block());
        header.setUrl("https://chroniclingamerica.loc.gov/lccn/sn85025905.json");
        assertNotNull(API.getNewspaper(header).block());
    }

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

}
