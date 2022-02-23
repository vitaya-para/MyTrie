package Trie;

import net.moznion.random.string.RandomStringGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class TestFind {
    private MyTrie myTrie = null;
    private String line = null;

    @Before
    public void before() {
        myTrie = new MyTrie();
        RandomStringGenerator generator = new RandomStringGenerator();
        generator.setNumOfUpperLimit(50);
        line = generator.generateByRegex("\\w+");
        myTrie.add(line);
    }

    @After
    public void after()
    {
        myTrie.add(line);
        Assertions.assertTrue(myTrie.find(line));
    }

    @Test
    //проверка на поиск сразу после добавления
    public void add() {
        Assertions.assertTrue(myTrie.find(line));
    }

    @Test
    //проверка на корректную работу после удаления записи и добавления зависимых от узла записей большей длины
    public void rmAfter() {
        myTrie.add(line + "a");
        myTrie.rm(line);
        Assertions.assertFalse(myTrie.find(line));
        Assertions.assertTrue(myTrie.find(line + "a"));
    }

    @Test
    //проверка 2
    public void rmAfter2() {
        myTrie.rm(line);
        myTrie.add(line + line);
        Assertions.assertFalse(myTrie.find(line));
    }

    @Test
    //проверка на добавление узла перед проверяемой записью
    public void addBeforeNode() {
        myTrie.rm(line);
        myTrie.add(line.substring(0, line.length() / 2));
        Assertions.assertFalse(myTrie.find(line));
    }

}
