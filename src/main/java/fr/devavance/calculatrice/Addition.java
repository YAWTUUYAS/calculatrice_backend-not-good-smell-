/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.calculatrice;

/**
 *
 * @author yassine
 */
public class Addition implements Operation{
    @Override
    public Double calculer(int operande_1, int operande_2){
        return (double) operande_1 + operande_2;
    }
    @Override
    public String getNom(){
        return "add";
    }
}
