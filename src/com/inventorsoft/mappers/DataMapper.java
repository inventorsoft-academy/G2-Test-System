package com.inventorsoft.mappers;

public interface DataMapper <T> {

	String format(T entity);

	T parse(String line);
}
