
package connectiontcp;

public class ThreadTCP {

    public static void main(String[] args) {
        ServerConnessioneTCP server = new ServerConnessioneTCP();
        server.start();
        ClientConnessioneTCP client = new ClientConnessioneTCP();
        client.start();
    }
    
}
