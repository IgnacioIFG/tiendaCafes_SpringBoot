function checkout_paso_0(){
	//mostrar el formulario donde insertar la informacion de envio
	$.getJSON("obtener-productos-carrito").done(
			function(res) {
				console.log("productos del carrito paso intermedio:")
				console.log(res)
				let res_html = Mustache.render(html_checkout_paso_intermedio_aceptar, res)
				$("#contenedor").html(res_html)
				let total_productos = 0
				let precio_total=0
				for (indice in res) {
					total_productos += res[indice].cantidad
					precio_total += res[indice].precio *  res[indice].cantidad
				}
				$("#total_productos").html(total_productos)
				$("#precio_total").html(precio_total)
				$("#aceptar_paso_intermedio").click(checkout_paso_intermedio_aceptar)	
			}
		)
}

function checkout_paso_intermedio_aceptar(){
	let codDescuento = $("#campo_codDescuento").val()
	let detallesEnvio = $("#campo_detallesEnvio").val()
	
	if (!validarCodigoDescuento(codDescuento)) {
		return
	}else{
		if(codDescuento==="1234"){
			alert("¡¡Tienes un 5% de descuento!!")
		}
	}
		
	$.post("realizar-pedido-paso-intermedio",{
		codigoDescuento: codDescuento,
		detallesEnvio: detallesEnvio
		
		
	}).done(function(res){
		if( res == "ok" ){
			$("#contenedor").html(html_checkout_1)
			$("#aceptar_paso_1").click(checkout_paso_1_aceptar)	
		}else{
			alert("NO se ha hecho el paso intermedio bien")
			alert(res)
		}
	})
}//end checkout_paso_1_aceptar

function checkout_paso_1_aceptar(){
	let nombre = $("#campo_nombre").val()
	let direccion = $("#campo_direccion").val()
	let provincia = $("#campo_provincia").val()
	
	let pais = $("#campo_pais").val()
	let ciudad = $("#campo_ciudad").val()
	let codPostal = $("#campo_codPostal").val()
	
	
		
	if (!validarNombreCompleto(nombre) ||
		!validarDireccion(direccion) ||
		!validarProvincia(provincia) ||
		!validarPais(pais) ||
		!validarCiudad(ciudad) ||
		!validarCodigoPostal(codPostal)) {
		alert("hay datos incorrectos")
		return
	}
	
	$.post("realizar-pedido-paso1",{
		nombre: nombre,
		direccion: direccion,
		pais: pais,
		provincia: provincia,
		ciudad: ciudad,
		codPostal: codPostal
		
	}).done(function(res){
		if( res == "ok" ){
			$("#contenedor").html(html_checkout_2)		
			$("#aceptar_paso_2").click(checkout_paso_2_aceptar)	
		}else{
			alert(res)
		}
	})
}//end checkout_paso_1_aceptar



function checkout_paso_2_aceptar(){
	
	let tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val()
	let numero_tarjeta = $("#numero_tarjeta").val()
	let titular_tarjeta = $("#titular_tarjeta").val()
	let numero_seguridad = $("#numSeguridad_tarjeta").val()
	let fecha_caducidad = $("#caducidad_tarjeta").val()  
	
	
	  if(!validarNumeroTarjeta(numero_tarjeta) ||
		!validarTitularTarjeta(titular_tarjeta) ||
		!validarNumeroSeguridad(numero_seguridad) ||
		!validarFechaCaducidad(fecha_caducidad)) {
		return
	}
	
	$.post("realizar-pedido-paso2",{
		tipoTarjeta: tipo_tarjeta,
		numeroTarjeta: numero_tarjeta,
		titularTarjeta: titular_tarjeta,
		numeroSeguridad: numero_seguridad,
		fechaCaducidad: fecha_caducidad
	}).done(function(res){
		console.log("resumen del pedido:")
				console.log(res)
				let html = Mustache.render(html_checkout_3, res)
				$("#contenedor").html(html)
				$("#boton_confirmar_pedido").click(confirmar_pedido)	
	})

}//end paso 2

function confirmar_pedido(){
	$.post("confirmar-pedido").done(function(res){
		alert("respuesta del REST de Pedidos: " + res)	
		if(res == "pedido completado"){
			alert("gracias por realizar tu pedido")
			obtenerProductos()
		}
	})
}//end confirmar







