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
        String s = args[0];
        root = new NodeF1(nodeNames(args, 1), null, null);
        int x = (bgin(args, 1));
        NodeF1 dummy = root;
        stc.push(dummy);
        for(int i = x; i < s.length()-1; i++){
            if(s.charAt(i) == ' '){
                throw new MyInvalidSintaxException("no spaces ' ' allowed");
            }
            /* if '(', then add to left of root */
            if(s.charAt(i) == '('){
                if(stc.peek().left == null){
                    /* first, create that new node */
                        NodeF1 tmp = new NodeF1(nodeNames(args, i+1), null, null);
                    /* then, connect that node to root left */
                        stc.peek().left = tmp;
                        stc.push(tmp);
                }
                else{
                    NodeF1 tmp = new NodeF1(nodeNames(args, i+1), null, null);
                    stc.peek().right = tmp;
                    stc.push(tmp);
                }
            }


            /*si es ')', debe de regresar al node que estaba antes */
            if(s.charAt(i) == ')'){
                if(stc.isEmpty()){
                    throw new MyStackEmptyException("Stack is empty.");
                }
                stc.pop();
            } 

        }
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
        
        return makeTree(args, root);
    }
    public static void main(String[] args){
        NodeF1 root;
        try {
            root = makeTree(args);

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