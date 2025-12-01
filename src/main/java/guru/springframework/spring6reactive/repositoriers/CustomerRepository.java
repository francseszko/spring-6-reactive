package guru.springframework.spring6reactive.repositoriers;

import guru.springframework.spring6reactive.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}
