package main.repo;


import main.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {


    @Query(value="SELECT SUM(amount_before) FROM transaction JOIN currency on currency.id=currency_from WHERE currency.name = :name", nativeQuery = true)
    Double findTransAmount(String name);

    @Query(value="SELECT * FROM transaction JOIN currency ON currency_from=currency.id WHERE amount_before/currency.course >= :amount", nativeQuery = true)
    List<Transaction> findAllForOneOper(double amount);

    @Query(value="SELECT SUM(amount_before) FROM transaction WHERE owner=:id", nativeQuery = true)
    Double findAmountForAllOper(int id);

    @Query(value="SELECT SUM(amount_before/currency.course) FROM transaction JOIN currency ON currency_from=currency.id WHERE owner=:userId group by owner", nativeQuery = true)
    Double calcAllSum(int userId);
}
