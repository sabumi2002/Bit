package day0118;

public class Ex01BinaryTree {
    static int[] array = {3, 7, 1, 2, 4, 0};
    static int currentIndex = 0;

    public static void main(String[] args) {
        Node zero = new Node();
        zero.value = array[currentIndex++];
        for(int i = 1; i< array.length; i++){
            insert(zero, array[i]);
        }
        TreeSet treeSet = new TreeSet();
        treeSet.add(9);
        treeSet.add(8);
        treeSet.add(1);
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(2);



//        System.out.println(zero.value);
//        System.out.println(zero.left.value);
//        System.out.println(zero.right.value);
//        System.out.println(zero.left.left.value);
//        System.out.println(zero.left.right.value);
//        System.out.println(zero.right.left.value);
    }

    public static void insert(Node parent, int value) {
        if (parent.value < value) {   // 오른쪽
            if (parent.right == null) {
                parent.right = createNode(value);
            } else {
                insert(parent.right, value);
            }
        } else {
            if (parent.left == null){
                parent.left = createNode(value);
            }else{
                insert(parent.left, value);
            }
        }
    }

    public static Node createNode(int value) {
        Node n = new Node();
        n.value = value;

        return n;
    }
}
