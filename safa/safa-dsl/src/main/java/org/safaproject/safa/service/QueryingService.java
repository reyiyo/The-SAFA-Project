package org.safaproject.safa.service;

public interface QueryingService {
/** EXAMPLE QUERY:
 * filter tipo.is: final 
 * AND autor.is: peralta 
 * AND fecha.between: 2000.year, 2005.year 
 * AND puntaje.quality.greater: 3.points 
 * AND NOT(fecha.month: july OR fecha.month: september) 
 * AND puntaje.prom.greater:5 
 * AND NOT(puntaje.laposta) 
 * AND facultad.in: [utn, uba] 
 * ORDER BY fecha.year.asc
 */
}
