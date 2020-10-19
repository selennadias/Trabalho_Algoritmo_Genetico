/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoalgortimogenetico;
import java.util.Vector;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Pc_selenna
 */
public class TrabalhoAlgortimoGenetico {

   private int tamCromossomo=0,numGeracoes,tamPopulacao,
                 probMutacao,qtdeCruzamentos;
    private double capacidade = 0;
    private Vector<Vector> populacao;
    private Vector<Disciplina> disciplinas = new Vector();
    private Vector<Integer> roleta = new Vector();
    //--------------------------------
   
    private void carregaArquivo(){
       String csvFile = "dados.csv";
        String line = "";
        String[] disciplina = null;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                disciplina = line.split(";");
                Disciplina prod = new Disciplina();
                prod.setNome(disciplina[0]);
                prod.setCargaHoraria(Double.parseDouble(disciplina[1]));
                prod.setInteressado(Double.parseDouble(disciplina[2]));
                disciplinas.add(prod);
                prod.show();
                this.tamCromossomo++;
            }// fim percurso no arquivo
            
            System.out.println("Tamanho do cromossomo:"+this.tamCromossomo);
           // this.tamCromossomo = desc_items.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //-------------------------
    private Vector criaCromossomo(){
        Vector cromossomo = new Vector();
        for(int i=0;i<this.tamCromossomo;i++){
            if(Math.random()<0.9)
                 cromossomo.add(0);
            else
                cromossomo.add(1);
        }// fim for
           return cromossomo;
    }
  //---------------------------------- 
    private void criaPopulacao(){
       populacao = new Vector();
       for(int i=0; i<this.tamPopulacao;i++)
           populacao.add(criaCromossomo());
    }
    //----------------------------------
    
      private double fitness(Vector cromossomo) {// Avalaicao
        double carga=0, beneficio=0;
        for(int i=0; i< this.tamCromossomo;i++){
            int leva = (int)cromossomo.get(i);
            if(leva==1){
              
                Disciplina d = new Disciplina();
                d = disciplinas.get(i);
                carga+= d.getCargaHoraria();
                beneficio+= d.getInteressado();

                
            }// fim leva
        }// fim for
         if(carga<=this.capacidade)
             return beneficio;
             
         else
             return 0;
    }
      
      
       //--------------------------------------
    private int torneio(){ // Seleção dos pais Metodo de seleçao
        int s1,s2,s3;
        double notaS1, notaS2, notaS3;
        Random r = new Random();
        s1 = r.nextInt(this.tamPopulacao);
        s2 = r.nextInt(this.tamPopulacao);
        s3 = r.nextInt(this.tamPopulacao);
        notaS1 = fitness(populacao.get(s1));
        notaS2 = fitness(populacao.get(s2));
        notaS3 = fitness(populacao.get(s3));
        if(notaS1 > notaS2 && notaS1 > notaS3)
            return s1;
        else if(notaS2 > notaS1 && notaS2 > notaS3)
            return s2;
        else
            return s3;   
     }
     //---------------
    private Vector cruzamento(){
        Vector filho1 = new Vector(); 
        Vector filho2 = new Vector();
        Vector<Vector>filhos = new Vector();
        Vector p1,p2 = new Vector();
        int ip1, ip2;
        ip1 = torneio();
        ip2 = torneio();
        p1 = populacao.get(ip1);
        p2 = populacao.get(ip2);
        Random r = new Random();
        int pos = r.nextInt(this.tamCromossomo); // ponto de corte
        for(int i=0;i<=pos;i++){
            filho1.add(p1.get(i));
            filho2.add(p2.get(i));
        }
        for(int i=pos+1;i<this.tamCromossomo;i++){
            filho1.add(p2.get(i));
            filho2.add(p1.get(i));
        }
        filhos.add(filho1);
        filhos.add(filho2);
        return filhos;
    }
      //----------------------------------------
     private void mutacao(Vector filho){
       Random r = new Random();
       int v = r.nextInt(100);
       if(v<this.probMutacao){ // probMutação probabilidedade da mutação
           int ponto = r.nextInt(this.tamCromossomo);
           if((int)filho.get(ponto)==1)
               filho.set(ponto,0);
           else
               filho.set(ponto,1);
         System.out.println("Ocorreu mutação!");
       }// fim if mutacao  
     }

    
     //--------------------------------------- 
    
    
    protected int obterPior(){
       int indicePior=0;
         double pior,nota=0;
        pior = fitness((Vector)populacao.get(0));
        for(int i=1;i<this.tamPopulacao;i++){
           nota = fitness((Vector)populacao.get(i));
           if(nota < pior){
               pior = nota;
               indicePior = i;
            }// fim if
        }// fim for
        return indicePior;
    }// fim funcao
    //---------------------------------
     private void novaPopulacao(){
       for(int i=0;i<this.qtdeCruzamentos;i++){
           populacao.remove(obterPior());
           populacao.remove(obterPior());
       }
     }
 //--------------------------------
     private void operadoresGeneticos(){
         Vector f1,f2,filhos = new Vector();
         for(int i=0;i<this.qtdeCruzamentos;i++){
            filhos = cruzamento();
            f1 = (Vector)filhos.get(0);
            f2 = (Vector)filhos.get(1);
            mutacao(f1);
            mutacao(f2);
            populacao.add(f1);
            populacao.add(f2);
         }
     }
   //------------------------------------------------
    protected int obterMelhor(){
         int indiceMelhor=0;
         double melhor,nota=0;
        melhor = fitness((Vector)populacao.get(0));
        for(int i=1;i<this.tamPopulacao;i++){
           nota = fitness((Vector)populacao.get(i));
           if(nota > melhor){
               melhor = nota;
               indiceMelhor = i;
            }
        }
        return indiceMelhor;
    }  
   
