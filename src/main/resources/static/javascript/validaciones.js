//validaciones usuario
let regexp_nombre = /^[a-z áéíóúñ]{2,10}$/i
let regexp_email = /^([a-z0-9_\.-]+)@([0-9a-z\.-]+)\.([a-z\.]+)$/i
let regexp_pass = /^[a-z0-9áéíóúñ]{3,10}$/i

let regexp_dni = /^[0-9]{8}[A-Za-z]$/i
let regexp_localidad = /^[a-z áéíóúñ]{2,10}$/i

let regexp_codigoPostal = /^[0-9]{5}$/i

//validaciones paso intermedio
let regexp_codigoDescuento = /^$|^1234$/;

//validaciones paso 1 
let regexp_nombreCompleto = /^[a-z áéíóúñ]{2,20}$/i
let regexp_direccion = /^[a-z áéíóúñ]{5,20}$/i
let regexp_pais = /^[a-z áéíóúñ]{3,10}$/i
let regexp_provincia = /^[a-z áéíóúñ]{3,10}$/i
let regexp_ciudad = /^[a-z áéíóúñ]{3,10}$/i

//validaciones paso 2 
let regexp_numeroTarjeta = /^[0-9]{16}$/;
let regexp_titularTarjeta = /^[a-z áéíóúñ]{2,40}$/i;
let regexp_numSeguridad = /^[0-9]{3}$/;
let regexp_fechaCaducidad = /^(0[1-9]|1[0-2])\/?([0-9]{2})$/; // formato MM/YY


//funciones validaciones intermedio
function validarCodigoDescuento(codigoDescuento){
	if( regexp_codigoDescuento.test(codigoDescuento)){
		return true;
	}else{
		alert("codigo descuento desconocido")
	}
}


//funciones validaciones paso 2 
function validarNumeroTarjeta(numeroTarjeta){
	if( regexp_numeroTarjeta.test(numeroTarjeta) ){
		return true;
	}else{
		alert("numero de tarjeta debe de ser de 16 numeros")
	}
}

function validarTitularTarjeta(titularTarjeta){
	if( regexp_titularTarjeta.test(titularTarjeta) ){
		return true;
	}else{
		alert("campo titular de la tarjeta incorrecto")
	}
}

function validarNumeroSeguridad(numeroSeguridad){
	if( regexp_numSeguridad.test(numeroSeguridad) ){
		return true;
	}else{
		alert("numero de seguridad debe tener 3 numeros")
	}
}
function validarFechaCaducidad(fechaCaducidad){
	if( regexp_fechaCaducidad.test(fechaCaducidad) ){
		return true;
	}else{
		alert("numero de seguridad debe tener 3 numeros")
	}
}


//funciones validaciones paso 1 
function validarNombreCompleto(nombreCompleto){
	if( regexp_nombreCompleto.test(nombreCompleto) ){
		return true;
	}else{
		alert("campo nombre incorrecto")
	}
}

function validarDireccion(direccion){
	if( regexp_direccion.test(direccion) ){
		return true;
	}else{
		alert("campo direccion incompleto")
	}
}

function validarPais(pais){
	if( regexp_pais.test(pais) ){
		return true;
	}else{
		alert("campo pais incorrecto")
	}
}

function validarProvincia(provincia){
	if( regexp_provincia.test(provincia) ){
		return true;
	}else{
		alert("campo provincia incorrecto")
	}
}

function validarPais(pais){
	if( regexp_pais.test(pais) ){
		return true;
	}else{
		alert("campo pais incorrecto")
	}
}

function validarCiudad(ciudad){
	if( regexp_ciudad.test(ciudad) ){
		return true;
	}else{
		alert("campo ciudad incorrecto")
	}
}



//funciones validaciones usuario
function validarNombre(nombre){
	if( regexp_nombre.test(nombre) ){
		return true;
	}else{
		alert("nombre incorrecto: solo letras de 2 a 10 caracteres")
	}
}

function validarEmail(email){
	if( regexp_email.test(email) ){
		return true
	}else{
		alert("email incorrecto")
	}		
}

function validarPass(pass){
	if(regexp_pass.test(pass)){
		return true
	}else{
		alert("pass incorrecto: solo letras y numeros de 3 a 10 caracteres")
	}
}

function validarDni(dni){
	if(regexp_dni.test(dni)){
		return true
	}else{
		alert("dni incorrecto: solo 8 numeros y una letra")
	}
}

function validarLocalidad(localidad){
	if(regexp_localidad.test(localidad)){
		return true
	}else{
		alert("localidad incorrecta: solo letras de 2 a 10 caracteres")
	}
}

function validarCodigoPostal(codPostal){
	if(regexp_codigoPostal.test(codPostal)){
		return true
	}else{
		alert("codigo postal incorrecto: solo 5 mumeros")
	}
}


