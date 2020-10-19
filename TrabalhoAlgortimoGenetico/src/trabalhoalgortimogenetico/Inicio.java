/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoalgortimogenetico;

/**
 *
 * @author Pc_selenna
 */
public class Inicio {
    
    public static void main(String[] args) {
        int numGeracoes=100, tamPopulacao=100, 
        probMutacao=20, 
        qtdeCruzamentos=10; 
        double capacidade=16;
        
        TrabalhoAlgortimoGenetico tag =
           new TrabalhoAlgortimoGenetico(numGeracoes,tamPopulacao,
                               probMutacao,qtdeCruzamentos,
                               capacidade);
        
       tag.executaAG();
        
    }
    
   
    
}
