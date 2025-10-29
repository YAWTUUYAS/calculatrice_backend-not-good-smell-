/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.devavance.service;

import fr.devavance.calculatrice.Addition;
import fr.devavance.calculatrice.Division;
import fr.devavance.calculatrice.Multiplication;
import fr.devavance.calculatrice.Operation;
import fr.devavance.calculatrice.Soustraction;
import fr.devavance.calculatrice.TypeOperation;
import javax.servlet.ServletException;


/**
 *
 * @author yassine
 */
public class CalculatorService {
    public double calculer(String operationNom, int operande_1, int operande_2) 
            throws ServletException {
        TypeOperation type = TypeOperation.fromCode(operationNom);
        Operation op;

        switch (type) {
            case ADD:
                op = new Addition();
                break;
            case SUB:
                op = new Soustraction();
                break;
            case MUL:
                op = new Multiplication();
                break;
            case DIV:
                op = new Division();
                break;
            default:
                throw new ServletException();
        }

        return op.calculer(operande_1, operande_2);
    }
}

