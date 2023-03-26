package tournoi.GomokolServer;

import java.io.IOException;

import tournoi.GomokolServer.Server.Server;

public class Main {

	private Server server;
	
	public Main() throws IOException {
		this.server  = new Server();
		server.open();
		
	}
	
	public static void main(String[] args) {
		try {
			new Main();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
