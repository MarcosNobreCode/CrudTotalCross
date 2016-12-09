package br.com.coleta_pesquisa.view;

import totalcross.sys.Settings;
import totalcross.ui.MainWindow;

/*@author Marcos Nobre - 06/12/2016
 * @Description Classe principal da camanda view da aplicação 
 * de coleta de dados. (Projeto de um  CRUD exigido na seleção  da Softsite
 */
public class ViewMain extends MainWindow {
	
	public ViewMain() {
		Settings.uiStyle = Settings.Android;
		
	}

	public void initUI() {
		
		HeadView head = new HeadView();
		BodyView corpo = new BodyView();
		FooterView rodape = new FooterView();
		
		add(head, LEFT, TOP + 3 , FILL + 5, PREFERRED + 10);
		
		add(rodape, SAME, BOTTOM - 3,  FILL, PREFERRED);
		add(corpo, SAME, AFTER + 2, FILL, FIT - 30, head);
		
	}

}
