UsuarioService = function(){
    this.usuarios = [];
    //regras de negocio
    //CRUD
    //metodos abaixo que fazem a persistencia(Registro)
    //Gerenciar o serviço dos usuários       
    //CREATE
    this.adicionar = function(usu, sucesso, erro){
       var xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
    	if(this.readyState == 4){
    		if(this.status == 200){
    			sucesso();
    		}else{
    			erro();
    		}
    	}
    	
      };	
  	  xhttp.open("POST", "usucontroller", true);
  	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	  xhttp.onerror = erro;
	    xhttp.send(usu);
    }
    //buscar tudo que esta no vetor
    //RETRIEVE
    this.buscarTodos = function(cb){
    
     var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
         cb	(this.usuarios = JSON.parse(this.responseText));
       }     
    };
  	  xhttp.open("GET", "usucontroller", true);
 	  xhttp.send();
    }
    //alterar no vetor
    //UPDATE
    this.alterar = function(indice, usu){
        this.usuarios.splice(indice, 1, usu);
        console.log(indice);
        console.log(usu);
    }
    //remover no vetor
    //DELETE
    this.excluir = function(indice){
        //remover vetor
        this.usuarios.splice(indice,1);
    }
    //busca o indice no vetor e e tras as informações
    this.buscarPorId = function(id, cb){
      var xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
         cb	(this.usuarios = JSON.parse(this.responseText))
       }     
    };
  	  xhttp.open("GET", "usucontroller?id="+id, true);
 	    xhttp.send();
    }
}