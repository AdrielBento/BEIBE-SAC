<!DOCTYPE html>
<html lang="pt-br">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login</title>

  <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" crossorigin="anonymous">
  <link rel="stylesheet" id="main-stylesheet" data-version="1.1.0" href="styles/shards-dashboards.1.1.0.min.css">
  <link rel="stylesheet" href="styles/extras.1.1.0.min.css">
  <link rel="stylesheet" href="styles/login.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.0/animate.min.css">

  <script async defer src="https://buttons.github.io/buttons.js"></script>
</head>

<body class="h-100">
  <div class="col-sm-12">
    <div class="row">
      <div class="h-100 col-sm-12">
        <!-- <div class="row d-flex justify-content-center">
          <strong>Welcome a Beibe</strong>
        </div> -->
        <!-- main -->
        <main class="col-sm-12 d-flex align-items-center justify-content-center">
          <!-- form -->
          <form action="LoginServlet" id="formIn" class="col-sm-4">
            <!-- Email -->
            <div class="input-group mb-3">
              <div class="input-group input-group-lg input-group-seamless">
                <span class="input-group-prepend">
                  <span class="input-group-text">
                    <i class="material-icons">email</i>
                  </span>
                </span>
                <input type="email" class="form-control" id="inputEmail" required placeholder="Email">
                <div class="invalid-feedback">The first name looks good!</div>
              </div>
            </div>
            <!-- Password -->
            <div class="input-group mb-3">
              <div class="input-group input-group-lg input-group-seamless">
                <input type="password" class="form-control" id="inputSenha" required placeholder="Password">
                <span class="input-group-append">
                  <span class="input-group-text">
                    <i class="material-icons">lock</i>
                  </span>
                </span>
                <div class="invalid-feedback">The first name looks good!</div>

              </div>
            </div>
            <div class="input-group mb-3">
              <button type="submit" id="loginIn" class="mb-2 btn btn-primary mr-2">Login In</button>
            </div>
            <div class="input-group mb-3">
              <span class="font-weight-light">Dont't have an account ? <strong>Sign Up</strong></span>
            </div>

          </form>
          <!-- fim form -->
        </main>
      </div>

    </div>

  </div>


  <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
  <script  src="https://cdnjs.cloudflare.com/ajax/libs/noty/3.1.4/noty.min.js"></script>
  <script src="js/login.js"></script>
</body>

</html>