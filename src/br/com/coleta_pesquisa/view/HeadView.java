package br.com.coleta_pesquisa.view;

import totalcross.ui.Container;
import totalcross.ui.Label;

/*@author Marcos Nobre - 06/12/2016
 * @Description Classe utilizada para compor o head
 * da aplicação
 */
public class HeadView extends Container {

	private Label tituloApp;
	//private Label subTituloApp;

	//Construtor
	public HeadView() {
		tituloApp = new Label("PESQUISA DE CAMPO");
		//subTituloApp = new Label("Informe seus dados");
	}
	
	// Calcula a altura preferencial do Container (Footer)
//		public int getPreferredHeight() {
//			
//			return Math.max(tituloApp.getPreferredHeight(), subTituloApp.getPreferredHeight()) + 3 ;
//		}
		

	 

	//Inicializa a interface de usuário com os elemento de cabeçalho em posicionamento relativo.
	public void initUI() {
		add(tituloApp, CENTER, CENTER);
		//add(subTituloApp, BOTTOM + 3, CENTER, PREFERRED , PREFERRED);
		//add(subTituloApp, CENTER,  BOTTOM + 20, PARENTSIZE+40, PREFERRED ,tituloApp);
	}

}
