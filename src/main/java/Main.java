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
        Stack dump = new Stack(); // menyimpan stack s yang telah di pop
        Stack ifthen = new Stack(); // menyimpan then 
        Stack kurung = new Stack(); // menyimpan )

        while (!s.isEmpty()) {
            int poped = (int) s.pop();
            //Proses ')'
            if (poped == 10) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    if (t != 1 && t != 10) {
                        return false;
                    }
                }
                kurung.push(10);
            }
            //Proses '('
            else if (poped == 9) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    if (t == 1) {
                        return false;
                    }
                }
                if (!kurung.isEmpty()) {
                    kurung.pop();
                }else{
                    return false;
                }
            } 
            //Proses 'then'
            else if (poped == 7) {
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
            else if (poped == 6) {
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
            else if (poped == 2) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    int dumped = (int) dump.pop();
                    if (dumped != 1 && dumped != 9 && t != 9) {
                        return false;
                    }
                }
            }
            //Proses 'and','or','xor', dan 'iff'
            else if (poped == 5 || poped == 4 || poped == 3 || poped == 8) {
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
            else if (poped == 1) {
                if (!s.isEmpty()) {
                    int t = (int) s.peek();
                    if (t == 1) {
                        return false;
                    }
                }
            }
            dump.add(poped);
        }
        return (kurung.isEmpty()) && (ifthen.isEmpty());
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        Stack s = new Stack(); // s berati Stack
        Stack n = new Stack(); // n berati Number yang merujuk ke angka nagka token
        System.out.print("Input String : ");
        String f = scan.nextLine();
        //mengubah string menjadi array of char
        char[] arChar = f.toCharArray();
        
        //memasukan char ke dalam stack char
        for (int i = arChar.length - 1; i >= 0; i--) {
            s.add(arChar[i]);
        }

        while (!s.isEmpty()) {
            // nge pop top of stack
            char poped = (char) s.pop();
            
            //membaca spasi
            if (poped == ' ') {
                //do nothing
            } 
            //Membaca operator : p,q,r,s
            else if (poped == 'p' || poped == 'q' || poped == 'r' || poped == 's') {
                n.add(1);
            }
            //Membaca operator : and
            else if (poped == 'a') {
                poped = (char) s.pop();
                if (poped == 'n') poped = (char) s.pop();
                if (poped == 'd') n.add(3);                
            } 
            //membaca operator : xor
            else if (poped == 'x') {
                poped = (char) s.pop();
                if (poped == 'o') poped = (char) s.pop();
                if (poped == 'r') n.add(5);                
            }
            //membaca operator : not
            else if (poped == 'n') {
                poped = (char) s.pop();
                if (poped == 'o') {
                    poped = (char) s.pop();
                    if (poped == 't') {
                        n.add(2);
                    }
                }
            } 
            //membaca operator : or
            else if (poped == 'o') {
                poped = (char) s.pop();
                if (poped == 'r') {
                    n.add(4);
                }
            }
            //membaca operator : if
            else if (poped == 'i') {
                poped = (char) s.pop();
                if (poped == 'f') {
                    n.add(6);
                }
            } 
            //membaca operator : then
            else if (poped == 't') {
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
            } 
            //membaca operator : if
            else if (poped == 'i') {
                poped = (char) s.pop();
                if (poped == 'f') {
                    poped = (char) s.pop();
                    if (poped == 'f') {
                        n.add(8);
                    }
                }
            } 
            //membaca grouping : (
            else if (poped == '(') {
                n.add(9);

            } 
            //membaca grouping : )
            else if (poped == ')') {
                n.add(10);
            } 
            //mengisi 0 ke stack jika semua kondisi tidak ada yang terpilih
            else {
                n.add(0);
            }
        }
        
        //Output Token
        System.out.print("TOKEN : |");
        for (Object i : n) {
            System.out.print(i + "|");
        }
        
        //Output hasil validasi
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
