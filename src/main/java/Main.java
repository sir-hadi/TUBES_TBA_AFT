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
        Stack dump = new Stack();
        Stack ifthen = new Stack();
        Stack kurung = new Stack();

        while (!s.isEmpty()) {
            int k = (int) s.pop();
            //Proses ')'
            if (k == 10) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    if (t != 1 && t != 10) {
                        return false;
                    }
                }
                tutup++;
                kurung.push(10);
            }
            //Proses '('
            else if (k == 9) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    if (t == 1) {
                        return false;
                    }
                }
                buka++;
                if (!kurung.isEmpty()) {
                    kurung.pop();
                }else{
                    return false;
                }
            } 
            //Proses 'then'
            else if (k == 7) {
                if (s.isEmpty()) {
                    return false;
                }
                if (!dump.isEmpty()) {
                    int dumped = (int) dump.pop();
                    int t = (int) s.peek();
                    if (dumped != 1 && dumped != 9 && t != 1 && t != 10) {
                        return false;
                    }
                }
                ifthen.add(7);
            } 
            //Proses 'if'
            else if (k == 6) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    if (!dump.isEmpty()) {
                        int dumped = (int) dump.pop();
                        if (dumped != 1 && dumped != 9) {
                            return false;
                        }
                    }
                }
                if (!ifthen.empty()) {
                    ifthen.pop();
                } else {
                    return false;
                }
            }
            //Proses 'not'
            else if (k == 2) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    int dumped = (int) dump.pop();
                    if (dumped != 1 && dumped != 9 && t != 9) {
                        return false;
                    }
                }
            }
            //Proses 'and','or','xor', dan 'iff'
            else if (k == 5 || k == 4 || k == 3 || k == 8) {
                if (s.empty()) {
                    return false;
                }
                int t = (int) s.peek();
                int dumped = (int) dump.pop();
                if (t != 1 && t != 10 && dumped != 9 && dumped != 1) {
                    return false;
                }
            }
            //Proses 'p','q','r','s'
            else if (k == 1) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    if (t == 1) {
                        return false;
                    }
                }
            }
            dump.add(k);
        }
        return (kurung.isEmpty()) && (ifthen.isEmpty());
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Stack s = new Stack();
        Stack n = new Stack();
        String f = scan.nextLine();
//        f = "if p then p";//nih kenapa??? berati yang belum validasi operator dan not sama if then then if bug, masalahnya di thennya,DARI SI SATU DAN SI KURUNG
//        f = "p xor ((q and not(p and q))";
//        //f = "if (p) then (p) then p";
//        f = "pq";
        char[] arChar = f.toCharArray();

        for (int i = arChar.length - 1; i >= 0; i--) {
            s.add(arChar[i]);
        }

        while (!s.isEmpty()) {
            char poped = (char) s.pop();
            
            if (poped == ' ') {
                //do nothing
            } else if (poped == 'p' || poped == 'q' || poped == 'r' || poped == 's') {
                n.add(1);
            } else if (poped == 'a') {
                poped = (char) s.pop();
                if (poped == 'n') poped = (char) s.pop();
                if (poped == 'd') n.add(3);
                
            } else if (poped == 'x') {
                poped = (char) s.pop();
                if (poped == 'o') poped = (char) s.pop();
                if (poped == 'r') n.add(5);
                
            } else if (poped == 'n') {
                poped = (char) s.pop();
                if (poped == 'o') {
                    poped = (char) s.pop();
                    if (poped == 't') {
                        n.add(2);
                    }
                }
            } else if (poped == 'o') {
                poped = (char) s.pop();
                if (poped == 'r') {
                    n.add(4);
                }
            } else if (poped == 'i') {
                poped = (char) s.pop();
                if (poped == 'f') {
                    n.add(6);
                }
            } else if (poped == 't') {
                poped = (char) s.pop();
                if (poped == 'h') {
                    poped = (char) s.pop();
                    if (poped == 'e') {
                        poped = (char) s.pop();
                        if (poped == 'n') {
                            n.add(7);
                        }
                    }
                }
            } else if (poped == 'i') {
                poped = (char) s.pop();
                if (poped == 'f') {
                    poped = (char) s.pop();
                    if (poped == 'f') {
                        n.add(8);
                    }
                }
            } else if (poped == '(') {
                n.add(9);

            } else if (poped == ')') {
                n.add(10);
            } else {
                n.add(0);
            }
        }
        
        System.out.print("TOKEN : |");
        for (Object i : n) {
            System.out.print(i + "|");
        }
        
        System.out.print("\nRESULT : ");
        if (n.contains(0)) {
            System.out.print("NOT VALID");
        } else if (validate(n)) {
            System.out.print("VALID");
        } else {
            System.out.print("NOT VALID");
        }
    }
}
