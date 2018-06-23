<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"       "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${vehicle.manufacturer} ${vehicle.model}</title>
    </head>
    <body>
    <h1>Vehicle details</h1>
        <table>
                <tr><td><b>Property</b></td><td><b>Value</b></td></tr>
                <tr><td>Id</td><td>${vehicle.id}</td></tr>
                <tr><td>Manufacturer</td><td>${vehicle.manufacturer}</td></tr>
                <tr><td>Model</td><td>${vehicle.model}</td></tr>
                <tr><td>Fuel</td><td>${vehicle.fuel}</td></tr>
                <tr><td>ProductionYear</td><td>${vehicle.productionYear}</td></tr>
                <tr><td>Mileage</td><td>${vehicle.mileage}</td></tr>
                <tr><td>Price</td><td>${vehicle.price}</td></tr>
                <tr><td>Sold</td><td>${vehicle.sold}</td></tr>
        </table>
    </body>
</html>

