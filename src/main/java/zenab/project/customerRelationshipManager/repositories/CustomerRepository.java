package zenab.project.customerRelationshipManager.repositories;

import org.springframework.data.repository.CrudRepository;
import zenab.project.customerRelationshipManager.entities.Customer;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface CustomerRepository extends CrudRepository<Customer, String> {

}
