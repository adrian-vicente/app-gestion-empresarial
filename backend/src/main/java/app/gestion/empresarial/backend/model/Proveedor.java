package app.gestion.empresarial.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity(name = "proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Proveedor {

    // Declaración de atributos 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    // Declaración de atributos para las relaciones

    @OneToMany(mappedBy = "proveedor")
    private List<Gasto> gastos;

} // class