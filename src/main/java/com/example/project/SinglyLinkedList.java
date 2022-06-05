package com.example.project;

import java.util.*;

public class SinglyLinkedList<T extends Comparable<T>> {
	private Node<T> first; // Primero nodo de la lista
	private int size; // Tamano de la lista

	// Constructor (crea lista vacia)
	SinglyLinkedList() {
		first = null;
		size = 0;
	}

	// Retorna el tamano de la lista
	public int size() {
		return size;
	}

	// Devuelve true si la lista esta vazia o false caso contrario
	public boolean isEmpty() {
		return (size == 0);
	}

	// Adiciona v al inicio de la lista
	public void addFirst(T v) {
		Node<T> newNode = new Node<T>(v, first);
		first = newNode;
		size++;
	}

	// Adiciona v al final de la lista
	public void addLast(T v) {
		Node<T> newNode = new Node<T>(v, null);
		if (isEmpty()) {
			first = newNode;
		} else {
			Node<T> cur = first;
			while (cur.getNext() != null)
				cur = cur.getNext();
			cur.setNext(newNode);
		}
		size++;
	}

	// Retorna el primer valor de la lista (o null si la lista esta vacia)
	public T getFirst() {
		if (isEmpty())
			return null;
		return first.getValue();
	}

	// Retorna el ultimo valor de la lista (o null si la lista esta vazia)
	public T getLast() {
		if (isEmpty())
			return null;
		Node<T> cur = first;
		while (cur.getNext() != null)
			cur = cur.getNext();
		return cur.getValue();
	}

	// Elimina el primer elemento de la lista (si esta vacia no hara nada)
	public void removeFirst() {
		if (isEmpty())
			return;
		first = first.getNext();
		size--;
	}

	// Elimina el ultimo elemento de la lista (si esta vacia no hara nada)
	public void removeLast() {
		if (isEmpty())
			return;
		if (size == 1) {
			first = null;
		} else {
			// Ciclo con for y uso de size para mostrar alternativa al while
			Node<T> cur = first;
			for (int i = 0; i < size - 2; i++)
				cur = cur.getNext();
			cur.setNext(cur.getNext().getNext());
		}
		size--;
	}

	// Convierte la lista para um String
	public String toString() {
		String str = "{";
		Node<T> cur = first;
		while (cur != null) {
			str += cur.getValue();
			cur = cur.getNext();
			if (cur != null)
				str += ",";
		}
		str += "}";
		return str;
	}

	// NUEVOS METODOS

	// Elimina aquellos nodos de la lista que esten duplicados
	public void deleteDuplicates() {
		if (size > 1) {
			ArrayList<T> sinRepetir = new ArrayList<T>();
			sinRepetir.add(getFirst());
			// Anterior al que es evaluado
			Node<T> anterior = first;
			Node<T> evaluado = anterior.getNext();
			boolean encontrado;
			while (anterior.getNext() != null) {
				encontrado = false;
				for(T a : sinRepetir) {
					if(a.compareTo(evaluado.getValue()) == 0) {
						anterior.setNext(evaluado.getNext());
						size--;
						encontrado = true;
						break;
					}
				}
				if(!(encontrado)) {
					sinRepetir.add(evaluado.getValue());
					anterior = anterior.getNext();
				}
				evaluado = anterior.getNext();
			}
		}
	}

	// Inserta un nuevo nodo en una posicion especifica de la lista
	public void insertNth(T data, int position) {
		if (position <= size) {
			Node<T> primero = first;
			if (position == 0) {
				Node<T> nuevo = new Node<T>(data, primero);
				first = nuevo;
			} else {
				for (int i = 0; i < position - 1; i++) {
					primero = primero.getNext();
				}
				Node<T> nuevo = new Node<T>(data, primero.getNext());
				primero.setNext(nuevo);
			}
			size++;
		} else {
			System.out.println("\"Fuera de rango.\"");
		}
	}

	// Elimina el nodo de una posicion especifica de la lista
	public void deleteNth(int position) {
		if (position <= size - 1) {
			Node<T> primero = first;
			if (position == 0) {
				first = primero.getNext();
				primero.setNext(null);
			} else {
				for (int i = 0; i < position - 1; i++) {
					primero = primero.getNext();
				}
				Node<T> delete = primero.getNext();
				primero.setNext(delete.getNext());
				delete.setNext(null);
			}
			size--;
		} else {
			System.out.println("\"Fuera de rango.\"");
		}
	}

	public static void main(final String[] args) {

		 testExercicio1();
		// testExercicio2();
		//testExercicio3();

	}

	public static void testExercicio1() {

		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

		list.addLast(47);
		list.addLast(89);
		list.addLast(56);
		list.addLast(89);
		list.addLast(56);

		System.out.println(list);

		list.deleteDuplicates();

		System.out.println(list);
	}

	public static void testExercicio2() {

		SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

		list.addLast('a');
		list.addLast('b');
		list.addLast('d');

		System.out.println(list);

		list.insertNth('c', 2);

		System.out.println(list);
	}

	public static void testExercicio3() {

		SinglyLinkedList<Character> list = new SinglyLinkedList<Character>();

		list.addLast('a');
		list.addLast('b');
		list.addLast('d');

		System.out.println(list);

		list.deleteNth(2);

		System.out.println(list);
	}

}