function getImgProtocol() {
    let protocol = document.getElementById('protocol').value;

    let window = document.getElementById('output');
    if (protocol != "0") {
        window.src = "../events/images/" + protocol + ".png";
    } else {
        window.src = "images/start.svg";
    }

    console.log(typeof protocol);
}

function changeBackgroundOutPut() {

    let colorOutput = document.getElementById('color').value;
    //let protocol = document.getElementById('protocol').value;

    let window = document.getElementById('output');
    if (colorOutput != "black") {
        //window.src = "../events/images/" + protocol + ".png";
        window.style.backgroundColor = colorOutput;

    } else {

        //window.src = "../events/images/" + protocol + "Black.png";
        window.style.backgroundColor = colorOutput;
        if (protocol == "formal") {
            window.style.backgroundColor = "white";
        }
    }


    console.log(window.src);
}

$('input[type=file]').change(function () {
      console.dir(this.files[0])
})

$(document).ready(function() {

  // Escuchamos el evento 'change' del input donde cargamos el archivo
  $(document).on('change', 'input[type=file]', function(e) {
    // Obtenemos la ruta temporal mediante el evento
    var TmpPath = URL.createObjectURL(e.target.files[0]);
    // Mostramos la ruta temporal
    $('span').html(TmpPath);
    $('img').attr('src', TmpPath);
  });

});

function getFilePath(){
     $('input[type=file]').change(function () {
         var filePath=$('#fileUpload').val(); 
     });
}