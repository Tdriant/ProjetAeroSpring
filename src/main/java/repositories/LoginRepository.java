package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Login;

public interface LoginRepository extends JpaRepository<Login, Integer> {

}