   //------------------------------------------  
    public void mostraPopulacao(){
        for(int i=0;i<this.tamPopulacao;i++){
            System.out.println("Cromossomo "+ i + 
                                populacao.get(i));
            System.out.println("Avaliação: "+ 
                          fitness((Vector)populacao.get(i)));
        }// fim for
    }
    //-------------------------
    public void executaAG(){ 
           criaPopulacao();
           // Executando todas gerações
           for(int i=0;i<this.numGeracoes;i++){
            System.out.println("Geração "+ i);
            mostraPopulacao();
            operadoresGeneticos(); // Seleção - Cruzamento - Mutacao - Adicionando na população
            novaPopulacao();
           }
           // Ao final do AG, mostrar o melhor
           
           int ind_melhor = obterMelhor();
           Vector melhor = new Vector();
           melhor = populacao.get(ind_melhor);
           System.out.println("Melhor Solução:"+melhor);
           System.out.println("Avaliação do Melhor:"+fitness(melhor));
           // percorrer a solução e mostrar os produtos
           System.out.println("Carga Hoaria Definidas:");
           for(int i=0;i<this.tamCromossomo;i++){
                int leva = (int)melhor.get(i);
                if(leva==1){
                 Disciplina d = new Disciplina();
                 d=disciplinas.get(i);
                 System.out.println(d.getNome() + 
                         " Carga:"+d.getCargaHoraria()+ " N° Alunos:"+d.getInteressado());
              }// fim if
           }// fim for
    }   // fim executa
    //--------------------------
   
  public TrabalhoAlgortimoGenetico (int numGeracoes,   
         int tamPopulacao,int probMutacao,int qtdeCruzamentos, double capacidade){
         this.numGeracoes = numGeracoes;
         this.tamPopulacao = tamPopulacao;
         this.probMutacao = probMutacao;
         this.qtdeCruzamentos = qtdeCruzamentos;
         this.capacidade = capacidade;
         this.carregaArquivo();
         
    }

}// fim classe
