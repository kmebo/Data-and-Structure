public class BinaryTreeExpression{
    NodeF1 root;
    public BinaryTreeExpression(){
        this.root = null;
    }
    public BinaryTreeExpression(NodeF1 root){
        this.root = root;
    }

    public int getHeight(){
        return getHeight(root);
    }
    private int getHeight(NodeF1 r){
        if(r.left == null && r.right == null) return 0;
        else{
            if(r.left != null && r.right == null)
                return 1 + getHeight(r.left);
            else if(r.left == null && r.right != null){
                return 1 + getHeight(r.right);
            }
            else{
                return 1 + getHeightHelper(r);
            }
        }
    }
    private int getHeightHelper(NodeF1 r){
        int lft = 0;
        int rgt = 0;
        lft = getHeight(r.left);
        rgt = getHeight(r.right);
        // if(lft < rgt){
        //     return rgt;
        // }
        // else if(lft > rgt){
        //     return lft;
        // }
        // else{
        //     return lft;//right
        // }
        if(lft <= rgt){
            return rgt;
        }
        return lft;
    }

    public int size(){
        NodeF1 dummy = root;
        return size(dummy);
    }
    private int size(NodeF1 root){
        if(root != null){
            return 1 + size(root.left) + size(root.right);
        }
        return 0;
    }

    public int totalParents(){
        NodeF1 dummy = root;
        return totalParents(dummy);
    }
    private int totalParents(NodeF1 root){
        // if(root.left != null && root.right != null){
        //     return 1 + totalParents(root.left) + totalParents(root.right);
        // }

        // if(root.left != null || root.right != null){
        //     return 1 + totalParents(root.left) + totalParents(root.right);
        // }
        // if((root.left != null && root.right != null) ||
        //     (root.left == null || root.right != null) ||
        //     (root.left != null || root.right == null)){
        //     return 1 + totalParents(root.left) + totalParents(root.right);
        // }
        // else if(root.left != null && root.right == null){
        //     return 1 + totalParents(root.left) + totalParents(root.right);
        // }
        // else if(root.left == null && root.right != null){
        //     return 1 + totalParents(root.left) + totalParents(root.right);
        // }
        // return 0;

        // if(root.left == null && root.right == null) return 0;
        // else{
        //     return 1 + totalParents(root.left) + totalParents(root.right);
        // }
        // if(root.left == null && root.right == null) {
        //     return 0;
        // }
        // return 1 + totalParents(root.left) + totalParents(root.right);

        if(root.left == null && root.right == null) return 0;
        else{
            if(root.left != null && root.right == null){
                return 1 + totalParents(root.left);
            }
            else if(root.left == null && root.right != null){
                return 1 + totalParents(root.right);
            }
            else{
                return 1 + totalParents(root.left) + totalParents(root.right);
            }
        }
    }
    public void getInOrder(){
        NodeF1 dummy = root;
        System.out.print("In order transversal: ");
        getInOrder(dummy);
        System.out.println();
    }
    public void getInOrder(NodeF1 root){
        if(root != null){
            System.out.print(root.data+" ");
            getInOrder(root.left);
            getInOrder(root.right);
        }
    }
}