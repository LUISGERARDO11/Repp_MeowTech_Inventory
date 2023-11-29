package com.example.appsistema.Modelo;

public interface Interface_ModeloR {
    void validarUsuario(String usuario, String QuestionS);
    void consultaUsuario(String usuario, String QuestionS);
    void reestablecerPass(String usuario, String nuevContrasenia);
    void editUser(String usuario, String nuevContrasenia);
}
