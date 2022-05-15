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
public class Products {

    private int productid;//auto number
    private String productname;
    private int supplierid, categoryid;
    private String quantityperunit;
    private double unitprice;
    private int unitsinstock, unitsonorder, reorderlevel, discontinued;
    private String picture;

}
