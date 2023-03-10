package backend.chapter4.bookstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username); // Springin tekemää sisäänkirjautumistestiä varten
}
