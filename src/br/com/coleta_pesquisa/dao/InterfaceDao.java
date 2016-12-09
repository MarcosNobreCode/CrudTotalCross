package br.com.coleta_pesquisa.dao;

import br.com.coleta_pesquisa.entity.Pesquisa;

/*@author Marcos Nobre - 07/12/2016
 * @Description Interface da camanda de persist�ncia
 * Define os m�todos a serem implementados pelas classes de DAO.
 */

//Obs.: Poderia ter trabalhado com a Class Object usando Gen�ric mas fiz assim por se tratar de um �nico
//objeto em quest�o.
public interface InterfaceDao {
		void inserir(Pesquisa obj);
		void atualizar(Pesquisa obj);
		void deletar(Pesquisa obj);
		String [][] pesquisar ();	

}
