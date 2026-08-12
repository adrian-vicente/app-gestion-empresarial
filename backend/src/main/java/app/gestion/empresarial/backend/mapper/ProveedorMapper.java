package app.gestion.empresarial.backend.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import app.gestion.empresarial.backend.config.ValidatorConfig;
import app.gestion.empresarial.backend.dto.ProveedorDTO;
import app.gestion.empresarial.backend.model.Gasto;
import app.gestion.empresarial.backend.model.Proveedor;
import app.gestion.empresarial.backend.repository.GastoRepository;
import java.util.ArrayList;

@Component
public class ProveedorMapper {

    // Inyección de dependencias 

    private final GastoRepository gastoRepository;
    public ProveedorMapper(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    // Método de conversión de dto a entidad 

    public Proveedor toEntity(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = new Proveedor();
        if(ValidatorConfig.identificadorValido(proveedorDTO.getId())) {
            proveedor.setId(proveedorDTO.getId());
            proveedor.setGastos(gastoRepository.findByProveedorId(proveedorDTO.getId()));

        } // if

        proveedor.setNombre(proveedorDTO.getNombre());
        proveedor.setDescripcion(proveedorDTO.getDescripcion());
        
        // Devolver el objeto con los datos transpilados 

        return proveedor;

    }

    // Método de conversión de entidad a dto 

    public ProveedorDTO toDTO(Proveedor proveedor) {
        ProveedorDTO proveedorDTO = new ProveedorDTO();
        if(ValidatorConfig.identificadorValido(proveedor.getId())) {
            proveedorDTO.setId(proveedor.getId());

        } // if

        proveedorDTO.setNombre(proveedor.getNombre());
        proveedorDTO.setDescripcion(proveedorDTO.getDescripcion());

        // Obtener los atributos de las relaciones 

        if(!proveedor.getGastos().isEmpty()) {
            List<Long> gastosIds = new ArrayList<Long>();
            for(Gasto g : proveedor.getGastos()) gastosIds.add(g.getId());
            proveedorDTO.setGastosIds(gastosIds);

        } // if

        // Devolver el objeto con los datos transpilados 
        
        return proveedorDTO;

    }

} // class