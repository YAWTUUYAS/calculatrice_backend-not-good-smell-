package fr.devavance.calculatrice.controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.devavance.calculatrice.Calculator;


import fr.devavance.calculatrice.exceptions.OperatorException;

import java.util.ArrayList;


/**
 *
 * @author B. LEMAIRE
 * Controller pour la calculatrice
 * <p>
 * Ce code utilise volontairement des anti-patterns, il n'a pas un
 * bon "good smell"
 * Ce code doit être refactorisé  pour respecter les
 * principes du "good smell code"
 */


@WebServlet(urlPatterns = {"/calculate/*"})
public class CalculatorController extends HttpServlet {

    private static ArrayList<String> operationsPermises = null;


    @Override
    public void init() throws ServletException {
        super.init();
        this.operationsPermises = new ArrayList<>();

        this.operationsPermises.add("add");
        this.operationsPermises.add("sub");
        this.operationsPermises.add("mul");
        this.operationsPermises.add("div");


    }

    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet responsevoid
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        
        String operation = request.getParameter("operation");
        String operande1 = request.getParameter("operande1");
        String operande2= request.getParameter("operande2");

        if (isInvalidOperator(operation))

            throw new OperatorException();



        // méthode de calcul du resultat
        double resultat = calculerResultat(operande1, operande2, operation);

        

        
        // méthode pour le traitement du resultat
        accessView(operande1, operande2, operation, resultat, response);   
        

    }
    
    public void accessView(String operande1, String operande2, String operation, Double resultat, HttpServletResponse response) 
            throws IOException{
        
        
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {


            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calculator</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div>");
            out.println("<p class=\"operande\">Operande 1 : " + operande1 + "</p>");
            out.println("<p class=\"operande\">Operande 2 : " + operande2 + "</p>");
            out.println("<p class=\"operation\">Operateur : " + operation + "</p>");
            out.println("<p class=\"resultat\">resultat : " + resultat + "</p>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();


        }
    }
    
    public Double calculerResultat(String operande1_s, String operande2_s, String operation) 
            throws ServletException{
        int operande1 = Integer.parseInt(operande1_s);
        int operande2 = Integer.parseInt(operande2_s);
        
        switch (operation) {
            case "add":
                return Calculator.addition(operande1, operande2);
                
            case "sub":
                return Calculator.soustraction(operande1, operande2);
                
            case "div":
                return Calculator.division(operande1, operande2);
                
            case "mul":
                return Calculator.multiplication(operande1, operande2);
                
            default:
                throw new ServletException("Opération invalide !");
        }
    }
    
    public boolean isInvalidOperator(String operation){
        return operation == null
                || operation.isEmpty()
                || !CalculatorController.operationsPermises.contains(operation);
    }
}
     