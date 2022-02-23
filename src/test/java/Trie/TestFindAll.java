package Trie;

import net.moznion.random.string.RandomStringGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class TestFindAll {
    private MyTrie myTrie = null;
    private String line = null;
    private ArrayList<String> ls = null;
    private ArrayList<String> out = null;
    private final RandomStringGenerator generator = new RandomStringGenerator();

    @Before
    public void before() {
        myTrie = new MyTrie();
        ls = new ArrayList<>();
        out = new ArrayList<>();
        generator.setNumOfUpperLimit(50);
        line = generator.generateByRegex("\\w\\w+");
    }

    @After
    public void after() {
        Collections.sort(ls);
        Collections.sort(out);
        Assertions.assertEquals(ls, out);
    }

    @Test
    //проверка на пустой set при отсутствии строк с таким префиксом
    public void emptySet() {
        out = myTrie.findAll(line);
    }

    @Test
    //проверка на вывод всех элементов списка
    public void findAll() {
        for (int i = 0; i < 10; i++) {
            line = generator.generateByRegex("\\w\\w+");
            myTrie.add(line);
            ls.add(line);
        }
        out = myTrie.findAll("");
    }

    @Test
    //поиск единичного элемента
    public void findSingle() {
        myTrie.add(line);
        ls.add(line);
        out = myTrie.findAll(line);
    }

    @Test
    //поиск элементов с одинаковым префиксом
    public void findSamePrefix() {
        String prefix = generator.generateByRegex("\\w\\w+");
        myTrie.add(line);
        myTrie.add(line + prefix);
        ls.add(line);
        ls.add(line + prefix);
        out = myTrie.findAll(line);
    }

    @Test
    //поиск после удаления одного элемента
    public void findAfterRm() {
        this.findSamePrefix();
        myTrie.rm(ls.get(0));
        ls.remove(0);
        out = myTrie.findAll(line);
    }

    @Test
    public void hardTets() {
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
        out = myTrie.findAll("");
    }
}
