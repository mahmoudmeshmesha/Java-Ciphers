/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task;

import java.util.*;

class Basic{ 
  String allChar="ABCDEFGHIJKLMNOPQRSTUVWXYZ";//makes an astring containig all letters
  boolean indexOfChar(char ch)//checks if the input c char is exisiting in allchar array
 {
     for(int i=0;i < allChar.length();i++)
     {
         if(allChar.charAt(i)==ch)
                return true;       
     }
  return false;
 }
}//end of basic
 

class PlayFair{
       Basic b=new Basic();
       char keyMatrix[][]=new char[5][5];  //creates matrix of size 5*5
       boolean repeat(char c)//checks repeated chars or if  j letter exists
       {
             if(!b.indexOfChar(c))//if the coming letter exists return true to repeat function 
                            {
                                return true;
                            }
             
         for(int i=0;i < keyMatrix.length;i++)//checks if letter j already exists 
                {
                    for(int j=0;j < keyMatrix[i].length;j++)
                    { 
                        if(keyMatrix[i][j]==c || c=='J')
                            return true;
                    }
                }
                return false;       
       }
        
       void insertKey(String key)//key insertion
       {
            key=key.toUpperCase();//all to uppercase
            key=key.replaceAll("J", "I");//removes j
            key=key.replaceAll(" ", "");// removes spaces 
            int a=0 /*rows*/,b=0/*columns*/;
             
            for(int k=0;k < key.length();k++)
            {
                    if(!repeat(key.charAt(k)))//if the key's char is not repeated 
                    {
                        keyMatrix[a][b++]=key.charAt(k);//insert key's char in the matrix 
                        if(b>4)// when the coloums ends
                        {
                            b=0;// to begin new colunm
                            a++;// to move the next row
                        }
                    }
            }
            // now adding other chars 
            char p='A';
             
            while(a < 5)//for all rows
            {
                   while(b < 5)// for all coloumns
                   {
                        if(!repeat(p)) //p char is not repeated
                        {
                            keyMatrix[a][b++]=p;//insert the char to the matrix  
                        }
                      p++;// move to the next char 
                   }
                   b=0;//first coloumn
                   a++;// next row 
            }
            
            // after adding all key's chars and other alphabet keys we can view the matrix 
             System.out.print("-------------------------Key Matrix-------------------");
            for(int i=0;i < 5;i++)
            {
                System.out.println();
                for(int j=0;j < 5;j++)
                {
                    System.out.print("\t"+keyMatrix[i][j]);
                }
            }
            System.out.println("\n---------------------------------------------------------");
             
       }
        
       
       int rowPos(char c)// return a char's row postion 
       {
             for(int i=0;i < keyMatrix.length;i++)//loops matrix rows 
                {
                    for(int j=0;j < keyMatrix[i].length;j++)//loops matrix coloumns 
                    { 
                        if(keyMatrix[i][j]==c)// when key is find return its row postion i
                            return i;
                    }
                }
             return -1;// if not found !!
       }
        
       int columnPos(char c)// returns coloumn position  
       {
             for(int i=0;i < keyMatrix.length;i++)
                {
                    for(int j=0;j < keyMatrix[i].length;j++)
                    { 
                        if(keyMatrix[i][j]==c)
                            return j;//coloumn position
                    }
                }
             return -1;
       }
        
       String encryptChar(String plain)//encryption method
       {
           plain=plain.toUpperCase();
           int r1,c1,r2,c2;
           String cipherChar="";
           char a=plain.charAt(0), b=plain.charAt(1); // gets the postion of the char and its next char
           ////gets row and col postion and its following char 
           r1=rowPos(a);
           c1=columnPos(a); 
           r2=rowPos(b); 
           c2=columnPos(b);
         
           if(c1==c2)// if the two chars are the same coloumn
           {
               ++r1;// get the next row and leave the coloumn the same to get the char below of the current char
               ++r2;
               if(r1>4)//if u exceeds the rows go to the first row
                   r1=0;
                
               if(r2>4)// same with the second char 
                   r2=0;
               cipherChar+=keyMatrix[r1][c2];//append new chars to the cipher char
               cipherChar+=keyMatrix[r2][c1];
           }
           else if(r1==r2)// if the they were the same row 
           {    
               ++c1;
               ++c2;
               if(c1>4)
                   c1=0;
                
               if(c2>4)
                   c2=0;
               cipherChar+=keyMatrix[r1][c1];
               cipherChar+=keyMatrix[r2][c2];
                
           }
           else{
               cipherChar+=keyMatrix[r1][c2];
               cipherChar+=keyMatrix[r2][c1];
           }
           return cipherChar;
       }
        
        
        String decryptChar(String cipher)
       {
           
           cipher=cipher.toUpperCase();
           char a=cipher.charAt(0),b=cipher.charAt(1);
           String plainChar="";
           int r1,c1,r2,c2;
           r1=rowPos(a);
           c1=columnPos(a);
           r2=rowPos(b);
           c2=columnPos(b);
         
           if(c1==c2)
           {
                --r1;
               --r2;
               if(r1 < 0)
                   r1=4;
                
               if(r2 < 0)
                   r2=4;
               plainChar+=keyMatrix[r1][c2];
               plainChar+=keyMatrix[r2][c1];
           }
           else if(r1==r2)
           {    
               --c1;
               --c2;
               if(c1 < 0)
                   c1=4;
                
               if(c2 < 0)
                   c2=4;
               plainChar+=keyMatrix[r1][c1];
               plainChar+=keyMatrix[r2][c2];
                
           }
           else{
               plainChar+=keyMatrix[r1][c2];
               plainChar+=keyMatrix[r2][c1];
           }
           return plainChar;
       }
        
        
       String Encrypt(String plainText,String key)
       {
           insertKey(key);
           String cipherText="";
           plainText=plainText.replaceAll("j", "i");
           plainText=plainText.replaceAll(" ", "");
           plainText=plainText.toUpperCase();
           int len=plainText.length();
          //System.out.println(plainText.substring(1,2+1));
           if(len/2!=0)// if the lengh of the plain texts is not divided in two parts add "X" to the plain text
           {
               plainText+="X";
               ++len;
           }
            
           for(int i=0;i < len-1;i=i+2)// to cut down the plaintext to two chars a time untile the end of the plaintext
           {
              cipherText+=encryptChar(plainText.substring(i,i+2));//he ll ox
              cipherText+=" "; 
           }
           return cipherText;
       }
        
        
       
        
        
        
       String Decrypt(String cipherText,String key)
       {
           
           String plainText="";
           cipherText=cipherText.replaceAll("j", "i");
           cipherText=cipherText.replaceAll(" ", "");
           cipherText=cipherText.toUpperCase();
           int len=cipherText.length();
           for(int i=0;i < len-1;i=i+2)
           {
              plainText+=decryptChar(cipherText.substring(i,i+2));
              plainText+=" ";
                
           }
           return plainText;
       }
}
