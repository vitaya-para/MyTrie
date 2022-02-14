package Trie;

import java.util.*;

public class MyTrie {

    private final RootNode root;

    public MyTrie() {
        root = new RootNode(0, false);
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

    public void rm(String line) throws Exception {
        this.myFind(line, true).selfRm();
    }

    public boolean find(String line) {
        try {
            return this.myFind(line, true).getLastNode();
        } catch (Exception e) {
            return line.isEmpty();
        }
    }

    public ArrayList<String> findAll(String prefix) {
        try {
            ArrayList<String> out = this.myFind(prefix, false).dfs(prefix);
            Collections.sort(out);
            return out;
        } catch (Exception e) {
            if (prefix.isEmpty()) {
                ArrayList<String> out = this.root.dfs("");
                Collections.sort(out);
                return out;
            } else
                return new ArrayList<>();
        }
    }

    private Node myFind(String line, boolean checkLastNode) throws Exception {
        if (line.isEmpty()) throw new Exception("Element not found");
        char[] arr = line.toCharArray();
        Node tmp = root.getNextStep(arr[0]);
        for (int i = 1; i < line.length(); i++) {
            tmp = tmp.getNextStep(arr[i]);
        }
        if (checkLastNode && !tmp.getLastNode()) throw new Exception("Not last node");
        return tmp;
    }
}
