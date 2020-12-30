/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.Date;

/**
 *
 * @author melika
 */
public class Login {    
    static Date lastLoginTime;
    
    String SecurityQuestion;
    String SecurityAnswer;

    public String getSecurityQuestion() {
        return SecurityQuestion;
    }

    public void setSecurityQuestion(String SecurityQuestion) {
        this.SecurityQuestion = SecurityQuestion;
    }

    public String getSecurityAnswer() {
        return SecurityAnswer;
    }

    public void setSecurityAnswer(String SecurityAnswer) {
        this.SecurityAnswer = SecurityAnswer;
    }
    
    public static boolean SignUp(String username, String password){
        if (DBMS.hasEntry(username, password))
            return false;
        else{
            DBMS.addEntry(username, password);
            lastLoginTime = new Date();
            return true;
        }
    }
    
    public static boolean ValidateLogin(String username, String password){
        String pass = DBMS.getPasword(username);
        if (pass != null){
            boolean result = pass.equals(password);
            if(result)
                lastLoginTime = new Date();
            return result;
        }else{
            return false;
        }
    }
}
