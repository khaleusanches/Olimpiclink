package com.example.olimpiclink.models

import android.text.Editable

class User(username: String, password: String, email: String) {
    var nome : String = TODO();
    var password : String = TODO();
    var email : String = TODO()

    public fun User(nome : String, password : String, email : String){
        this.nome = nome;
        this.password = password;
        this.email = email;
    }
}
