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
import javax.servlet.ServletException;


/**
 *
 * @author yassine
 */
public class CalculatorService {
    public double calculer(String operationNom, int operande_1, int operande_2) 
        throws ServletException {
        Operation op;

        switch (operationNom) {
            case "add":
                op = new Addition();
                break;
            case "sub":
                op = new Soustraction();
                break;
            case "mul":
                op = new Multiplication();
                break;
            case "div":
                op = new Division();
                break;
            default:
                throw new ServletException("Opération invalide !");
        }

        return op.calculer(operande_1, operande_2);
    }
}

