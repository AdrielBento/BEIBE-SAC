$("#loginIn").click(function (e) {

    e.preventDefault();

    let email = $("#inputEmail").val();
    let senha = $("#inputSenha").val();


    $.ajax({
        method: "POST",
        url: "LoginServlet",
        data: {
            senha,
            email
        },
        dataType: "json",

    }).done(function (res) {

        if (res.status) {
            $(email).addClass("is-invalid");
            $(senha).addClass("is-invalid");

        } else {
            $(email).removeClass("is-invalid");
            $(senha).removeClass("is-invalid");
        }

        console.log(res);

    }).fail(function (jqXHR, textStatus) {
        console.error(jqXHR, textStatus);
    });


});


var Usuarios = (() => {
    return {
        teste: () => {
            console.log("beibe");
        }
    }
})();


var contador = (function () {

    var placar = 0;

    function muda() {
        placar++;
        console.log("aumentou", placar);
    }

    return {
        aumenta: function () {
            muda();
        }
    }
})();