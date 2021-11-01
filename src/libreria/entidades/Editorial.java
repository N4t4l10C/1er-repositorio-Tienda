package libreria.entidades;

import com.sun.istack.internal.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Editorial{
    
    @Id
    @GeneratedValue
    protected int id;
    
    @NotNull
    @Column(unique = true)
    protected String nombre;
    
    protected boolean alto;
    
    // Constructores
    public Editorial() {
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isAlto() {
        return alto;
    }
    public void setAlto(boolean alto) {
        this.alto = alto;
    }

    // toString
    @Override
    public String toString() {
        return "Editorial{" + "id=" + id + ", nombre=" + nombre + ", alto=" + alto + '}';
    }
    
    
    
}
