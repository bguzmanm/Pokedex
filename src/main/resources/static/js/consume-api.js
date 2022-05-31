var elementos_pagina = 6;
let uri = window.location + 'pokeRest';

// Call the dataTables jQuery plugin
$.fn.callAPI = function ($desde, $hasta) {

    /* reserteo objetos */
    $("#pokes").empty(".col-sm-4 p-2");

    let url = uri;

    if ($desde != undefined && $hasta != undefined) {
        url += '/?offset=' + $desde + "&limit=" + $hasta;
    }
    $("#page-loading").show();
    $.getJSON(url, function (data) {

            //muestro u oculto botones dependiendo de los datos.
            $.fn.onOff(data[0][0], data[0][1], $desde)

            let pokemones = data[1];

            //div con tarjeta de cada pokemon
            let card = '<div class="col-sm-4 p-2 placeholder"><div class="card w-75" style="width: 18rem;">\n' +
                ' <img src="$urlimg" class="card-img-top" alt="$nombrePokemon">\n' +
                ' <div class="card-body">\n' +
                ' <h5 class="card-title" >$nombrePokemon</h5>\n' +
                ' <p class="card-text">Tipo: $tipo</p>' +
                ' <p class="card-text">Peso: $peso</p>' +
                ' <p class="card-text">Habilidades: $habilidades</p>' +
                ' <a href="#pokeModal" id="$nombrePokemon" class="btn btn-primary" data-bs-toggle="modal" ' +
                ' data-bs-target="#pokeModal"> Atrapalo! </a></div></div></div>';


            for (let i = 0; i < pokemones.length; i++) {

                let element = pokemones[i];
                let urlimg = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" +
                    element.id + ".png";

                let card1 = card.replace("$nombrePokemon", element.name);
                card1 = card1.replace("$nombrePokemon", element.name);
                card1 = card1.replace("$urlimg", urlimg);

                card1 = card1.replace("$tipo", $.fn.getTypes(element.types));
                card1 = card1.replace("$habilidades", $.fn.getAbilities(element.abilities));

                card1 = card1.replace("$peso", element.weight);
                $("#pokes").append(
                    card1.replace("$nombrePokemon", element.name)
                );
            }

            /* Configuro botón de Atrápalo!*/
            $(".btn.btn-primary").click(function () {
                $.fn.callDetallePokemon($(this).attr('id'));
            });

        });
    $("#page-loading").hide();

};

$.fn.callDetallePokemon = function ($name) {
    $("#pokeModalNombre").text($name);
    let url = uri + "/" + $name;

    $("#modal-loading").show();
    $.getJSON(url, function (data) {
        $("#pokeModalTipo").text("Tipo: " + $.fn.getTypes(data.types));
        $("#pokeModalPeso").text("Peso: " + data.weight);
        $("#pokeModalHabilidades").text("Habilidades: " + $.fn.getAbilities(data.abilities));
        $("#pokeModalImg").attr("src", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" +
            data.id + ".png");
    });
    $("#modal-loading").hide();

}

/* oculto y muestro botones de siguiente y anterior */
$.fn.onOff = function ($p, $n, $desde) {

    if ($p != "null") {

        $("#prior").show();
        $("#prior").off("click");
        $("#prior").click(function () {
            $.fn.callAPI($desde - elementos_pagina, elementos_pagina);
        });
    } else {
        $("#prior").hide();
    }
    if ($n != "null") {

        $("#next").show();
        $("#next").off("click");
        $("#next").click(function () {
            $.fn.callAPI($desde + elementos_pagina, elementos_pagina);
        });
    } else {
        $("#next").hide();
    }

}

/*obtengo los tipos */
$.fn.getTypes = function ($types) {
    let strTypes = "";
    $.each($types, function (i, tipo) {
        if (strTypes.length > 0)
            strTypes = strTypes.concat(", ");
        strTypes = strTypes.concat(tipo.type[0].name);
    });
    return strTypes;
}

/*obtengo las habilidades */
$.fn.getAbilities = function ($abilities) {
    let strAbility = "";
    $.each($abilities, function (i, habilidad) {
        if (strAbility.length > 0)
            strAbility = strAbility.concat(", ");
        strAbility = strAbility.concat(habilidad.ability.name);
    });
    return strAbility;
}

/*Llamo a la API*/
$(document).ready(function () {
    $.fn.callAPI(0, elementos_pagina);
});
