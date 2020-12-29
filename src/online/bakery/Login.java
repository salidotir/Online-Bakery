/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.bakery;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author melika
 */
public class Login {
    private Map<String,String> map;  

    public Login() {
        this.map = new HashMap<String,String>(); 
    }
    
    Date lastLoginTime;
    
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
    
    public boolean SignUp(String username, String password){
        if (this.map.containsKey(username))
            return false;
        else{
            this.map.put(username, password);
            return true;
        }
    }
    
    public boolean ValidateLogin(String username, String password){
        String pass = this.map.get(username);
        if (pass != null){
            boolean result = pass.equals(password);
            if(result)
                this.lastLoginTime = new Date();
            return result;
        }else{
            return false;
        }
    }
}
