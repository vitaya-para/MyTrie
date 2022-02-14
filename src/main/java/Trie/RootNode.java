package Trie;

import java.util.ArrayList;
import java.util.HashMap;

public class RootNode {
    private final int level;
    protected HashMap<Character, Node> heir;
    protected boolean lastNode;

    public RootNode(int level, boolean lastNode) {
        this.level = level;
        this.lastNode = lastNode;
        this.heir = new HashMap<>();
    }

    public boolean getLastNode() {
        return this.lastNode;
    }

    public void setLastNode(boolean lastNode) {
        this.lastNode = lastNode;
    }

    public Node setNextStep(char symbol) {
        if (!heir.containsKey(symbol)) {
            Node a = new Node(symbol, this.level + 1, false, this);
            heir.put(symbol, a);
        }
        return heir.get(symbol);
    }

    public Node getNextStep(char symbol) throws Exception {
        if (heir.containsKey(symbol)) {
            return heir.get(symbol);
        } else {
            throw new Exception("Element not found");
        }
    }

    public ArrayList<String> dfs(String prefix) {
        ArrayList<String> out = new ArrayList<>();
        if (this.lastNode)
            out.add(prefix);
        if (heir.isEmpty()) {
            return out;
        }
        for (HashMap.Entry<Character, Node> entry : heir.entrySet()) {
            String newPrefix = prefix + entry.getKey();
            out.addAll(entry.getValue().dfs(newPrefix));
        }
        return out;
    }

    protected void masterRmStep(char symbol) throws Exception {
        if (heir.containsKey(symbol)) {
            heir.remove(symbol);
        } else {
            throw new Exception("Element not found");
        }
    }

    protected void rmStep(char symbol) throws Exception {
        this.masterRmStep(symbol);
    }
}
