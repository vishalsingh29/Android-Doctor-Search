package com.example.vishalsingh.practosearch;

public class Doctor {
    private String name;
    private String email;
    private String education;
    private int experience_in_years;
    private int recommendations;
    private String description;
    private String area;
    private String city;

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicName() {
        return clinicName;
    }

    private String speciality;
    private String id;
    private String phoneNumber;
    private String specialization;
    private String clinicName;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setExperience_in_years(int experience_in_years) {
        this.experience_in_years = experience_in_years;
    }

    public void setRecommendations(int recommendations) {
        this.recommendations = recommendations;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) { this.name = name; }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return email;
    }

    public String getEducation() {
        return education;
    }

    public int getExperience_in_years() {
        return experience_in_years;
    }

    public int getRecommendations() {
        return recommendations;
    }

    public String getDescription() {
        return description;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String getId() {

        return id;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
