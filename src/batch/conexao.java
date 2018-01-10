package batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;




public class conexao {

    
    // Conectar ao banco
    public static Connection abrir() {
    	try	{
			Connection c;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver JDBC encontrado!");
			
			String bdUrl = "jdbc:mysql://localhost:3306/batch";
			String bdUsuario = "root";
			String bdSenha = "";

			c = DriverManager.getConnection(bdUrl,bdUsuario,bdSenha); 
			
			System.out.println("Conexao realizada com sucesso! Nome da Conexao: " + c);
			return c;
		}	
		catch(ClassNotFoundException ex){
			JOptionPane.showMessageDialog(null,"Driver JDBC não encontrado!  "+ex,"Erro", JOptionPane.ERROR_MESSAGE);
			System.out.println("Driver JDBC não encontrado!  "+ex); System.exit(0); return null;
		}
    	catch(SQLException ex){
			JOptionPane.showMessageDialog(null,"Problemas na conexao com a fonte de dados!\n"+ex, "Erro", JOptionPane.ERROR_MESSAGE);			
			System.out.println("Problemas na conexao com a fonte de dados   ");	return null;
		}
    }

}