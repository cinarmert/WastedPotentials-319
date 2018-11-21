package kubitz.client.main;

import kubitz.client.gui.MainFrame;
import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;

public class Runner {

    public static void main(String[] args){
//        new MainFrame();
        RESTRequestManager.login(new Account(45, "mert2cli"));
    }

}
