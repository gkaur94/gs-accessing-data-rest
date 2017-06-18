package hello;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	List<Person> findByLastName(@Param("name") String name);

	Boolean existsByFirstNameAndLastName(String firstName, String lastName);

	Person findById(Long id);

	List<Person> findAllByOrderByLastNameAsc();

}
