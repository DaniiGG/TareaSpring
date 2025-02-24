package com.tarea.Repositorios;

import com.tarea.Entidades.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTarea extends JpaRepository<Tarea, Long> {
}
