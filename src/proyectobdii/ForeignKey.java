/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobdii;

import java.util.List;

/**
 *
 * @author santiago
 */
class ForeignKey {
    String name;
    List<Column> columns;
    Table references; 

    public ForeignKey(String name) {
        this.name = name;
    }
    
    
    
}
