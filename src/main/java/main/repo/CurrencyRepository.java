package main.repo;

import main.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {

    ArrayList<Currency> findAll();

    Optional<Currency> findOneByName(String currency);



}
