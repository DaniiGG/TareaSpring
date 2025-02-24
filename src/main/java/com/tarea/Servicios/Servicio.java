package com.tarea.Servicios;

import com.tarea.Entidades.Tarea;
import com.tarea.Repositorios.RepositoryTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Servicio{

    private final RepositoryTarea tareaRepository;

    public Servicio(RepositoryTarea tareaRepository) {
       this.tareaRepository = tareaRepository;
    }

    public List<Tarea> getAllTareas(){
        return this.tareaRepository.findAll();
    }

    public Tarea getTarea(Long id) {
        return this.tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
    }

    public Tarea insertTarea(Tarea tarea) {
        return this.tareaRepository.save(tarea);
    }

    public void deleteTarea(Long id) {
        this.tareaRepository.deleteById(id);
    }

    public Tarea updateTarea(Long id, Tarea tarea) {
        Optional<Tarea> tareaExistente = tareaRepository.findById(id);

        if (tareaExistente.isPresent()) {
            Tarea tareaActualizada = tareaExistente.get();
            tareaActualizada.setNombre(tarea.getNombre());  // Actualizar los campos necesarios
            tareaActualizada.setDescripcion(tarea.getDescripcion());
            tareaActualizada.setFechaFin(tarea.getFechaFin());
            return tareaRepository.save(tareaActualizada);  // Guardar la tarea actualizada
        } else {
            // Si la tarea no existe, puedes lanzar una excepción o manejar el caso según lo desees.
            return null;  // O lanzar una excepción personalizada si prefieres
        }
    }
}