package Trie;

public class Node extends RootNode {

    private final char symbol;

    private final RootNode father;

    public Node(char symbol, int level, boolean lastNode, RootNode father) {
        super(level, lastNode);
        this.symbol = symbol;
        this.father = father;
    }

    public void selfRm() throws Exception {
        lastNode = false;
        if (this.heir.isEmpty())
            father.rmStep(symbol);
    }

    @Override
    protected void rmStep(char symbol) throws Exception {
        masterRmStep(symbol);
        this.selfRm();
    }
}