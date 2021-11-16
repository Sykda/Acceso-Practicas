package com.add.ejercicio4;

import java.util.List;

public interface EstudianteDAOInterfaz {

	public <T> void insert(T t);
	public <T> boolean update(T t);
	public boolean deleteById(Integer id);
	public <T> T read(Integer id);
	public <T> List<T> findAll(); public <T> List<T> findByName(String name);
	public boolean removeAll();
}
