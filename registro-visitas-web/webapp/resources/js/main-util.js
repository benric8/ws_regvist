/**
 * -----------------------------
 * CODIGO ASCII 
 * -----------------------------
 * 0: caracter nulo
 * 8: retroceso
 * 9: tab horizontal
 * 11: tab vertical
 * 27: escape
 * 32: espacio
 * 45: guion medio
 * 46: punto
 * 48 - 57: numeros del 0 - 9
 * 64: arroba
 * 65 - 90: letras mayusculas A - Z
 * 95: guion bajo
 * 97 - 122: letras minusculas a - z
 * 127: suprimir
 * 130 : letra e con tilde
 * 144 : letra E con tilde
 * 160 : letra a con tilde
 * 161 : letra i con tilde
 * 162 : letra o con tilde
 * 163 : letra u con tilde
 * 181 : letra A con tilde 
 * 209 : letra mayuscula Ñ 
 * 214 : letra I con tilde
 * 224 : letra O con tilde
 * 233 : letra U con tilde
 * 241 : letra minuscula ñ 
 * 
 */

/** RESTRICCIONES **/

function restringirPorTipoDocumento(e){
	
	var str = document.getElementById("frmVisita:visitanteTipoDocumento").value;

	if(str == 1)
    {    
		 var tecla = (document.all) ? e.keyCode : e.which;
		 return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || (tecla>=48 && tecla<=57) || tecla==127 );
     }else{
    	 var tecla = (document.all) ? e.keyCode : e.which;
    	 return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==45 || (tecla>=48 && tecla<=57) || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 );
     }
	 
	
}

function restringirPorTipoDocumentoLogin(e){
	
	var tipoDocLog = document.getElementById("frmLogin:usuario").value;
	
	 if(str == 1)
     {    
		 var tecla = (document.all) ? e.keyCode : e.which;
		 return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || (tecla>=48 && tecla<=57) || tecla==127 );
     }else{
    	 var tecla = (document.all) ? e.keyCode : e.which;
    	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==45 || (tecla>=48 && tecla<=57) || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 );
     }
	 
	
}

function restringirNumero(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || (tecla>=48 && tecla<=57) || tecla==127 );
}

function restringirNumeroGuion(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==45 || (tecla>=48 && tecla<=57) || tecla==127 );
}

function restringirNumeroPunto(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==46 || (tecla>=48 && tecla<=57) || tecla==127 );
}

function restringirNumeroLetrasGuion(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==45 || (tecla>=48 && tecla<=57) || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 );
}

function restringirNumeroLetrasPunto(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==46 || (tecla>=48 && tecla<=57) || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 );
}

function restringirNumeroLetrasGuionesArrobaPunto(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==45 || tecla==46 || (tecla>=48 && tecla<=57) || tecla==64 || (tecla>=65 && tecla<=90) || tecla==95 || (tecla>=97 && tecla<=122) || tecla==127 );
}

function restringirNumeroLetras(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || (tecla>=48 && tecla<=57) || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 );
}

function restringirNumeroLetrasEspacio(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==32 || (tecla>=48 && tecla<=57) || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 );
}

function restringirNumeroLetrasEspacioGuion(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==32 || tecla==45 || (tecla>=48 && tecla<=57) || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 );
}

function restringirLetrasEspacio(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==8 || tecla==9 || tecla==11 || tecla==32 || tecla==45 || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 || tecla==209 || tecla==241 ||
			tecla==130 || tecla==144 || tecla==160 || tecla==161 || tecla==162 || tecla==163 || tecla==181 || tecla==214 || tecla==224 || tecla==233);
}

function restringirLetrasGuion(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || tecla==45 || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 || tecla==209 || tecla==241 );
}

function restringirLetras(e){
	var tecla = (document.all) ? e.keyCode : e.which;
	return ( tecla==0 || tecla==8 || tecla==9 || tecla==11 || (tecla>=65 && tecla<=90) || (tecla>=97 && tecla<=122) || tecla==127 || tecla==209 || tecla==241 );
}
