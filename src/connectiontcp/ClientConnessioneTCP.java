
package connectiontcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 *
 * @author Manuel
 */
public class ClientConnessioneTCP {
    Socket connection;
    String serverAddress;
    int port;
    DataOutputStream out;
    DataInputStream in;
    public ClientConnessioneTCP(){
        serverAddress = "localhost";
        port = 2000;
        out = null;
        in = null;
    }
    public void Connetti() throws IOException{
        connection = new Socket(serverAddress, port);
        System.out.println("Connessione aperta"); 
    }
    public void Invio() throws IOException{
        out = new DataOutputStream(connection.getOutputStream());
        out.writeUTF("Dammi la data...");
        out.flush();  
    }  
    public void Ricevi() throws IOException{
        in = new DataInputStream(connection.getInputStream());
        System.out.println("Data: "+ in.readUTF());
    }
    public void Chiusura(){
        try {
            if (connection!=null)
                {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
    }
    public static void main(String[] args) throws IOException {
        ClientConnessioneTCP client = new ClientConnessioneTCP();
        client.Connetti();
        client.Invio();
        client.Ricevi();
        client.Chiusura();
    }
}
