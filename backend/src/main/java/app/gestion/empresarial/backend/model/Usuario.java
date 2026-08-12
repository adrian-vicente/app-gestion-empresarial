package app.gestion.empresarial.backend.model;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.gestion.empresarial.backend.model.enums.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {

    // Identificador de usuario en la aplicación 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Rol identificativo del usuario

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol = Rol.USUARIO;

    // Campo para la password del usuario 

    private String password;

    // Permite identificar el estado de un usuario

    @Column(nullable = false)
    private Boolean activo = true;

    // Longitud mínimo 3 y máximo de 20 a 50 carácteres
    
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 carácteres")
    private String nombre;

    // Longitud máxima de 254 carácteres (Estándar RFC)
    
    @Email(message = "El email no tiene el formato correcto")
    @Size(max = 254, message = "El email debe tener 254 cáracteres cómo máximo")
    @Column(nullable = false, unique = true)
    private String email;

    // Edad de rango máximo de 60 años
    
    @Min(value = 18, message = "La edad debe tener mínimo 18 años.")
    @Max(value = 60, message = "La edad debe tener cómo máximo 60 años.")
    private Integer edad;

    // Fecha de creación de la cuenta de usuario 

    private LocalDate fechaCreacionUsuario = LocalDate.now();

    // Fecha del último inicio de sesión 

    private LocalDate fechaUltimoLogin;

    // Fecha de la última actualización del usuario 

    private LocalDate fechaUltimaActualizacion;

    // Teléfono del usuario 

    private String telefono;

    // Declaración de atributos para las relaciones

    @OneToMany(mappedBy = "usuario")
    private List<Gasto> gastos;

    @OneToMany(mappedBy = "usuario")
    private List<Ingreso> ingresos;

    // Método para obtener los roles del usuario 

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
            new SimpleGrantedAuthority("ROLE_" + rol.name())
        );
    }

    // Método para obtener el username (Devolverá el email)

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Método para comprobar si el usuario está o no activo

    @Override
    public boolean isEnabled() {
        return activo;
    }

} // class