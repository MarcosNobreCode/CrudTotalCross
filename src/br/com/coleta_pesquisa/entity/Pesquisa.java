package br.com.coleta_pesquisa.entity;

/*@author Marcos Nobre - 07/12/2016
 * @Description Classe que faz o mapeamento da entidade de banco
 * para o mundo O.O. (Representa a tabela pesquisa na base)
 */
public class Pesquisa {
	
		//Atributos da entidade
		private Integer id;
		private  String nome;
		private int idade;
		private  String sexo;
		private String voto;
				
		//Metodos acessores - Getteres and Setters		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public int getIdade() {
			return idade;
		}
		public void setIdade(int idade) {
			this.idade = idade;
		}
		public String getSexo() {
			return sexo;
		}
		public void setSexo(String sexo) {
			this.sexo = sexo;
		}
		
		public String getVoto() {
			return voto;
		}
		public void setVoto(String voto) {
			this.voto = voto;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + idade;
			result = prime * result + ((nome == null) ? 0 : nome.hashCode());
			result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
			result = prime * result + ((voto == null) ? 0 : voto.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pesquisa other = (Pesquisa) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (idade != other.idade)
				return false;
			if (nome == null) {
				if (other.nome != null)
					return false;
			} else if (!nome.equals(other.nome))
				return false;
			if (sexo == null) {
				if (other.sexo != null)
					return false;
			} else if (!sexo.equals(other.sexo))
				return false;
			if (voto == null) {
				if (other.voto != null)
					return false;
			} else if (!voto.equals(other.voto))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Pesquisa [id=" + id + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", voto=" + voto
					+ "]";
		}
		
		
}
