package com.amigoscode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customer")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @GetMapping
    public List<Customer> getCustomer(){
        return customerRepository.findAll();
    }

    record  NewCustomerRequest(
            String name,
            String email,
            Integer age
    ){
        
    }
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);

    }

    @DeleteMapping("{customerId}")
    public void  deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }


//    @GetMapping("/greet")
//    public GreetResponse greet(){
//        GreetResponse response = new GreetResponse("Hello",List.of("Java","JavaScript","Python"),new Person("Jhon",34,56750));
//        return response;
//    }
//
//    record Person (String name, int age, double salary){}
//
//    record GreetResponse(String greet,List<String> ProgrammingLanguages,Person person){}

//    class GreetResponse  {
//        private final String greet;
//
//        GreetResponse(String greet) {
//            this.greet = greet;
//        }
//
//       public String getGreet(){
//            return greet;
//       }
//
//        @Override
//        public String toString() {
//            return "GreetResponse{" +
//                    "greet='" + greet + '\'' +
//                    '}';
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            GreetResponse that = (.greet);
//        }GreetResponse) o;
////            return Objects.equals(greet, that
//
//        @Override
//        public int hashCode() {
//            return Objects.hashCode(greet);
//        }
//    }
}
