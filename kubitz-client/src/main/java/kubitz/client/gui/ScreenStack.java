package kubitz.client.gui;

import java.util.Stack;

public class ScreenStack {
    Stack<BaseScreen> stack;

    public ScreenStack(){
        stack = new Stack<>();
    }

    public BaseScreen peek(){
        return stack.peek();
    }

    public BaseScreen pop(){
        BaseScreen pop = stack.pop();

        if(stack.isEmpty()){
            stack.push(new MainMenuScreen(MainFrame.getInstance().getResolution()));
        }

        return pop;
    }

    public void clear(){
        stack.clear();
    }

    public BaseScreen push(BaseScreen screen){
        BaseScreen push = stack.push(screen);
        return push;
    }

    public Stack<BaseScreen> getStack() {
        return stack;
    }
}
