
package connectiontcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class ClientConnessioneTCP extends Thread{
    Socket connection;
    String serverAddress;
    int port;
    DataOutputStream out;
    DataInputStream in;
    BufferedReader tastiera;
    public ClientConnessioneTCP(){
        serverAddress = "localhost";
        port = 2000;
        out = null;
        in = null;
        tastiera = new BufferedReader(new InputStreamReader(System.in));
    }
    public void Connetti() throws IOException{
        connection = new Socket(serverAddress, port);
        System.out.println("Connessione aperta"); 
    }
    public void Invio() throws IOException{
        String chiedi = tastiera.readLine();
        out = new DataOutputStream(connection.getOutputStream());
        out.writeUTF(chiedi);
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
    @Override
        public void run(){
        try {
            ClientConnessioneTCP client = new ClientConnessioneTCP();
            client.Connetti();
            client.Invio();
            client.Ricevi();
            client.Chiusura();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnessioneTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
}

}
