package br.com.test.springdata.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.test.springdata.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findByName(String name);
}