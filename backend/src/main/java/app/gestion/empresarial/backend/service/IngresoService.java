package app.gestion.empresarial.backend.service;

import org.springframework.stereotype.Service;

import app.gestion.empresarial.backend.mapper.IngresoMapper;
import app.gestion.empresarial.backend.repository.IngresoRepository;

@Service
public class IngresoService {

    // Inyección de dependencias 

    private final IngresoRepository ingresoRepository;
    private final IngresoMapper ingresoMapper;

    public IngresoService(IngresoRepository ingresoRepository, IngresoMapper ingresoMapper) {
        this.ingresoRepository = ingresoRepository;
        this.ingresoMapper = ingresoMapper;
    }

} // class