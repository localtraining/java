package br.com.itau.working.workingtime.time;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends CrudRepository<Time, Integer> {
    List<Time> findAllByUserId(Integer userId);
}