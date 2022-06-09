package Controller;

import Model.Product;
import sevice.ProductServiceImple;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "Servlet", value = "/products")
public class ProductServlet extends HttpServlet {
    ProductServiceImple productService = new ProductServiceImple();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForce(request, response);
                break;
            case "edit":
                showEditForce(request,response);
                break;
            case "find":
                showfoundList(request,response);
                break;
            default:
                showListPage(request, response);
        }

    }

    private void showfoundList(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("find");
        List<Product> products = this.productService.findAll();
        if (key != null){
            products = Collections.singletonList(this.productService.findByName(key));
        }
        else{
            products = this.productService.findAll();
        }
    }

    private void showEditForce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("fixProduct", product);
        requestDispatcher.forward(request,response);
    }

    private void showCreateForce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/Create.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showListPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String order = request.getParameter("order");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
        List<Product> products = productService.findAll();
        if(order != null ){
            products.sort((o1, o2) -> Integer.compare(o1.getPrice(), o2.getPrice()));
        }
        request.setAttribute("ds", products);
        requestDispatcher.forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                EditProduct(request,response);
                break;
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        String name = request.getParameter("name");
        productService.save(new Product(id, name, price));
        response.sendRedirect("/products");
    }
    private void EditProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        String name = request.getParameter("name");
        productService.update(id, new Product(id, name, price));
        response.sendRedirect("/products");
    }
}
