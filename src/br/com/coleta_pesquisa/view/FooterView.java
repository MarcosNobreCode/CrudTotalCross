package br.com.coleta_pesquisa.view;

import totalcross.ui.Container;
import totalcross.ui.Label;

public class FooterView extends Container {

	private Label rodape;
	// private Label infor;

	public FooterView() {
		rodape = new Label("Softsite - Sele��o: Marcos Nobre");
		// infor = new Label("Sele��o: Marcos Nobre");
	}

	// Calcula a altura preferencial do Container (Footer)
	// public int getPreferredHeight() {
	//
	// return Math.max(rodape.getPreferredHeight(), infor.getPreferredHeight())
	// + 3 ;
	// }

	// Inicializa a interface de usu�rio com os elemento de rodap� em
	// posicionamento relativo.
	public void initUI() {

		add(rodape, CENTER, AFTER, PREFERRED, PREFERRED);
		// add(infor, LEFT,AFTER,PARENTSIZE,PREFERRED, rodape);
	}

}
