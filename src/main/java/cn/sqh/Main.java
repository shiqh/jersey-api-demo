package cn.sqh;

import cn.sqh.application.DemoApiApplication;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;

/**
 * 项目启动入口
 * Created by Administrator on 2017/2/17.
 */
public class Main {
    public static final String BASE_URI = "http://localhost:8080/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        DemoApiApplication demoApiApplication = new DemoApiApplication();
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), demoApiApplication);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("SERVICE STARTING... \n"
                + "view WADL %sapplication.wadl \n"
                + "Hit enter to stop it...", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }

}
