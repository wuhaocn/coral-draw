function loadXMLDoc() {
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            document.getElementById("myDiv").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "http://127.0.0.1:8082/list", true);
    xmlhttp.send();
}