package app.gestion.empresarial.backend.mapper;

import org.springframework.stereotype.Component;

import app.gestion.empresarial.backend.config.ValidatorConfig;
import app.gestion.empresarial.backend.dto.Gasto.GastoDTO;
import app.gestion.empresarial.backend.exception.ProveedorNotFoundException;
import app.gestion.empresarial.backend.exception.UsuarioNotFoundException;
import app.gestion.empresarial.backend.model.Gasto;
import app.gestion.empresarial.backend.model.Proveedor;
import app.gestion.empresarial.backend.model.Usuario;
import app.gestion.empresarial.backend.repository.ProveedorRepository;
import app.gestion.empresarial.backend.repository.UsuarioRepository;

@Component
public class GastoMapper {

    // Inyección de dependencias 

    private final UsuarioRepository usuarioRepository;
    private final ProveedorRepository proveedorRepository;

    public GastoMapper(UsuarioRepository usuarioRepository, ProveedorRepository proveedorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.proveedorRepository = proveedorRepository;

    }

    // Método de conversión de dto a entidad 

    public Gasto toEntity(GastoDTO gastoDTO) throws UsuarioNotFoundException, ProveedorNotFoundException {
        Gasto gasto = new Gasto();
        if(ValidatorConfig.identificadorValido(gastoDTO.getId())) gasto.setId(gastoDTO.getId());
        gasto.setCategoriaGasto(gastoDTO.getCategoriaGasto());
        gasto.setMetodoPago(gastoDTO.getMetodoPago());
        gasto.setIva(gastoDTO.getIva());
        gasto.setTotal(gastoDTO.getTotal());
        gasto.setNombre(gastoDTO.getNombre());
        gasto.setDescripcion(gastoDTO.getDescripcion());
        gasto.setNumeroFactura(gastoDTO.getNumeroFactura());

        // Obtención de atributos a partir 

        Usuario usuario = usuarioRepository.findById(gastoDTO.getUsuarioId())
            .orElseThrow(() -> new UsuarioNotFoundException("No se ha encontrado a ningún usuario con id: " + gastoDTO.getUsuarioId()));

        Proveedor proveedor = proveedorRepository.findById(gastoDTO.getProveedorId())
            .orElseThrow(() -> new ProveedorNotFoundException("No se ha encontrado a ningún proveedor con id: " + gastoDTO.getProveedorId()));

        gasto.setUsuario(usuario);
        gasto.setProveedor(proveedor);

        // Devolver el objeto con los datos añadidos

        return gasto;

    }

    // Método de conversión de entidad a dto 

    public GastoDTO toDTO(Gasto gasto) {
        GastoDTO gastoDTO = new GastoDTO();
        if(ValidatorConfig.identificadorValido(gasto.getId())) gastoDTO.setId(gasto.getId());
        gastoDTO.setCategoriaGasto(gasto.getCategoriaGasto());
        gastoDTO.setMetodoPago(gasto.getMetodoPago());
        gastoDTO.setIva(gasto.getIva());
        gastoDTO.setTotal(gasto.getTotal());
        gastoDTO.setNombre(gasto.getNombre());
        gastoDTO.setDescripcion(gasto.getDescripcion());
        gastoDTO.setNumeroFactura(gasto.getNumeroFactura());
        
        // Obtención de valores a par tir de los objetos 

        if(gasto.getUsuario() != null) gastoDTO.setUsuarioId(gasto.getUsuario().getId());
        if(gasto.getProveedor() != null) gastoDTO.setProveedorId(gasto.getProveedor().getId());

        // Devolver el objeto con los datos añadidos

        return gastoDTO;

    }

} // class