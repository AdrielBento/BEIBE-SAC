$("#loginIn").click(function(e) {
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
    dataType: "json"
  })
    .done(function(res) {
      if (!res.status) {
        $("#inputEmail").addClass("is-invalid");
        $("#inputSenha").addClass("is-invalid");
      } else {
        $("#inputEmail").removeClass("is-invalid");
        $("#inputSenha").removeClass("is-invalid");
      }

      console.log(res);
    })
    .fail(function(jqXHR, textStatus) {
      console.error(jqXHR, textStatus);
    });
});

var Usuarios = (() => {
  return {
    teste: () => {
      console.log("beibe");
    }
  };
})();

var contador = (function() {
  var placar = 0;

  function muda() {
    placar++;
    console.log("aumentou", placar);
  }

  return {
    aumenta: function() {
      muda();
    }
  };
})();
