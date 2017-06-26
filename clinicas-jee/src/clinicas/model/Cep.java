package clinicas.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cep", catalog = "clinicasdb", schema = "")
@NamedQueries({
	@NamedQuery(name = "Cep.findAll", query = "SELECT c FROM Cep c")
})
public class Cep implements Serializable {

	private static final long serialVersionUID = -2784017290616489037L;
	
	@Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
	
    @Column(name = "cidade", length = 255)
    private String cidade;
    
    @Column(name = "rua", length = 255)
    private String rua;
    
    @Column(name = "bairro", length = 255)
    private String bairro;
    
    @Basic(optional = false)
    @Column(name = "cep", nullable = false, length = 255)
    private String cep;
    
    @Column(name = "tipo", length = 255)
    private String tipo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		Cep other = (Cep) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
