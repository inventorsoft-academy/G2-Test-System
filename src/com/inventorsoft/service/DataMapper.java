package com.inventorsoft.service;

public interface DataMapper <T> {

	String format(T entity);

	T parse(String line);
}
