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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Commands {
  private List lines = new List();
  private String arquiveName = null;
  private int[] clipBoard = new int[2];
  private List clipBoardList = new List();

  public Commands() {
      clipBoard[0] = -1;
      clipBoard[1] = -1;
    }

  public void start(){
	  String[] optionArray;
	  String option;
	  Scanner entry = new Scanner(System.in);
	    do{
	      System.out.print(">");
	      option = entry.nextLine();
	      optionArray = option.split(" ");
	      this.commandsOptions(optionArray);

	    }while(!optionArray[0].equals(":q!"));
	    entry.close();
	  }

  public void commandsOptions(String[] option){

    switch(option[0]){
      case ":e":
        if(option.length > 1 && Archive.existArchive(option[1])){
          try {
            lines = Archive.openArchive(option[1]);
            System.out.println("Arquivo aberto com sucesso!");
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
          this.arquiveName = option[1];
        }else if(option.length > 1 && !Archive.existArchive(option[1])){
          Archive.createArchive(option[1]);
          this.arquiveName = option[1];
        }else {
          System.out.println("Digite o comando no formato ':e nomearquivo.txt'");
        }


      break;

      case ":w":
        if(option.length > 1){
          try {
            if(Archive.existArchive(option[1])){
              Archive.saveArchive(lines, option[1]);
              System.out.println("Arquivo salvo com sucesso!");
            }else{
              Archive.createArchive(option[1]);
              Archive.saveArchive(lines, option[1]);
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }else{
          if(arquiveName!=null){ try {
            Archive.saveArchive(lines, arquiveName);
            System.out.println("Arquivo salvo com sucesso!");
          } catch (Exception e) {
            e.printStackTrace();
          } }else{
            System.out.println("Nenhum arquivo aberto atualmente! Use ':e Nomedoarquivo.txt'");
          }
        }
        break;

        case ":q!":
          Scanner input = new Scanner(System.in);

          try {
      if(!Archive.compareArchive(lines,this.arquiveName)) {

        System.out.println("Deseja salvar as modificações? (S/N): ");

          String choice = input.nextLine();
          choice = choice.toUpperCase();
          choice = choice.strip();

          if(choice.equals("S")){
            if(arquiveName!=null){ 
              try {
              Archive.saveArchive(lines, arquiveName);
              System.out.println("Arquivo salvo com sucesso!");
            } catch (Exception e) {
              Archive.createArchive(arquiveName);
              try {
                Archive.saveArchive(lines, arquiveName);
              } catch (Exception e1) {
                e1.printStackTrace();
              }
              System.out.println("Arquivo salvo em novo arquivo .txt com sucesso!");
              }
            }else{
              System.out.println("Nenhum arquivo aberto atualmente!");
            }
          }
          break;
    }
      System.out.println("Arquivo sem alterações a serem salvas!");
       }catch (IOException e) {
      e.printStackTrace();
    }

      break;

      case ":v":
        if(option.length == 3){
          if(Integer.parseInt(option[1]) > 0 && Integer.parseInt(option[2]) > 0 && Integer.parseInt(option[2])<=lines.count() && Integer.parseInt(option[1])<lines.count() && Integer.parseInt(option[1])<=Integer.parseInt(option[2])){
            clipBoard[0] = Integer.parseInt(option[1]);
            clipBoard[1] = Integer.parseInt(option[2]);
            System.out.println("Intervalo ("+clipBoard[0]+","+clipBoard[1]+") salvo na área de transferência!");
          }else{
            System.out.println("Digite um intervalo válido!");
          }
        }else{
          System.out.println("Digite no formato ':v LinIni LinFim'");
        }
        break;

        case ":y":
            if(clipBoard[0] != -1 && clipBoard[1] != -1){
              this.clipBoardList.clear();
              int count=1;
              Node aux = lines.getNode(clipBoard[0]);
              Node aux2 = lines.getNode(clipBoard[1]);
              while(aux!=aux2.getNext()){
                this.clipBoardList.append(aux.getData(),count);
                aux = aux.getNext();
                count++;
              }
              
              Node clipaux = clipBoardList.getHead();
              do {
            	  System.out.println(clipaux);
            	  clipaux = clipaux.getNext();
            	  
              }while(clipaux != clipBoardList.getHead());
              
              
              System.out.println("Intervalo copiado com sucesso!");
            }else{
              System.out.println("Marcação para a lista de transferência vazia!");
            }
        break;

        case ":c":
          if(clipBoard[0] != -1 && clipBoard[1] != -1){
            this.clipBoardList.clear();
            int count=1;
            Node aux = lines.getNode(clipBoard[0]);
            Node aux2 = lines.getNode(clipBoard[1]);
            int line = clipBoard[0];
            do{
              this.clipBoardList.append(aux.getData(),count);
              aux = aux.getNext();
              lines.removeNode(line);
              line++;
              count++;
            }while(aux!=aux2);
            
            this.clipBoardList.append(aux2.getData(), clipBoard[1]);
            lines.removeNode(clipBoard[1]);
            
            aux = clipBoardList.getHead();
            do {
            	System.out.println(aux);
            	aux = aux.getNext();
            	
            }while(aux != clipBoardList.getHead());
            
            lines.correctLine();
            System.out.println("Intervalo recortado com sucesso!");
          }else{
            System.out.println("Marcação para a lista de transferência vazia!");
          }
        break;

        case ":p":
            if(option.length == 2){
              if(this.clipBoardList.count()>0){
              int choosen = Integer.parseInt(option[1]);
                Node clip = clipBoardList.getHead();

                do{
                  lines.insertAfter(clip.getData(), choosen);
                  clip = clip.getNext();
                  choosen++;
                }while(clip != clipBoardList.getHead());

                lines.correctLine();

                System.out.println("Conteúdo da área de transferência colado com sucesso!");
              }else{
                System.out.println("Área de transferência vazia!");
              }
            }else{
              System.out.println("Digite no formato ':p LinIni'");
            }

          break;

      case ":s":
      if(lines.getHead() == null) { System.out.println("Lista vazia!"); break; }
        if(option.length == 1){
          int countLines = 0;
          Node aux = lines.getHead();
          do{
            System.out.println(aux);
            aux = aux.getNext();
            countLines++;
            if(countLines%20==0){
              System.out.println("Gostaria de continuar imprimindo as próximas 20 linhas? [S/N]");
              Scanner leitura = new Scanner(System.in);
              String op = leitura.nextLine();
              //leitura.close();
              op = op.toUpperCase();
              if(!op.equals("S")){
                break;
              }
            }
          }while(aux != lines.getHead());

        }else if(option.length==3){
          //option[1] option[2]
          int countLines = Integer.parseInt(option[1]);
          int finalLinhas = Integer.parseInt(option[2]);
          Node aux = lines.getHead();
          do{
            System.out.println(aux);
            aux = aux.getNext();
            countLines++;
            if(countLines%20==0){
              System.out.println("Gostaria de continuar imprimindo as próximas 20 linhas? [S/N]");
              Scanner entry = new Scanner(System.in);
              String escolha = entry.nextLine();
              entry.close();
              escolha = escolha.toUpperCase();
              if(!escolha.equals("S")){
                break;
              }
            }
        }while(countLines != finalLinhas);
        }else{
          System.out.println("Use o comando no formato: ':s LinIni LinFim'");
        }

        break;

      case ":x":
        if(option.length == 2){
          int linha = Integer.parseInt(option[1]);
          lines.removeNode(linha);
          System.out.println("Linha "+ linha +" removida com sucesso!");

          lines.correctLine();

        }else{
          System.out.println("Use o comando no formato: ':x Lin'");
        }

        break;

      case ":xG":
        if(option.length == 2){
          int linha = Integer.parseInt(option[1]);
          System.out.println("Linha sucessoras a "+ linha + " removidas com sucesso!");
          Node aux = lines.getNode(linha);
          do{
            aux = aux.getNext();
            lines.removeNode(linha);
            linha++;
          }while(aux != lines.getHead());

          lines.correctLine();

        }else{
          System.out.println("Use o comando no formato: ':xG Lin'");
        }

        break;

      case ":XG":
        if(option.length == 2){
          int linha = Integer.parseInt(option[1]);
          System.out.println("Linha antecessoras a " + linha + " removidas com sucesso!");
          Node aux = lines.getNode(linha);
          do{
            aux = aux.getPrevious();
            lines.removeNode(linha);
            linha--;
          }while(aux != lines.getTail());
          

          lines.correctLine();

        }else{
          System.out.println("Use o comando no formato: ':xG Lin'");
        }

        break;

      case ":/":
        if(option.length == 1 || option.length > 4){
            System.out.println("Use o comando no formato ':/ Elem ElemTroca [Linha]'");
        }else if(option.length == 2){
            Node aux = lines.getHead();
            do{
              if(aux.find(option[1])){
                System.out.println(aux);
              }

              aux = aux.getNext();

            }while(aux != lines.getHead());

        }else if(option.length == 3){
            Node aux = lines.getHead();
            do{
              if(aux.find(option[1])){
                aux.setData(aux.getData().replaceAll(option[1], option[2]));
                
              }
              aux = aux.getNext();
            }while(aux != lines.getHead());
            
            System.out.println("Todas as ocorrências de "+option[1]+" substituidas por "+option[2]);
        }
        else if(option.length == 4){
            int myline = Integer.parseInt(option[3]);
            Node aux = lines.getNode(myline);
            if(aux.find(option[1])){
              aux.setData(aux.getData().replaceAll(option[1], option[2]));
              System.out.println("Todas as ocorrências de "+option[1]+" na linha "+ myline +" substituidas por "+option[2]);
            }else{
              System.out.println("Elemento não encontrado na linha digitada!");
            }
        }

        break;

      case ":a":
        if(option.length == 2){
          int linha = Integer.parseInt(option[1]);
          if(linha>lines.count() || linha<0){ System.out.println("Linha inválida!"); break; }
          Scanner read = new Scanner(System.in);
          String write = "";
          int count;
          if(linha == 0 && lines.getHead()==null){
            count = 1;
            while(!write.equals(":a")){
              write = read.nextLine();
              if(!write.equals(":a")) {
              lines.append(write,count);
              count++;
              }
            }
          }else{
            count = linha + 1;
            while(!write.equals(":a")){
              write = read.nextLine();
              if(!write.equals(":a")) {
              lines.insertAfter(write,count);
              count++;
              }
            }
          }
          //read.close();
          lines.correctLine();
        }else{
          System.out.println("Use o comando no formato ':a Linha' Digite 0 se arquivo vazio!");
        }
        break;

      case ":i":
        if(option.length == 2){
          int linha = Integer.parseInt(option[1]);
          if(linha>lines.count() || linha <= 0){ System.out.println("Linha inválida!"); break; }
          Scanner read = new Scanner(System.in);
          String write = "";
          int count;
          count = linha;
          while(!write.equals(":i")){
            write = read.nextLine();
            if(!write.equals(":i")) {
            lines.insertBefore(write,count);
            count++;
            }
          }
          //read.close();
          lines.correctLine();
        }else{
          System.out.println("Use o comando no formato ':i Linha'");
        }

        break;

      case ":help":
        System.out.println("HELP:");
        System.out.println("Operações -> Ação resultante\r\n\n"
              + ":e NomeArq.ext = Abrir o arquivo de nome NomeArq.ext e armazenar cada linha em um nó da lista.\r\n\n"
              + ":w = Salvar a lista no arquivo atualmente aberto.\r\n\n"
              + ":w NomeArq.ext = Salvar a lista no arquivo de nome NomeArq.ext.\r\n\n"
              + ":q! = Encerrar o editor. Caso existam modificações não salvas na lista, o programa deve solicitar confirmação se a pessoa usuária do "
              + "editor deseja salvar as alterações em arquivo antes de encerrar o "
              + "editor.\r\n\n"
              + ":v LinIni LinFim = Marcar um texto da lista (para cópia ou recorte – “área de"
              + "transferência”) da LinIni até LinFim. Deve ser verificado se o intervalo [LinIni, LinFim] é válido.\r\n\n"
              + ":y = Copiar o texto marcado (ver comando anterior) para uma lista "
              + "usada como área de transferência.\r\n\n"
              + ":c = Recortar o texto marcado para a lista de área de transferência.\r\n\n"
              + ":p LinIniColar = Colar o conteúdo da área de transferência na lista, a partir da linha "
              + "indicada em LinIniColar. Deve ser verificado se LinIniColar é"
              + "válido.\r\n\n"
              + ":s = Exibir em tela o conteúdo completo do código-fonte que consta na "
              + "lista, de 20 em 20 linhas.\r\n\n"
              + ":s  LinIni LinFim = Exibir na tela o conteúdo do código-fonte que consta na lista, da "
              + "linha inicial LinIni até a linha final LinFim, de 20 em 20 linhas.\r\n\n"
              + ":x Lin = Apagar a linha de posição Lin da lista.\r\n\n"
              + ":xG Lin = Apagar o conteúdo a partir da linha Lin até o final da lista.\r\n\n"
              + ":XG Lin = Apagar o conteúdo da linha Lin até o início da lista.\r\n\n"
              + ":/ = Elemento Percorrer a lista, localizar as linhas que contém Elemento e exibir "
              + "o conteúdo das linhas por completo.\r\n\n"
              + ":/ Elem ElemTroca = Percorrer a lista e realizar a troca de Elem por ElemTroca em todas "
              + "as linhas do código-fonte.\r\n\n"
              + ":/ Elem ElemTroca = Linha Realizar a troca de Elem por ElemTroca na linha Linha do códigofonte.\r\n\n"
              + ":a PosLin = Permitir a inserção de uma ou mais linhas e inserir na lista depois "
              + "da posição PosLin. O término da entrada do novo conteúdo é "
              + "dado por um :a em uma linha vazia.\r\n\n"
              + ":i PosLin Permitir = a inserção de uma ou mais linhas e inserir na lista antes "
              + "da posição PosLin. O término da entrada do novo conteúdo é "
              + "dado por um :i em uma linha vazia.\r\n\n"
              + ":help = Apresentar na tela todas as operações permitidas no editor. ");
          System.out.println();
        break;

        default:
        System.out.println("Comando Inválido!");
        break;
    }
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