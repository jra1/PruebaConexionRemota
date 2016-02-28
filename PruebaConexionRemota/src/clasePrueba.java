import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import org.h2.tools.*;

public class clasePrueba {

	public static void main(String[] args) {
		System.setProperty("h2.bindAddress", "192.168.1.129");
		
		//arrancarServidor();
		introduceIP();
	}

	public static void introduceIP(){
		String ip = JOptionPane.showInputDialog("Introduce la IP a conectarse");
		compruebaConexion("sa",ip);
	}
	public static boolean compruebaConexion(String pUsu, String pIp){
		boolean esValida = false;
		boolean hayError = false;
		try {			
			//Con base de datos H2 (PRUEBA) 
			//Usuario: sa
			//Pass:
			Class.forName("org.h2.Driver");
			DriverManager.getConnection("jdbc:h2:tcp://"+pIp+"/C:/H2DB/BD_Ruara", pUsu, "");
			//DriverManager.getConnection("jdbc:h2:C:/H2DB/BD_Ruara", pUsu, String.valueOf(pPass));
			 
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hubo un error de conexión: "+ e.getMessage());
			//e.printStackTrace();
			hayError = true;
		}
		if(hayError == false){
			JOptionPane.showMessageDialog(null, "Conexión establecida con éxito");
			esValida = true;
		} else{
			esValida = false;
			//JOptionPane.showMessageDialog(null, "Se produjo un error de conexión.");
		}
		
		return esValida;
	}
	
	public static void arrancarServidor(String args){
		try 
		{ 
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			System.out.println(InetAddress.getLocalHost().getHostName());
			System.out.println(NetworkInterface.getNetworkInterfaces());
		   //directorio/ejecutable es el path del ejecutable y un nombre 
		   //Process p = Runtime.getRuntime().exec("C:/Program Files (x86)/H2/bin/h2.bat"); 
			//Runtime.getRuntime().exec("C:/Program Files (x86)/iTunes/iTunes.exe");
			new Console().runTool(args);
		} 
		catch (Exception e) 
		{ 
			JOptionPane.showMessageDialog(null, "Hubo un error al arrancar el servidor: "+ e.getMessage());
		}
	}
}
