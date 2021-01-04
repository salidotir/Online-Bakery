/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

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
        }else{
            if (Admin.getInstance().hasEntry(username, password)){
                return false;
            }
            else{
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
    
    public static boolean ValidateLogin(String username, String password, Role role){
        if(!Admin.getInstance().hasEntry(username, password) || !Admin.getInstance().hasSalt(username)){
            return false;
        }else
            return Admin.getInstance().checkPasword(username , password);
    }
}
