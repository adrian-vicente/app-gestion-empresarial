package app.gestion.empresarial.backend.mapper;

import org.springframework.stereotype.Component;
import app.gestion.empresarial.backend.model.Usuario;
import app.gestion.empresarial.backend.config.ValidatorConfig;
import app.gestion.empresarial.backend.dto.IngresoDTO;
import app.gestion.empresarial.backend.exception.UsuarioNotFoundException;
import app.gestion.empresarial.backend.model.Ingreso;
import app.gestion.empresarial.backend.repository.UsuarioRepository;

@Component
public class IngresoMapper {

    // Inyección de dependencias 

    private final UsuarioRepository usuarioRepository;
    public IngresoMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método de conversión dto a entidad 

    public Ingreso toEntity(IngresoDTO ingresoDTO) {
        Ingreso ingreso = new Ingreso();
        if(ValidatorConfig.identificadorValido(ingresoDTO.getId())) ingreso.setId(ingresoDTO.getId());
        ingreso.setMetodoPago(ingresoDTO.getMetodoPago());
        ingreso.setCategoriaIngreso(ingresoDTO.getCategoriaIngreso());
        ingreso.setNombre(ingresoDTO.getNombre());
        ingreso.setDescripcion(ingresoDTO.getDescripcion());
        ingreso.setIva(ingresoDTO.getIva());
        ingreso.setTotal(ingresoDTO.getTotal());

        // Obtener atributos relacionados con las relaciones 

        Usuario usuario = usuarioRepository.findById(ingresoDTO.getUsuarioId())
            .orElseThrow(() -> new UsuarioNotFoundException("No se ha encontrado a ningún usuario con id: " + ingresoDTO.getUsuarioId()));

        ingreso.setUsuario(usuario);

        // Devolver el objeto con los datos transpilados 

        return ingreso;

    }

    // Método de conversión entidad a dto

    public IngresoDTO toDTO(Ingreso ingreso) {
        IngresoDTO ingresoDTO = new IngresoDTO();
        if(ValidatorConfig.identificadorValido(ingreso.getId())) ingresoDTO.setId(ingreso.getId());
        ingresoDTO.setMetodoPago(ingreso.getMetodoPago());
        ingresoDTO.setCategoriaIngreso(ingreso.getCategoriaIngreso());
        ingresoDTO.setNombre(ingreso.getNombre());
        ingresoDTO.setDescripcion(ingreso.getDescripcion());
        ingresoDTO.setIva(ingreso.getIva());
        ingresoDTO.setTotal(ingreso.getTotal());

        // Obtener atributos relacionado con las relaciones 

        if(ingreso.getUsuario() != null) ingresoDTO.setUsuarioId(ingreso.getUsuario().getId());

        // Devolver el objeto con los datos transpilados 

        return ingresoDTO;

    }

} // class