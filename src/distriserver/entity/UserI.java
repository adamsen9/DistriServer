/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriserver.entity;

/**
 *
 * @author FrederikSwag
 */
public interface UserI {
    
    void UserI(String ID);
    
    String getName();
    
    String getId();
    String setID(String newId);
    
    int getPositivePoints();
    int setPositivePoints();
    
    int getNegativePoints();
    int setNegativePoints();

    int addPositivePoints();
    int addNegativePoints();
}
