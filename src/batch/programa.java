package batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Random;


public class programa {

	public static void main(String[] args) throws Exception {
		try {
			Connection conn = conexao.abrir();
	        PreparedStatement comando_truncate = conn.prepareStatement("TRUNCATE tb_customer_account");
	        comando_truncate.executeUpdate();
	        comando_truncate.close();
	        
	        for(int i = 0;i<3000;i++) {
	        	//Gerar numero aleatorio para o CPF.
	        	Random numeroAleatorio = new Random();
	        	int n1 = numeroAleatorio.nextInt(10);
	            int n2 = numeroAleatorio.nextInt(10);
	            int n3 = numeroAleatorio.nextInt(10);
	            int n4 = numeroAleatorio.nextInt(10);
	            int n5 = numeroAleatorio.nextInt(10);
	            int n6 = numeroAleatorio.nextInt(10);
	            int n7 = numeroAleatorio.nextInt(10);
	            int n8 = numeroAleatorio.nextInt(10);
	            int n9 = numeroAleatorio.nextInt(10);
	            int n10 = numeroAleatorio.nextInt(10); 
	            int n11 = numeroAleatorio.nextInt(10);
	            String cpf = n1+""+n2+""+n3+"."+n4+""+n5+""+n6+"."+n7+""+n8+""+n9+"-"+n10+""+n11;
	        	//FIM
	        	
	        	//gerando o valor aleatorio de cada cliente
	        	 Random r = new Random();
	        	 double a = r.nextDouble() * 1850;
	        	 //FIM
	        	 
	        	 //Nome do cliente baseado no numero dele no for
	        	 String cliente = "Cliente "+i;
	        	 //FIM
	        	 
	        	 //INSERT MYSQL
	        	 String sql = "INSERT INTO tb_customer_account(cpf_cnpj, nm_customer, vl_total) VALUES(?,?,?)";
	        	 PreparedStatement stmt = conn.prepareStatement(sql);
	                stmt.setString(1, cpf);  
	                stmt.setString(2, cliente);  
	                stmt.setDouble(3, a);
	                stmt.execute();  
	                stmt.close();
	                
	        }
	        
	        
	        PreparedStatement query = conn.prepareStatement("select * from tb_customer_account where vl_total > 560 and id_customer BETWEEN 1500 and 2700 order by vl_total desc");
            ResultSet resultado = query.executeQuery();
            double valortotal = 0;
            int linhas = 0;
            
	        while(resultado.next()) {
	        	linhas++;
	        	valortotal += resultado.getDouble("vl_total");
	        	DecimalFormat dff = new DecimalFormat("0.##");
		        String saidaa = dff.format(resultado.getDouble("vl_total"));
	        	System.out.println("Cliente: " + resultado.getString("nm_customer") + " CPF: " + resultado.getString("cpf_cnpj") + " Valor: " + saidaa);
	        }
	        
	        DecimalFormat df = new DecimalFormat("0.##");
	        String saida = df.format(valortotal/linhas);
	        System.out.print("Média final: " + saida);
	        
	        
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		

	}

}
