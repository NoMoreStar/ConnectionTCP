
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class ServerConnessioneTCP extends Thread{
    public static final String Purple = "\u001B[35m";
    public static final String Black = "\u001B[30m";
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
        System.out.println(Purple +"In attesa di connessioni!"+Black);
    }
    public void Connetti() throws IOException{
        connection = sSocket.accept();
        System.out.println(Purple +"Connessione stabilita!"+Black);
        System.out.println(Purple +"Socket server: " + connection.getLocalSocketAddress());
        System.out.println(Purple +"Socket client: " + connection.getRemoteSocketAddress());
    }
    public void Comunica() throws IOException{
        in = new DataInputStream(connection.getInputStream());
        System.out.println(Purple +"Il client ha detto: "+ in.readUTF() + Black);
        out = new DataOutputStream(connection.getOutputStream());
        out.writeUTF(app);
        out.flush();
    }
    public void Chiusura(){
        try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println(Purple +"Errore nella chiusura della connessione!");
            }
            System.out.println(Purple +"Connessione chiusa!"+Black);
    }
    @Override
        public void run(){
        try {
            ServerConnessioneTCP server = new ServerConnessioneTCP();
            server.Accendi();
            server.Connetti();
            server.Comunica();
            server.Chiusura();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnessioneTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}
