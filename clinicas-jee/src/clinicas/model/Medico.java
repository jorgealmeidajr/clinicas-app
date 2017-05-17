package clinicas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico implements Serializable {

	private static final long serialVersionUID = 4509841646079640760L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_medico")
	private Integer id;
	
	@Column(name = "nome_medico", length = 255, nullable = false)
	private String nome;
	
	@Column(length = 55)
	private String cpf;
	
	@Lob
	@Column(name = "foto_medico")
	private byte[] foto;
	
	@Column
	private String telefone;
	
	@Column(name = "numero_crm")
	private Integer numeroCrm;
	
	@ManyToOne
	@JoinColumn(name = "id_estado_crm", referencedColumnName = "id_estado")
	private Estado estadoCrm;
	
	@ManyToOne
	@JoinColumn(name = "id_especialidade", referencedColumnName = "id_especialidade")
	private Especialidade especialidade;

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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getNumeroCrm() {
		return numeroCrm;
	}

	public void setNumeroCrm(Integer numeroCrm) {
		this.numeroCrm = numeroCrm;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Estado getEstadoCrm() {
		return estadoCrm;
	}

	public void setEstadoCrm(Estado estadoCrm) {
		this.estadoCrm = estadoCrm;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Medico other = (Medico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
