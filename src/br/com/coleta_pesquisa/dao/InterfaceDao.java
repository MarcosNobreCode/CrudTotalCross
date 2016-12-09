package br.com.coleta_pesquisa.dao;

import br.com.coleta_pesquisa.entity.Pesquisa;

/*@author Marcos Nobre - 07/12/2016
 * @Description Interface da camanda de persistência
 * Define os métodos a serem implementados pelas classes de DAO.
 */

//Obs.: Poderia ter trabalhado com a Class Object usando Genéric mas fiz assim por se tratar de um único
//objeto em questão.
public interface InterfaceDao {
		void inserir(Pesquisa obj);
		void atualizar(Pesquisa obj);
		void deletar(Pesquisa obj);
		String [][] pesquisar ();	

}
