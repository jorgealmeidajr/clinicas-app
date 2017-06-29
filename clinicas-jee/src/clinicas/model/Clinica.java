package clinicas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clinica")
public class Clinica implements Serializable {

	private static final long serialVersionUID = -694779838693623304L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_clinica")
	private Integer id;
	
	@Column(name = "razao_social", length = 255, nullable = false)
	private String razaoSocial;
	
	@Column
	private String cnpj;
	
	@Column
	private String email;
	
	@Column
	private String telefone;
	
	@Column(name = "atende_sus")
	private Boolean atendeSus;
	
	@Column(name = "valor_consulta")
	private Double valorConsulta;
	
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco", nullable = false)
    @ManyToOne(optional = false)
    private EnderecoClinica endereco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Boolean getAtendeSus() {
		return atendeSus;
	}

	public void setAtendeSus(Boolean atendeSus) {
		this.atendeSus = atendeSus;
	}

	public Double getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(Double valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	public EnderecoClinica getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoClinica endereco) {
		this.endereco = endereco;
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
		Clinica other = (Clinica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
