package com.epam.yarn.web;

import com.epam.yarn.appmaster.Appmaster;
import com.epam.yarn.models.AvailableResources;
import com.epam.yarn.models.RequestedResources;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.grizzly.http.server.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author Anton_Solovev
 * @since 10/6/16.
 */
@Component
@EnableAutoConfiguration
public class EmbeddedServer {

    private static final Log log = LogFactory.getLog(EmbeddedServer.class);

    @Autowired
    @Value("${app.urlResources}")
    private String urlResources;
    @Autowired
    @Value("${app.server.port}")
    private String port;
    @Autowired
    @Value("${app.indexPath}")
    private String indexPath;
    @Autowired
    private Appmaster appmaster;
    private HttpServer server;

    public EmbeddedServer() {
    }

    public void start() {
        log.debug("port: " + port);
        server = HttpServer.createSimpleServer("/", Integer.parseInt(port));
        final ServerConfiguration serverConfiguration = server.getServerConfiguration();
        serverConfiguration.addHttpHandler(handleHomePage(), "/home");
        serverConfiguration.addHttpHandler(handleSubmit(), "/submit");
        serverConfiguration.addHttpHandler(handleShutdown(), "/shutdown");
        try {
            server.start();
        } catch (final Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private HttpHandler handleShutdown() {
        return new HttpHandler() {
            @Override
            public void service(final Request request, final Response response) throws Exception {
                response.setContentType("text/html");
                appmaster.stop();
                response.getWriter().write("shutted down");
                stop();
            }
        };
    }

    private HttpHandler handleSubmit() {
        return new HttpHandler() {
            @Override
            public void service(final Request req, final Response resp) throws Exception {
                resp.setContentType("text/html");
                final RequestedResources neededResources = new RequestedResources(
                        Integer.parseInt(req.getParameter("memory")),
                        Integer.parseInt(req.getParameter("cores")),
                        Integer.parseInt(req.getParameter("priority")),
                        Integer.parseInt(req.getParameter("containers"))
                );
                appmaster.submitApplication(neededResources);
                resp.getWriter().write("submitted");
            }
        };
    }

    private HttpHandler handleHomePage() {
        return new HttpHandler() {
            @Override
            public void service(final Request request, final Response response) throws Exception {
                response.setContentType("text/html");
                String content = getContentWithAvailableResources();
                response.setContentLength(content.length());
                response.getWriter().write(content);
            }
        };
    }

    private String getContentWithAvailableResources() throws IOException {
        AvailableResources availableRes = new RestTemplate().getForObject(urlResources, AvailableResources.class);
        Path path = Paths.get(indexPath);
        String indexFile = Files.lines(path).collect(Collectors.joining("\n"));
        return String.format(indexFile, availableRes.getBeans()[0].getAvailableMB(), availableRes.getBeans()[0].getAvailableVCores());
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
}
