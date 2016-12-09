package br.com.coleta_pesquisa.view;

import totalcross.ui.Edit;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.FocusListener;

public class FormatarEdit extends Edit {

	//Contrutor do edit padrão
	public FormatarEdit() {
		addFocusListener(new FormatarEditFocusListener());
		
	}

	//Contrutor do edit quando tiver máscara de entrada
	public FormatarEdit(String mask) {
		super(mask);
		addFocusListener(new FormatarEditFocusListener());
	}
	
	private static final class FormatarEditFocusListener implements FocusListener{

		
		public void focusIn(ControlEvent e) {
			((FormatarEdit) e.target).setBackColor(totalcross.ui.gfx.Color.ORANGE);			
			
		}

		
		public void focusOut(ControlEvent e) {
			((FormatarEdit) e.target).setBackColor(totalcross.ui.gfx.Color.WHITE);	
			
		}
		
	}

}
