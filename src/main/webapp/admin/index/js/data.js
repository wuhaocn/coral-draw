loadXMLDoc()
function loadXMLDoc() {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log( xmlhttp.responseText)
        }
    }
    xmlhttp.open("GET", "/file/list?page=1&limit=10", true);
    xmlhttp.send();
}