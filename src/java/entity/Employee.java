/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@Getter
@Setter
@ToString
public class Employee {
     private int employeeid;//auto number
    private String lastname,
            firstname,
            title,
            titleofcourtesy,
            birthdate, 
            hiredate, 
            address, 
            city,
            region,
            postalcode,
            country,
            homephone,
            extension,
            photo,
            notes;
    private int reportsto;
    private String photopath;
    private String username;
    private String password;
    private int roll;
}
