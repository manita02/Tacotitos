/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author ana
 */
public class Taco {

    int id;
    double precio;
    List<Tortilla> listaTortillas;
    List<Alimento> listaAlimentos;
    Salsa salsa;

    public Taco() {
    }

    public Taco(double precio) {
    }
 
    //getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Tortilla> getListaTortillas() {
        return listaTortillas;
    }

    public void setListaTortillas(List<Tortilla> listaTortillas) {
        this.listaTortillas = listaTortillas;
    }

    public List<Alimento> getListaAlimentos() {
        return listaAlimentos;
    }

    public void setListaAlimentos(List<Alimento> listaAlimentos) {
        this.listaAlimentos = listaAlimentos;
    }

    public Salsa getSalsa() {
        return salsa;
    }

    public void setSalsa(Salsa salsa) {
        this.salsa = salsa;
    }

    @Override
    public String toString() {
        return "Taco{" + "id=" + id + ", precio=" + precio + ", listaTortillas=" + listaTortillas + ", listaAlimentos=" + listaAlimentos + ", salsa=" + salsa + '}';
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
        final Taco other = (Taco) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.listaTortillas, other.listaTortillas)) {
            return false;
        }
        if (!Objects.equals(this.listaAlimentos, other.listaAlimentos)) {
            return false;
        }
        return Objects.equals(this.salsa, other.salsa);
    }

}
