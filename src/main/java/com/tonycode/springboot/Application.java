package com.tonycode.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.*;
import java.util.Optional;
import java.util.Arrays;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Application {

    private final CustomerRepository customerRepository;

    public Application(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        algorithmsForBubbleSort();
        System.out.println();
        algorithmsForInsertionSort();
        System.out.println();
        algorithmsForSelectionSort();
    }

    public static void algorithmsForBubbleSort() {
        int[] array = new int[]{99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        IntStream stream = Arrays.stream(array);
        stream.forEach(str -> System.out.print(str + " "));
    }

    public static void algorithmsForInsertionSort() {
        int[] array = new int[]{99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        IntStream stream = Arrays.stream(array);
        stream.forEach(str -> System.out.print(str + " "));
    }

    public static void algorithmsForSelectionSort() {
        int[] array = new int[]{99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            int temp = array[i];
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            array[i] = array[min];
            array[min] = temp;
        }

        IntStream stream = Arrays.stream(array);
        stream.forEach(str -> System.out.print(str + " "));
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {
    }

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer id,
                               @RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        customerRepository.save(customer);
    }

    /*@GetMapping("/")
    public GreetResponse greet() {
        return new GreetResponse(
                "Hello World!",
                List.of("Java", "Python"),
                new Person("Tony")
        );
    }

    record Person(String name) {
    }

    record GreetResponse(
            String greet,
            List<String> favProgramingLanguages,
            Person person
    ) {
    }*/

    /*class GreetResponse {
        private final String greet;

        GreetResponse(String greet) {
            this.greet = greet;
        }

        public String getGreet() {
            return greet;
        }

        @Override
        public String toString() {
            return "GreetResponse{" +
                    "greet='" + greet + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GreetResponse that = (GreetResponse) o;
            return Objects.equals(greet, that.greet);
        }

        @Override
        public int hashCode() {
            return Objects.hash(greet);
        }
    }*/
}
