<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="row">
             <div class="col-md-4" id="authors">Mazlum Dogan Sert, Junes Zuri & Alexander Rode Nielsen </div>

            <div class="col-md-4" id="class">A</div>

            <div class="col-md-4" id= "group">12</div> 
        </div>
        
        
        <div>
            <input type="text" name="Id" placeholder="Enter ID here:" id="input1"/>
            <button type="button" id="btn1" class="btn btn-primary">Alt info</button>
            <button type="button" id="btn2" class="btn btn-primary">Kontakt info</button>
        </div>
        
        <h4 id="titel">Informationer:</h4>
        <div id="print1">
        </div>
        
        <div>
            <input type="text" name="firstName" placeholder="Enter first name here:" id="input2"/>
            <input type="text" name="lastName" placeholder="Enter last name here:" id="input3"/>
            <input type="email" name="email" placeholder="Enter email here:" id="input4"/>
            <button type="button" id="btn3" class="btn btn-primary">Opret bruger</button>
        </div>
        
        <div id="respons"></div>
        
        

        <script src="//code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
        <script src="js.js" type="text/javascript"></script>
    </body>
</html>
