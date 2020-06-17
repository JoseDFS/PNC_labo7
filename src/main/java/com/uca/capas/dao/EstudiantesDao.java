package com.uca.capas.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;



import com.uca.capas.domain.Estudiante;

public interface EstudiantesDao{
	
	public List<Estudiante>findAll() throws DataAccessException;
	
	public void save(Estudiante estudiante) throws DataAccessException;

	public Estudiante findOne(Integer codigoEstudiante);

}
