package fr.devavance.calculatrice.controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.devavance.calculatrice.exceptions.OperatorException;
import fr.devavance.service.CalculatorService;

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

    private CalculatorService service;


    @Override
    public void init() throws ServletException {
        super.init();
        this.service = new CalculatorService();
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
        
            // 1. Récupération des paramètres
            String operation = request.getParameter("operation"); 
            String operande1_s = request.getParameter("operande1"); 
            String operande2_s = request.getParameter("operande2");
            
            // 2. Validation
            validerOperation(operation);

            // 3. Conversion
            int operande1 = parseOperande(operande1_s);
            int operande2 = parseOperande(operande2_s);

            // 4. Calcul
            double resultat = calculerResultat(operation, operande1, operande2);

            // 5. Affichage
            accessView(operande1_s, operande2_s, operation, resultat, response);
            
    }
    
    private void validerOperation(String operation) {
        if (operation == null || operation.isEmpty()) {
            throw new OperatorException();
        }
    }
    
    private int parseOperande(String value) {
        return Integer.parseInt(value);
    }
    
    public void accessView(String operande1_s, String operande2_s, String operation, Double resultat, HttpServletResponse response) 
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
            out.println("<p class=\"operande\">Operande 1 : " + operande1_s + "</p>");
            out.println("<p class=\"operande\">Operande 2 : " + operande2_s + "</p>");
            out.println("<p class=\"operation\">Operateur : " + operation + "</p>");
            out.println("<p class=\"resultat\">resultat : " + resultat + "</p>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();


        }
    }
    
    private double calculerResultat(String operation, int operande_1, int operande_2) 
            throws ServletException {
        return service.calculer(operation, operande_1, operande_2);
    }
}