function obtenerProductos() {
	$.ajax("obtener-productos-json").done(function(respuesta) {
		// Vamos a procesar la respuesta con el JSON de los libros:
		// Transformamos el JSON recibido a array de JavaScript:
		let cafes = JSON.parse(respuesta);
		console.log(cafes);
		let texto_html = "";

		texto_html = Mustache.render(html_listado_productos, cafes);
		$("#contenedor").html(texto_html);
		$(".enlace_ver_detalles_cafe").click(mostrarDetallesProducto)
	})
} // end obtenerProductos



function mostrarDetallesProducto() {

	let idProducto = $(this).attr("id-producto")
	$.getJSON("obtener-detalles-cafe",
		{
			id: idProducto
		}
	).done(function(res) {
		let html = Mustache.render(html_detalles_cafe, res)
		$("#contenedor").html(html)
		$("#enlace_agregar_al_carrito").click(agregarProductoAlCarrito)

	})

}

function agregarProductoAlCarrito() {
	if (nombre_login == "") {
		alert("tienes que identificarte para poder comprar productos")
		return;
	}
	let idProducto = $(this).attr("id-producto")

	$.post("agregar-producto-carrito", {
		id: idProducto,
		cantidad: 1
	}).done(function(res) {
		if (res == "ok") {
			alert("producto agregado al carrito")
		}
	})
}



function enviarInfoUsuarioAlServidor() {
	let nombre = $("#nombre").val();
	let email = $("#email").val();
	let pass = $("#pass").val();
	let dni = $("#dni").val();
	let localidad = $("#localidad").val();
	let codPostal = $("#codPostal").val();


	if (!validarNombre(nombre) ||
		!validarEmail(email) ||
		!validarPass(pass) ||
		!validarDni(dni) ||
		!validarLocalidad(localidad) ||
		!validarCodigoPostal(codPostal)) {
		alert("hay datos incorrectos")
		return
	}


	$.post("registrar-usuario-cliente", {
		nombre: nombre,
		email: email,
		pass: pass,
		dni: dni,
		localidad: localidad,
		codPostal: codPostal

	}).done(function(res) {
		alert(res);
	});
} // end enviarInfoUsuarioAlServidor

function mostrarFormularioRegistroUsuario() {
	$("#contenedor").html(html_formulario_registro_usuario);
	$("#boton_registro_usuario").click(enviarInfoUsuarioAlServidor);
}//end mostrarFormularioRegistroUsuario

function mostrarFormularioLogin() {
	$("#contenedor").html(html_formulario_identificacion_usuario)

	if (typeof (Cookies.get("email")) != "undefined") {
		$("#email").val(Cookies.get("email"))
	}
	if (typeof (Cookies.get("pass")) != "undefined") {
		$("#pass").val(Cookies.get("pass"))
	}

	//para evitar el comportamiento por defecto de un form
	//al hacer click en el boton de submit:
	$("#form_login").submit(
		function(e) {
			//la siguiente es necesaria para que la pagina no se
			//refresque con el envio de formulario
			e.preventDefault()
			//vamos mandar por post el email y pass introducido
			//a un servicioWEB de identificacion de usuario
			$.post("identificar-usuario", {
				email: $("#email").val(),
				pass: $("#pass").val()
			}).done(function(res) {
				console.log("respuesta recibida")
				console.log(res)
				if (res.operacion == "ok") {
					//si el login fue correcto y se activo el checkbox
					//guardo el email y pass del usuario en una cookie
					if ($("#recordar_datos").prop("checked")) {
						Cookies.set("email", $("#email").val(), { expires: 100 })
						Cookies.set("pass", $("#pass").val(), { expires: 100 })
					} else {
						//la idea basica es que si el usuario se identifica
						//sin activar el recordar datos, que se borren
						//las cookies previas con su email y pass
						if (typeof (Cookies.get("email")) != "undefined") {
							Cookies.remove("email")
						}
						if (typeof (Cookies.get("pass")) != "undefined") {
							Cookies.remove("pass")
						}
					}

					nombre_login = res.usuario
					alert("bienvenido " + nombre_login + " ya puedes comprar tu cafe favorito!!")
					obtenerProductos()
					$("#menu-cerrar-sesion").css("visibility", "visible")
					$("#menu-identificarme").hide()
					$("#menu-registrarme").hide()
				} else {
					alert("email o pass incorrecto")
				}
			})
		}//end function
	)//end submit
}//end mostrarFormularioLogin

function cerrarSesionUsuario() {

	$.get("cerrar-sesion-usuario").done(function(res) {
		if (res == "ok") {
			alert("hasta pronto " + nombre_login)
			nombre_login = ""
			$("#menu-cerrar-sesion").css("visibility", "hidden")
			$("#menu-identificarme").show()
			$("#menu-registrarme").show()
			obtenerProductos()
		}
	})

}//end cerrarSesionUsuario

