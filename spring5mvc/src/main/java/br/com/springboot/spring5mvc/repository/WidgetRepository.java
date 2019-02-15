package br.com.springboot.spring5mvc.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.springboot.spring5mvc.model.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Long> {
}