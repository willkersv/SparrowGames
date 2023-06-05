package controller;

import java.util.Random;

public class ControlKey {
    
    public String gerarKey(){
        String alphaNum = "ABCDEFGHIJKLMNOIPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String key = "";

        for(int i = 0; i < 15; i++){
	        int index = random.nextInt(alphaNum.length());
	        char randomChar = alphaNum.charAt(index);
	        sb.append(randomChar);
	        if(i==4 || i==9) {
	        	sb.append("-");
	        }
        }
        key = sb.toString();
        System.out.println("Key de ativacao do jogo: " + key);
        return key;
    }
}
