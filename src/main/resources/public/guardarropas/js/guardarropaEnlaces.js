function irAAgregarPrenda(){
    location.href="/agregarPrenda"+location.search
}

function volverAGuardarropa(){
    location.href="/prendas"+location.search
}

function volverAAgregarPrenda1(){
    location.href="/agregarPrenda"+location.search
}

function irAAgregarPrenda2(){
      var tipo = document.getElementById("tipoDePrenda").value
      var color1 = document.getElementById("color").value
      var color2 = document.getElementById("colorSecundario").value

      if(tipo == 0 && color1 == 0 && color2 == 'Nulo'){
              alert("Por favor, ingrese un tipo de prenda y un color");location.reload;
      }
      else if(tipo == 0){ alert("Por favor, ingrese un tipo de prenda");location.reload;}
      else if(color1==0 && color2=='Nulo'){alert("Por favor, ingrese un color");location.reload;}
      else{
        if(color1==0 && color2!='Nulo'){ color1=color2; color2='Nulo';}
          location.href="/agregarPrenda2"+location.search+"&tipoDePrenda="+tipo+"&color="+color1+"&colorSecundario="+color2
      }
}

function agregarDefinitivamente(){
    var tela = document.getElementById("telasAdmitidas").value
    var protocolo = document.getElementById("protocol").value

    if(tela == 0 && protocolo == 0){alert("Por favor, ingrese una tela y un protocolo");location.reload}
    else if (tela == 0){alert("Por favor, ingrese una tela");location.reload}
    else if (protocolo == 0){alert("Por favor, ingrese un protocolo");location.reload}
    else{
        location.href="/agregar"+location.search+"&tela="+tela+"&protocolo="+protocolo
    }
}

