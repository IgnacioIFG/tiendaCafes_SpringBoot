package com.ignacio.tiendaCafe.constantes.validaciones;

public class ConstantesValidaciones {

    public final static String regExpNombreRegistroUsuario = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,10}$";

    public final static String regExpEmailRegistroUsuario = "^([a-zA-Z0-9_\\.-]+)@([0-9a-zA-Z\\.-]+)\\.([a-zA-Z\\.]+)$";

    public final static String regExpPassRegistroUsuario = "^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ]{3,10}$";  

    public final static String regExpDniRegistroUsuario = "^[0-9]{8}[A-Za-z]$";

    public final static String regExpLocalidadRegistroUsuario = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]{2,20}$";

    public final static String regExpCodPostalRegistroUsuario = "^[0-9]{5}$";

    // Validaciones para pedido
    public final static String regExpCodigoDescuento = "^$|^1234$";

    // Validaciones Paso 1
    public final static String regExpNombreCompleto = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ]{2,20}$";  
    public final static String regExpDireccion = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ]{5,20}$";     
    public final static String regExpPais = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ]{3,10}$";         
    public final static String regExpProvincia = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ]{3,10}$";    
    public final static String regExpCiudad = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ]{3,10}$";       

    // Validaciones Paso 2
    public final static String regExpNumeroTarjeta = "^[0-9]{16}$";
    public final static String regExpTitularTarjeta = "^[a-zA-Z áéíóúÁÉÍÓÚñÑ]{2,40}$";  
    public final static String regExpNumSeguridad = "^[0-9]{3}$";
    public final static String regExpFechaCaducidad = "^(0[1-9]|1[0-2])\\/([0-9]{2})$";  // Formato MM/YY
}
