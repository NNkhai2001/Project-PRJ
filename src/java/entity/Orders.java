/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Orders {
    private int orderid; //auto number
    private String customerid;
    private int employeeid;
    private String orderdate
      ,requireddate
      ,shippeddate;
    private int shipvia;
    private double freight;
    private String shipname
      ,shipaddress
      ,shipcity
      ,shipregion
      ,shippostalcode
      ,shipcountry;
    int status;
    private double total;
}
