package com.sahabt.project.exception;

@SuppressWarnings("serial")
public class ProjectAlreadyExistException extends RuntimeException{
    public ProjectAlreadyExistException(){
        super("Project Already Exists!");
    }
}
