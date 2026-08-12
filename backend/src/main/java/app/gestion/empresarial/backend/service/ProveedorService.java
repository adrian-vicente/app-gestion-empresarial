package app.gestion.empresarial.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.gestion.empresarial.backend.dto.Proveedor.ProveedorCreateDTO;
import app.gestion.empresarial.backend.dto.Proveedor.ProveedorDTO;
import app.gestion.empresarial.backend.dto.Proveedor.ProveedorUpdateDTO;
import app.gestion.empresarial.backend.exception.ProveedorNameAlreadyExistsException;
import app.gestion.empresarial.backend.exception.ProveedorNotFoundException;
import app.gestion.empresarial.backend.mapper.ProveedorMapper;
import app.gestion.empresarial.backend.model.Proveedor;
import app.gestion.empresarial.backend.repository.ProveedorRepository;

@Service
public class ProveedorService {

    // Inyección de dependencias 

    private final ProveedorRepository proveedorRepository;
    private final ProveedorMapper proveedorMapper;

    public ProveedorService(ProveedorRepository proveedorRepository, ProveedorMapper proveedorMapper) {
        this.proveedorRepository = proveedorRepository; 
        this.proveedorMapper = proveedorMapper;
    }

    // Método para obtener todos los proveedores 

    @Transactional(readOnly = true)
    public List<ProveedorDTO> obtenerProveedores() {
        return proveedorRepository.findAll().stream()
            .map(prov -> proveedorMapper.toDTO(prov))
            .collect(Collectors.toList());

    }

    // Método para obtener proveedor a partir del id 

    @Transactional(readOnly = true)
    public ProveedorDTO obtenerProveedorId(Long id) throws ProveedorNotFoundException {
        return proveedorMapper.toDTO(
            proveedorRepository.findById(id)
            .orElseThrow(() -> new ProveedorNotFoundException("No se ha encontrado a ningún proveedor con id: " + id))
        
        );

    } 

    // Método para crear un nuevo proveedor 

    @Transactional
    public ProveedorDTO crearNuevoProveedor(ProveedorCreateDTO proveedorCreateDTO) {
        Proveedor proveedor = proveedorMapper.toEntityFromCreateDTO(proveedorCreateDTO);
        return proveedorMapper.toDTO(proveedorRepository.saveAndFlush(proveedor));

    }

    // Método para modificar un proveedor existente

    @Transactional
    public ProveedorDTO modificarProveedorExistente(ProveedorUpdateDTO proveedorUpdateDTO, Long proveedor_id) throws ProveedorNameAlreadyExistsException {
        if(proveedorRepository.existsByNombre(proveedorUpdateDTO.getNombre())) {
            throw new ProveedorNameAlreadyExistsException("Ya existe un proveedor con el nombre: " + proveedorUpdateDTO.getNombre());

        } // if

        // Obtener el proveedor actual y modificar los datos 

        Proveedor proveedorActual = proveedorRepository.findById(proveedor_id)
            .orElseThrow(() -> new ProveedorNotFoundException("No se ha encontrado a ningún proveedor con id: " + proveedor_id));

            proveedorActual.setNombre(proveedorUpdateDTO.getNombre());
            proveedorActual.setDescripcion(proveedorUpdateDTO.getDescripcion());

        // Devolver el proveedor con los datos modificados

        return proveedorMapper.toDTO(proveedorRepository.saveAndFlush(proveedorActual));

    }

} // class