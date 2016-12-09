package br.com.coleta_pesquisa.dao;

import br.com.coleta_pesquisa.entity.Pesquisa;
import litebase.LitebaseConnection;

public class PesquisaDao  implements InterfaceDao{
	
	private LitebaseConnection con  = LitebaseConnection.getInstance("CONN");
	
	public PesquisaDao() {
		
		if(!con.exists("t_voto")){
			con.execute("create table t_voto ("
					+ "id int primary key not null, "
					+ "nome char(100) not null, "
					+ "idade int not null,  "
					+ "sexo char(10) not null, "
					+ "voto char(3) not null)");
		}
				
	}
	
private int ultimoId (){
		
		int ultimoId = 1;
		
		litebase.PreparedStatement lastId = con.prepareStatement("select max(id) as idMax from t_voto");
		litebase.ResultSet registros =  lastId.executeQuery();
		registros.beforeFirst();
		
		while (registros.next()) {
			ultimoId = registros.getInt("idMax") + 1 ;
			
		}
		
		registros.close();
		return ultimoId;
	}

	
	public void inserir(Pesquisa obj) {
		litebase.PreparedStatement psInserir = con.prepareStatement("insert into t_voto values (?, ?, ?, ?, ?)");
		psInserir.clearParameters();
		//Chama o método que pega o último id sendo incrementado
		psInserir.setInt(0, ultimoId());
		psInserir.setString(1, obj.getNome());
		psInserir.setInt(2, obj.getIdade());
		psInserir.setString(3, obj.getSexo());
		psInserir.setString(4, obj.getVoto());
		
		psInserir.executeUpdate();
				
	}
	
	

	
	public void atualizar(Pesquisa obj) {
//		litebase.PreparedStatement psUpdate = con.prepareStatement("update t_voto  set nome = ?, "
//				+ "set idade =  ?, "
//				+ "set sexo = ?, "
//				+ "set voto = ? "
//				+ "where id = ?");
//		psUpdate.setString(1, obj.getNome());
//		psUpdate.setInt(2, obj.getIdade());
//		psUpdate.setString(3, obj.getSexo());
//		psUpdate.setString(4, obj.getVoto());
//		psUpdate.setInt(5, obj.getId());
//		
//		psUpdate.executeUpdate();
		
		litebase.PreparedStatement psUpdate = con.prepareStatement("update t_voto  set nome = ?, idade = ?, sexo =? where id = ?");
		psUpdate.setString(0,  obj.getNome());
		psUpdate.setInt(1, obj.getIdade());
		psUpdate.setString(2,  obj.getSexo());
		psUpdate.setInt(3, obj.getId());
		
//		psUpdate.setString(3, obj.getSexo());
//		psUpdate.setString(4, obj.getVoto());
//		psUpdate.setInt(5, obj.getId());
		
		psUpdate.executeUpdate();
		
	}

	
	public void deletar(Pesquisa obj) {
		litebase.PreparedStatement psDelete = con.prepareStatement("delete t_voto  where id = ?");
		psDelete.setInt(0, obj.getId());
		psDelete.executeUpdate();
	
		
	}

	public String[][] pesquisar() {
		litebase.PreparedStatement psFindAll = con.prepareStatement("select * from t_voto");
		litebase.ResultSet allRecords = psFindAll.executeQuery();
		String[][] todos = null;
		if(allRecords.first() == true){
			//allRecords.first();
			 todos = allRecords.getStrings();
			allRecords.close();
		}				
		return todos;
	}
	
	
//	public static void main(String [] args){
//		new PesquisaDao();
//		Pesquisa p = new Pesquisa();
//		p.setId(3);
//		p.setNome("Danúbia");
//		p.setIdade(20);
//		p.setSexo("F");
//		p.setVoto("SIM");
//		new PesquisaDao().atualizar(p);
//		
//				
////		p.setId(7);
////		new PesquisaDao().deletar(p);
//		
//		String [][] registros =  new PesquisaDao().pesquisar();
//		
//		if(registros != null){
//				for (int i = 0; i < registros.length; i++) {
//						for (int j = 0; j < registros.length; j++) {
//							System.out.println(registros[i][j]);
//						}
//				}
//				
//				System.out.println(new PesquisaDao().ultimoId());
//		}else{
//			    System.out.println("Não tem registros");
//		}
		
		
		
		
//	}

				
	
		

}
