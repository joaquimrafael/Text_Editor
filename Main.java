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

public class Main {

  public static void main(String[] args) {
	  System.out.println(" <<EDITOR DE TEXTO SIMPLES>> °TM" );
	  Commands comando = new Commands();
	  
	  comando.start();
	  System.out.println("FIM");
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
