package com.mamunrs.servicecore;


import com.mamunrs.servicecore.customer.CustomerController;
import com.mamunrs.servicecore.customer.entity.Customer;
import com.mamunrs.servicecore.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Using plain JUnit 5 (or JUnit 4) with Mockito
 * and independent of the application framework
 */

//@RunWith(MockitoJUnitRunner.class)    // jUnit-4
@ExtendWith(MockitoExtension.class)     // jUnit-5
public class CustomerControllerTests {


    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;


    @Test
    public void shouldReturnListSizeOne() {
        // given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setCode("C0001");
        customer.setName("Ali Kan");
        List<Customer> allCustomer = Arrays.asList(customer);

        // when
        Mockito.when(customerService.getAllCustomer()).thenReturn(allCustomer);

        // then
        ResponseEntity<List<Customer>> response = customerController.getAll();
        List<Customer> listData = response.getBody();
        assert listData != null;
        assertThat(listData.size()).isEqualTo(1);
    }


}
