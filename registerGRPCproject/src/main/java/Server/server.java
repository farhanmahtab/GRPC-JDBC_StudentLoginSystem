package Server;

import Service.registration;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class server {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new registration())
                .build();
        server.start();
        System.out.println("Server started at " + server.getPort());
        server.awaitTermination(60, TimeUnit.SECONDS);
    }
}
