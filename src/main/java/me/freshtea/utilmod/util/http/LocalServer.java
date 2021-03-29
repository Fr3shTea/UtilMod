package me.freshtea.utilmod.util.http;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class LocalServer {

    private Server server;

    private ContextHandlerCollection handlerCollection = new ContextHandlerCollection();

    public LocalServer(int port) {
        server = new Server(new InetSocketAddress(InetAddress.getLoopbackAddress(), port));

        server.setHandler(handlerCollection);
    }

    public void registerHandler(String path, Handler handler) {
        ContextHandler contextHandler = new ContextHandler(path);

        contextHandler.setHandler(handler);

        handlerCollection.addHandler(contextHandler);
    }

    public void startServer() throws Exception {
        server.start();
    }

    public void stopServer() throws Exception {
        server.stop();
    }

}
