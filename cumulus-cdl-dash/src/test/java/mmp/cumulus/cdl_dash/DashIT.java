package mmp.cumulus.cdl_dash;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.Exceptions;

@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = "spring.main.web-application-type=reactive",
        classes = DashIT.Config.class)
public class DashIT {

    @EnableAutoConfiguration
    static class Config {
    }

    private static final Dash API = new Dash();

    @Test
    public void testIndex() {
        assertNotNull(API.index()
                .blockOptional()
                .orElseThrow(Exceptions::failWithCancel)
                .getLinks().getDatasetsLink().getHref());
    }
    
    @Test
    public void testDatasets() {
        System.out.println(API.datasets().take(20).blockLast());
    }

}
