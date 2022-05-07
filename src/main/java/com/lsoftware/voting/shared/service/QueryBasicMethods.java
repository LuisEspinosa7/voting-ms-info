/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.shared.service;

import java.util.Set;

/**
 * The Interface QueryBasicMethods.
 * 
 * @author Luis Espinosa
 *
 * @param <T> the generic type
 */
public interface QueryBasicMethods<T> {
	
	
	/**
	 * List all.
	 *
	 * @return the sets the
	 */
	Set<T> listAll();

}
