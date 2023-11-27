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

public class List {
  //variaveis da lista encapsuladas
      private int count;// contador de elementos
      private Node head;// primeiro elemento da lista
      private Node tail;// ultimo elemento da lista

      //Create
      public List() {
        count = 0;
        head = null;
        tail = null;
      }

      //verifica se a lista esta vazia
      public boolean isEmpty() { return head == null; }


      //obtem o nó da linha buscada
      public Node getNode(int line)  {

        Node aux = head;

        do {
          if(aux.getLine() == line) {
            return aux;
          } 
          aux = aux.getNext(); 

        } while(aux != head);

        return null; 
      }

      /*
      public Node getNodeWord(String targetWord) {
        if(head == null) {
          return null;
        }

        Node aux = head;

        do {
          String[] words = aux.getData().split(" ");

          for(String word : words) {
            if(word.equals(targetWord)) {
              return aux;
            }
          }

          aux = aux.getNext();

        }while(aux != null && aux != head);

        return null;
      } */

      //insere um elemento no começo da lista
      public void insert(String str, int line) {
        Node newNode = new Node(str, line);
        if(head == null) {
          head = newNode;
          tail = newNode;
        }else {

          newNode.setNext(head);
          head.setPrevious(newNode);
          head = newNode;
        }
        head.setPrevious(tail);
        tail.setNext(head);
        count++;
      }
      //insere um elemento no final da lista
      public void append(String str, int line) {
        Node node = new Node(str, line);

        // Verifica se a lista está vazia.
        if (head == null) {
          head = node;
        } else {
          tail.setNext(node);
          node.setPrevious(tail);
        }

        tail = node;

        tail.setNext(head);
        head.setPrevious(tail);

        ++count;
      }

      public void insertAfter(String str, int line) {

        if(head == null) {return;}

        Node aux = head;

        do{
          if(aux.getLine() == line) {
            Node newNode = new Node(str);
            newNode.setNext(aux.getNext());
            aux.getNext().setPrevious(newNode);
            aux.setNext(newNode);
            newNode.setPrevious(aux);
            count++;
            correctLine();
            return;
          }
          aux = aux.getNext();
          
        }while(aux != head);

        
      }

      public void insertBefore(String str, int line){
        if(head == null) {return;}

        Node aux = head;

        do{
          if(aux.getLine() == line) {
            Node newNode = new Node(str);
            newNode.setNext(aux);
            aux.getPrevious().setNext(newNode);
            newNode.setPrevious(aux.getPrevious());
            aux.setPrevious(newNode);
            count++;
            correctLine();
            return;
          }
          aux = aux.getNext();
          
        }while(aux != head);

      }
      //remove o primeiro elemento da lista
      public Node removeHead()  {
        if(this.isEmpty()) {return null;}

        Node removed = head;
        head = head.getNext();

        head.setPrevious(tail);
        tail.setNext(head); 

        count--;

        if(count == 0) {
          tail = null; 
          head = null; 
        }


        removed.setNext(null);
        removed.setPrevious(null);

        return removed;
      }
      //remove o ultimo elemento da lista
      public Node removeTail()  {
        if(this.isEmpty()) {return null;}


        Node removed = tail;

        tail = tail.getPrevious();

        tail.setNext(head);
        head.setPrevious(tail);

        count--;

        if(count == 0) {
          head = null;
          tail = null;
        }

        removed.setNext(null);
        removed.setPrevious(null);

        return removed;

      }
      // remove o nó na linha do parametro
      public Node removeNode(int line) {
            if (isEmpty()) {//verifica se esta vazia
                return null;
            }
            //se estiver na primeira chama o metodo removehead()
            if (head.getLine() == line) {
                return this.removeHead();
            }

            if (tail.getLine() == line) {
                return this.removeTail();
            }

            //utiliza 2 variavies do tipo node para percorrer ate a posição do nó desejado e a anterior
            Node removed = head;

            do {
                if (removed.getLine() == line) {

                    removed.getPrevious().setNext(removed.getNext());
                    removed.getNext().setPrevious(removed.getPrevious());

                    count--;

                    removed.setNext(null);
                    removed.setPrevious(null);

                    return removed;

                }
                removed = removed.getNext();
            }while(removed != head);


            return null;// se o parametro passado não corresponde a nenhum elemento da lista, retorna null
        }


      // limpa totalmente da memoria a lista encadeada, apagando os dados dos nodes e suas refencias ao proximo
      public void clear() {
          if (head == null) {
              return;
          }

          Node aux = head;
          do {
              Node next = aux.getNext();
              aux.setNext(null);
              aux.setPrevious(null);
              aux = next;
          } while (aux != head);

          head = null;
          tail = null;
          count = 0;
      }

      // na linguagem Java, destroy tem a mesma função de clear
      public void Destroy() {
          if (head == null) {
              return;
          }

          Node aux = head;
          do {
              Node next = aux.getNext();
              aux.setNext(null);
              aux.setPrevious(null);
              aux = next;
          } while (aux != head);

          head = null;
          tail = null;
          count = 0;
      }
      // retorna o primeiro nó da lista
      public Node getHead(){ return head; }

      // retorna o ultimo nó da lista
      public Node getTail(){ return tail; }

      // retorna o numero de elementos
      public int count() {return this.count;}

      public void correctLine(){
        Node aux = this.head;
        for(int i=1; i <= this.count();i++){
            aux.setLine(i);
            aux = aux.getNext();
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
