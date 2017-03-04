package edu.cmu.mis.iccfb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
        private long id;
        private String name;
        private String description;
        private double ratings;

        public Movie(Long id,String name, String description, double ratings) {
        super();
            this.id = id;
        this.name = name;
        this.description = description;
        this.ratings = ratings;
        }
        public Movie(){

        }
        public long getId() {
        return id;
        }
        public void setId(long id) {
        this.id = id;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public double getRatings() {
            return ratings;
        }
        public void setRatings(double ratings) {
            this.ratings = ratings;
        }
        @Override
        public String toString() {
            return "Movie [name=" + name + ", description=" + description + ", ratings=" + ratings + "]";
        }


    }


