// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

function openProfile() {
    var x = document.getElementById("profileBtn");
    if (x.className.indexOf("w3-show") == -1)
        x.className += " w3-show";
    else
        x.className = x.className.replace(" w3-show", "");

}

function updateItemLettersCount(text) {
    var length = text.value.length;
    document.getElementById('countLetters').innerHTML = 'Осталось символов: ' + (300 - length);

}

function getMetaContentByName(name, content) {
    var content = (content == null) ? 'content' : content;
    return document.querySelector("meta[name='" + name + "']").getAttribute("content")
}

function addOrderToCart(itemId) {
    var xhr = new XMLHttpRequest();
    var url = '/cart/addOrder';
    var params = 'itemId=' + encodeURIComponent(itemId) + '&quantity=' + encodeURIComponent('1');
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    var header = getMetaContentByName('_csrf_header');
    var token = getMetaContentByName('_csrf');
    xhr.setRequestHeader(header, token);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            alert("Item successfully added");
        }
    };
    xhr.send(params);


}
