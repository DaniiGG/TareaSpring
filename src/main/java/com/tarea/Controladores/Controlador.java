package com.tarea.Controladores;
import com.tarea.Entidades.Tarea;
import com.tarea.Repositorios.RepositoryTarea;
import com.tarea.Servicios.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/tareas")
public class Controlador {

    private final Servicio servicioTarea;
    @Autowired
    private RepositoryTarea tareaRepository;

    public Controlador(Servicio servicioTarea) {
        this.servicioTarea = servicioTarea;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTareaById(@PathVariable Long id) {
        try {
            Tarea tarea = servicioTarea.getTarea(id);
            return ResponseEntity.ok(tarea);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error 400: ID de tarea no válido.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error 404: Tarea con ID " + id + " no encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error 500: Error interno del servidor.");
        }
    }

    @GetMapping
    public ResponseEntity<?> tareas() {
        try {
            return ResponseEntity.ok(this.servicioTarea.getAllTareas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error 500: No se pudo recuperar la lista de tareas.");
        }
    }

    @PostMapping
    public ResponseEntity<?> insertarTarea(@RequestBody Tarea tarea) {
        try {
            return ResponseEntity.ok(this.servicioTarea.insertTarea(tarea));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error 400: Datos de la tarea inválidos.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error 500: No se pudo insertar la tarea.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTarea(@PathVariable Long id) {
        try {
            if (!tareaRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error 404: La tarea con ID " + id + " no existe.");
            }
            servicioTarea.deleteTarea(id);
            return ResponseEntity.ok("Tarea eliminada correctamente con ID: " + id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error 400: ID de tarea no válido.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error 500: No se pudo eliminar la tarea.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarTarea(@PathVariable Long id, @RequestBody Tarea tarea) {
        try {
            return ResponseEntity.ok(this.servicioTarea.updateTarea(id, tarea));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error 400: Datos inválidos para la actualización.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error 404: No se pudo actualizar la tarea con ID " + id + ".");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error 500: No se pudo actualizar la tarea.");
        }
    }
}

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNotFoundError(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Error 404: Endpoint no encontrado. Verifica la URL.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error 500: Ocurrió un error en el servidor.");
    }
}
