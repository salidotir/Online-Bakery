/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author melika
 */
public class Login {    
    static Date lastLoginTime;
    static boolean isLogedIn;

    public static boolean SignUp(String username, String password, Role role){
        if (Admin.getInstance().hasEntry(username, password))
            return false;
        else{
            Admin.getInstance().addEntry(username, password);
            lastLoginTime = new Date();
            List<String> answers = new ArrayList<String>();
            for (String q : Admin.getInstance().getQuestions()) {
                Scanner scan = new Scanner(System.in);
                System.out.printf(q);
                String ans = scan.nextLine();
                answers.add(ans);
            }
            Admin.getInstance().setAnswers(username, answers);
            return true;
        }
    }
    
    public static boolean ValidateLogin(String username, String password, Role role){
        if(!Admin.getInstance().hasEntry(username, password) || !Admin.getInstance().hasSalt(username)){
            return false;
        }
       
        boolean result = Admin.getInstance().checkPasword(username , password);
        if(result){
            lastLoginTime = new Date();
            isLogedIn = true;
            return result;
        }else
            return false;
    }
    
    public static void Logout(){
        isLogedIn = false;
    }
}
