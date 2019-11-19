/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah Hadi
 */
import java.util.*;

public class Main {

    public static boolean validate(Stack s) {
        int tutup = 0;
        int buka = 0;
        int jumThen = 0; //yesy
        int jumIf = 0;
        Stack dump = new Stack();
        while (!s.isEmpty()) {
            int k = (int) s.pop();
            int p = 0;
            p++;
            System.out.println("\n"+p+".");
            System.out.println("yg di pop : "+k );
            
            
            if (k == 10) {
                int t = (int) s.peek();
                System.out.println("selanjutnya : "+t);
                System.out.println("=====");
                System.out.println(t);
                System.out.println("=====");
                
                if(t != 1 && t != 10){
                    return false;
                }
                tutup++;
            } else if (k == 9) {
                buka++;
            }else if(k == 8){
                
            }else if(k == 7){
                
            }else if(k == 6){
                int dumped = (int) dump.pop();
                if(dumped != 1 && dumped != 9){
                    return false;
                }
            }else if(k == 5 || k == 4 || k == 3 || k == 2 ){
                
                int t = (int) s.peek();
                System.out.println("selanjutnya : "+t);
                if(t != 1 && t != 10){
                    return false;
                }
            }else if(k == 1){
                int t = (int) s.peek();
                System.out.println("selanjutnya : "+t);
                System.out.println("");
                if(t == 1){
                    return false;
                }
            }
            
            dump.add(k);
        }
        return buka == tutup;
    }

    public static void main(String[] args) {

        Stack s = new Stack();
        Stack n = new Stack();
        String f = "p(p and q)"; //peaknya kosongkan, abis 9 gak boleh langsung 1 distack [PR]
        char[] arChar = f.toCharArray();

        for (int i = arChar.length - 1; i >= 0; i--) {
            s.add(arChar[i]);
        }

        while (!s.isEmpty()) {
            char poped = (char) s.pop();
            
            if (poped == ' ') {
                //do nothing
            } 
            
            else if (poped == 'p' || poped == 'q') {
                n.add(1);
                System.out.print("1");
            } 
            
            else if (poped == 'a') {
                poped = (char) s.pop();
                if (poped == 'n') {
                    poped = (char) s.pop();
                    if (poped == 'd') {
                        n.add(3);
                        System.out.print("3");
                    }
                }
            } 
            
            else if (poped == 'x') {
                poped = (char) s.pop();
                if (poped == 'o') {
                    poped = (char) s.pop();
                    if (poped == 'r') {
                        n.add(5);
                        System.out.print("5");
                    }
                }
            } 
            
            else if (poped == 'n') {
                poped = (char) s.pop();
                if (poped == 'o') {
                    poped = (char) s.pop();
                    if (poped == 't') {
                        n.add(2);
                        System.out.print("2");
                    }
                }
            } 
            
            else if (poped == 'o') {
                poped = (char) s.pop();
                if (poped == 'r') {
                    n.add(4);
                    System.out.print("4");
                }
            } 
            
            else if (poped == 'i') {
                poped = (char) s.pop();
                if (poped == 'f') {
                    n.add(6);
                    System.out.print("6");
                }
            } 
            
            else if (poped == 't') {
                poped = (char) s.pop();
                if (poped == 'h') {
                    poped = (char) s.pop();
                    if (poped == 'e') {
                        poped = (char) s.pop();
                        if (poped == 'n') {
                            n.add(7);
                            System.out.print("7");
                        }
                    }
                }
            } 
            
            else if (poped == 'i') {
                poped = (char) s.pop();
                if (poped == 'f') {
                    poped = (char) s.pop();
                    if (poped == 'f') {
                        n.add(8);
                        System.out.print("8");
                    }
                }
            } 
            
            else if (poped == '(') {
                n.add(9);
                System.out.print("9");

            } 
            
            else if (poped == ')') {
                n.add(10);
                System.out.print("10");

            } 
            
            else {
                n.add(0);
            }

        }

        if (n.contains(0)) {
            System.out.println("NOT VALID");
        } else if (validate(n)) {
            System.out.println("VALID");
        } else {
            System.out.println("NOT VALID");
        }

    }

}