function obtenerProductosCarrito() {
	if (nombre_login == "") {
		alert("tienes que identificarte para acceder a tu carrito")
		mostrarFormularioLogin()
		return
	}
	$.getJSON("obtener-productos-carrito").done(
		function(res) {
			console.log("productos del carrito:")
			console.log(res)


			let res_html = Mustache.render(html_carrito, res)
			$("#contenedor").html(res_html)

			$(".enlace_ver_detalles_cafe").click(mostrarDetallesProducto)



			let total_productos = 0
			let precio_total = 0


			for (indice in res) {
				total_productos += res[indice].cantidad
				precio_total += res[indice].precio * res[indice].cantidad

			}

			$("#total_productos").html(total_productos)
			$("#precio_total").html(precio_total)
			//$("#producto")
			$("#realizar-pedido").click(checkout_paso_0)
		}
	)
}//end obtenerProductosCarrito


function actualizarCantidadProductoEnCarrito(idProducto, nuevaCantidad) {
	// Verifica si el usuario está autenticado antes de proceder
	if (nombre_login == "") {
		alert("Tienes que identificarte para poder actualizar el carrito");
		return;
	}

	// Enviar la solicitud al servidor para actualizar la cantidad del producto en el carrito
	$.post("actualizar-cantidad-producto-carrito", {
		id: idProducto,
		cantidad: nuevaCantidad
	}).done(function(res) {
		// Verifica la respuesta del servidor
		if (res == "ok") {

		} else {
			alert("Hubo un error al actualizar la cantidad en el carrito");
		}
	}).fail(function() {
		alert("Error de conexión con el servidor");
	});
}


$(document).on('click', '.boton-sumar-cantidad', function() {
	let cantidadContenedor = $(this).closest('.cantidad-cafe-carrito').find('.contenedor-cantidad-cafe');
	let totalProductos = $('.productos-totales #total_productos');
	let precioTotal = $('.productos-totales #precio_total');
	
	let idProducto = $(this).attr("id-producto")
	let cantidadActual = parseInt(cantidadContenedor.text());

	//nuevos valores
	let nuevaCantidad = cantidadActual + 1;
	//let nuevaCantidadTotal = totalProductosActual + 1;

	cantidadContenedor.text(nuevaCantidad);
	//totalProductos.text(nuevaCantidadTotal);


	$.post("actualizar-cantidad-producto-carrito", {
		id: idProducto,
		cantidad: nuevaCantidad
	}).done(function(res) {
		// Verifica la respuesta del servidor
		if (res == "ok") {
			actualizarTotales();
		} else {
			alert("Hubo un error al actualizar la cantidad en el carrito");
		}
	}).fail(function() {
		alert("Error de conexión con el servidor");
	});

});

function actualizarTotales() {
    $.getJSON("obtener-productos-carrito").done(function(res) {
        let totalProductos = 0;
        let precioTotal = 0;

        // Recalcular los totales con los productos actualizados
        for (let indice in res) {
            totalProductos += res[indice].cantidad;
            precioTotal += res[indice].precio * res[indice].cantidad;
        }

        // Actualizar los totales en el HTML
        $('.productos-totales #total_productos').text(totalProductos);
        $('.productos-totales #precio_total').text(precioTotal.toFixed(3)); // Asegúrate de mostrar el precio con 2 decimales
    }).fail(function() {
        alert("Error al obtener los productos del carrito");
    });
}


$(document).on('click', '.boton-restar-cantidad', function() {
	let cantidadContenedor = $(this).closest('.cantidad-cafe-carrito').find('.contenedor-cantidad-cafe');

	let totalProductos = $('.productos-totales #total_productos');

	let idProducto = $(this).attr("id-producto")

	let cantidadActual = parseInt(cantidadContenedor.text());

	let totalProductosActual = parseInt(totalProductos.text()) || 0;

	//nuevos valores
	let nuevaCantidad = cantidadActual - 1;
	let nuevaCantidadTotal = totalProductosActual - 1;

	cantidadContenedor.text(nuevaCantidad);
	totalProductos.text(nuevaCantidadTotal);


	$.post("actualizar-cantidad-producto-carrito", {
		id: idProducto,
		cantidad: nuevaCantidad
	}).done(function(res) {
		// Verifica la respuesta del servidor
		if (res == "ok") {
			actualizarTotales();
		} else {
			alert("Hubo un error al actualizar la cantidad en el carrito");
		}
	}).fail(function() {
		alert("Error de conexión con el servidor");
	});
});

//fin operaciones con plantillas

