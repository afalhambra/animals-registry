//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2019.11.06 a las 08:38:33 PM CET 
//


package org.mycompany.animals.dogs.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java Enum Class for complex type Dogs.
 * 
 * <p>Below schema fragment specifies the expected content to be for this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dog" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="weight" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                   &lt;element name="breed" type="{}dogsBreedEnum"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author Antonio Fernandez Alhambra
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "dog"
})
@XmlRootElement(name = "dogs")
public class Dogs {

    protected List<Dog> dog;

    /**
     * Gets the value of the dog property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dog property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDog().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dog }
     * 
     * @return List of the dogs
     */
    public List<Dog> getDogs() {
        if (dog == null) {
            dog = new ArrayList<Dog>();
        }
        return this.dog;
    }
}
