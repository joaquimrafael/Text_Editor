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

import java.nio.file.*;
import java.io.*;

public class Archive {

  public static List openArchive(String nameArchive) throws FileNotFoundException, IOException{
      List list = new List();

      String pathToFile = "./" + nameArchive;

      try (FileReader arquive = new FileReader(pathToFile);
           BufferedReader readArq = new BufferedReader(arquive)) {

          String line = readArq.readLine();
          int lineNum = 1;

          while(line != null) {
              list.append(line, lineNum);
              line = readArq.readLine();
              lineNum++;
          }
      } catch (FileNotFoundException e) {
          throw new FileNotFoundException("Arquivo não encontrado!");
      } catch (IOException e) {
          throw new IOException("Erro de E/S ao manipular o arquivo!");
      }

      return list;
  }

  public static List createArchive(String nameArchive){
    List list = new List();
    File dir = new File("./");
    File arq = new File(dir, nameArchive);
    try {
        if (arq.createNewFile()) {
            System.out.println("Arquivo criado: " + arq.getName());
        } else {
            System.out.println("Arquivo já existe!");
        }
    } catch (IOException e) {
        System.out.println("Um erro ocorreu: " + e.getMessage());
        e.printStackTrace();
    }
    return(list);
  }

  public static boolean existArchive(String nameArchive){
    File dir = new File("./");
    File arq = new File(dir, nameArchive);
    return arq.exists();
  }

  public static boolean compareArchive(List list, String nameArchive) throws IOException {
      String pathToFile = "./" + nameArchive;

      try (BufferedReader readArq = new BufferedReader(new FileReader(pathToFile))) {
          String line = readArq.readLine();

          Node aux = list.getHead();

          if (aux == null && line == null) {
              // Se a lista e o arquivo estão vazios, são considerados iguais
              return true;
          } else if (aux == null || line == null) {
              // Se apenas um dos dois está vazio, são considerados diferentes
              return false;
          }

          do {
              if (!line.equals(aux.getData())) {
                  return false;
              }
              line = readArq.readLine();
              aux = aux.getNext();
          } while (aux != list.getHead());

          return(true);
      } catch (FileNotFoundException e) {
          throw new FileNotFoundException("Inicialize um arquivo para realizar as operações!");
      } catch (IOException e) {
          throw new IOException("Erro de E/S ao manipular o arquivo!");
      }
  }



  public static void saveArchive(List list, String nameArquive) throws Exception{
      OutputStream arquive;
  try {
    arquive = new FileOutputStream("./"+nameArquive);
  } catch (FileNotFoundException e) {
    throw new Exception("Erro ao salvar o arquivo!");
  }
      Writer writerArquive = new OutputStreamWriter(arquive);
      BufferedWriter writter = new BufferedWriter(writerArquive);

      writter.write("");
      writter.flush();

      Node aux = list.getHead();
      do{
        writter.write(aux.getData());
        writter.newLine();
        aux = aux.getNext();
      }while(aux != list.getHead());

      writter.close();
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
