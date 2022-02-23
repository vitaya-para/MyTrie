package Trie;

import java.util.*;

public class MyTrie {

    private final Node root;

    public MyTrie() {
        root = new Node(null, false);
    }

    public void add(String line) {
        if (line.isEmpty()) return;
        char[] arr = line.toCharArray();
        Node tmp = root.setNextStep(arr[0]);
        for (int i = 1; i < line.length(); i++) {
            tmp = tmp.setNextStep(arr[i]);
        }
        tmp.setLastNode(true);
    }

    public boolean rm(String line) {
        return this.root.rm(line.toCharArray(), 0) != Ans.Fail;
    }

    public boolean find(String line) {
        Node res = this.root.find(line.toCharArray(), 0);
        return res != null && res.getLastNode();
    }

    public ArrayList<String> findAll(String prefix) {
        Node res = this.root.find(prefix.toCharArray(), 0);
        ArrayList<String> out = new ArrayList<>();
        if (res != null) out = res.dfs(new StringBuilder(prefix));
        return out;
    }
}
