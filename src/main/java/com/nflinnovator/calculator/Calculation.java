package com.nflinnovator.calculator;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calculation {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Integer id;
private String a;
private String b;
private String result;
private Timestamp createdAt;
private String sum;

protected Calculation() {}

public Calculation(String a, String b, String sum,Timestamp createdAt){

   this.a = a;
   this.b = b;
   this.sum = sum;
   this.createdAt = createdAt;
}

   public String getSum() {
     return sum;
  }

}
