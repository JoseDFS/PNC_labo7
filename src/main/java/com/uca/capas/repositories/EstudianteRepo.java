package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Estudiante;



public interface EstudianteRepo extends JpaRepository<Estudiante, Integer> {

		List<Estudiante> findByNombre(String cadena) throws DataAccessException;
		
		public List<Estudiante> findByApellidoStartingWith(String apellido) throws DataAccessException;
		
		@Query(nativeQuery = true, value = "SELECT * FROM public.estudiante")
		public List<Estudiante> showAll() throws DataAccessException;
		
		@Query(nativeQuery = true, value = "SELECT * FROM public.estudiante WHERE nombre = :nombre")
		public List<Estudiante> showByName(String nombre) throws DataAccessException;
}
