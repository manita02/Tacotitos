/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Objects;

/**
 *
 * @author ana
 */
public class Salsa {
    private int id;
    private String nombre;
    private double precio;

    
    //constructor vacio
    public Salsa() {
    }

    //constructor para agregar
    public Salsa(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    //constructor para actualizar
    public Salsa(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    
    //getters y setters 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Salsa{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Salsa other = (Salsa) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
      
    
}
