package br.com.coleta_pesquisa.view;

import br.com.coleta_pesquisa.dao.PesquisaDao;
import br.com.coleta_pesquisa.entity.Pesquisa;
import totalcross.sys.Convert;
import totalcross.sys.InvalidNumberException;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Control;
import totalcross.ui.Edit;
import totalcross.ui.Grid;
import totalcross.ui.Label;
import totalcross.ui.Radio;
import totalcross.ui.RadioGroupController;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;

/*@author Marcos Nobre - 07/12/2016
 * @Description Classe utilizada para compor o corpo da aplicacação
 * da aplicação
 */
public class BodyView extends Container {

	// Objeto que realiza a persistência
	private final PesquisaDao pesquisaDao;
	// Objeto que sofre a persistência
	private Pesquisa pesquisa;
	private Edit nome;
	private Edit idade;
	private RadioGroupController controllerSexo;
	private RadioGroupController controllerVoto;
	private Grid tabela;
	// Constantes de referência dos botões
	private static final int BTN_INSERT = 01;
	private static final int BTN_DELETE = 02;
	private static final int BTN_UPDATE = 03;
	private static final int BTN_SELECT = 04;
	private static final int BTN_LIMPAR = 05;
	private boolean selecaoUpdate = false;

	// Construtor que deixa um objeto de persistência a disposição de uso na
	// classe
	public BodyView() {

		// Instanciando os componentes de edição
		nome = new FormatarEdit();
		idade = new Edit();
		controllerSexo = new RadioGroupController();
		controllerVoto = new RadioGroupController();
		// Criando no cosntrutor uma instância do objeto de persistência e do
		// objeto persistido
		pesquisa = new Pesquisa();
		pesquisaDao = new PesquisaDao();
		// Sempre inicializa com todos os campos limpos
		limpaCampos();
	}

	// Inicializa a interface de usuário com os elemento do body.
	public void initUI() {

		// Campo Name da pesquisa
		add(new Label("Nome:"), LEFT + 10, TOP + 3);
		// Edit criado apartir da classe FormataEdit que sobrescreve o
		// comportamento da classe edit
		add(nome, AFTER + 3, CENTER_OF, FILL - 120, PREFERRED);

		// Campo Idade

		// Validando entrada de caracteres
		idade.setValidChars("0123456789");
		add(new Label("Idade:"), AFTER + 10, TOP + 3);
		add(idade, AFTER + 3, CENTER_OF, PREFERRED - 10, PREFERRED);

		// Campo sexo
		add(new Label("sexo:"), LEFT + 10, TOP + 30, PREFERRED, PREFERRED);
		add(new Radio("M", controllerSexo), AFTER + 3, SAME, PREFERRED, PREFERRED);
		add(new Radio("F", controllerSexo), AFTER + 6, SAME, SAME - 3, SAME);

		// Botões que dispararam eventos na tela
		// Botão usado na deleção de voto
		Button deletarBtn = new Button("Deletar");
		add(deletarBtn, RIGHT - 10, SAME, SAME + 21, SAME);
		deletarBtn.appId = BTN_DELETE;

		// Botão usado na atualização de um voto
		Button selectBtn = new Button("Obter");
		add(selectBtn, BEFORE - 3, TOP + 30, SAME, SAME, deletarBtn);
		selectBtn.appId = BTN_SELECT;

		// Botão usado na inserção de um votomc
		Button inserirBtn = new Button("Votar");
		add(inserirBtn, BEFORE - 3, SAME, SAME - 5, SAME, selectBtn);
		// Criando um identificador unico para o botão através do método appId
		inserirBtn.appId = BTN_INSERT;

		// ComboBox estadoCivil = new ComboBox(new String[]{"Solteiro(a)",
		// "Casado(a)", "Divorciado(a)", "Viuvo(a)"});
		// add(estadoCivil, AFTER + 3, CENTER_OF, FILL - 10, PREFERRED);

		// Pergunta da pesquisa
		add(new Label("Você é a favor da maioridade aos 16 anos?"), LEFT + 10, TOP + 60);

		// Campo voto:
		add(new Label("voto:"), LEFT + 10, TOP + 90, PREFERRED, PREFERRED);
		add(new Radio("SIM", controllerVoto), AFTER + 3, SAME, PREFERRED, PREFERRED);
		add(new Radio("NÃO", controllerVoto), AFTER + 6, SAME, SAME + 3, SAME);

		// Botão usado na inserção de um votomc
		Button atualizarBtn = new Button("Atualizar");
		add(atualizarBtn, AFTER + 20, SAME, SAME + 15, SAME);
		// Criando um identificador unico para o botão através do método appId
		atualizarBtn.appId = BTN_UPDATE;

		// Botão usado na inserção de um votomc
		Button limparBtn = new Button("Limpar");
		add(limparBtn, AFTER + 5, SAME, SAME - 2, SAME);
		// Criando um identificador unico para o botão através do método appId
		limparBtn.appId = BTN_LIMPAR;

		// Montando a tabela
		tabela = new Grid(new String[] { "id", "nome", "idade", "sexo", "voto" }, new int[] { -10, -50, -10, -10, -10 },
				new int[] { CENTER, LEFT, CENTER, CENTER, CENTER }, false);
		add(tabela, LEFT + 10, AFTER + 10, FILL - 10, FILL - 3);
		// Populando os registro
		tabela.setItems(pesquisaDao.pesquisar());

	}

