package kz.clinic.servlets;

import kz.lessons.lesson.Animal;
import kz.lessons.lesson.Dog;
import kz.lessons.lesson.Pet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClinicServlet extends HttpServlet {

    final List<Pet> pets = new CopyOnWriteArrayList<>();
    String petName;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "     <form action='" + request.getContextPath() + "/' method='post'>" +
                        "         Name : <input type='text' name='name'>" +
                        "         Age : <input type='text' name='age'>" +
                        "         <input type='submit' value='add new Pet'>" +
                        "     <form>" +
                        this.viewAllPets() +
                        "<br>" +
                        "<br>" +
                        "     <form action='" + request.getContextPath() + "/' method='post'>" +
                        "         Name : <input type='text' name='find'>" +
                        "         <input type='submit' value='findByPetName'>" +
                        "         Name : <input type='text' name='delete'>" +
                        "         <input type='submit' value='deletePetName'>" +
                        "     <form>" +
                        this.searchPets(petName) +
                        this.removePets(petName) +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("name").isEmpty() && !request.getParameter("age").isEmpty())
        this.pets.add(new Dog(new Animal(request.getParameter("name"), Integer.parseInt(request.getParameter("age")))));
        if (!request.getParameter("find").isEmpty())
        this.petName = request.getParameter("find");
        if (!request.getParameter("name").isEmpty() && !request.getParameter("age").isEmpty())
        this.pets.remove(new Dog(new Animal(request.getParameter("name"), Integer.parseInt(request.getParameter("age")))));
        doGet(request, response);
     }


    private String viewAllPets(){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>All Pets:</p>");
        sb.append("<table>");
        sb.append("<tr><td>").append(" Pet name: </td><td> Pet age: </td></tr>");
        for (Pet pet : this.pets) {
            sb.append("<tr><td>").append(pet.getName()).append(pet.getAge()).append("</td></tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    /**
     * Searching for by pet name
     * @param nameOfPet search pet name
     * @return last pet name
     */
    private String searchPets(String nameOfPet){
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        if(!pets.isEmpty() && nameOfPet != null){
            sb.append("<p>Pets found:</p>");
            sb.append("<table>");
            sb.append("<tr><td>").append(" Pet name: </td><td>  Pet age: </td></tr>");
            for (Pet pet : this.pets){
                if(nameOfPet.equals(pet.getName())){
                    sb.append("<tr><td>").append(pet.getName()).append("</td><td>").append(pet.getAge()).append("</td></tr>");
                    found = true;
                }
            }
        }
        if(!found){
            sb.delete(0, sb.capacity());
            sb.append("<p>Nothing else to find</p>");
        }
        return sb.toString();
    }

    /**
     * Delete by pet name
     * @param nameOfPet name for delete
     */
    private String removePets(String nameOfPet){
        StringBuilder sb = new StringBuilder();
        if(!pets.isEmpty() && nameOfPet != null)
            sb.append("<p>Pets deleted</p>");
        for(Pet pet:this.pets){
            if(nameOfPet != null){
                sb.append("<tr><td>").append(pet.getName()).append("</td><td>").append(pet.getAge()).append("</td></tr>");
                this.pets.remove(pet);
            }
        }
        return sb.toString();
    }
}
