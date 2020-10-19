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
public class Disciplina {
    
private String nome;
    private Double CargaHoraria;
    private Double Interessado;

    /**
     * @return the nome
     */
    public String getNome() { //getDescrição
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {  // setDescrição
        this.nome = nome;
    }

    /**
     * @return the CargaHoraria
     */
    public Double getCargaHoraria() {  // getPeso
        return CargaHoraria;
    }

    /**
     * @param CargaHoraria the CargaHoraria to set
     */
    public void setCargaHoraria(Double CargaHoraria) {  // setPeso
        this.CargaHoraria = CargaHoraria;
    }

    /**
     * @return the Interessado
     */
    public Double getInteressado() { //getValor
        return Interessado;
    }

    /**
     * @param Interessado the Interessado to set
     */
    public void setInteressado(Double Interessado) {  // setValor
        this.Interessado = Interessado;
    }
    
   public void show() {
        System.out.println("Disciplina:"+this.getNome()+" | Peso:"+this.getCargaHoraria()+" | Valor:"+this.getInteressado());
    } 
    
}
