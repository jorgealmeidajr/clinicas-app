package clinicas.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco_clinica")
public class EnderecoClinica implements Serializable {

	private static final long serialVersionUID = -2419769621160928799L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_endereco", nullable = false)
    private Integer id;
	
    @Column(name = "rua", length = 255)
    private String rua;
    
    @Column(name = "bairro", length = 55)
    private String bairro;
    
    @Column(name = "numero")
    private Integer numero;
    
    @Column(name = "complemento", length = 55)
    private String complemento;
    
    @Column(name = "cep")
    private String cep;
    
    @JoinColumn(name = "id_cidade", referencedColumnName = "id_cidade")
    @ManyToOne
    private Cidade idCidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		EnderecoClinica other = (EnderecoClinica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
