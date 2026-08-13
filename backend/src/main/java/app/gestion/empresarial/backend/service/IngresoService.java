package app.gestion.empresarial.backend.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import app.gestion.empresarial.backend.dto.Ingreso.IngresoCreateDTO;
import app.gestion.empresarial.backend.dto.Ingreso.IngresoDTO;
import app.gestion.empresarial.backend.exception.IngresoAlreadyExistsException;
import app.gestion.empresarial.backend.exception.IngresoNotFoundException;
import app.gestion.empresarial.backend.mapper.IngresoMapper;
import app.gestion.empresarial.backend.repository.IngresoRepository;

@Service
public class IngresoService {

    // Inyección de dependencias 

    private final IngresoRepository ingresoRepository;
    private final IngresoMapper ingresoMapper;
    private final UsuarioService usuarioService;

    public IngresoService(IngresoRepository ingresoRepository, IngresoMapper ingresoMapper, UsuarioService usuarioService) {
        this.ingresoRepository = ingresoRepository;
        this.ingresoMapper = ingresoMapper;
        this.usuarioService = usuarioService;

    }

    // Método para obtener todos los ingresos

    public List<IngresoDTO> obtenerTodosLosIngresos() {
        return ingresoRepository.findByUsuarioId(
            usuarioService.getAuthenticatedUser().getId()
        )
        .stream()
        .map(ingreso -> ingresoMapper.toDTO(ingreso))
        .collect(Collectors.toList());

    }

    // Método para obtener ingreso por id

    public IngresoDTO obtenerIngresoPorId(Long id) throws IngresoNotFoundException {
        return ingresoMapper.toDTO(
            ingresoRepository.findById(id)
                .orElseThrow(() -> new IngresoNotFoundException("No se ha encontrado ningún ingreso con el id: " + id))
        );

    }

    // Método para crear nuevo ingreso

    public IngresoDTO crearNuevoIngreso(IngresoCreateDTO ingresoCreateDTO) throws IngresoAlreadyExistsException {
        if(ingresoRepository.existsByNombre(ingresoCreateDTO.getNombre())) {
            throw new IngresoAlreadyExistsException("Ya existe un ingreso con el nombre: " + ingresoCreateDTO.getNombre());

        } // if

        // Devolver el nuevo ingreso creado en formato DTO

        return ingresoMapper.toDTO(
            ingresoRepository.save(
                ingresoMapper.toEntityFromCreateDTO(ingresoCreateDTO)
            )

        );

    }

    // Método para modificar ingreso existente

} // class