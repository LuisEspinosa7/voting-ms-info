/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.shared.service;


/**
 * The Interface ServiceModifiableMethods.
 * 
 * @author Luis Espinosa
 *
 * @param <T> the generic type
 */
public interface ServiceModifiableMethods<T> {
	
	/**
	 * Update.
	 *
	 * @param obj the obj
	 * @return the t
	 */
	T update(T obj);
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	void delete(Long id);
}
