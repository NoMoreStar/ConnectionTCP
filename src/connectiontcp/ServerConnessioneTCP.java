
package connectiontcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Manuel
 */
public class ServerConnessioneTCP {
    int port;
    ServerSocket sSocket;
    Socket connection;
    DataInputStream in;
    DataOutputStream out;
    GregorianCalendar calendario ;
    Date prova;
    DateFormat df;
    String app;
    public ServerConnessioneTCP(){
        port=2000;
        calendario = new GregorianCalendar();
        prova = new Date();
        calendario.setTime(prova);
        df = new SimpleDateFormat("dd/MM/yyyy");
        app = df.format(prova);
    }
    public void Accendi() throws IOException{
         sSocket = new ServerSocket(port);
        System.out.println("In attesa di connessioni!");
    }
    public void Connetti() throws IOException{
        connection = sSocket.accept();
        System.out.println("Connessione stabilita!");
        System.out.println("Socket server: " + connection.getLocalSocketAddress());
        System.out.println("Socket client: " + connection.getRemoteSocketAddress());
    }
    public void Comunica() throws IOException{
        in = new DataInputStream(connection.getInputStream());
        System.out.println("Il client ha detto: "+ in.readUTF());
        out = new DataOutputStream(connection.getOutputStream());
        out.writeUTF(app);
        out.flush();
    }
    public void Chiusura(){
        try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
    }
    public static void main(String[] args) throws IOException {
        ServerConnessioneTCP server = new ServerConnessioneTCP();
        server.Accendi();
        server.Connetti();
        server.Comunica();
        server.Chiusura();
    }
}
