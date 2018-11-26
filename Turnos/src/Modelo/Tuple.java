/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 */
public class Tuple<E> {
	private E first;
	private E second;

	public Tuple(E first, E second) {
		this.first = first;
		this.second = second;
	}

	public Tuple() {
	}
		
	public E getFirst() {
		return first;
	}

	public void setFirst(E first) {
		this.first = first;
	}

	public E getSecond() {
		return second;
	}

	public void setSecond(E second) {
		this.second = second;
	}
	
	
	
}
