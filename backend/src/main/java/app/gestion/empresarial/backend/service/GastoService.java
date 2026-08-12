package app.gestion.empresarial.backend.service;

import org.springframework.stereotype.Service;

import app.gestion.empresarial.backend.mapper.GastoMapper;
import app.gestion.empresarial.backend.repository.GastoRepository;

@Service
public class GastoService {

    // Inyección de dependencias 

    private final GastoMapper gastoMapper;
    private final GastoRepository gastoRepository;
    
    public GastoService(GastoRepository gastoRepository, GastoMapper gastoMapper) {
        this.gastoRepository = gastoRepository;
        this.gastoMapper = gastoMapper;

    }

    // Método para obtener todos los gastos 

    // Método para obtener gasto por id

    // Método para crear un nuevo gasto

    // Método para modificar gasto existente

} // class