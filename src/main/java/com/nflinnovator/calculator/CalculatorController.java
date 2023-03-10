package com.nflinnovator.calculator;

import java.time.Instant;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CalculatorController {

     @Autowired
     private CalculationRepository calculationRepository;

     @Autowired
     private Calculator calculator;

     @RequestMapping("/sum")
     String sum(@RequestParam("a") Integer a,
                @RequestParam("b") Integer b) {
           String result = String.valueOf(calculator.sum(a,b));
           calculationRepository.save(new Calculation(a.toString(),b.toString(), result, Timestamp.from(Instant.now())));
           return result;
     }
}
