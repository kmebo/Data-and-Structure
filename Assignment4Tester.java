import java.util.Stack;
public class Assignment4Tester {
    public static void UnblcPrint(Stack<Character> stcValidator)
        throws MyInvalidSintaxException
    {
        if(!stcValidator.isEmpty()){
            throw new MyInvalidSintaxException("Expression is not balanced.");
        }
        else{
            System.out.println("Assignment part I && II is completed.\n\nBeginning "+
                                "Assignment PartIII:");
        }
    }

    public static void PostfixValidation(String args, Stack<Character> stcValidator) 
                    throws MyInvalidSintaxException, MyStackEmptyException
    {
        for (int i = 0; i < args.length(); i++) {
            //In case first character is ')'; provide this message
            if(stcValidator.isEmpty() && args.charAt(i) == ')'){
                throw new MyInvalidSintaxException("Cannot start your expression w/"+
                                                        " a ')'.");
            }
            if(args.charAt(i) == '(')
                stcValidator.push(args.charAt(i));
            if(args.charAt(i) == ')'){
                if(stcValidator.isEmpty()){
                    throw new MyStackEmptyException("Something went wrong. Either your started your"+
                                        " expression with a ')' or stack is empty.");
                }
                stcValidator.pop();
            }
        }
    }

    public static void evaluator(String[] args, Stack<Character> stcValidator)
        throws MyInvalidSintaxException, MyStackEmptyException
    {
        for (int i = 0; i < args.length; i++) {
            if(i == 1)
                throw new MyInvalidSintaxException("Your request contains an space.");
            PostfixValidation(args[i],stcValidator);
        }
    }

    public static int bgin(String[] args, int i){
        String s = args[0];
        for(int j = i; j < s.length()-1; j++){
            if(s.charAt(j) == '(' || s.charAt(j) == ')'){
                break;
            }
            i++;
        }
        return i;
    }

    public static String nodeNames(String[] args, int i)
        throws MyInvalidSintaxException
    {
        String name = "";
        String s = args[0];
        for(int j = i; j < s.length()-1; j++){
            if(s.charAt(j) == '(' || s.charAt(j) == ')'){
                break;
            }
            else if(s.charAt(j) == ' '){
                throw new MyInvalidSintaxException("not use spaces");
            }
            else{
                name += s.charAt(j);
            }
        }
        return name;
    }
    
    public static NodeF1 makeTree(String[] args, NodeF1 root)
        throws MyInvalidSintaxException, MyStackEmptyException
    {
        Stack<NodeF1> stc = new Stack<NodeF1>();
        String s = args[0];//(A(C(k))(n(24)))
        root = new NodeF1(nodeNames(args, 1), null, null);
        int x = (bgin(args, 1));
        NodeF1 dummy = root;
        stc.push(dummy);
        for(int i = x; i < s.length()-1; i++){
            if(s.charAt(i) == ' '){
                throw new MyInvalidSintaxException("no spaces ' ' allowed");
            }
            /* si es '(', then add to left of root */
            if(s.charAt(i) == '('){
                // System.out.println("is stc.Peek().left null: "+stc.peek().left);
                if(stc.peek().left == null){
                    /* first, create that new node */
                        NodeF1 tmp = new NodeF1(nodeNames(args, i+1), null, null);
                    /* then, connect that node to root left */
                        stc.peek().left = tmp;
                        // System.out.println("before pushing into the stack: "+stc.peek().data);
                        stc.push(tmp);
                        // System.out.println("after pushing into the stack: "+stc.peek().data);
                }
                else{
                    // System.out.println("is stc.Peek().left null: "+stc.peek().left.data);
                    NodeF1 tmp = new NodeF1(nodeNames(args, i+1), null, null);
                    stc.peek().right = tmp;
                    // System.out.println("before pushing into the stack: "+stc.peek().data);
                    stc.push(tmp);
                    // System.out.println("after pushing into the stack: "+stc.peek().data);
                }
            }


            /*si es ')', debe de regresar al node que estaba antes */
            if(s.charAt(i) == ')'){
                if(stc.isEmpty()){
                    throw new MyStackEmptyException("Stack is empty.");
                }
                // System.out.println("Before popping the stack: "+stc.peek().data);
                stc.pop();
                // System.out.println("After popping the stack: "+stc.peek().data);
            } 

        }
        // System.out.println();//delete
        root = dummy;
        return root;
    }

    public static NodeF1 makeTree(String[] args)
        throws MyInvalidSintaxException, MyStackEmptyException
    {
        NodeF1 root = new NodeF1();

        Stack<Character> stcValidator = new Stack<Character>();
        evaluator(args, stcValidator);
        UnblcPrint(stcValidator);

        // makeTree(args, root);//@ some point return this function
        // return root;
        
        return makeTree(args, root);
    }
    public static void main(String[] args){
        NodeF1 root;// = new NodeF1();
        try {
            root = makeTree(args);
            // System.out.println(root.data+": "+root);
            // System.out.println(root.left.data+": "+root.left);
            // System.out.println(root.left.left.data+": "+root.left.left);
            // System.out.println(root.left.right.data+": "+root.left.right);

            // "(A(C(k)(2))(n(24)))"
            // System.out.println("Expected A = "+root.data);
            // System.out.println("Expected C = "+root.left.data);
            // System.out.println("Expected k = "+root.left.left.data);
            // System.out.println("Expected 2 = "+root.left.right.data);
            // System.out.println("Expected n = "+root.right.data);
            // System.out.println("Expected 24 = "+root.right.left.data);
            // // "(A(C(k)(2))(n(24)(38)))"
            // System.out.println("Expected 38 = "+root.right.right.data);

            // "(A(C(k)(2))(n()(24)))"
            //trying what happens with (n()(24))
            // System.out.println("Expected A = "+root.data);
            // System.out.println("Expected C = "+root.left.data);
            // System.out.println("Expected k = "+root.left.left.data);
            // System.out.println("Expected 2 = "+root.left.right.data);
            // System.out.println("Expected n = "+root.right.data);
            // System.out.println("Expected - = "+root.right.left.data);
            // System.out.println("Expected 24 = "+root.right.right.data);

            BinaryTreeExpression bte = new BinaryTreeExpression(root);
            System.out.println("1. Heigth of tree = " + bte.getHeight());
            System.out.println("2. Size of tree = " + bte.size());
            System.out.println("3. Parent(s) in tree = " + bte.totalParents());
            bte.getInOrder();
        } catch (MyInvalidSintaxException e1) {
            System.out.println(e1);
        }
        catch(MyStackEmptyException e2){
            System.out.println(e2);
        }
    }
}