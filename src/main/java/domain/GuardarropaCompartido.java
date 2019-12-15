package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import db.EntidadPersistente;

@Entity
@Table(name = "GuardarropaCompartido")
public class GuardarropaCompartido extends EntidadPersistente
{
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuardarropaC", referencedColumnName = "id")
	private List<Usuario> usuario;
    
    @ManyToOne
    @JoinColumn(name = "idGuardarropa", referencedColumnName = "id")
    private Guardarropa guardarropa;

    public GuardarropaCompartido(List<Usuario> usuario, Guardarropa guardarropa) {
    	this.usuario = new ArrayList<Usuario>();
        this.usuario = usuario;
        this.guardarropa = guardarropa;
    }

    
    
    public GuardarropaCompartido() {}



	public Guardarropa getGuardarropa() {
        return guardarropa;
    }

    public void setGuardarropa(Guardarropa guardarropa) {
        this.guardarropa = guardarropa;
    }

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

    
}
