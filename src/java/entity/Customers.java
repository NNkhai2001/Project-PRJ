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
public class Customers {
     private String customerid, companyname, contactname, 
            contacttitle, address, city, region, 
            postalcode, country, phone, fax;

    private String username;
    private String password;
    private int roll;
}
