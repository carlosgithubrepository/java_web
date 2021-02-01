//clss
 Autenticacao = function(){
    //metodo
    this.validarCampos = function(){
        ele = document.getElementById("txtUsuario");
        sen = document.getElementById("txtSenha");
         textoDigitado = ele.value;
         senhaDigitada = sen.value;
    
         if (textoDigitado == "" || senhaDigitada == ""){
             window.alert("Campos devem ser preenchidos#")
            return false;	
         }
            return true;
    }
}
//instancia objeto
aut = new Autenticacao();
console.log(aut);