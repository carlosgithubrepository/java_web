//Gerenciar o serviço dos usuários          
            //Controlador de tela
            UsuarioController = function(){
                this.usuarioService = new UsuarioService();              
                this.modoEdicao = false;
                //renderizar tabela, espera como parametros usuarios e gera o DOM
                this.renderizarTabelaUsuarios = function(ArrayUsuarios){
                    dados="";
                    for(i=0;i<ArrayUsuarios.length;i++){
                        dados += "<tr>";
                        dados += "<td>"+ArrayUsuarios[i].id + "</td>";
                        dados += "<td>"+ArrayUsuarios[i].nome + "</td>";
                        dados += "<td>"+ArrayUsuarios[i].senha + "</td>";
                        dados += "<td><input type='button' value='Excluir' onclick='uc.aoClicarExcluir("+ArrayUsuarios[i].id+")'/></td>";
                        dados += "<td><input type='button' value='Editar' onclick='uc.aoClicarEditar("+ArrayUsuarios[i].id+")'/></td>";
                        dados += "</tr>";                       
                    }                   
                    document.getElementById("tbUsuarios").innerHTML = dados;
                }
                //Eventos dos botoes DOM(PáginaHTML)
                this.aoClicarEmSalvar = function(){
                	var self = this;
                    //leitura dos dados
                    nomeUsuario = document.getElementById("txtUsuario").value;
                    senhaUsuario = document.getElementById("txtSenha").value;  
                    //constroi o objeto
                    usu = "nome="+nomeUsuario+"&senha="+senhaUsuario;
                    if(this.modoEdicao==false){
                        //adiciona no vetor
                        this.usuarioService.adicionar(usu, function(){
                       		 window.alert("Salvo com sucesso!");
                       		 self.aoClicarEmListar();
                        }, function(){
                        	 window.alert("Erro ao salvar");
                        });
                    }else{
                        this.usuarioService.alterar(this.indiceEdicao, usu);
                    }
                    this.limparCampos();
                    this.sairDoModoEdicao();
                    this.renderizarAutomaticamente();
                }
                //metodo que exibe na tela
                //DOM
                this.aoClicarEmListar = function(){
                    var self = this;
                    usuarios = this.usuarioService.buscarTodos(function(usuarios){
                        self.renderizarTabelaUsuarios(usuarios);
                    });
                            
                }
                this.renderizarAutomaticamente = function(){
                    usuarios = this.usuarioService.buscarTodos();
                    this.renderizarTabelaUsuarios(usuarios);
                }
                this.aoClicarExcluir = function(indice){
                    this.usuarioService.excluir(indice);
                    this.renderizarAutomaticamente();
                }
                this.aoClicarEditar = function(id){
                    this.entrarEmModoEdicao();
                    this.indiceEdicao = id;
                    this.usuarioService.buscarPorId(id, function(usuario){
                    document.getElementById("txtId").value = usuario.id;
                    document.getElementById("txtUsuario").value = usuario.nome;
                    document.getElementById("txtSenha").value = usuario.senha;
                    });
                   
                }
                this.aoClicarCancelar = function(){
                    //Limpa dados da tela
                    this.limparCampos();
                    this.sairDoModoEdicao(); 
                    this.renderizarAutomaticamente();  
                }
                this.limparCampos = function(){
                    document.getElementById("txtUsuario").value = "";
                    document.getElementById("txtSenha").value = "";
                }
                this.entrarEmModoEdicao = function(){
                    this.modoEdicao = true;
                }
                this.sairDoModoEdicao = function(){
                    this.modoEdicao = false;
                }
            }