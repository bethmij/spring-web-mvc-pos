package lk.ijse.gdse66.spring.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = {"/getAll"},produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO saveCustomer(@RequestPart("id") String id,
                                    @RequestPart("name") String name,
                                    @RequestPart("address") String address,
                                    @RequestPart("salary") String salary
//                                    @RequestPart("profilePic") String profilePic
    ){
//        String base64ProfilePic = Base64.getEncoder().encodeToString(profilePic.getBytes()); //Build Base64 image
//        CustomerDTO customer = new CustomerDTO(id, name, address, salary, base64ProfilePic);
        CustomerDTO customer = new CustomerDTO(id, name, address, salary);
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") String id){
        customerService.deleteCustomer(id);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@Valid @RequestBody CustomerDTO customer){
        customerService.updateCustomer(customer);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDTO GetCustomerDetails(@PathVariable("id") String id){
        return customerService.getCustomerDetails(id);
    }

    @GetMapping(value = {"/getIds"})
    public List<String> GetCustomerIDs(){
        return customerService.GetCustomerIDs();
    }
}
