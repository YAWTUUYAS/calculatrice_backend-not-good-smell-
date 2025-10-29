/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.calculatrice;

/**
 *
 * @author yassine
 */
public class Division implements Operation{
    @Override
    public Double calculer(int operande_1, int operande_2) {
        if (operande_2 == 0) throw new ArithmeticException();
        return (double) operande_1 / operande_2;
    }
    @Override
    public String getNom() { return "div"; }
}
