/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.shared.service;

import java.util.List;

/**
 * The Interface ServiceListMethods.
 * 
 * @author Luis Espinosa
 *
 * @param <T> the generic type
 */
public interface ServiceListMethods<T> {
	
	
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	List<T> listAll();

}
