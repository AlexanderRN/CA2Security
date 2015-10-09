<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h3>Nogle funktioner som at få en person med phone nr. virker ikke pt.</h3><br>
        <div class="row">
             <div class="col-md-4" id="authors">Mazlum Dogan Sert, Junes Zuri & Alexander Rode Nielsen </div>

            <div class="col-md-2" id="class">A</div>

            <div class="col-md-2" id= "group">12</div>
            
            <div class="col-md-4"></div> 
        </div><br>
        
        
        
        <div>
            <input type="text" name="Id" placeholder="Enter ID/Phone here:" id="input1"/>
            <button type="button" id="btn1" class="btn btn-primary">Alt info</button>
            <button type="button" id="btn2" class="btn btn-primary">Kontakt info</button>
            <button type="button" id="btn6" class="btn btn-primary">Hent person med tlf nr.</button>
            <button type="button" id="btn4" class="btn btn-primary">DELETE Person</button>
            <button type="button" id="btn7" class="btn btn-primary">Hent person med tlf nr.</button>
        </div>
        
        <h4 id="titel">Informationer:</h4>
        <div id="print1">
        </div>
        
        <div>
            <input type="text" name="firstName" placeholder="Enter first name here:" id="input2"/>
            <input type="text" name="lastName" placeholder="Enter last name here:" id="input3"/>
            <input type="email" name="email" placeholder="Enter email here:" id="input4"/>
            <button type="button" id="btn3" class="btn btn-primary">Opret bruger</button>
            <button type="button" id="btn5" class="btn btn-primary">Alle Personer</button>
        </div>
        
        <div id="respons"></div>
        
        <div id="alle"></div>


        <script src="//code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="js.js" type="text/javascript"></script>
    </body>
</html>
