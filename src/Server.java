import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
    public void StartServer(){
        try{
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept(); // this is a blocking method
                System.out.println("A new client has connected");
                ClientHandler clientHandler = new ClientHandler(socket); // this class will implment a runnable
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException ioException){
            closeServerSocket();
        }
    }

    public void closeServerSocket(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.StartServer();
    }
}
