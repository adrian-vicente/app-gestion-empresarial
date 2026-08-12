package app.gestion.empresarial.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import app.gestion.empresarial.backend.dto.Gasto.GastoCreateDTO;
import app.gestion.empresarial.backend.dto.Gasto.GastoDTO;
import app.gestion.empresarial.backend.dto.Gasto.GastoUpdateDTO;
import app.gestion.empresarial.backend.exception.GastoAlreadyExistsException;
import app.gestion.empresarial.backend.exception.GastoNotFoundException;
import app.gestion.empresarial.backend.mapper.GastoMapper;
import app.gestion.empresarial.backend.model.Gasto;
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

    public List<GastoDTO> obtenerTodosLosGastos() {
        return gastoRepository.findAll().stream()
            .map(gasto -> gastoMapper.toDTO(gasto))
            .collect(Collectors.toList());

    }

    // Método para obtener gasto por id

    public GastoDTO obtenerGastoPorId(Long gasto_id) throws GastoNotFoundException {
        return gastoMapper.toDTO(gastoRepository.findById(gasto_id)
            .orElseThrow(() -> new GastoNotFoundException("No se ha encontrado ningún gasto con el id indicada.")));

    }

    // Método para crear un nuevo gasto

    public GastoDTO crearNuevoGasto(GastoCreateDTO gastoCreateDTO) throws GastoAlreadyExistsException {
        if(gastoRepository.existsByNumeroFactura(gastoCreateDTO.getNumeroFactura())) {
            throw new GastoAlreadyExistsException("Ya existe un gasto con el número de factura: " + gastoCreateDTO.getNumeroFactura());

        } // if 

        return gastoMapper.toDTO(gastoRepository.saveAndFlush(gastoMapper.toEntityFromCreateDTO(gastoCreateDTO)));

    }

    // Método para modificar gasto existente

    public GastoDTO modificarGastoExistente(GastoUpdateDTO gastoUpdateDTO, Long gasto_id) throws GastoAlreadyExistsException, GastoNotFoundException {
        if(gastoRepository.existsByNumeroFactura(gastoUpdateDTO.getNumeroFactura())) {
            throw new GastoAlreadyExistsException("Ya existe un gasto con el número de factura: " + gastoUpdateDTO.getNumeroFactura());

        } // if 

        if(gastoRepository.existsById(gasto_id)) {
            Gasto gasto = gastoMapper.toEntityFromUpdateDTO(gastoUpdateDTO);
            return gastoMapper.toDTO(gastoRepository.saveAndFlush(gasto));

        } else throw new GastoNotFoundException("No se ha encontrado ningún gasto con id: " + gasto_id);


    }

    // Método para obtener todos los gastos de un proveedor 

} // class