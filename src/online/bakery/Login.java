/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author melika
 */
public class Login {   
    
    public static boolean SignUp(String username, String password, Role role){
        if (role == Role.ADMIN){
            return false;
        }
        else if(role == Role.EMPLOEE) {
            if (Admin.getInstance().hasEntry(username, password)){
                return false;
            }
            System.out.print("Hello ");
            System.out.println(username);
            Admin.getInstance().addEntry(username, password);
            return true;
        } else{
            if (Admin.getInstance().hasEntry(username, password)){
                return false;
            }
            else{
                System.out.print("Hello ");
                System.out.println(username);
                System.out.println("Please answer these security questions.");
                Admin.getInstance().addEntry(username, password);
                List<String> answers = new ArrayList<>();
                Admin.getInstance().getQuestions().stream().map((q) -> {
                    Scanner scan = new Scanner(System.in);
                    System.out.printf(q);
                    String ans = scan.nextLine();
                    return ans;
                }).forEachOrdered((ans) -> {
                    answers.add(ans);
                });
                Admin.getInstance().setAnswers(username, answers);
                return true;
            }
        }
    }
    
    public static AbstractMap.SimpleEntry ValidateLogin(String username, String password, Role role){
        if(!Admin.getInstance().hasEntry(username, password) || !Admin.getInstance().hasSalt(username)){
            return new AbstractMap.SimpleEntry(role, null);
        }else{
            boolean res = Admin.getInstance().checkPasword(username , password);
            if(res){
                return Admin.getInstance().findAccountWithRoleAndUsername(role, username);
            }else
                return new AbstractMap.SimpleEntry(role, null);
        }
    }
}
