package Trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrie {

    @Test
    public void constructorTest(){
        //проверка корректной работы конструкторов
        RootNode a = new RootNode(0, true);
        Node b = new Node('a', 1, true, a);
        Node b1 = new Node('a', 1, true, b);
        MyTrie c = new MyTrie();
    }

    @Test
    public void testFind() throws Exception {
        MyTrie myTrie = new MyTrie();
        rndString rnd = new rndString();
        String line = rnd.gen((int) (Math.random() * 50) + 1);
        //проверка на поиск сразу после добавления
        myTrie.add(line);
        Assertions.assertTrue(myTrie.find(line));
        //проверка на корректную работу после удаления записи и добавления зависимых от узла записей большей длины
        myTrie.add(line + "a");
        myTrie.rm(line);
        Assertions.assertFalse(myTrie.find(line));
        Assertions.assertTrue(myTrie.find(line + "a"));
        //проверка 2
        myTrie.add(line + line);
        Assertions.assertFalse(myTrie.find(line));
        //проверка на добавление узла перед проверяемой записью
        myTrie.add(line.substring(0, line.length() / 2));
        Assertions.assertFalse(myTrie.find(line));
        //проверка корректного поиск после всех добавлений
        myTrie.add(line);
        Assertions.assertTrue(myTrie.find(line));
    }

    @Test
    public void testRm() throws Exception {
        MyTrie myTrie = new MyTrie();
        rndString rnd = new rndString();
        String line = rnd.gen((int) (Math.random() * 50) + 1);
        String noLastNode = "Not last node";
        String elemNFound = "Element not found";

        //проверка на удаление несуществующей записи
        this.checkExceptionRm(myTrie, line, elemNFound);

        //проверка на удаление существующей записи
        myTrie.add(line);
        myTrie.rm(line);

        //проверка на повторное удаление
        this.checkExceptionRm(myTrie, line, elemNFound);

        //проверка на удаление записи и наличие зависимых от узла записи
        myTrie.add(line + "a");
        this.checkExceptionRm(myTrie, line, noLastNode);
        myTrie.rm(line + "a");

        //проверка на удаление зависимой записи и сохранение более ранней записи
        myTrie.add(line.substring(0, line.length() / 2));
        this.checkExceptionRm(myTrie, line, elemNFound);

        //проверка на нормальную работу после всех манипуляций
        myTrie.add(line + "a");
        myTrie.add(line);
        myTrie.rm(line);
    }

    @Test
    public void testFindAll() throws Exception {
        MyTrie myTrie = new MyTrie();
        rndString rnd = new rndString();
        ArrayList<String> ls = new ArrayList<>();
        String line = rnd.gen((int) (Math.random() * 50) + 1);

        //проверка на пустой set при отсутствии строк с таким префиксом
        Assertions.assertEquals(ls, myTrie.findAll(line));

        //проверка на вывод всех элементов списка
        for (int i = 0; i < 10; i++) {
            line = rnd.gen((int) (Math.random() * 5) + 1);
            myTrie.add(line);
            ls.add(line);
        }
        Collections.sort(ls);
        Assertions.assertEquals(ls, myTrie.findAll(""));
        ls.clear();

        //поиск единичного элемента
        line = rnd.gen((int) (Math.random() * 50) + 1);
        myTrie.add(line);
        ls.add(line);
        Assertions.assertEquals(ls, myTrie.findAll(line));
        ls.clear();

        //поиск элементов с одинаковым префиксом
        String prefix = rnd.gen((int) (Math.random() * 50) + 1);
        line = rnd.gen((int) (Math.random() * 50) + 1);
        myTrie.add(prefix+line);
        ls.add(prefix+line);

        line = rnd.gen((int) (Math.random() * 50) + 1);
        myTrie.add(prefix+line);
        ls.add(prefix+line);

        Collections.sort(ls);
        Assertions.assertEquals(ls, myTrie.findAll(prefix));

        //поиск после удаления одного элемента
        myTrie.rm(ls.get(0));
        ls.remove(0);
        Assertions.assertEquals(ls, myTrie.findAll(prefix));

        //
        //expected: <[A0Tb8, E, HdtjH, LL, Ocu77, R, RcQ, Wi2o, b3I, tp]>
        // but was: <[A0Tb8, E, HdtjH, LL, Ocu77, RcQ, Wi2o, b3I, tp]>
        myTrie = new MyTrie();
        ls.clear();
        myTrie.add("A0Tb8");
        myTrie.add("E");
        myTrie.add("HdtjH");
        myTrie.add("LL");
        myTrie.add("Ocu77");
        myTrie.add("R");
        myTrie.add("RcQ");
        myTrie.add("Wi2o");
        myTrie.add("b3I");
        myTrie.add("tp");
        ls.add("A0Tb8");
        ls.add("E");
        ls.add("HdtjH");
        ls.add("LL");
        ls.add("Ocu77");
        ls.add("R");
        ls.add("RcQ");
        ls.add("Wi2o");
        ls.add("b3I");
        ls.add("tp");
        ArrayList<String> out = myTrie.findAll("");
        assertEquals(ls, out);
    }


    private void checkExceptionRm(MyTrie myTrie, String line, String err) {
        try {
            myTrie.rm(line);
            throw new Exception("Exception not generated");
        } catch (Exception ex) {
            assertEquals(err, ex.getMessage());
        }
    }
}

class rndString {
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String lower = upper.toLowerCase();

    public static final String digits = "0123456789";

    public static final char[] alphanum = (upper + lower + digits).toCharArray();

    public String gen(int light) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < light; i++) {
            sb.append(alphanum[(int) (Math.random() * alphanum.length)]);
        }
        return sb.toString();
    }
}