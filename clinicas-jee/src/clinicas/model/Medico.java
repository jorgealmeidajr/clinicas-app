package clinicas.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Lob
	@Column(name = "foto_medico")
	private byte[] foto;
	
	@Column
	private String telefone;
	
	@Column(name = "numero_crm")
	private Integer numeroCrm;
	
	@ManyToOne
	@JoinColumn(name = "id_estado_crm", referencedColumnName = "id_estado", nullable = true)
	private Estado estadoCrm;
	
	@ManyToOne
	@JoinColumn(name = "id_especialidade", referencedColumnName = "id_especialidade")
	private Especialidade especialidade;
	
	@ManyToOne
	@JoinColumn(name = "id_clinica", referencedColumnName = "id_clinica")
	private Clinica clinica;

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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
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
