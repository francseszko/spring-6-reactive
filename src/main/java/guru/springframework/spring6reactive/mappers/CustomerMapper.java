package guru.springframework.spring6reactive.mappers;

import guru.springframework.spring6reactive.domain.Customer;
import guru.springframework.spring6reactive.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
