<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>	
	<title>Vou de Bike!</title>
</head>
<body>

<script>

$( document ).ready(function() {
	$('#botaoLogin').on('click', function() {
   
		var email = $("#email").val();
	   	var senha = $("#senha").val();
	   	//var urlServer = "http://172.16.215.107:8080/Voudebike/UsuarioServlet?email=" + email +'&senha=' + senha;
	   	var urlServer = "http://localhost:8080/Voudebike/Voudebike?email=" + email +'&senha=' + senha;
	   
		$.ajax({
			url : 'Voudebike',
			type: "post",
			dataType: "json",
			data: 
			{ 
				email : email,
				senha : senha
			},
			success : function(json)
			{
				if (json === true)
				{
					window.location.href = 'listarEstacionamentos.html';
				}
				else
				{
					$('.alert').html('Usuário ou senha incorretos')
					$('.alert').show();
				}
			},
			error: function (xhr, ajaxOptions, thrownError)
			{
				$('.alert').html('Sistema indisponível, tenta mais tarde novamente.')
				$('.alert').show();
		    }
		});	
    });
});

</script>

<div class="container">
  <h2 style="color:green;">Faça seu login no Vou de Bike:</h2>
  <form class="form-horizontal" action="">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email" style="color:green;">Email:</label>
      <div class="col-sm-10">
        <input type="email" class="form-control" id="email" placeholder="Digite seu e-mail" name="email">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="senha" style="color:green;">Senha:</label>
      <div class="col-sm-10">          
        <input type="password" class="form-control" id="senha" placeholder="Digite a senha" name="senha">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="button" id="botaoLogin" class="btn btn-success">Login</button>
      </div>
    </div>
  </form>
  <div class="alert alert-danger" style='display: none;'>
    Usuário ou senha incorretos
  </div>
</div>


</body>
</html>