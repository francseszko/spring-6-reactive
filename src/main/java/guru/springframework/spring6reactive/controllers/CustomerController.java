package guru.springframework.spring6reactive.controllers;

import com.sun.jdi.VoidType;
import guru.springframework.spring6reactive.model.CustomerDTO;
import guru.springframework.spring6reactive.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v2/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH)
    Flux<CustomerDTO> listCustomers() {
        return customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    Mono<CustomerDTO> getCustomerById(@PathVariable("customerId") Integer id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(CUSTOMER_PATH)
    Mono<ResponseEntity<VoidType>> createNewCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        return  customerService.saveCustomer(customerDTO)
                .map(savedDto -> ResponseEntity.created(URI.create(CUSTOMER_PATH + savedDto.getId()))
                        .build());

    }

    @PutMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<VoidType>> updateCustomer(@PathVariable("customerId") Integer customerId,
                                                  @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerId, customerDTO)
                .map(updatedDto -> ResponseEntity.noContent().build());
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<VoidType>> patchCustomer(@PathVariable("customerId") Integer customerId,
                                                 @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(customerId, customerDTO)
                .map(patchedDto -> ResponseEntity.ok().build());
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<VoidType>> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        return customerService.deleteCustomer(customerId).thenReturn(ResponseEntity.noContent().build());
    }
}