	// Tratando eventos
	public void onEvent(Event evento) {
		// Checando se algum botão foi pressionado
		if (evento.type == ControlEvent.PRESSED) {
			// Capturando dentro de um objeto Control o elemento utilizado.
			// (No caso: O tipo de botão! Mas poderia ser utilizado para
			// qualquer tipo de componente alvo)
			Control controle = (Control) evento.target;

			switch (controle.appId) {
			case BTN_INSERT:

				if (nome.getText().length() > 0 && idade.getText().length() > 0
						&& (controllerSexo.getRadio(0).isChecked() == true
								|| controllerSexo.getRadio(1).isChecked() == true)
						&& (controllerVoto.getRadio(0).isChecked() == true
								|| controllerVoto.getRadio(1).isChecked() == true)) {

					pesquisa.setNome(nome.getText());
					// Convertendo o valor da idade em inteiro e setando no
					// atributo em questão
					pesquisa.setIdade(Integer.parseInt(idade.getText()));
					// Usando operador if ternário para capturar o sexo
					pesquisa.setSexo((controllerSexo.getSelectedIndex() == 0) ? "M" : "F");
					// Usando operador if ternário para capturar o sexo
					pesquisa.setVoto((controllerVoto.getSelectedIndex() == 0) ? "S" : "N");

					// Realizando a inserção do objeto
					pesquisaDao.inserir(pesquisa);
					// Atulizando a lista de votos na tela
					tabela.setItems(pesquisaDao.pesquisar());
					limpaCampos();

					new MessageBox("Obrigado!", "Voto registrado!").popup();
					break;

				} else {
					new MessageBox("Opa!", "Preencha todos os campos").popup();
					break;
				}

			case BTN_SELECT:

				// Pegando a linha selecionada
				if (tabela.getSelectedItem() != null) {

					String[] linha = tabela.getSelectedItem();

					try {
						// Populando os dados do registro a alterar
						pesquisa.setId(Convert.toInt(linha[0]));
						nome.setText(linha[1]);
						idade.setText(linha[2]);
						controllerSexo.setSelectedItem(linha[3]);
						controllerVoto.setSelectedItem(linha[4]);
						selecaoUpdate = true;
						break;
					} catch (InvalidNumberException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					new MessageBox("Opa!", "Selecione um registro.").popup();
					break;
				}

			
			case BTN_DELETE:

				// Pegando a linha selecionada
				if (tabela.getSelectedItem() != null) {

					String[] linha = tabela.getSelectedItem();

					try {
						// Pegando o primeiro campo da linha selecionada no caso
						// o ID
						pesquisa.setId(Convert.toInt(linha[0]));
						// Realizando a deleção do objeto
						pesquisaDao.deletar(pesquisa);
						// Atulizando a lista de votos na tela
						tabela.setItems(pesquisaDao.pesquisar());
						new MessageBox("Exclusão!", "Registro excluído!").popup();
						break;
					} catch (InvalidNumberException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					new MessageBox("Opa!", "Selecione um registro.").popup();
					break;
				}
				
			case BTN_LIMPAR:
				limpaCampos();
				break;
				
				

								
			case BTN_UPDATE:

				// Pegando a linha selecionada
				if (tabela.getSelectedItem() != null && selecaoUpdate == true) {

					String[] linha = tabela.getSelectedItem();

					try {
						pesquisa.setId(Convert.toInt(linha[0]));
						pesquisa.setNome(nome.getText());
						pesquisa.setIdade(Convert.toInt(idade.getText()));
						if (controllerSexo.getSelectedIndex() == 0) {
							pesquisa.setSexo("M");
						} else {
							pesquisa.setSexo("F");
						}

						pesquisaDao.atualizar(pesquisa);

						// Atulizando a lista de votos na tela
						tabela.setItems(pesquisaDao.pesquisar());
						limpaCampos();
						new MessageBox("Atualização!", "Registro alterado!").popup();
						selecaoUpdate = false;
						break;

					} catch (InvalidNumberException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					new MessageBox("Opa!", "Obtenha um registro.").popup();
					break;
				}


			}
			
			

		}

	}

	public void limpaCampos() {
		nome.setText(null);
		idade.setText(null);
		controllerSexo.setSelectedItem(clearValueStr);
		controllerVoto.setSelectedItem(clearValueStr);
	}

}
