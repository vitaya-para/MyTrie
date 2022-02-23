package Trie;

import net.moznion.random.string.RandomStringGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestRm {
    private MyTrie myTrie = null;
    private String line = null;

    @Before
    public void before() {
        myTrie = new MyTrie();
        RandomStringGenerator generator = new RandomStringGenerator();
        generator.setNumOfUpperLimit(50);
        line = generator.generateByRegex("\\w\\w+");
    }

    @Test
    //проверка на удаление несуществующей записи
    public void rmNonExist()
    {
        Assertions.assertFalse(myTrie.rm(line));
    }

    @Test
    //проверка на удаление существующей записи
    public void rmExist()
    {
        myTrie.add(line);
        Assertions.assertTrue(myTrie.rm(line));
    }

    @Test
    //проверка на повторное удаление
    public void rmExistAgain()
    {
        myTrie.add(line);
        Assertions.assertTrue(myTrie.rm(line));
        Assertions.assertFalse(myTrie.rm(line));
    }

    @Test
    public void rmSaveHeirs()
    {
        myTrie.add(line + "a");
        myTrie.add(line);
        Assertions.assertTrue(myTrie.rm(line));
        Assertions.assertFalse(myTrie.rm(line));
        Assertions.assertTrue(myTrie.rm(line + "a"));
    }

    @Test
    public void rmSaveHeirsReverseAdd()
    {
        myTrie.add(line);
        myTrie.add(line + "a");
        Assertions.assertTrue(myTrie.rm(line));
        Assertions.assertFalse(myTrie.rm(line));
        Assertions.assertTrue(myTrie.rm(line + "a"));
    }

    @Test
    public void rmSaveFather()
    {
        myTrie.add(line);
        myTrie.add(line.substring(0, line.length() / 2));
        Assertions.assertTrue(myTrie.find(line.substring(0, line.length() / 2)));

        Assertions.assertTrue(myTrie.rm(line));
        Assertions.assertTrue(myTrie.find(line.substring(0, line.length() / 2)));

        Assertions.assertFalse(myTrie.rm(line));
        Assertions.assertTrue(myTrie.find(line.substring(0, line.length() / 2)));
    }


}
