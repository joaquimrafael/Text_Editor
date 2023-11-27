package apl2;

/*-Editor Simples de Programas-
* Aplicação 2 - Estrutura de Dados I
* Faculdade de Computação e Informática
* Ciência da Computação
* Estrutura de Dados I – 3ª etapa – 2023.2
* Professor André Kishimoto
* 
 * Grupo:
 * Lucas Trebacchetti Eiras - 32236905
 * Joaquim Rafael Mariano Prieto Pereira - 42201731
 * Antonio Carlos Sciamarelli Neto - 42209935 
 * Henrique Arabe Neres de Farias- 42246830
 */

public class Node {

  private String data;
  private int line;
  private Node previous;
  private Node next;


  public Node() {this("", 0, null, null);}

  public Node(String data) {
    this(data, 0, null, null);
  }

  public Node(String data, int line) {
    this(data, line, null, null);
  }

  public Node(String data, int line, Node previous, Node next) {
    this.line = line;
    this.data = data;
    this.previous = previous;
    this.next = next;
  }

  public boolean find(String data) {
    if (this.data == null){ return false; }

    String[]words = this.data.split(" ");

    for(String word : words){
      if(word.equals(data)){return true;}
    }

    return false;
  }

  public String getData() { return data; }

  public void setData(String data) { this.data = data; }

  public int getLine() { return line; }

  public void setLine(int line) { this.line = line; }

  public Node getPrevious() { return previous; }

  public void setPrevious(Node previous) { this.previous = previous; }

  public Node getNext() { return next; }

  public void setNext(Node next) { this.next = next; }

  @Override
  public String toString() {
      String dataString = (data != null) ? data : "null";
      return line + "	"+ dataString ;
  }
}

/*Referências:
*Material de aula:
*Programação de Computadores (versão "Java 101")
*POO - Conceitos básicos, classes e objetos (material do prof. Dr. Ivan Carlos Alcântara de *Oliveira)
*Tipos de dados
*
*Outros Materiais:
*TAD Lista circular e TAD Lista duplamente encadeada
*https://www.devmedia.com.br/leitura-e-escrita-de-arquivos-de-texto-em-java/25529
*http://www.universidadejava.com.br/java/java-leitura-arquivo/
*https://www.devmedia.com.br/usando-generics-em-java/28981
*https://www.devmedia.com.br/trabalhando-com-excecoes-em-java/27601
*ORACLE. Java Documentation. Disponível em: https://docs.oracle.com/en/java/.
*https://www.vivaolinux.com.br/script/Implementacao-de-lista-duplamente-encadeada-orientada-a-objetos/
*https://github.com/lramon2001/EstruturaDeDados1
*https://www.arquivodecodigos.com.br/dicas/3592-java-como-usar-o-metodo-append-para-adicionar-mais-conteudo-ao-final-de-um-stringbuffer.html
*/
