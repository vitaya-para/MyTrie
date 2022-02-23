package Trie;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private HashMap<Character, Node> heir;
    private boolean lastNode;
    private Character symbol;

    public Node(Character symbol, boolean lastNode) {
        this.lastNode = lastNode;
        this.symbol = symbol;
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
            Node a = new Node(symbol, false);
            heir.put(symbol, a);
        }
        return heir.get(symbol);
    }

    public ArrayList<String> dfs(StringBuilder prefix) {
        ArrayList<String> out = new ArrayList<>();
        if (this.lastNode)
            out.add(prefix.toString());
        if (heir.isEmpty()) {
            return out;
        }
        for (HashMap.Entry<Character, Node> entry : heir.entrySet()) {
            prefix.append(entry.getKey());
            out.addAll(entry.getValue().dfs(prefix));
            prefix.deleteCharAt(prefix.length() - 1);
        }
        return out;
    }

    public Node find(char[] arr, int pos) {
        if (pos == arr.length) return this;
        Node next = heir.getOrDefault(arr[pos], null);
        return (next == null) ? null : next.find(arr, pos + 1);
    }

    public Ans rm(char[] arr, int pos) {
        if (pos == arr.length) {
            if (!lastNode) return Ans.Fail;
            if (heir.isEmpty())
                return Ans.SuccessesLast;
            else {
                this.lastNode = false;
                return Ans.SuccessesNonLast;
            }
        }
        Node next = heir.getOrDefault(arr[pos], null);
        if (next == null) return Ans.Fail;

        Ans nextStatus = next.rm(arr, pos + 1);
        if (nextStatus == Ans.SuccessesLast) {
            heir.remove(arr[pos]);
            return (heir.isEmpty() && !lastNode) ? Ans.SuccessesLast : Ans.SuccessesNonLast;
        } else {
            return nextStatus;
        }
    }
}

