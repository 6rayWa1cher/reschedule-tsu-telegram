package space.delusive.tversu.component;

import com.sun.net.httpserver.HttpServer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.extern.log4j.Log4j2;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

@Log4j2
public class MetricsExposer {
    private final int port;
    private final PrometheusMeterRegistry meterRegistry;

    public MetricsExposer(int port, PrometheusMeterRegistry meterRegistry) {
        this.port = port;
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/prometheus", httpExchange -> {
                String response = meterRegistry.scrape();
                httpExchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            });

            new Thread(server::start).start();
            log.info("Metrics enabled on port {}", port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
