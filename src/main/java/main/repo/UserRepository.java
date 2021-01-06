package main.repo;

import main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);

    @Query(value="SELECT * FROM users JOIN transaction on owner=users.id WHERE amount_before >= :amount group by users.id", nativeQuery = true)
    ArrayList<User> findAllExceedLimit(int amount);

    @Query(value="SELECT * FROM users JOIN transaction on owner=users.id WHERE (SELECT SUM(amount_before) from transaction WHERE owner=users.id) >= :amount group by users.id", nativeQuery = true)
    ArrayList<User> findAllExceedWholeLimit(int amount);


}

